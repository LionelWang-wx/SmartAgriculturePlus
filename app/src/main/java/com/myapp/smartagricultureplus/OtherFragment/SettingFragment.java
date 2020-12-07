package com.myapp.smartagricultureplus.OtherFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.myapp.smartagricultureplus.Activity.OtherActivity;
import com.myapp.smartagricultureplus.R;

public class SettingFragment extends Fragment implements View.OnClickListener {
    ImageView iv_back;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        iv_back=getActivity().findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.iv_back:
                Intent intent5=new Intent(getActivity(), OtherActivity.class);
                intent5.putExtra("fragmentName","ivback");
                getActivity().finish();
                break;

        }
    }
}
