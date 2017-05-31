package com.example.hank.myappdemo.mveiw.draw.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Jun on 2017/5/31.
 * 绘制贝赛尔曲线
 */

public class MyViewDrawBezier extends View {
    public MyViewDrawBezier(Context context) {
        super(context);
    }

    public MyViewDrawBezier(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(10);

        Path path = new Path();
        path.moveTo(100,300);//指定起始点
        path.quadTo(200,200,300,300);//指定控制点与终点
        path.quadTo(400,400,500,300);//连着调用，将以上面的终点为起点

        canvas.drawPath(path,paint);



    }
}
