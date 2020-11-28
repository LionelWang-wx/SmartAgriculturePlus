package com.myapp.smartagricultureplus.interfaceJava;

public interface RequestDataBackListener {
      void onFinish(String responseData);
      void onError(Exception e);
}
