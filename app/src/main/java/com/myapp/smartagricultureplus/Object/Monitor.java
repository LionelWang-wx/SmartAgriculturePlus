package com.myapp.smartagricultureplus.Object;

public class Monitor {
    int monitorIcon;
    String monitorName;

    public Monitor() {

    }

    public Monitor(int monitorIcon, String monitorName) {
        this.monitorIcon = monitorIcon;
        this.monitorName = monitorName;
    }

    public int getMonitorIcon() {
        return monitorIcon;
    }

    public void setMonitorIcon(int monitorIcon) {
        this.monitorIcon = monitorIcon;
    }

    public String getMonitorName() {
        return monitorName;
    }

    public void setMonitorName(String monitorName) {
        this.monitorName = monitorName;
    }
}
