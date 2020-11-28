package com.myapp.smartagricultureplus.tool;

import android.util.Log;

import com.myapp.smartagricultureplus.interfaceJava.RequestDataBackListener;

import java.io.IOException;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WeatherNetworkRequest {
    OkHttpClient okHttpClient;

    public void setRequestData(final String cityName, final RequestDataBackListener requestDataBackListener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    okHttpClient=new OkHttpClient();
                    FormBody formBody = new FormBody.Builder()
                            .add("city",cityName)
                            .add("key","b3b5ddc3e69a8af33f75b23060c01a42")
                            .build();
                    Request request= new Request.Builder()
                            .url("http://apis.juhe.cn/simpleWeather/query?")
                            .post(formBody)
                            .build();
                    Response response=okHttpClient.newCall(request).execute();
                    String responseData=response.body().string();
                    Log.e("setRequestData","234567890:"+responseData);
                    //回调方法
                    requestDataBackListener.onFinish(responseData);
                } catch (IOException e) {
                    e.printStackTrace();
                    //回调方法
                    requestDataBackListener.onError(e);
                }
            }
        }).start();
    }

}
