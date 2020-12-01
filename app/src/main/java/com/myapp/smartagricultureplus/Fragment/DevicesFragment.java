package com.myapp.smartagricultureplus.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.XmlRes;
import androidx.fragment.app.Fragment;

import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myapp.smartagricultureplus.R;

import org.xmlpull.v1.XmlPullParser;


public class DevicesFragment extends Fragment {
    XmlPullParser devicesLayout;
    Context context;

    public DevicesFragment(XmlPullParser devicesLayout, Context context) {
        this.devicesLayout = devicesLayout;
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(devicesLayout, container, false);
    }

}