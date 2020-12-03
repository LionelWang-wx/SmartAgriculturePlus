package com.myapp.smartagricultureplus.Animation;


import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

import androidx.annotation.Nullable;
import androidx.core.view.animation.PathInterpolatorCompat;

import com.myapp.smartagricultureplus.R;


/**
 * 实现下拉刷新的绘制效果
 */
public class TouchPullView extends View {

    //定义画笔
    private Paint mCirclePaint;
    //设置圆的半径
    private float mCircleRadius = 50;
    //圆心的运算
    private float mCirclePointX ,mCirclePointY;
    //进度值
    private float mProgress;

    //可拖动的高度
    private  int mDragHeight = 300;

    //目标宽度
    private int mTargetWidth =400;

    //贝塞尔的路径和画笔
    private Path mPath = new Path();
    private Paint mpathPaint;

    //重心点最终高度，决定控制点的Y坐标
    private int mTargetGravityHeight = 10;

    //角度变换 0~135度
    private int mTangentAngle = 105;

    //进度差值器
    private Interpolator mProgressInterrpolator = new DecelerateInterpolator();
    //角度差值器
    private  Interpolator mTangentAngleInterrpolator;
    //添加释放操作
    private  ValueAnimator valueAnimator;

    private Drawable mContent = null;
    private int mContentMargin = 0;


    public TouchPullView(Context context) {
        super(context);
        init(null);
    }

    public TouchPullView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public TouchPullView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

//    public TouchPullView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context,attrs, defStyleAttr, defStyleRes);
//        init(attrs);
//    }



    /**
     * 初始化
     */
    private void init(AttributeSet attrs)
    {


         //得到用户设置参数
         final  Context context = getContext();
         TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TouchPullView,0,0);
         int color = array.getColor(R.styleable.TouchPullView_pColor,0x2000000);
         mCircleRadius  = array.getDimension(R.styleable.TouchPullView_pRadius,mCircleRadius);
         mDragHeight = array.getDimensionPixelOffset(R.styleable.TouchPullView_pDragHeight,mDragHeight);
         mTangentAngle = array.getInteger(R.styleable.TouchPullView_pTangentAngle,100);
         mTargetWidth = array.getDimensionPixelOffset(R.styleable.TouchPullView_pTargetWidth,mTargetWidth);
         mTargetGravityHeight = array.getDimensionPixelOffset(R.styleable.TouchPullView_pTargetGravityHeight,mTargetGravityHeight);
         mContent = array.getDrawable(R.styleable.TouchPullView_pContentDrawable);
         mContentMargin = array.getDimensionPixelOffset(R.styleable.TouchPullView_pContenDrawableMargin,0);


         //销毁
         array.recycle();

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //抗锯齿
        paint.setAntiAlias(true);
        //抗抖动
        paint.setDither(true);
        //设置为填充方式
        paint.setStyle(Paint.Style.FILL);
        //颜色
        paint.setColor(0xff08DCAB);
        //提交
        mCirclePaint = paint;



        //初始化路径部分画笔
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //抗锯齿
        paint.setAntiAlias(true);
        //抗抖动
        paint.setDither(true);
        //设置为填充方式
        paint.setStyle(Paint.Style.FILL);
        //颜色
        paint.setColor(0xff08DCAB);
        //提交
        mpathPaint = paint;

        //切角路径插值器
        mTangentAngleInterrpolator = PathInterpolatorCompat.create((mCircleRadius*2.0f)/mDragHeight,90.0f/mTangentAngle);

    }

    /**
     * 绘制圆
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //基础坐标系参数转换改变
        int count = canvas.save();
        float tranX = (getWidth() - getValueByLine(getWidth(),mTargetWidth,mProgress))/2;
        canvas.translate(tranX,0);


        //画贝塞尔曲线
        canvas.drawPath(mPath,mpathPaint);
        //圆
        canvas.drawCircle(
                mCirclePointX,
                mCirclePointY,
                mCircleRadius,
                mCirclePaint);

        Drawable drawable = mContent;
        if (drawable != null)
        {
            canvas.save();
            //截切矩形区域
            canvas.clipRect(drawable.getBounds());
            //绘制Drawable
            drawable.draw(canvas);
        }

        canvas.restoreToCount(count);
    }

    //当进行测量时触发
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
       // super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //宽度的类型 宽度的意图
        int widthMode  = MeasureSpec.getMode(widthMeasureSpec);
        int width      = MeasureSpec.getSize(widthMeasureSpec);
        //高度的类型 高度的意图
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int height     = MeasureSpec.getSize(heightMeasureSpec);

        //最小值
        int  iHeight = (int) ((mDragHeight*mProgress+0.5f)+
                getPaddingTop()+getPaddingBottom());

        int  iWidth = (int) (2*mCircleRadius+getPaddingLeft()+getPaddingRight());

        int measureWidth,measureHeight;

        //左右
        if(widthMode == MeasureSpec.EXACTLY)
        {
            //确切的值
            measureWidth = width;
        }else if (widthMode == MeasureSpec.AT_MOST)
        {
            //最多的值
            measureWidth = Math.min(iWidth,width);
        }else
        {
            //最小的值
            measureWidth = iWidth;
        }


        //上下
        if(heightMode == MeasureSpec.EXACTLY)
        {
            //确切的值
            measureHeight = height;
        }else if (heightMode == MeasureSpec.AT_MOST)
        {
            //最多的值
            measureHeight = Math.min(iHeight,height);
        }else
        {
            //最小的值
            measureHeight = iHeight;
        }

        //设置测量的高度
        setMeasuredDimension(measureWidth,measureHeight);
    }

    //当大小改变时触发
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //右移一位 相当于除以2
//        mCirclePointX = getWidth()>>1;
//        mCirclePointY = getHeight()>>1;

        /**
         * 当高度发生变换时路径更新
         */
        UpdataePathLayout();

    }

    /**
     * 设置进度
     * @param progress 进度
     */
    public void setProgress(float progress)
    {
        Log.d("TAG","p = "+progress);
        mProgress = progress;
        //刷新页面 请求重新测量
        requestLayout();
    }

    /**
     * 更新我们的路径等相关操作
     */
    private void UpdataePathLayout()
    {
        //获取进度
        final  float progress = mProgressInterrpolator.getInterpolation(mProgress);

        //获取可绘制区域高度宽度
        final float w = getValueByLine(getWidth(),mTargetWidth,mProgress);
        final float h = getValueByLine(0,mDragHeight,mProgress);
        //圆的圆点x对称轴的参数
        final float cPontx = w/2.0f;
        //圆的半径
        final  float cRadius = mCircleRadius;
        //圆的中心点Y坐标
        final float cPointy = h - cRadius;
        //控制的结束Y的值
        final float endControlY = mTargetGravityHeight;

        //更新圆的坐标
        mCirclePointX = cPontx;
        mCirclePointY = cPointy;
        //路径
        final  Path path = mPath;
        //复位操作
        path.reset();
        path.moveTo(0,0);


        //左边部分的结束点和控制点
        float lEndPointX,lEndPointY;
        float lControlPointX,lControlPointY;

        //获取当前切线的弧度
        float angle = mTangentAngle * mTangentAngleInterrpolator.getInterpolation(progress);
        double radian = Math.toRadians(angle);
        float X = (float) (Math.sin(radian)*cRadius);
        float Y = (float) (Math.cos(radian)*cRadius);

        //结束点
        lEndPointX = cPontx -X;
        lEndPointY = cPointy+Y;

        //控制点的Y轴变化
        lControlPointY = getValueByLine(0,endControlY,progress);
        //控制点与结束点的高度
        float tHeight = lEndPointY - lControlPointY;
        //控制点与X的坐标距离
        float tWidth = (float) (tHeight/Math.tan(radian));

        lControlPointX = lEndPointX - tWidth;
        path.quadTo(lControlPointX,lControlPointY,lEndPointX,lEndPointY);
        //连接到右边
        path.lineTo(cPontx+(cPontx-lEndPointX),lEndPointY);
        //画右边的贝塞尔曲线
        path.quadTo(cPontx+cPontx-lControlPointX,lControlPointY,w,0);

        //更新内容部分Drawable
        updateContentLayout(cPontx,cPointy,cRadius);

    }

    /**
     * 对内柔你部分进行测量并设置
     * @param cx  圆心X
     * @param cy  圆心Y
     * @param radius 半径
     */
    private void updateContentLayout(float cx,float cy,float radius)
    {
      Drawable drawable = mContent;
      if (drawable != null)
      {
          int margin = mContentMargin;
          int l = (int) (cx - radius + margin);
          int r = (int) (cx + radius - margin);
          int t = (int) (cy - radius + margin);
          int b = (int) (cy + radius - margin);
          drawable.setBounds(l,t,r,b);
      }
    }

    /**
     * 获取当前值
     * @param start    起始值
     * @param end      结束值
     * @param Progress 进度
     * @return         当前进度的值
     */
    private float getValueByLine(float start,float end,float Progress)
    {
        return start + (end - start)*Progress;
    }


    /**
     * 释放动画
     */
    public void release() {
        if (valueAnimator == null){
            valueAnimator = ValueAnimator.ofFloat(mProgress,0);
            valueAnimator.setDuration(400);
            valueAnimator.setInterpolator(new DecelerateInterpolator());
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    Object value = animation.getAnimatedValue();
                    if (value instanceof Float){
                        setProgress((Float) value);
                    }
                }
            });
        }else {
            valueAnimator.cancel();
            valueAnimator.setFloatValues(mProgress,0);
        }
        valueAnimator.start();

    }


}
