package com.example.hank.myappdemo.mveiw.draw.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
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

        //先创建两个大小一样的路径
        //第一个逆向生成
        Path mCCWRectpath = new Path();
        RectF mCCWRectF = new RectF(110,10,600,400);
        mCCWRectpath.addRect(mCCWRectF, Path.Direction.CCW);

        //第二个顺向生成
        Path mCWRectpath = new Path();
        RectF mCWRectF = new RectF(10,650,550,900);
        mCWRectpath.addRect(mCWRectF, Path.Direction.CW);

        //先画出两个路径
        canvas.drawPath(mCCWRectpath,paint);
        canvas.drawPath(mCWRectpath,paint);

        //依据路径写出文字
        String text = "风萧萧兮易水寒，壮士一去兮不复返";
        paint.setColor(Color.GRAY);
        paint.setTextSize(50);
        canvas.drawTextOnPath(text,mCCWRectpath,0,18,paint);//逆时针生成
        canvas.drawTextOnPath(text,mCWRectpath,0,18,paint);//顺时针生成
    }
}
/*
    直线路径:
        void moveTo (float x1, float y1):直线的开始点；即将直线路径的绘制点定在（x1,y1）的位置；
        void lineTo (float x2, float y2)：直线的结束点，又是下一次绘制直线路径的开始点；lineTo（）可以一直用；
        void close ():如果连续画了几条直线，但没有形成闭环，调用Close()会将路径首尾点连接起来，形成闭环；


    矩形路径
        void addRect (float left, float top, float right, float bottom, Path.Direction dir)
        void addRect (RectF rect, Path.Direction dir)

        这里Path类创建矩形路径的参数与上篇canvas绘制矩形差不多，唯一不同的一点是增加了Path.Direction参数；
        Path.Direction有两个值：
                Path.Direction.CCW：是counter-clockwise缩写，指创建逆时针方向的矩形路径；
                Path.Direction.CW：是clockwise的缩写，指创建顺时针方向的矩形路径；
 */
