package com.example.hank.myappdemo.mveiw.draw.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

/**
 * Created by Jun on 2017/4/27.
 * 该控件用于展示自定义的路径与文字
 */

public class MyViewDrawPath extends View{

    public MyViewDrawPath(Context context) {
        super(context);
    }
    /**
     * 重写OnDraw()函数，在每次重绘时自主实现绘图
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.RED);//设置画笔的颜色
        paint.setStyle(Paint.Style.STROKE);//设置样式为描边
        paint.setStrokeWidth(10);//设置画笔的宽度

        Path path = new Path();
        path.moveTo(10,10);//设置起始点
        path.lineTo(10,100);//第一条线的终点，也就是第二条线的起点
        path.lineTo(100,50);//第二条线的终点，也就是第三条线的起点
        path.lineTo(20,30);//第三条线的终点....
        path.close();//闭环


        canvas.drawPath(path,paint);//画路径
    }
}
/*
    直线路径:
        void moveTo (float x1, float y1):直线的开始点；即将直线路径的绘制点定在（x1,y1）的位置；
        void lineTo (float x2, float y2)：直线的结束点，又是下一次绘制直线路径的开始点；lineTo（）可以一直用；
        void close ():如果连续画了几条直线，但没有形成闭环，调用Close()会将路径首尾点连接起来，形成闭环；
 */
