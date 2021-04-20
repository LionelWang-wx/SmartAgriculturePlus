package com.myapp.smartagricultureplus.Fragment;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.myapp.smartagricultureplus.Activity.Data;
import com.myapp.smartagricultureplus.R;
import com.myapp.smartagricultureplus.SimulationData.SimulationDataService;

import static android.content.Context.BIND_AUTO_CREATE;


public class DevicesFragment extends Fragment {
    int devicesLayout1;
    Context context;
    WebView wv_temperatureChart;
    String devicesFragmentName;
    Button btn_button;
    String TAG = "DevicesFragment";
    SimulationDataService.SimulationDataBinder binder;
    String jsonData;

    public DevicesFragment(int devicesLayout, Context context, String devicesFragmentName) {
        this.devicesLayout1 = devicesLayout;
        this.context = context;
        this.devicesFragmentName = devicesFragmentName;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(devicesLayout1, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initWeb();
        initData();
        Intent intent = new Intent(context, SimulationDataService.class);
        context.bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                binder = (SimulationDataService.SimulationDataBinder) service;
                binder.getService().setDataCallBack(new SimulationDataService.DataCallBack() {
                    @Override
                    public void success(Data data) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                jsonData = data.getMon() + "," + data.getTue() + "," + data.getWed() + "," + data.getThu()
                                        + "," + data.getFri() + "," + data.getSat() + "," + data.getSun();
                                wv_temperatureChart.loadUrl("javascript:optionObj('" + jsonData + "')");
                            }
                        });
                    }

                    @Override
                    public void failed(String msg) {

                    }
                });
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, BIND_AUTO_CREATE);

    }

    private void initWeb() {
        //通过getSettings方法可以设置浏览器的属性
        //setJavaScriptEnabled让webView支持JavaScript脚本
        WebSettings settings = wv_temperatureChart.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
//        wv_temperatureChart.setWebContentsDebuggingEnabled(true);
        //保证一个网页跳转另一个网页时，仍在webView中打开
        wv_temperatureChart.setWebViewClient(new WebViewClient());
        //展示指定的url网页
        wv_temperatureChart.loadUrl("file:///android_asset/line-simple.html");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            jsonData = "250,330,324,218,135,147,260,323,424,456,676,878,342";
                            wv_temperatureChart.loadUrl("javascript:optionObj('" + jsonData + "')");
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private void initData() {
//        switch (devicesFragmentName) {
//            case "温度传感器":

//                btn_button.setOnClickListener(v -> {
//                    jsonData = "250,330,324,218,135,147,260,323,424,456,676,878,342";
//                    Gson gson = new Gson();
//                    String jsonData = gson.toJson(data);
//                    Log.e(TAG,"jsonData = "+jsonData);
//                    wv_temperatureChart.loadUrl("javascript:optionObj('" + jsonData + "')");
//                });
        //处理js中弹窗
        wv_temperatureChart.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                AlertDialog.Builder b = new AlertDialog.Builder(context);
                b.setTitle("Alert");
                b.setMessage(message);
                b.setPositiveButton(android.R.string.ok, (dialog, which) -> result.confirm());
                b.setCancelable(false);
                b.create().show();
                return true;
            }
        });
//                break;
//        }
    }

    private void initView() {
        wv_temperatureChart = getActivity().findViewById(R.id.wv_temperatureChart);
//        btn_button = getActivity().findViewById(R.id.btn_button);
    }

}