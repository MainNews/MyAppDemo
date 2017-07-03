package com.example.hank.myappdemo.mveiw.draw.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.SumPathEffect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Jun on 2017/7/3.
 * 该控件用于展示使用Paint函数合并特效
 * 分别为：ComposePathEffect与SumPathEffect
 *  ComposePathEffect(PathEffect outerpe,PathEffect innerpe):
 *          该对象合并效果是有先后顺序的，它会先将第二参数innerpe的特效作用于路径上，然后再在此加了特效的路径上作用第一个特效
 *  SumPathEffect(PathEffect outerpe,PathEffect innerpe)
 *          该对象是分别对原始路径作用第一个和第二个特效，然后将两条路径合并，做为最终效果显示
 *
 */

public class MyViewDrawPaintFunctionMerge extends View {
    public MyViewDrawPaintFunctionMerge(Context context) {
        super(context);
    }

    public MyViewDrawPaintFunctionMerge(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyViewDrawPaintFunctionMerge(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = getPaint();
        Path path = getPath();
        canvas.drawPath(path,paint);//原始路径

        //仅应用圆形特效的路径
        canvas.translate(0,200);
        CornerPathEffect cornerPathEffect = new CornerPathEffect(50);
        paint.setPathEffect(cornerPathEffect);
        canvas.drawPath(path,paint);

        //仅应用虚线特效的路径
        canvas.translate(0,200);
        DashPathEffect dashPathEffect = new DashPathEffect(new float[]{2,5,10,10},0);
        paint.setPathEffect(dashPathEffect);
        canvas.drawPath(path,paint);

        //利用ComposePathEffect先应用圆角特效，再应用虚线特效
        canvas.translate(0,200);
        ComposePathEffect composePathEffect = new ComposePathEffect(dashPathEffect,cornerPathEffect);
        paint.setPathEffect(composePathEffect);
        canvas.drawPath(path,paint);

        //利用SumPathEffect，分别将圆角特效应用于原始路径，然后将生成的两条特效路径合并
        canvas.translate(0,200);
        SumPathEffect sumPathEffect = new SumPathEffect(cornerPathEffect,dashPathEffect);
        paint.setPathEffect(sumPathEffect);
        canvas.drawPath(path,paint);


    }

    private Paint getPaint(){
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
        return paint;
    }
    private Path getPath(){
        Path path = new Path();
        path.moveTo(0,0);
        for (int i = 0; i <= 40;i++){
            path.lineTo(i * 35 , (float) (Math.random()*150));
        }
        return path;
    }
}
