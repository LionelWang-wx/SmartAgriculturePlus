package com.myapp.smartagricultureplus.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.myapp.smartagricultureplus.Fragment.NotificationCenterFragment;
import com.myapp.smartagricultureplus.OtherFragment.FeedbackFragment;
import com.myapp.smartagricultureplus.OtherFragment.SettingFragment;
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
             FragmentTransaction transaction1=fragmentManager.beginTransaction();
             notificationCenterFragment=new NotificationCenterFragment();
             transaction1.replace(R.id.llt_fragment,notificationCenterFragment);
             transaction1.commit();
             break;
         case "feedback":
             FragmentTransaction transaction2=fragmentManager.beginTransaction();
             FeedbackFragment feedbackFragment=new FeedbackFragment();
             transaction2.replace(R.id.llt_fragment,feedbackFragment);
             transaction2.commit();
             break;
         case "setting":
             FragmentTransaction transaction3=fragmentManager.beginTransaction();
             SettingFragment settingFragment=new SettingFragment();
             transaction3.replace(R.id.llt_fragment,settingFragment);
             transaction3.commit();
             break;

     }
    }

    private void initView() {

    }
    private void initData() {
        fragmentManager=getSupportFragmentManager();
    }
}