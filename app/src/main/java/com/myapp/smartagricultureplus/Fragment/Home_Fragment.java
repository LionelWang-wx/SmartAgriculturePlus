package com.myapp.smartagricultureplus.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.myapp.smartagricultureplus.Activity.OtherActivity;
import com.myapp.smartagricultureplus.R;

import java.util.ArrayList;


public class Home_Fragment extends Fragment implements View.OnClickListener {
    //轮播图片
    private int [] Carousel=new int[]{R.mipmap.img_carousel3,R.mipmap.img_carousel1,R.mipmap.img_carousel2,R.mipmap.img_carousel3,R.mipmap.img_carousel1};
    private  boolean icon=true;
    //小圆点容器
    private LinearLayout circlecan;
    ViewPager viewPager;
    private ArrayList<ImageView> mdots=new ArrayList<>();
    ImageView iv_notice;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_blank, container, false);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initViewPager();
        onClick();
        viewPager.setOffscreenPageLimit(3);
        viewPager.setPageMargin(40);
        viewPager.setCurrentItem(1);
    }

    private void onClick() {
        iv_notice.setOnClickListener(this);
    }


    private void initViewPager() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position==0)
                {
                    viewPager.setCurrentItem(Carousel.length-2,false);

                }
                else if(position== Carousel.length-1)
                {
                    viewPager.setCurrentItem(1,false);
                }
                setDotsImgs();

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        final Handler handler = new Handler()
        {
            @Override
            public void handleMessage(@NonNull Message msg) {

                if (icon == true)
                {
                    if (msg.what == 0)
                    {
                        this.removeMessages(0);
                        viewPager.setCurrentItem(viewPager.getCurrentItem()+1,true);
                        this.sendEmptyMessageDelayed(0,2000);

                    }
                }else if (icon == false)
                {
                    if ( this != null)
                    {
                        Toast.makeText(getContext(),"停止轮播", Toast.LENGTH_SHORT).show();
                        this.removeMessages(0);
                    }

                }

                super.handleMessage(msg);
            }
        };
        handler.sendEmptyMessageDelayed(0, 2000);

        for (int i = 0;i<Carousel.length-2;i++)
        {
            ImageView view = new ImageView(getContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(10,10);
            params.leftMargin = 5;
            params.rightMargin = 5;
            view.setLayoutParams(params);
            mdots.add(view);
            circlecan.addView(view);
        }

        /**
         * viewpager 初始化
         */
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return Carousel.length;
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                View view = new View(container.getContext());

                view.setBackgroundResource(Carousel[position]);
                ViewGroup.LayoutParams viewGroup = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                container.addView(view);

                return view;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                //强制复写
                container.removeView((View) object);

            }
        });

    }

    private void setDotsImgs() {
        for (int i=0;i<mdots.size();i++)
        {
            if (i==viewPager.getCurrentItem()-1)
            {
                mdots.get(i).setBackgroundResource(R.mipmap.img_pagenow);
            }
            else {
                mdots.get(i).setBackgroundResource(R.mipmap.img_page);
            }
        }
    }

    private void initView() {
        iv_notice=getActivity().findViewById(R.id.iv_notice);
        circlecan=getActivity().findViewById(R.id.ll_circle_can);
        viewPager=getActivity().findViewById(R.id.vp_carousel);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_notice:
                Intent intent=new Intent(getActivity(), OtherActivity.class);
                intent.putExtra("fragmentName","NotificationCenter");
                getActivity().startActivity(intent);
                break;
        }
    }
}