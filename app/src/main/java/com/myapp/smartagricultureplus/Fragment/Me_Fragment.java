package com.myapp.smartagricultureplus.Fragment;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.myapp.smartagricultureplus.Object.Weather;
import com.myapp.smartagricultureplus.R;
import com.myapp.smartagricultureplus.interfaceJava.RequestDataBackListener;
import com.myapp.smartagricultureplus.tool.DatabaseHelper;
import com.myapp.smartagricultureplus.tool.WeatherNetworkRequest;

import java.util.HashMap;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

import static android.content.Context.MODE_PRIVATE;

public class Me_Fragment extends Fragment implements View.OnClickListener {
    LinearLayout llt_login;
    TextView tv_backLogin,tv_userId;
    public SharedPreferences sp;
    public SharedPreferences.Editor edit;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_me_, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
        onClick();
    }

    private void onClick() {
        llt_login.setOnClickListener(this);
    }

    private void initData() {

    }

    private void initView() {
        tv_userId=getActivity().findViewById(R.id.tv_userId);
         llt_login=getActivity().findViewById(R.id.llt_login);
         tv_backLogin=getActivity().findViewById(R.id.tv_backLogin);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.llt_login:
                sendCode(getActivity());
                break;
        }
    }
    public void sendCode(Context context) {
        RegisterPage page = new RegisterPage();
        //如果使用我们的ui，没有申请模板编号的情况下需传null
        page.setTempCode(null);
        page.setRegisterCallback(new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    // 处理成功的结果
                    HashMap<String,Object> phoneMap = (HashMap<String, Object>) data;
                    // 国家代码，如“86”
                    String country = (String) phoneMap.get("country");
                    // 手机号码，如“13800138000”
                    String phone = (String) phoneMap.get("phone");
                    // TODO 利用国家代码和手机号码进行后续的操作
                    tv_userId.setText(phone);
                    tv_backLogin.setVisibility(View.VISIBLE);
                    sp = getActivity().getSharedPreferences("user", MODE_PRIVATE);
                    String userId = sp.getString("userId","");
                    if (userId==""){
                        edit = sp.edit();
                        edit.putString("userId",phone);
                        edit.commit();
                    }
                } else{
                    // TODO 处理错误的结果
                }
            }
        });
        page.show(context);
    }
}