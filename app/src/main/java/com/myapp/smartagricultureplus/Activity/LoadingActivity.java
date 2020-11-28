package com.myapp.smartagricultureplus.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.myapp.smartagricultureplus.R;

public class LoadingActivity extends BaseActivity {
    public SharedPreferences sp;
    public SharedPreferences.Editor edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_loading);
        sp = getSharedPreferences("user", MODE_PRIVATE);
        Boolean isFirst = sp.getBoolean("isFirst", false);
        if (isFirst){
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }else {
            Intent intent=new Intent(this,NavigationActivity.class);
            startActivity(intent);
            finish();
        }
    }
}