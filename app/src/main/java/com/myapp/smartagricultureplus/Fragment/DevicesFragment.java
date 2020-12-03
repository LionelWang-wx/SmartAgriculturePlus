package com.myapp.smartagricultureplus.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.myapp.smartagricultureplus.R;


public class DevicesFragment extends Fragment {
    int devicesLayout1;
    Context context;
    WebView wv_temperatureChart;
    String devicesFragmentName;

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
        initData();

    }

    private void initData() {
//        int a1=1000,a2 = 500,a3=200,a4=20,a5=200,a6=300;
//        wv_temperatureChart.loadUrl("javascript:if(window.remote){window.remote('"+a1+"')}");
//         switch (devicesFragmentName){
//             case "温度传感器":
//                 wv_temperatureChart.setWebContentsDebuggingEnabled(true);
                 //通过getSettings方法可以设置浏览器的属性
                 //setJavaScriptEnabled让webView支持JavaScript脚本
                 wv_temperatureChart.getSettings().setJavaScriptEnabled(true);
                 //保证一个网页跳转另一个网页时，仍在webView中打开
                 wv_temperatureChart.setWebViewClient(new WebViewClient());
//                 wv_temperatureChart.addJavascriptInterface(new ImoocJsInterface(this),"imoocLauncher ");
                 //展示指定的url网页
                 wv_temperatureChart.loadUrl("http://31780179bx.qicp.vip:22863/HTML/tow.html");
//                 break;

//         }
    }

    private void initView() {
        wv_temperatureChart=getActivity().findViewById(R.id.wv_temperatureChart);
    }

}