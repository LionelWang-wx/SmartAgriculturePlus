package com.myapp.smartagricultureplus.Fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myapp.smartagricultureplus.Adapter.ControrAdapter;
import com.myapp.smartagricultureplus.Object.Device;
import com.myapp.smartagricultureplus.R;

import java.util.ArrayList;

/**
 * 控制
 */
public class Contror_Fragment extends Fragment {


        private RecyclerView mRcvContror;
        private ArrayList<Device> devices;
        Device device1,device2,device3;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_contror_, container, false);
        }

        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            initView();
            initData();
            initAdapter();
        }

        private void initAdapter() {
            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            mRcvContror.setLayoutManager(linearLayoutManager);
            ControrAdapter controrAdapter=new ControrAdapter(getActivity(),devices);
            mRcvContror.setAdapter(controrAdapter);
        }

        private void initData() {
            devices = new ArrayList<>();

            device1 = new Device(R.mipmap.img_water_level,"智慧水位");
            devices.add(device1);

            device2 =new Device(R.mipmap.img_camera,"智慧监控");
            devices.add(device2);

            device3 =new Device(R.mipmap.img_illumination,"智能光照");
            devices.add(device3);
        }

        private void initView() {
            mRcvContror=getActivity().findViewById(R.id.rcv_contror);
        }
}