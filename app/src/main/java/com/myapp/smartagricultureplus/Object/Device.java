package com.myapp.smartagricultureplus.Object;

public class Device {
    int deviceIcon;
    String deviceName;

    public Device() {
    }

    public Device(int deviceIcon, String deviceName) {
        this.deviceIcon = deviceIcon;
        this.deviceName = deviceName;
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
