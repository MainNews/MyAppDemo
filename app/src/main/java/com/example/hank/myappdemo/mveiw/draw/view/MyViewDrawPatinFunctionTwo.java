package com.example.hank.myappdemo.mveiw.draw.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Jun on 2017/7/3.
 * 该自定义控件主要是显示Paint的方法功能体现,离散路径效果
 */

public class MyViewDrawPatinFunctionTwo extends View {
    public MyViewDrawPatinFunctionTwo(Context context) {
        super(context);
    }

    public MyViewDrawPatinFunctionTwo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyViewDrawPatinFunctionTwo(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint mPaint = getPaint();
        Path mPath = getPath();

        //第一条原生Path
        canvas.drawPath(mPath,mPaint);

        //第二条Path
        canvas.translate(0,200);//移动动画布起始点
        /*
            DiscretePathEffect(参数一，参数二)
                参数一：表示将原来的路径切成多长的线段
                参数二：表示被切成小线段的可偏移量
         */
        mPaint.setPathEffect(new DiscretePathEffect(2,5));
        canvas.drawPath(mPath,mPaint);

        //第三条Path
        canvas.translate(0,200);
        mPaint.setPathEffect(new DiscretePathEffect(6,5));
        canvas.drawPath(mPath,mPaint);

        //第四条Path
        canvas.translate(0,200);
        mPaint.setPathEffect(new DiscretePathEffect(6,15));
        canvas.drawPath(mPath,mPaint);

    }

    public Paint getPaint() {
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        return paint;
    }

    public Path getPath() {
        Path path = new Path();
        //自定义起始路径
        path.moveTo(0,0);
        //定义路径的各个点
        for (int i = 0 ; i <= 40 ; i++){
            path.lineTo(i*35, (float) (Math.random() * 150));
        }
        return path;
    }
}
