package com.myapp.smartagricultureplus.Activity;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.myapp.smartagricultureplus.Fragment.DevicesFragment;
import com.myapp.smartagricultureplus.Object.Device;
import com.myapp.smartagricultureplus.R;

public class DevicesActivity extends BaseActivity {
    int devicesLayout;
    String fragmentName;
    FragmentManager fragmentManager;
    LinearLayout llt_devicesFragment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devices);
        llt_devicesFragment=findViewById(R.id.llt_devicesFragment);
        Device device= (Device) getIntent().getSerializableExtra("device");
        fragmentName=device.getDeviceName();
        devicesLayout=device.getDevicesLayout();
        fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction4=fragmentManager.beginTransaction();
        DevicesFragment devicesFragment=new DevicesFragment(devicesLayout,DevicesActivity.this,fragmentName);
        transaction4.replace(R.id.llt_devicesFragment,devicesFragment);
        transaction4.commit();
    }
}
