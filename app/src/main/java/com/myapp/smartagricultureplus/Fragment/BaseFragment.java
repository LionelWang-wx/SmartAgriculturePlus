package com.myapp.smartagricultureplus.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myapp.smartagricultureplus.R;


public class BaseFragment extends Fragment {


    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach(context);
        Log.e("BaseFragment","Me_Fragment:onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("BaseFragment","Me_Fragment:onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.e("BaseFragment","Me_Fragment:onCreateView");
        return inflater.inflate(R.layout.fragment_base, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("BaseFragment","Me_Fragment:onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("BaseFragment","Me_Fragment:onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("BaseFragment","Me_Fragment:onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("BaseFragment","Me_Fragment:onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("BaseFragment","Me_Fragment:onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("BaseFragment","Me_Fragment:onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("BaseFragment","Me_Fragment:onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("BaseFragment","Me_Fragment:onDetach");
    }
}