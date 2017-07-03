package com.example.hank.myappdemo.mveiw.draw.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Jun on 2017/7/3.
 * 使用Paint的函数完成印章效果 ，即使用图形做为路径
 */

public class MyViewDrawPaintFunctionThree extends View {
    private int dx = 0;
    public MyViewDrawPaintFunctionThree(Context context) {
        super(context);
    }

    public MyViewDrawPaintFunctionThree(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewDrawPaintFunctionThree(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = getPaint();

        //画出原始路径
        Path path = getPath();
        canvas.drawPath(path, paint);

        //使用印章路径效果
        canvas.translate(0, 200);
        paint.setPathEffect(new PathDashPathEffect(getStampPath(), 35, dx,
                PathDashPathEffect.Style.MORPH));
        canvas.drawPath(path,paint);

        canvas.translate(0, 200);
        paint.setPathEffect(new PathDashPathEffect(getStampPath(), 35, dx,
                PathDashPathEffect.Style.TRANSLATE));
        canvas.drawPath(path,paint);

        canvas.translate(0, 200);
        paint.setPathEffect(new PathDashPathEffect(getStampPath(), 35, dx,
                PathDashPathEffect.Style.ROTATE));
        canvas.drawPath(path,paint);

        dx++;
        invalidate();


    }
    /**
     * 原始路径
     */
    @NonNull
    private Path getPath() {
        Path path = new Path();
        path.moveTo(100, 200);
        path.lineTo(300, 50);
        path.lineTo(500, 200);
        return path;
    }

    /**
     *     构建印章路径,印章路径是用来构造画笔的
     */
    @NonNull
    private Path getStampPath() {
        Path stampPath = new Path();
        stampPath.moveTo(0, 20);
        stampPath.lineTo(10, 0);
        stampPath.lineTo(20, 20);
        stampPath.close();
        stampPath.addCircle(0, 0, 3, Path.Direction.CCW);
        return stampPath;
    }

    private Paint getPaint() {
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.FILL);
        return paint;
    }
}
/*
使用PathDashPathEffect将Path图形来填充当前路径
     参数一：Path图形
     参数二：每个图形间的间距
     参数三：偏移量,根据该值的变化，也是可以实现动画效果的
     参数四：分为三种：
             MORPH   :图形会以发生拉伸或压缩等变形的情况与下一段相连接
             ROTATE  :线段连接处的图形转换以旋转到与下一段移动方向相一致的角度进行转转
             TRANSLATE:图形会以位置平移的方式与下一段相连接
             使用方法：
 mPaint.setPathEffect(new PathDashPathEffect(getStampPath(),35,dx, PathDashPathEffect.Style.MORPH));
         */
