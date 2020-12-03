package com.myapp.smartagricultureplus.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.ResultPoint;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.CompoundBarcodeView;
import com.myapp.smartagricultureplus.Fragment.Me_Fragment;
import com.myapp.smartagricultureplus.Fragment.Monitor_Fragment;
import com.myapp.smartagricultureplus.R;
import com.tbruyelle.rxpermissions2.RxPermissions;
//import com.uuzuche.lib_zxing.activity.ZXingLibrary;
//import com.uuzuche.lib_zxing.activity.CodeUtils;
//import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import java.util.List;

public class Scanning_DeviceActivity extends AppCompatActivity {


    public static void startActivity(Activity context) {
        new RxPermissions(context)
                .request(Manifest.permission.CAMERA)
                .subscribe(granted -> {
                    if (granted) {
                        Intent intent = new Intent(context, Scanning_DeviceActivity.class);
                        context.startActivity(intent);
                    }
                });
    }

    public static void startActivity(Activity context, int requestCode) {
        new RxPermissions(context)
                .request(Manifest.permission.CAMERA)
                .subscribe(granted -> {
                    if (granted) {
                        Intent intent = new Intent(context, Scanning_DeviceActivity.class);
                        intent.putExtra("type", 1);
                        context.startActivityForResult(intent, requestCode);
                    }
                });
    }

    //扩展闪光灯，边框，扫码线，去掉红线
    private CompoundBarcodeView barcodeView;
    private ImageView flashlight;
    private int mType;
    private boolean isLightOn = false;

    private BarcodeCallback callback = new BarcodeCallback() {
        @Override
        public void barcodeResult(final BarcodeResult result) {
            if (result != null) {
                //这里写业务逻辑
                Log.d("wewq", result.getText());
                Toast.makeText(Scanning_DeviceActivity.this, result.getText(),Toast.LENGTH_SHORT).show();
                initSuccess();
                finish();
            }
        }

        @Override
        public void possibleResultPoints(List<ResultPoint> resultPoints) {
        }
    };

    private void initSuccess()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(Scanning_DeviceActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                });

            }
        }).start();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanning_device);
        //全屏显示
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        new RxPermissions(this)
                .request(Manifest.permission.CAMERA)
                .subscribe(granted -> {
                    if (granted) {

                    }
                });
        barcodeView = (CompoundBarcodeView) findViewById(R.id.barcode_scanner);
        flashlight = (ImageView) findViewById(R.id.flashlight);
        barcodeView.decodeContinuous(callback);
        Intent intent = new Intent();
        barcodeView.initializeFromIntent(intent);
        mType = getIntent().getIntExtra("type", 0);
        flashlight.setOnClickListener(v -> {
            if (isLightOn) {
                //关闭状态 闪光灯
                barcodeView.setTorchOff();
                flashlight.setImageResource(R.mipmap.img_flashlight_off);
            } else {
                //打开状态 闪光灯
                barcodeView.setTorchOn();
                flashlight.setImageResource(R.mipmap.img_flashlight_on);
            }
            isLightOn = !isLightOn;
        });
        // 如果没有闪光灯功能，就去掉相关按钮
        if (!hasFlash()) {
            flashlight.setVisibility(View.GONE);
        }

    }

    private boolean hasFlash() {
        return getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }

    @Override
    protected void onResume() {
        super.onResume();
        barcodeView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        barcodeView.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return barcodeView.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }


}