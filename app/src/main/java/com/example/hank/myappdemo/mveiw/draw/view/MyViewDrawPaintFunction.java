package com.example.hank.myappdemo.mveiw.draw.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by Jun on 2017/7/2.
 * 该自定义控件主要是显示Paint的方法功能体现
 *  线段路径效果
 */

public class MyViewDrawPaintFunction extends View {
    private Paint mPaint;
    private Path mPathM;
    private Path mPathR;
    private Path mPathB;
    private Path mPath;
    private Path mPathLine;
    private int dx;

    public MyViewDrawPaintFunction(Context context) {
        super(context);
        initObject();
    }

    public MyViewDrawPaintFunction(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initObject();
    }

    public MyViewDrawPaintFunction(Context context, AttributeSet attrs) {
        super(context, attrs);
        initObject();
    }

    /**
     * 初始对象，由于该控件主要是在onDraw()方法中执行，而该方法会被多次调用，所以不适合创建对象
     */
    private void initObject() {
        mPaint = new Paint();
        mPathM = new Path();
        mPathR = new Path();
        mPathB = new Path();
        mPath = new Path();
        mPathLine = new Path();
    }

    /**
     * 设置画笔属性
     */
    public void setPaint(int size){
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(size);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setPaint(40);
        //设置画笔是否抗锯齿
        /*
            设置线冒样式有以下几种取值：
                Cap.ROUND  圆形线冒
                Cap.SQUARE 方形线冒
                Cap.BUTT   无线冒
         */
        mPaint.setStrokeCap(Paint.Cap.BUTT);//无线冒
        canvas.drawLine(100, 100, 200, 100, mPaint);

        mPaint.setStrokeCap(Paint.Cap.SQUARE);//方形线冒
        canvas.drawLine(100, 200, 200, 200, mPaint);

        mPaint.setStrokeCap(Paint.Cap.ROUND);//圆形线冒
        canvas.drawLine(100, 300, 200, 300, mPaint);


        /*
            画出两线结合处，取值的区别,取值如下：
                Join.MITER(结合处为锐角)
                Join.ROUND(结合处为圆角)
                Join.BEVEL(结合处为直角)
         */
        mPaint.reset();
        setPaint(40);
        mPathM.moveTo(250, 50);
        mPathM.lineTo(550,50);
        mPathM.lineTo(550,100);
        mPaint.setStrokeJoin(Paint.Join.MITER);//锐角
        canvas.drawPath(mPathM,mPaint);

        mPathB.moveTo(250,150);
        mPathB.lineTo(550,150);
        mPathB.lineTo(550,200);
        mPaint.setStrokeJoin(Paint.Join.BEVEL);//直角
        canvas.drawPath(mPathB,mPaint);

        mPathR.moveTo(250,250);
        mPathR.lineTo(550,250);
        mPathR.lineTo(550,300);
        mPaint.setStrokeJoin(Paint.Join.ROUND);//圆角
        canvas.drawPath(mPathR,mPaint);

        //垂直画一条直线，查看三面上条带有线冒的区别
        mPaint.reset();//重置画笔
        mPaint.setStrokeWidth(2);
        mPaint.setColor(Color.RED);
        canvas.drawLine(100, 50, 100, 350, mPaint);

        /*
            设置路径样式,取值类型是所有派生自PathEffect的子类
            setPathEffect(PathEffect effect)
         */
        /*
            圆形拐角，下面在同一个位置画出三个不同拐角的效果
            该拐角通过CornerPathEffect(float radius)对角设置半径
         */
        mPaint.setStyle(Paint.Style.STROKE);
        mPath.moveTo(50,750);
        mPath.lineTo(200,400);
        mPath.lineTo(350,750);
        canvas.drawPath(mPath,mPaint);//无圆形拐角

        mPaint.setColor(Color.GREEN);
        mPaint.setPathEffect(new CornerPathEffect(50));//50半径值的圆角
        canvas.drawPath(mPath,mPaint);

        mPaint.setColor(Color.YELLOW);
        mPaint.setPathEffect(new CornerPathEffect(100));//100半径值的圆角
        canvas.drawPath(mPath,mPaint);

        /*
            虚线线断
                由DashPathEffect(float intervals[] ,float phase)
                intervals[] 表示实线与空距的长度，循环实现虚线的构成
                phase 表示开始绘制的偏移值
         */
        mPaint.reset();
        mPaint.setColor(Color.YELLOW);
        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.STROKE);
        mPathLine.moveTo(400,750);
        mPathLine.lineTo(550,400);
        mPathLine.lineTo(700,750);
        canvas.drawPath(mPathLine,mPaint);//无线段

        //有线段，但没有偏移值
        mPaint.setPathEffect(new DashPathEffect(new float[]{20,10,50,50},0));
        canvas.translate(0,50);//将当前画布向下移用50个px，后面的都以这个点为起点
        mPaint.setColor(Color.RED);
        canvas.drawPath(mPathLine,mPaint);

        //画同一条线，偏移值为10
        mPaint.setPathEffect(new DashPathEffect(new float[]{20,10,100,100},dx));
        mPaint.setColor(Color.GREEN);
        canvas.translate(0,100);
        canvas.drawPath(mPathLine,mPaint);
        invalidate();

    }

    /**
     * 使用上面的PathEffect做为路径，使其动起来，在关闭当前界面，停业动画
     * @return 动画对象
     */
    public ValueAnimator startAnim(){

        ValueAnimator valueAnimator = ValueAnimator.ofInt(0,230);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setDuration(500);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                dx = (int) animator.getAnimatedValue();
                postInvalidate();
            }
        });
        valueAnimator.start();
        return valueAnimator;

    }
}
