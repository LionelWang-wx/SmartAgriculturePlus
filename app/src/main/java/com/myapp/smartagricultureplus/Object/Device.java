package com.myapp.smartagricultureplus.Object;

public class Device {
    String userId;
    int deviceIcon;
    String deviceName;
    int deviceBackground;

    public Device() {
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

    public Device(String userId, int deviceIcon, String deviceName) {
        this.userId = userId;
        this.deviceIcon = deviceIcon;
        this.deviceName = deviceName;
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
