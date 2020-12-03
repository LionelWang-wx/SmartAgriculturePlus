package com.myapp.smartagricultureplus.Object;


import java.io.Serializable;

public class Device implements Serializable {
    String userId;
    int deviceIcon;
    String deviceName;
    int deviceBackground;
    int devicesLayout;

    public Device() {
    }
//    userIdReturn,deviceIconReturn,deviceNameReturn,devicesLayout
    public Device(String userId, int deviceIcon, String deviceName, int devicesLayout) {
        this.userId = userId;
        this.deviceIcon = deviceIcon;
        this.deviceName = deviceName;
        this.devicesLayout = devicesLayout;
    }

    public Device(int deviceIcon, String deviceName) {
        this.deviceIcon = deviceIcon;
        this.deviceName = deviceName;
    }

    public Device(int deviceIcon, String deviceName, int deviceBackground) {
        this.deviceIcon = deviceIcon;
        this.deviceName = deviceName;
        this.deviceBackground = deviceBackground;
    }
//    deviceIconAll[i],deviceNameAll[i],deviceBackgroundAll[i],devicesLayout[i]
    public Device(int deviceIcon, String deviceName, int deviceBackground, int devicesLayout) {
        this.deviceIcon = deviceIcon;
        this.deviceName = deviceName;
        this.deviceBackground = deviceBackground;
        this.devicesLayout = devicesLayout;
    }

    public Device(String userId, int deviceIcon, String deviceName) {
        this.userId = userId;
        this.deviceIcon = deviceIcon;
        this.deviceName = deviceName;
    }

    public int getDevicesLayout() {
        return devicesLayout;
    }

    public void setDevicesLayout(int devicesLayout) {
        this.devicesLayout = devicesLayout;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getDeviceBackground() {
        return deviceBackground;
    }

    public void setDeviceBackground(int deviceBackground) {
        this.deviceBackground = deviceBackground;
    }

    public int getDeviceIcon() {
        return deviceIcon;
    }

    public void setDeviceIcon(int deviceIcon) {
        this.deviceIcon = deviceIcon;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
}
