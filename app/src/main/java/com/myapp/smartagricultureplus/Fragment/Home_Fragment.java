package com.myapp.smartagricultureplus.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.myapp.smartagricultureplus.Animation.TouchPullView;
import com.myapp.smartagricultureplus.Object.Weather;
import com.myapp.smartagricultureplus.R;
import com.myapp.smartagricultureplus.interfaceJava.RequestDataBackListener;
import com.myapp.smartagricultureplus.tool.WeatherNetworkRequest;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class Home_Fragment extends Fragment implements View.OnClickListener {
    //轮播图片
    private int [] Carousel=new int[]{R.mipmap.img_carousel3,R.mipmap.img_carousel1,R.mipmap.img_carousel2,R.mipmap.img_carousel3,R.mipmap.img_carousel1};
    private  boolean icon=true;
    //小圆点容器
    private LinearLayout circlecan;
    ViewPager viewPager;
    private ArrayList<ImageView> mdots=new ArrayList<>();
    TextView tv_temperature,tv_date,tv_cityName;

    /**
     * 下拉刷新
     */
    private LinearLayout linear_home_refresh;
    private static final float TOUCH_MOVE_MAX_Y = 600;
    private float mTouchMoveStartY = 0;
    private TouchPullView touchPull;

    /**
     *  智慧场景
     */
    //智慧托管
    //判断
    boolean flag1 = true;
    private CardView cv_smart_hosting;
    private CircleImageView civ_smart_hosting;
    private ImageView iv_smart_hosting;

    //大雨
    //判断
    boolean flag2 = true;
    private ImageView iv_heavy_rain;
    private CardView  cv_heavy_rain;
    private CircleImageView civ_heavy_rain;

    //气温过高
    //判断
    boolean flag3 = true;
    private ImageView iv_high_temperature;
    private CircleImageView cvi_high_temperature;
    private CardView cv_high_temperature;

    //大雾
    //判断
    boolean flag4 = true;
    private ImageView iv_heavy_fog;
    private CircleImageView civ_heavy_fog;
    private CardView cv_heavy_fog;

    //气温过低
    //判断
    boolean flag5 = true;
    private ImageView iv_low_temperature;
    private CardView cv_low_temperature;
    private CircleImageView civ__low_temperature;

    //水位过低
    //判断
    boolean flag6 = true;
    private ImageView iv_low_water_level;
    private CardView cv_low_water_level;
    private CircleImageView civ_low_water_level;

    /**
     * 快捷键
     */
    private CardView
            cv_home_headlight,//大灯
            cv_home_fast_fan,//排风扇
            cv_home_quick_sluice,//水闸
            cv_home_quick_temperature;//温湿度

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
        initViewPager();
        onClick();
        viewPager.setOffscreenPageLimit(3);
        viewPager.setPageMargin(40);
        viewPager.setCurrentItem(1);
    }

    private void initData(){

            WeatherNetworkRequest weatherNetworkRequest=new WeatherNetworkRequest();
            weatherNetworkRequest.setRequestData("成都", new RequestDataBackListener() {
        @Override
        public void onFinish(String responseData) {
            Gson gson=new Gson();
            Weather weather=gson.fromJson(responseData,Weather.class);
            if (weather.getReason().equals("查询成功!")){
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_temperature.setText(weather.getResult().getRealtime().getTemperature()+"℃");
                        tv_date.setText(weather.getResult().getFuture().get(0).getDate());
                        tv_cityName.setText(weather.getResult().getCity());
                    }
                });
            }else{
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(),"更新天气失败！",Toast.LENGTH_SHORT).show();
                    }
                });
            }

        }

        @Override
        public void onError(Exception e) {
            Log.e("printStackTrace",""+e);
        }
    });


        /**
         * 下拉刷新
         */
//        linear_home_refresh = getActivity().findViewById(R.id.linear_home_refresh);
//        touchPull = getActivity().findViewById(R.id.TouchPull);
//        linear_home_refresh.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//
//                //得到用户意图
//                int action = motionEvent.getActionMasked();
//                switch (action)
//                {
//                    case MotionEvent.ACTION_DOWN://按下
//
//                        mTouchMoveStartY = motionEvent.getY();
//
//                        return true;
//
//                    case MotionEvent.ACTION_MOVE://滑动
//                        float y = motionEvent.getY();
//
//                        if (y>=mTouchMoveStartY)
//                        {
//                            float moveSize = y-mTouchMoveStartY;
//                            float progress = moveSize>=TOUCH_MOVE_MAX_Y
//                                    ?
//                                    1:moveSize/TOUCH_MOVE_MAX_Y;
//                            //设置进度给TouchPull
//                            touchPull.setProgress(progress);
//
//                        }
//                        return  true;
//                    case MotionEvent.ACTION_UP:
//                        touchPull.release();
//                        return true;
//                    default:
//
//                        break;
//                }
//                return false;
//            }
//        });


    }

    private void onClick() {

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
         circlecan=getActivity().findViewById(R.id.ll_circle_can);
         viewPager=getActivity().findViewById(R.id.vp_carousel);
         tv_temperature=getActivity().findViewById(R.id.tv_temperature);
         tv_date=getActivity().findViewById(R.id.tv_date);
         tv_cityName=getActivity().findViewById(R.id.tv_cityName);

        /**
         * 智慧场景
         */
        //智慧托管
        cv_smart_hosting = getActivity().findViewById(R.id.cv_smart_hosting);
        civ_smart_hosting = getActivity().findViewById(R.id.civ_smart_hosting);
        iv_smart_hosting = getActivity().findViewById(R.id.iv_smart_hosting);
        cv_smart_hosting.setOnClickListener(this);
        civ_smart_hosting.setOnClickListener(this);
        iv_smart_hosting.setOnClickListener(this);

        //大雨
        iv_heavy_rain =getActivity().findViewById(R.id.iv_heavy_rain);
        cv_heavy_rain =getActivity().findViewById(R.id.cv_heavy_rain);
        civ_heavy_rain =getActivity().findViewById(R.id.civ_heavy_rain);
        iv_heavy_rain.setOnClickListener(this);
        cv_heavy_rain.setOnClickListener(this);
        civ_heavy_rain.setOnClickListener(this);

        //气温过高
        iv_high_temperature =getActivity().findViewById(R.id.iv_high_temperature);
        cvi_high_temperature =getActivity().findViewById(R.id.civ_high_temperature);
        cv_high_temperature =getActivity().findViewById(R.id.cv_high_temperature);
        iv_high_temperature.setOnClickListener(this);
        cvi_high_temperature.setOnClickListener(this);
        cv_high_temperature.setOnClickListener(this);

        //大雾
        iv_heavy_fog =getActivity().findViewById(R.id.iv_heavy_fog);
        civ_heavy_fog =getActivity().findViewById(R.id.civ_heavy_fog);
        cv_heavy_fog =getActivity().findViewById(R.id.cv_heavy_fog);
        iv_heavy_fog.setOnClickListener(this);
        civ_heavy_fog.setOnClickListener(this);
        cv_heavy_fog.setOnClickListener(this);

        //气温过低
        iv_low_temperature =getActivity().findViewById(R.id.iv_low_temperature);
        cv_low_temperature =getActivity().findViewById(R.id.cv_low_temperature);
        civ__low_temperature =getActivity().findViewById(R.id.civ_low_temperature);
        iv_low_temperature.setOnClickListener(this);
        cv_low_temperature.setOnClickListener(this);
        civ__low_temperature.setOnClickListener(this);

        //水位过低
        iv_low_water_level  =getActivity().findViewById(R.id.iv_low_water_level);
        cv_low_water_level  =getActivity().findViewById(R.id.cv_low_water_level);
        civ_low_water_level  =getActivity().findViewById(R.id.civ_low_water_level);
        iv_low_water_level.setOnClickListener(this);
        cv_low_water_level.setOnClickListener(this);
        civ_low_water_level.setOnClickListener(this);


        /**
         * 快捷键
         */
        cv_home_headlight = getActivity().findViewById(R.id.cv_home_headlight);//大灯
        cv_home_fast_fan = getActivity().findViewById(R.id.cv_home_fast_fan);//排风扇
        cv_home_quick_sluice = getActivity().findViewById(R.id.cv_home_quick_sluice);//水闸
        cv_home_quick_temperature = getActivity().findViewById(R.id.cv_home_quick_temperature);//温湿度

        cv_home_headlight.setOnClickListener(this);
        cv_home_fast_fan.setOnClickListener(this);
        cv_home_quick_sluice.setOnClickListener(this);
        cv_home_quick_temperature.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
//            case R.id.iv_notice:
//                Intent intent=new Intent(getActivity(), OtherActivity.class);
//                intent.putExtra("fragmentName","NotificationCenter");
//                getActivity().startActivity(intent);
//                break;

            case R.id.iv_smart_hosting://智能托管
                //开
                if (flag1 == true)
                {
                    civ_smart_hosting.setImageResource(R.color.theme_green);
                    iv_smart_hosting.setImageResource(R.mipmap.img_scene_open);
                    flag1 = false;
                }else if (flag1 == false)
                {
                    civ_smart_hosting.setImageResource(R.color.second_theme);
                    iv_smart_hosting.setImageResource(R.mipmap.img_scene_off);
                    flag1 = true;
                }
                break;

            case R.id.iv_heavy_rain://大雨
                //开
                if (flag2 == true)
                {
                    civ_heavy_rain.setImageResource(R.color.theme_green);
                    iv_heavy_rain.setImageResource(R.mipmap.img_scene_open);
                    flag2 = false;
                }else if (flag2 == false)
                {
                    civ_heavy_rain.setImageResource(R.color.second_theme);
                    iv_heavy_rain.setImageResource(R.mipmap.img_scene_off);
                    flag2 = true;
                }
                break;

            case R.id.iv_high_temperature://气温过高
                //开
                if (flag3 == true)
                {
                    cvi_high_temperature.setImageResource(R.color.theme_green);
                    iv_high_temperature.setImageResource(R.mipmap.img_scene_open);
                    flag3 = false;
                }else if (flag3 == false)
                {
                    cvi_high_temperature.setImageResource(R.color.second_theme);
                    iv_high_temperature.setImageResource(R.mipmap.img_scene_off);
                    flag3 = true;
                }
                break;

            case R.id.iv_heavy_fog://大雾
                //开
                if (flag4 == true)
                {
                    civ_heavy_fog.setImageResource(R.color.theme_green);
                    iv_heavy_fog.setImageResource(R.mipmap.img_scene_open);
                    flag4 = false;
                }else if (flag4 == false)
                {
                    civ_heavy_fog.setImageResource(R.color.second_theme);
                    iv_heavy_fog.setImageResource(R.mipmap.img_scene_off);
                    flag4 = true;
                }
                break;

            case R.id.iv_low_temperature://气温过低
                //开
                if (flag5 == true)
                {
                    civ__low_temperature.setImageResource(R.color.theme_green);
                    iv_low_temperature.setImageResource(R.mipmap.img_scene_open);
                    flag5 = false;
                }else if (flag5 == false)
                {
                    civ__low_temperature.setImageResource(R.color.second_theme);
                    iv_low_temperature.setImageResource(R.mipmap.img_scene_off);
                    flag5 = true;
                }
                break;

            case R.id.iv_low_water_level://水位过低
                //开
                if (flag6 == true)
                {
                    civ_low_water_level.setImageResource(R.color.theme_green);
                    iv_low_water_level.setImageResource(R.mipmap.img_scene_open);
                    flag6 = false;
                }else if (flag6 == false)
                {
                    civ_low_water_level.setImageResource(R.color.second_theme);
                    iv_low_water_level.setImageResource(R.mipmap.img_scene_off);
                    flag6 = true;
                }
                break;


            case R.id.cv_home_headlight: //大灯

                break;
            case R.id.cv_home_fast_fan: //排风扇
                break;
            case R.id.cv_home_quick_sluice: //水闸
                break;
            case R.id.cv_home_quick_temperature : //温湿度
                break;

        }
    }


}