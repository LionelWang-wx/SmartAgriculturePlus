package com.myapp.smartagricultureplus.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.myapp.smartagricultureplus.Adapter.NavigationAdapter;
import com.myapp.smartagricultureplus.R;

public class NavigationActivity extends BaseActivity{
    public SharedPreferences sp;
    public SharedPreferences.Editor edit;
    RecyclerView rcv_Navigation;
    NavigationAdapter navigationAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        sp = getSharedPreferences("user", MODE_PRIVATE);
        edit = sp.edit();
        edit.putBoolean("isFirst",true);
        edit.commit();
        rcv_Navigation=this.findViewById(R.id.rcv_Navigation);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rcv_Navigation.setLayoutManager(linearLayoutManager);
        navigationAdapter=new NavigationAdapter(this);
        rcv_Navigation.setAdapter(navigationAdapter);
        LinearSnapHelper linearSnapHelper=new LinearSnapHelper();
        linearSnapHelper.attachToRecyclerView(rcv_Navigation);
    }
}