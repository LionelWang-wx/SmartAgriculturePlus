package com.myapp.smartagricultureplus.Activity;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import com.myapp.smartagricultureplus.Fragment.DevicesFragment;
import com.myapp.smartagricultureplus.Fragment.Me_Fragment;
import com.myapp.smartagricultureplus.Fragment.NotificationCenterFragment;
import com.myapp.smartagricultureplus.Object.Device;
import com.myapp.smartagricultureplus.OtherFragment.FeedbackFragment;
import com.myapp.smartagricultureplus.OtherFragment.GreenHouseFragment;
import com.myapp.smartagricultureplus.OtherFragment.MoreServiceFragment;
import com.myapp.smartagricultureplus.OtherFragment.OperationRecordFragment;
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
         case "operating":
             FragmentTransaction transaction4=fragmentManager.beginTransaction();
             OperationRecordFragment operationRecordFragment=new OperationRecordFragment();
             transaction4.replace(R.id.llt_fragment,operationRecordFragment);
             transaction4.commit();
             break;
         case "moreservice":
             FragmentTransaction transaction5=fragmentManager.beginTransaction();
             MoreServiceFragment moreServiceFragment=new MoreServiceFragment();
             transaction5.replace(R.id.llt_fragment,moreServiceFragment);
             transaction5.commit();
             break;
         case "greenhouse":
             FragmentTransaction transaction6=fragmentManager.beginTransaction();
             GreenHouseFragment greenHouseFragment=new GreenHouseFragment();
             transaction6.replace(R.id.llt_fragment,greenHouseFragment);
             transaction6.commit();
             break;
         case "ivback":
             FragmentTransaction transaction7=fragmentManager.beginTransaction();
             Me_Fragment me_fragment=new Me_Fragment();
             transaction7.replace(R.id.llt_fragment,me_fragment);
             transaction7.commit();
             break;

     }
    }

    private void initView() {

    }
    private void initData() {
        fragmentManager=getSupportFragmentManager();
    }
}