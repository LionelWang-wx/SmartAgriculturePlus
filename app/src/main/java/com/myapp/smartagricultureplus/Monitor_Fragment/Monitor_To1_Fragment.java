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

public class Monitor_To1_Fragment extends Fragment {
    private RecyclerView rcv_monitor_To1;
    private ArrayList<Device> devices;
    int[] deviceIconTo1=new int[]{R.mipmap.img_temperature,R.mipmap.img_brightnesssensor,R.mipmap.img_floodsensor,R.mipmap._monitor_img,R.mipmap.img_camera,};
    String[] deviceNameTo1=new String[]{"温度传感器","光照传感器","土壤湿度传感器","风向风力传感器","摄像头传感器"};
    int[] deviceBackgroundTo1=new int[]{R.mipmap.img_small_background,R.mipmap.iv_device_background,R.mipmap.img_small_background,R.mipmap.iv_device_background
            ,R.mipmap.img_small_background};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_monitor__to1_, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
        initAdapter();
    }

    private void initView() {
        rcv_monitor_To1 = getActivity().findViewById(R.id.rcv_monitor_To1);
    }

    private void initData() {
        devices = new ArrayList<>();
        for (int i=0;i<deviceIconTo1.length;i++){
            Device device=new Device(deviceIconTo1[i],deviceNameTo1[i],deviceBackgroundTo1[i]);
            devices.add(device);
        }
    }

    private void initAdapter() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2,LinearLayoutManager.VERTICAL,false);
        rcv_monitor_To1.setLayoutManager(gridLayoutManager);
        MonitorAdapter monitorAdapter = new MonitorAdapter(getActivity(), devices);
        rcv_monitor_To1.setAdapter(monitorAdapter);
    }
}