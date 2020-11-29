package com.myapp.smartagricultureplus.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myapp.smartagricultureplus.DiyView.MonitorTabView;
import com.myapp.smartagricultureplus.DiyView.TabView;
import com.myapp.smartagricultureplus.Monitor_Fragment.Monitor_All_Fragment;
import com.myapp.smartagricultureplus.Monitor_Fragment.Monitor_To1_Fragment;
import com.myapp.smartagricultureplus.Monitor_Fragment.Monitor_To2_Fragment;
import com.myapp.smartagricultureplus.Monitor_Fragment.Monitor_To3_Fragment;
import com.myapp.smartagricultureplus.Monitor_Fragment.Monitor_To4_Fragment;
import com.myapp.smartagricultureplus.Monitor_Fragment.Monitor_To5_Fragment;
import com.myapp.smartagricultureplus.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 监测 1
 */
public class Monitor_Fragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_monitor_, container, false);
    }

    //滑动
    private ViewPager mVpMonitor;
    //Fragment
    private Monitor_All_Fragment all_fragment;
    private Monitor_To1_Fragment to1_fragment;
    private Monitor_To2_Fragment to2_fragment;
    private Monitor_To3_Fragment to3_fragment;
    private Monitor_To4_Fragment to4_fragment;
    private Monitor_To5_Fragment to5_fragment;
    //数据
    private SparseArray<Fragment> fgtSparseArray = new SparseArray<>();
    //选项卡
    private MonitorTabView tab_monitor_to1,tab_monitor_to2,tab_monitor_to3,tab_monitor_to4,tab_monitor_to5,tab_monitor_all;
    //选项卡容器
    private List<MonitorTabView> monitorTabViews = new ArrayList<>();
    //页面定位
    private int mMonitorCurTabPos;
    private static final String MONITOR_KEY_POS = "monitor_key_pos";
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        // 后台中存储关键信息和数据
        // 旋转屏幕后在回到页面时恢复数据
        if (savedInstanceState != null) {
            mMonitorCurTabPos = savedInstanceState.getInt(MONITOR_KEY_POS, 0);
        }
        initView();
        initData();
        initAdapter();
        initTend();
        for (int i =0; i<monitorTabViews.size();i++)
        {
            MonitorTabView tabView = monitorTabViews.get(i);
            final  int finalI1 = i;
            tabView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mVpMonitor.setCurrentItem(finalI1,false);

                    setMonitorCurrnnTab(finalI1);
                }
            });
        }
    }
    /**
     * 手机旋转横屏处理 在后台中存储关键信息和数据
     * 由于屏幕旋转之后 onCrea会重新执行 getItem没有执行会把数据清除
     */
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(MONITOR_KEY_POS,mVpMonitor.getCurrentItem());
    }

    private void initTend()
    {

    }

    private void initAdapter()
    {

    }

    private void initData()
    {
        mVpMonitor.setOffscreenPageLimit(fgtSparseArray.size());

        mVpMonitor.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                switch (position)
                {
                    case 1:
                        return fgtSparseArray.get(1);
                    case 2:
                        return fgtSparseArray.get(2);
                    case 3:
                        return fgtSparseArray.get(3);
                    case 4:
                        return fgtSparseArray.get(4);
                    case 5:
                        return fgtSparseArray.get(5);
                }
                return fgtSparseArray.get(0);
            }

            @Override
            public int getCount() {
                return fgtSparseArray.size();
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                return super.instantiateItem(container, position);
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                super.destroyItem(container, position, object);
            }
        });
        mVpMonitor.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                if (positionOffset > 0) {
                    MonitorTabView left = monitorTabViews.get(position);
                    MonitorTabView rigth = monitorTabViews.get(position + 1);

                    //回划时候的算法
                    left.setProgress(1 - (positionOffset));
                    rigth.setProgress((positionOffset));
                }

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initView()
    {
        mVpMonitor = getActivity().findViewById(R.id.vp_monitor);

        all_fragment = new Monitor_All_Fragment();
        to1_fragment = new Monitor_To1_Fragment();
        to2_fragment = new Monitor_To2_Fragment();
        to3_fragment = new Monitor_To3_Fragment();
        to4_fragment = new Monitor_To4_Fragment();
        to5_fragment = new Monitor_To5_Fragment();

        fgtSparseArray.append(0,all_fragment);
        fgtSparseArray.append(1,to1_fragment);
        fgtSparseArray.append(2,to2_fragment);
        fgtSparseArray.append(3,to3_fragment);
        fgtSparseArray.append(4,to4_fragment);
        fgtSparseArray.append(5,to5_fragment);


        tab_monitor_all = getActivity().findViewById(R.id.tab_monitor_all);
        tab_monitor_to1 = getActivity().findViewById(R.id.tab_monitor_to1);
        tab_monitor_to2 = getActivity().findViewById(R.id.tab_monitor_to2);
        tab_monitor_to3 = getActivity().findViewById(R.id.tab_monitor_to3);
        tab_monitor_to4 = getActivity().findViewById(R.id.tab_monitor_to4);
        tab_monitor_to5 = getActivity().findViewById(R.id.tab_monitor_to5);

        monitorTabViews.add(tab_monitor_all);
        monitorTabViews.add(tab_monitor_to1);
        monitorTabViews.add(tab_monitor_to2);
        monitorTabViews.add(tab_monitor_to3);
        monitorTabViews.add(tab_monitor_to4);
        monitorTabViews.add(tab_monitor_to5);

        tab_monitor_all.setText("所有");
        tab_monitor_to1.setText("传感器");
        tab_monitor_to2.setText("温湿度");
        tab_monitor_to3.setText("照明设备");
        tab_monitor_to4.setText("监控设备");
        tab_monitor_to5.setText("其他");



        setMonitorCurrnnTab(mMonitorCurTabPos);
    }

    /**
     * Tab 点击切换页面
     */

    private void setMonitorCurrnnTab(int pos)
    {
        for (int i = 0;i<monitorTabViews.size();i++)
        {
            MonitorTabView mTab = monitorTabViews.get(i);
            if (i == pos)
            {
                mTab.setProgress(1);
            }else
            {
                mTab.setProgress(0);
            }
        }
    }
}