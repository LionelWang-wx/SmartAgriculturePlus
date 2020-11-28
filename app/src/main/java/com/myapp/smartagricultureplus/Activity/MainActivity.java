package com.myapp.smartagricultureplus.Activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.myapp.smartagricultureplus.DiyView.TabView;
import com.myapp.smartagricultureplus.Fragment.Contror_Fragment;
import com.myapp.smartagricultureplus.Fragment.Home_Fragment;
import com.myapp.smartagricultureplus.Fragment.Me_Fragment;
import com.myapp.smartagricultureplus.Fragment.Monitor_Fragment;
import com.myapp.smartagricultureplus.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabView tb_wechat1, tb_wechat2, tb_wechat3, tb_wechat4;
    private List<String> mtitls = new ArrayList<>(Arrays.asList("首页", "检测", "控制", "我的"));
    private List<TabView> mtad = new ArrayList<>();
    private static final String BUNDLE_KEY_POS = "bundle_key_pos";

    private int mCurTabPos;

    private Home_Fragment home_fragment;
    private Monitor_Fragment monitor_fragment;
    private Contror_Fragment contror_fragment;
    private Me_Fragment me_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);


        // 后台中存储关键信息和数据
        // 旋转屏幕后在回到页面时恢复数据
        if (savedInstanceState != null) {
            mCurTabPos = savedInstanceState.getInt(BUNDLE_KEY_POS, 0);
        }

        initviews();
        initviewpagerAdapter();
        initend();
    }

    /**
     * 手机旋转横屏处理 在后台中存储关键信息和数据
     * 由于屏幕旋转之后 onCrea会重新执行 getItem没有执行会把数据清除
     */
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(BUNDLE_KEY_POS, viewPager.getCurrentItem());
    }

    private void initend() {

        for (int i = 0; i < mtad.size(); i++) {
            TabView view = mtad.get(i);
            //点击事件 切换页面
            final int finalI = i;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    viewPager.setCurrentItem(finalI, false);

                    setCurrentTab(finalI);
                }
            });

        }
    }

    private void initviewpagerAdapter() {
        viewPager.setOffscreenPageLimit(mtitls.size());

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {

                switch (position)
                {
                    case 1:
                        return monitor_fragment = new Monitor_Fragment();
                    case 2:
                        return contror_fragment = new Contror_Fragment();
                    case 3:
                        return me_fragment = new Me_Fragment();
                }

//                Tab_fragment fragment = Tab_fragment.newInstance(mtitls.get(position));
                return home_fragment=new Home_Fragment();
            }

            @Override
            public int getCount() {
                return mtitls.size();
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
//
                return super.instantiateItem(container,position);
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//                frag.remove(position);
                super.destroyItem(container, position, object);
            }
        });


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                //左 -> 右  0~1 left:pas  ; rigth left+1  ; positionOffset 0~1
                //left 效果 :0~1 （1-positionOffset）；rigth 效果：0~1（positionOffset）


                //右 -> 左  1~0 left:pas  ; rigth left+1  ; positionOffset 1~0
                //left 效果 :0~1 （1-positionOffset）；rigth 效果：1~0（positionOffset）

                if (positionOffset > 0) {
                    TabView left = mtad.get(position);
                    TabView rigth = mtad.get(position + 1);

                    //回划时候的算法
                    left.setprogress(1 - (positionOffset));
                    rigth.setprogress((positionOffset));
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

    private void initviews() {
        viewPager = findViewById(R.id.tab_vp_pager);
        tb_wechat1 = findViewById(R.id.tb_bt_wechat1);
        tb_wechat2 = findViewById(R.id.tb_bt_wechat2);
        tb_wechat3 = findViewById(R.id.tb_bt_wechat3);
        tb_wechat4 = findViewById(R.id.tb_bt_wechat4);

        mtad.add(tb_wechat1);
        mtad.add(tb_wechat2);
        mtad.add(tb_wechat3);
        mtad.add(tb_wechat4);


        tb_wechat1.setIconandText(R.mipmap.home_img, R.mipmap._home_img, "首页");
        tb_wechat2.setIconandText(R.mipmap.monitor_img, R.mipmap._monitor_img, "检测");
        tb_wechat3.setIconandText(R.mipmap.control_img, R.mipmap._control_img, "控制");
        tb_wechat4.setIconandText(R.mipmap.me_img, R.mipmap._me_img, "我的");


        setCurrentTab(mCurTabPos);
    }

    private void setCurrentTab(int pos) {    /**tab的点击切换页面*/

        for (int i = 0; i < mtad.size(); i++) {
            TabView view = mtad.get(i);
            if (i == pos) {
                view.setprogress(1);
            } else {
                view.setprogress(0);
            }

        }
    }
}