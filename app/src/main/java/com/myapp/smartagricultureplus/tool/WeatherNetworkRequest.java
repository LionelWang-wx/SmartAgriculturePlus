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
                    okHttpClient = new OkHttpClient();
                    FormBody formBody = new FormBody.Builder()
                            .add("checkPassword", "123456");
                            .add("password", "123456");
                            .add("phone", "15730587500");
                            .build();
                    Request request = new Request.Builder()
                            .url("Request URL\n" +
                                    "http://121.196.173.248:9002/user/zhuce?checkPassword=123456&password=123456&phone=15730587500")
                            .post(formBody)
                            .build();
                    Response response = okHttpClient.newCall(request).execute();
                    String responseData = response.body().string();
                    Log.e("setRequestData", "234567890:" + responseData);
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
