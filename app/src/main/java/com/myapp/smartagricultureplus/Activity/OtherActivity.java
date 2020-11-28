package com.myapp.smartagricultureplus.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.myapp.smartagricultureplus.Fragment.NotificationCenterFragment;
import com.myapp.smartagricultureplus.R;

public class OtherActivity extends BaseActivity {
    String fragmentName;
    NotificationCenterFragment notificationCenterFragment;
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        fragmentName=getIntent().getStringExtra("fragmentName");
        initView();
        initData();
        fragmentLoading(fragmentName);
    }

    private void fragmentLoading(String fragmentName) {
     switch (fragmentName){
         case "NotificationCenter":
             FragmentTransaction transaction=fragmentManager.beginTransaction();
             notificationCenterFragment=new NotificationCenterFragment();
             transaction.replace(R.id.llt_fragment,notificationCenterFragment);
             transaction.commit();
             break;
     }
    }

    private void initView() {

    }
    private void initData() {
        fragmentManager=getSupportFragmentManager();
    }
}