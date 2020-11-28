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
import com.myapp.smartagricultureplus.Object.Monitor;
import com.myapp.smartagricultureplus.R;

import java.util.ArrayList;

public class Monitor_To3_Fragment extends Fragment {
    private RecyclerView rcv_monitor_To3;
    private ArrayList<Monitor> monitors;
    private Monitor monitor1, monitor2, monitor3, monitor4;
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
        monitors = new ArrayList<>();
        monitor1 = new Monitor(R.mipmap._monitor_img, "温湿度传感器");
        monitors.add(monitor1);
        monitor2 = new Monitor(R.mipmap._monitor_img, "温湿度传感器");
        monitors.add(monitor2);
        monitor3 = new Monitor(R.mipmap._monitor_img, "温湿度传感器");
        monitors.add(monitor3);
        monitor4 = new Monitor(R.mipmap._monitor_img, "温湿度传感器");
        monitors.add(monitor4);
    }

    private void initAdapter() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),3,LinearLayoutManager.VERTICAL,false);
        rcv_monitor_To3.setLayoutManager(gridLayoutManager);
        MonitorAdapter monitorAdapter = new MonitorAdapter(getActivity(), monitors);
        rcv_monitor_To3.setAdapter(monitorAdapter);
    }
}