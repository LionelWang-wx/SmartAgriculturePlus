package com.myapp.smartagricultureplus.Monitor_Fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myapp.smartagricultureplus.Adapter.MonitorAdapter;
import com.myapp.smartagricultureplus.Object.Device;
import com.myapp.smartagricultureplus.R;

import java.util.ArrayList;


public class Monitor_To2_Fragment extends Fragment {
    private RecyclerView rcv_monitor_To2;
    private ArrayList<Device> devices;
    int[] deviceIconTo2=new int[]{R.mipmap._monitor_img,R.mipmap._monitor_img,R.mipmap._monitor_img,R.mipmap._monitor_img};
    String[] deviceNameTo2=new String[]{"智能负离子风扇","吸顶式风扇灯","WiFi水浸报警器","双模智能空调"};
    int[] deviceBackgroundTo2=new int[]{R.mipmap.img_small_background,R.mipmap.iv_device_background,R.mipmap.img_small_background,R.mipmap.iv_device_background};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_monitor__to2_, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
        initAdapter();
    }

    private void initView() {
        rcv_monitor_To2 = getActivity().findViewById(R.id.rcv_monitor_To2);
    }

    private void initData() {
        devices = new ArrayList<>();
        for (int i=0;i<deviceIconTo2.length;i++){
            Device device=new Device(deviceIconTo2[i],deviceNameTo2[i],deviceBackgroundTo2[i]);
            devices.add(device);
        }
    }

    private void initAdapter() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2,LinearLayoutManager.VERTICAL,false);
        rcv_monitor_To2.setLayoutManager(gridLayoutManager);
        MonitorAdapter monitorAdapter = new MonitorAdapter(getActivity(), devices);
        rcv_monitor_To2.setAdapter(monitorAdapter);
    }
}