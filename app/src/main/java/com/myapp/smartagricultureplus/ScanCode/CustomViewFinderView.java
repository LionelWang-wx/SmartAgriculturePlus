package com.myapp.smartagricultureplus.ScanCode;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;

import com.journeyapps.barcodescanner.ViewfinderView;
import com.myapp.smartagricultureplus.R;
import com.uuzuche.lib_zxing.view.ViewfinderResultPointCallback;

public class CustomViewFinderView extends ViewfinderView {
   private int screenWidth = 0;
   private int screenHeight = 0;
   private RectF buttonRect;
   private String text_btn = "个性化需求";
    /**
     * 重绘时间间隔
     */
    private static long CUSTOME_ANIMATION_DELAY = 16;

    /**
     * 边角线颜色
     */
   private int mLineColor = getContext().getResources().getColor(R.color.blue);

    /**
     * 线性梯度各个位置对应的颜色
     */
    private Rect nowScanRect;
    /**
     * 边角线厚度 (建议使用dp)
     */
    private float mLineDepth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,2F,getResources().getDisplayMetrics());

    /**
     * 边角线长度 扫描边框长度 的占比 ps:比例越大，线越长
     */
    private  float mLineRate = 0.05f;

    /**
     * 扫描线的起始位置
     */
    private  int mScanLinePosition = 0;

    /**
     *扫描线每次重绘的距离
     */
    private float mScanLineDy = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,3F,getResources().getDisplayMetrics());

    /**
     * 线性梯度
     */
    private LinearGradient mlinearGradient;

    /**
     * 线性梯度位置
     */
    private  float[] mPositions = {0f,0.5f,1f};

    /**
     * 扫描线的厚度
     */

    private  float mScanLineDepth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,1F,getResources().getDisplayMetrics());

    /**
     * 扫描渐变色
     */
    private int laserColor_center = getContext().getResources().getColor(R.color.blue);

    private int laserColor_light = getContext().getResources().getColor(R.color.blue);

    private int[] mScanLineColor = {laserColor_light,laserColor_center,laserColor_light};


    private float mDist = 0;


    public CustomViewFinderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData();
    }

    private void initData()
    {
        WindowManager windowManager = ((Activity)getContext()).getWindowManager();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getRealMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;

        int W = screenWidth *3/5;
        int marginL = screenWidth / 5;
        int marginT = screenHeight / 4;

        nowScanRect = new Rect(marginL,marginT,W+marginL,W+marginT);
    }

    @SuppressLint("DrawAllocation")
    @Override
    public void onDraw(Canvas canvas) {
        refreshSizes();
        if (framingRect == null || previewFramingRect == null)
        {
            return;
        }
        Rect frame = nowScanRect;
        Rect previewFrame = previewFramingRect;
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        if (resultBitmap != null)
        {
            paint.setColor(resultColor);
        }else
        {
            paint.setColor(maskColor);
        }

        canvas.drawRect(0f,0f,width,frame.top,paint);
        canvas.drawRect(0f,frame.top,frame.left,(frame.bottom+1),paint);
        canvas.drawRect((frame.right+1),frame.top,width,(frame.bottom + 1),paint);
        canvas.drawRect(0f,(frame.bottom),width,height,paint);

        //绘制边角
        paint.setColor(mLineColor);

        //定义画笔颜色
        //左上-横线
        canvas.drawRect(frame.left - mLineDepth,
                frame.top - mLineDepth,
                frame.left + frame.width() * mLineRate,
                frame.top, paint);
        //左上-纵线
        canvas.drawRect(frame.left - mLineDepth, frame.top, frame.left, frame.top + frame.height() * mLineRate, paint);
        //右上-横线
        canvas.drawRect(frame.right - frame.width() * mLineRate, frame.top - mLineDepth, frame.right + mLineDepth, frame.top, paint);
        //右上-纵线
        canvas.drawRect(frame.right, frame.top - mLineDepth, frame.right + mLineDepth, frame.top + frame.height() * mLineRate, paint);
        //左下-横线
        canvas.drawRect(frame.left - mLineDepth, frame.bottom, frame.left + frame.width() * mLineRate, frame.bottom + mLineDepth, paint);
        //左下-纵线
        canvas.drawRect(frame.left - mLineDepth, frame.bottom - frame.height() * mLineRate, frame.left, frame.bottom, paint);
        //右下-横线
        canvas.drawRect(frame.right - frame.width() * mLineRate, frame.bottom, frame.right + mLineDepth, frame.bottom + mLineDepth, paint);
        //右下-纵线
        canvas.drawRect(frame.right, frame.bottom - frame.height() * mLineRate, frame.right + mLineDepth, frame.bottom + mLineDepth, paint);

        if (resultBitmap !=null)
        {
            paint.setAlpha(ViewfinderView.CURRENT_POINT_OPACITY);
            canvas.drawBitmap(resultBitmap,null,frame,paint);
        }else
        {
            //绘制扫描线
            mScanLinePosition += mScanLineDy;
            if (mScanLinePosition >frame.height())
            {
                mScanLinePosition = 0;
            }


            mlinearGradient = new LinearGradient(frame.left,(frame.top + mScanLinePosition),frame.right,(frame.top+mScanLinePosition),mScanLineColor,mPositions, Shader.TileMode.CLAMP);

            paint.setShader(mlinearGradient);

            canvas.drawRect(frame.left,(frame.top+mScanLinePosition),frame.right,frame.top+mScanLinePosition+mScanLineDepth,paint);

            paint.setShader(null);

        }
        super.onDraw(canvas);
    }
}

















