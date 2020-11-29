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

public class Monitor_To3_Fragment extends Fragment {
    private RecyclerView rcv_monitor_To3;
    private ArrayList<Device> devices;
    int[] deviceIconTo3=new int[]{R.mipmap._monitor_img,R.mipmap._monitor_img};
    String[] deviceNameTo3=new String[]{"Wi-Fi智能调光器","Wi-Fi智能彩色冷暖光球泡灯"};
    int[] deviceBackgroundTo3=new int[]{R.mipmap.img_small_background,R.mipmap.iv_device_background};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_monitor__to3_, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
        initAdapter();
    }

    private void initView() {
        rcv_monitor_To3 = getActivity().findViewById(R.id.rcv_monitor_To3);
    }

    private void initData() {
        devices = new ArrayList<>();
        for (int i=0;i<deviceIconTo3.length;i++){
            Device device=new Device(deviceIconTo3[i],deviceNameTo3[i],deviceBackgroundTo3[i]);
            devices.add(device);
        }
    }

    private void initAdapter() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2,LinearLayoutManager.VERTICAL,false);
        rcv_monitor_To3.setLayoutManager(gridLayoutManager);
        MonitorAdapter monitorAdapter = new MonitorAdapter(getActivity(), devices);
        rcv_monitor_To3.setAdapter(monitorAdapter);
    }
}