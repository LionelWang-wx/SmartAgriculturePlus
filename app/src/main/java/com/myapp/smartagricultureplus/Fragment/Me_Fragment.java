package com.myapp.smartagricultureplus.Fragment;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.myapp.smartagricultureplus.Activity.OtherActivity;
import com.myapp.smartagricultureplus.Object.Weather;
import com.myapp.smartagricultureplus.R;
import com.myapp.smartagricultureplus.interfaceJava.RequestDataBackListener;
import com.myapp.smartagricultureplus.tool.DatabaseHelper;
import com.myapp.smartagricultureplus.tool.WeatherNetworkRequest;

import java.util.HashMap;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.MODE_PRIVATE;

public class Me_Fragment extends BaseFragment implements View.OnClickListener {
    LinearLayout llt_login,ll_feedback,ll_setting,ll_operating,ll_moreservice,ll_green_house;
    TextView tv_backLogin,tv_userId;
    public SharedPreferences sp;
    public SharedPreferences.Editor edit;

    //头像
    private CircleImageView civ_userHeader;
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
        ll_green_house.setOnClickListener(this);
        ll_moreservice.setOnClickListener(this);
        ll_operating.setOnClickListener(this);
        ll_setting.setOnClickListener(this);
        ll_feedback.setOnClickListener(this);
        llt_login.setOnClickListener(this);
        tv_backLogin.setOnClickListener(this);
        civ_userHeader.setOnClickListener(this);
    }

    private void initData() {
        sp = getActivity().getSharedPreferences("user", MODE_PRIVATE);
        String userId=sp.getString("userId","");
        if (userId==""){
            tv_userId.setText("立即登录");
        }else {
            tv_userId.setText(userId);
        }
    }

    private void initView() {
        ll_green_house=getActivity().findViewById(R.id.ll_green_house);
        ll_moreservice=getActivity().findViewById(R.id.ll_moreservice);
        ll_operating=getActivity().findViewById(R.id.ll_operating);
         ll_setting=getActivity().findViewById(R.id.ll_setting);
         ll_feedback=getActivity().findViewById(R.id.ll_feedback);
         tv_userId=getActivity().findViewById(R.id.tv_userId);
         llt_login=getActivity().findViewById(R.id.llt_login);
         tv_backLogin=getActivity().findViewById(R.id.tv_backLogin);

         //头像
         civ_userHeader = getActivity().findViewById(R.id.civ_userHeader);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.llt_login:
                sp = getActivity().getSharedPreferences("user", MODE_PRIVATE);
                String userId=sp.getString("userId","");
                if (userId==""){
                    sendCode(getActivity());
                }else {
                    tv_userId.setText(userId);
                    tv_backLogin.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.tv_backLogin:
                AlertDialog.Builder dialog=new AlertDialog.Builder(getActivity());
                dialog.setTitle("即将退出登录？");
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sp = getActivity().getSharedPreferences("user", MODE_PRIVATE);
                        edit = sp.edit();
                        edit.putString("userId","");
                        edit.commit();
                        tv_userId.setText("立即登录");
                        tv_backLogin.setVisibility(View.INVISIBLE);
                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getActivity(),"取消退出登录",Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show();
                break;
            case R.id.ll_feedback:
                Intent intent1=new Intent(getActivity(), OtherActivity.class);
                intent1.putExtra("fragmentName","feedback");
                getActivity().startActivity(intent1);
                break;
            case R.id.ll_setting:
                Intent intent2=new Intent(getActivity(), OtherActivity.class);
                intent2.putExtra("fragmentName","setting");
                getActivity().startActivity(intent2);
                break;
            case R.id.civ_userHeader:
                Zoominphoto();
                break;
            case R.id.ll_operating:
                Intent intent3=new Intent(getActivity(), OtherActivity.class);
                intent3.putExtra("fragmentName","operating");
                getActivity().startActivity(intent3);
                break;
            case R.id.ll_moreservice:
                Intent intent4=new Intent(getActivity(), OtherActivity.class);
                intent4.putExtra("fragmentName","moreservice");
                getActivity().startActivity(intent4);
                break;
            case R.id.ll_green_house:
                Intent intent5=new Intent(getActivity(), OtherActivity.class);
                intent5.putExtra("fragmentName","greenhouse");
                getActivity().startActivity(intent5);
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

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden){
            sp = getActivity().getSharedPreferences("user", MODE_PRIVATE);
            String userId=sp.getString("userId","");
            if (userId==""){
                tv_userId.setText("立即登录");
            }else {
                tv_userId.setText(userId);
                tv_backLogin.setVisibility(View.VISIBLE);
            }
            Log.e("BaseFragment","Me_Fragment:true");
        }
        else {
            Log.e("BaseFragment","Me_Fragment:false");
        }
    }
    private void Zoominphoto()
    {
    }

}