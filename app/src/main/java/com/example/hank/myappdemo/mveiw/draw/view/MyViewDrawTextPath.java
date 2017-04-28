package com.example.hank.myappdemo.mveiw.draw.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by Jun on 2017/4/28.
 * 该类用于画出文字路径
 */

public class MyViewDrawTextPath extends View {
    public MyViewDrawTextPath(Context context) {
        super(context);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.RED);//设置画笔颜色
        paint.setStrokeWidth(5);//设置画笔宽度
        paint.setAntiAlias(true);//设置画笔是否抗锯齿功能，如果使用，会使绘图速度变慢
        paint.setTextSize(50);//设置文字大小
        //样式设置
        paint.setFakeBoldText(true);//是否为粗体
        paint.setUnderlineText(true);//设置下划线
        paint.setStrikeThruText(true);//是否带有删除效果
        //绘图样式，填充
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText("欢迎使用Paint完成文字绘画",10,100,paint);

        //设置字体水平倾斜度，普通倾斜字体是-0.25,可见往右倾斜
        paint.setTextSkewX((float)-0.25);
        //设置水平拉伸两倍,只会水平拉伸，高度不变
        paint.setTextScaleX(2);
        //绘图样式，描边
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawText("欢迎使用Paint完成文字绘画",10,200,paint);

        //设置字体水平倾斜度，设置0.25,可见往左倾斜
        paint.setTextSkewX((float) 0.25);
        //还原水平拉伸
        paint.setTextScaleX(1);
        //绘图样式，填充且描边
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawText("欢迎使用Paint完成文字绘画",10,300,paint);

    }
}
/*

//普通设置
    paint.setStrokeWidth (5);//设置画笔宽度
    paint.setAntiAlias(true); //指定是否使用抗锯齿功能，如果使用，会使绘图速度变慢
    paint.setStyle(Paint.Style.FILL);//绘图样式，对于设文字和几何图形都有效
    paint.setTextAlign(Align.CENTER);//设置文字对齐方式，取值：align.CENTER、align.LEFT或align.RIGHT
    paint.setTextSize(12);//设置文字大小

//样式设置
    paint.setFakeBoldText(true);//设置是否为粗体文字
    paint.setUnderlineText(true);//设置下划线
    paint.setTextSkewX((float) -0.25);//设置字体水平倾斜度，普通斜体字是-0.25
    paint.setStrikeThruText(true);//设置带有删除线效果

//其它设置
    paint.setTextScaleX(2);//只会将水平方向拉伸，高度不会变

 */
