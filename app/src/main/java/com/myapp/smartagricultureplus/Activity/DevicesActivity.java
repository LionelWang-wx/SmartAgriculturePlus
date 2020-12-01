package com.myapp.smartagricultureplus.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.myapp.smartagricultureplus.Fragment.NotificationCenterFragment;
import com.myapp.smartagricultureplus.R;

/**
 * 设备
 */
public class DevicesActivity extends AppCompatActivity {
    FragmentManager fragmentManager;
    String devicesFragmentName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment);
        fragmentManager=getSupportFragmentManager();
        devicesFragmentName=getIntent().getStringExtra("devicesFragmentName");
        fragmentLoading(devicesFragmentName);
    }
    private void fragmentLoading(String devicesFragmentName) {
        switch (devicesFragmentName){
            case "NotificationCenter":
                FragmentTransaction transaction=fragmentManager.beginTransaction();

                transaction.commit();
                break;
        }
    }
}