package com.myapp.smartagricultureplus.SimulationData;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.myapp.smartagricultureplus.Activity.Data;

import java.util.Random;

public class SimulationDataService extends Service {
    SimulationDataBinder binder;
    DataCallBack dataCallBack;
    //    String data;
    String msg;
    int weather;
    Data data;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        if (binder == null) {
            binder = new SimulationDataBinder();
        }
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("TAG", "SimulationDataService");
        weather = 38;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    //控制温度
//                    try {
//                        Thread.sleep(10000);
//                        dataCallBack.success(weather);
//                        weather=weather + 1;
//                    }catch (Exception e){
//                        e.printStackTrace();
////                        dataCallBack.failed(e.getMessage());
//                    }
                    //数据展示更新
                    try {
                        Thread.sleep(10000);
                        Random random = new Random();
                        random.nextInt();
                        data = new Data(random.nextInt(), random.nextInt(),
                                random.nextInt(), random.nextInt(),
                                random.nextInt(), random.nextInt(),
                                random.nextInt());
                        dataCallBack.success(data);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    public void setDataCallBack(DataCallBack dataCallBack) {
        this.dataCallBack = dataCallBack;
    }

    public interface DataCallBack {
        void success(Data data);

        void failed(String msg);
    }

    //Binder与Activity进行通信
    public class SimulationDataBinder extends Binder {
        //得到Service对象
        public SimulationDataService getService() {
            return SimulationDataService.this;
        }
    }
}
