package com.example.hank.myappdemo.mveiw.draw.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by Jun on 2017/6/28.
 * 该类为一个自定义的View，用于绘制出水波纹效果
 */

public class MyViewDrawWave extends View {

    private Paint mPaint;
    private Path mPath;
    private int mItemWaveLength = 400;
    /** 保存当前动画波长值 */
    private int dx = 0;
    public MyViewDrawWave(Context context) {
        super(context);
    }

    public MyViewDrawWave(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        int originY = 300;
        int halfWaveLen = mItemWaveLength/2;
        mPath.moveTo(-mItemWaveLength + dx,originY);
        /*
            画出适合屏幕的波纹
         */
        for (int i = -mItemWaveLength; i <= getWidth() + mItemWaveLength; i += mItemWaveLength){
            mPath.rQuadTo(halfWaveLen/2,-100,halfWaveLen,0);
            mPath.rQuadTo(halfWaveLen/2,100,halfWaveLen,0);
        }
        /*
            将路径区域闭合
         */
        mPath.lineTo(getWidth(),getHeight());
        mPath.lineTo(0,getHeight());
        mPath.close();

        canvas.drawPath(mPath,mPaint);

       /* Paint paint = new Paint();
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.GREEN);

        Path path = new Path();
        path.moveTo(100,300);*/
        /*
            参数一：控制点X坐标，表示相对上一个终点X坐标的位移坐标，正值相加，负值相减
            参数二：控制点Y坐标，表示相对上一个终点Y坐标的位移坐标，正值相加，负值相减
            参数三：终点X坐标，表示相对上一个终点X坐标的位移坐标，正值相加，负值相减
            参数四：终点Y坐标，表示相对上一个终点Y坐标的位移坐标，正值相加，负值相减
         */
       /* path.rQuadTo(100,-100,200,0);
        path.rQuadTo(100,100,200,0);
        canvas.drawPath(path,paint);*/

    }

    private ValueAnimator animator;
    /**
     * 创建一个动画，使水波纹无限动起来
     */
    public void startAnim(){
        animator = ValueAnimator.ofInt(0,mItemWaveLength);
        animator.setDuration(1000);
        animator.setRepeatCount(ValueAnimator.INFINITE);//循环次数
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                dx = (int) animator.getAnimatedValue();
                postInvalidate();
            }
        });
        animator.start();
    }
    public void stopAnim(){
        animator.cancel();
    }
}
