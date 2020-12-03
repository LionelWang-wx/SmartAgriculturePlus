package com.myapp.smartagricultureplus.OtherFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.myapp.smartagricultureplus.Adapter.MonitorAdapter;
import com.myapp.smartagricultureplus.Adapter.OperationAdapter;
import com.myapp.smartagricultureplus.Object.Device;
import com.myapp.smartagricultureplus.Object.Operation;
import com.myapp.smartagricultureplus.R;

import java.util.ArrayList;

public class OperationRecordFragment extends Fragment {
    private RecyclerView re_operation;
    private ArrayList<Operation> operations;
    int [] deviceimage=new int []{R.mipmap.img_blowingfan,R.mipmap.img_blowingfan,R.mipmap.img_blowingfan,};
    String [] devicename=new String[]{"风扇","风扇","风扇"};
    String [] devicedate=new String[]{"12月1日 18:19","12月1日 18:19","12月1日 18:19"};
    String []deviceoperating=new String[]{"打开风扇","打开风扇","打开风扇"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_operationrecord, container, false);
    }
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
        initAdapter();
    }

    private void initAdapter() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),1, LinearLayoutManager.VERTICAL,false);
        re_operation.setLayoutManager(gridLayoutManager);
        OperationAdapter operationAdapter = new OperationAdapter(getActivity(), operations);
        re_operation.setAdapter(operationAdapter);
    }

    private void initData() {
        operations = new ArrayList<>();
        for (int i=0;i<deviceimage.length;i++){
            Operation operation=new Operation(devicename[i],deviceimage[i],devicedate[i],deviceoperating[i]);
            operations.add(operation);
        }
    }

    private void initView() {
        re_operation=getActivity().findViewById(R.id.re_iv_operation);
    }
}
