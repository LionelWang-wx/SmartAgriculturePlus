package com.myapp.smartagricultureplus.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.ResultPoint;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.CompoundBarcodeView;
import com.myapp.smartagricultureplus.R;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import java.util.List;

public class Scanning_DeviceActivity extends AppCompatActivity {

    private CardView card_sm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_scanning__device);


        //初始化相机权限
        ZXingLibrary.initDisplayOpinion(this);
        initView();
        initData();

        new RxPermissions(this).request(Manifest.permission.CAMERA)
                .subscribe(granted ->
                {
                    if (granted)
                    {

                    }
                });


    }


    public static void startActivity(Activity context)
    {
        new RxPermissions(context).request(Manifest.permission.CAMERA)
                .subscribe(granted ->
                {
                    if (granted)
                    {
                        Intent intent = new Intent(context,Scanning_DeviceActivity.class);
                        context.startActivity(intent);
                    }

                });
    }
    public static void startActivity(Activity context,int requestCode)
    {
        new RxPermissions(context).request(Manifest.permission.CAMERA)
                .subscribe(granted ->
                {
                    if (granted)
                    {
                        Intent intent = new Intent(context,Scanning_DeviceActivity.class);
                        intent.putExtra("type",1);
                        context.startActivity(intent);
                    }

                });
    }

    /**
     * 扩展闪光灯 边框 扫码线 去除红线
     */
    //获取CompoundBarcodeView类
    private CompoundBarcodeView barcodeView;
    private ImageView flashlight;
    private int mType;
    private boolean isLightOn = false;

    private BarcodeCallback callback = new BarcodeCallback() {
        @Override
        public void barcodeResult(BarcodeResult result) {
            if (result != null)
            {
                //编写业务逻辑
                Log.d("BarcodeCallbacksys",result.getText());
            }
        }

        @Override
        public void possibleResultPoints(List<ResultPoint> resultPoints) {

        }
    };

    private void initData()
    {
        card_sm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //先判断手机版本是否在6.0以上，如果在6.0以上则需要动态申请权限
                if (Build.VERSION.SDK_INT > 22) {
                    if (ContextCompat.checkSelfPermission(Scanning_DeviceActivity.this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        //先判断有没有权限 ，没有就在这里进行权限的申请
                        ActivityCompat.requestPermissions(Scanning_DeviceActivity.this,
                                new String[]{android.Manifest.permission.CAMERA}, 1);
                    } else {
                        //说明已经获取到摄像头权限了 想干嘛干嘛
                        Intent intent = new Intent(Scanning_DeviceActivity.this, TextView.class);
                        startActivityForResult(intent, 1);
                    }
                } else {
                    //这个说明系统版本在6.0之下，不需要动态获取权限。
                    Intent intent = new Intent(Scanning_DeviceActivity.this, TextView.class);
                    startActivityForResult(intent, 1);
                }
            }

        });
    }

    private void initView()
    {
        barcodeView = findViewById(R.id.barcode_scanner);
        flashlight = findViewById(R.id.flashlight);
        barcodeView.decodeContinuous(callback);
        Intent intent = new Intent();
        barcodeView.initializeFromIntent(intent);
        mType = getIntent().getIntExtra("Type",0);
        flashlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isLightOn)
                {
                    //没有打开的手电筒
                    barcodeView.setTorchOff();
                    flashlight.setImageResource(R.mipmap._home_img);
                }else
                {
                    //已经打开的手电筒
                    barcodeView.setTorchOff();
                    flashlight.setImageResource(R.mipmap.home_img);
                }
            }
        });
        // 如果没有闪光灯的功能就会去掉闪光灯
        if (!hasFlash())
        {
            flashlight.setVisibility(View.GONE);
        }
    }
    //判断手机闪光灯的功能
    private boolean hasFlash() {
        return getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return barcodeView.onKeyDown(keyCode,event) || super.onKeyDown(keyCode,event);

    }

    //获取手机相机权限
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(this, TextView.class);
                startActivityForResult(intent, 1);
            } else {
                Toast.makeText(Scanning_DeviceActivity.this, "请打开相机权限", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //处理扫描结果
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (data != null) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    //获取到扫描的结果
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(Scanning_DeviceActivity.this,result,Toast.LENGTH_LONG).show();
                    Log.d("res:",result);
                }
            }
        }
    }




}