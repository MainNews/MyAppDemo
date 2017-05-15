package com.example.hank.myappdemo.mveiw.draw.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

/**
 * Created by Jun on 2017/5/15.
 * 该类用于画出canvas裁剪之后的效果，与如何保存画布与恢复画布
 */

public class MyViewDrawCanvasGoTo extends View {
    public MyViewDrawCanvasGoTo(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.RED);//画出原来的画布大小
        /*
            在操作画布将变成操作后的样子，即不会返回原来的样子，但Google提供了:
                save()保存，restore()恢复，这两个方法。
                    Save（）：每次调用Save（）函数，都会把当前的画布的状态进行保存，然后放入特定的栈中；
                    restore（）：每当调用Restore（）函数，就会把栈中最顶层的画布状态取出来，
                            并按照这个状态恢复当前的画布，并在这个画布上做画
         */
        canvas.save();//先将画布保存一个原来的样子
         /*
            裁剪画布后的效果(clip)
            裁剪画布是利用Clip系列函数，通过与Rect、Path、Region取交、并、差等集合运算来获得最新的画布形状。除了调用Save、Restore函数以外，这个操作是不可逆的，一但Canvas画布被裁剪，就不能再被恢复！
              Clip系列函数如下：
                    boolean	clipPath(Path path)
                    boolean	clipPath(Path path, Region.Op op)
                    boolean	clipRect(Rect rect, Region.Op op)
                    boolean	clipRect(RectF rect, Region.Op op)
                    boolean	clipRect(int left, int top, int right, int bottom)
                    boolean	clipRect(float left, float top, float right, float bottom)
                    boolean	clipRect(RectF rect)
                    boolean	clipRect(float left, float top, float right, float bottom, Region.Op op)
                    boolean	clipRect(Rect rect)
                    boolean	clipRegion(Region region)
                    boolean	clipRegion(Region region, Region.Op op)
             */
        canvas.clipRect(new Rect(100,100,800,800));//取得画布的大小
        canvas.drawColor(Color.GREEN);//在裁剪后，画布的大小
        canvas.save();//在保存裁剪后的样子，在后面保存多个

        canvas.clipRect(new Rect(200,200,700,700));//取得画布的大小
        canvas.drawColor(Color.BLUE);//在裁剪后，画布的大小
        canvas.save();//在保存裁剪后的样子，在后面保存多个

        canvas.clipRect(new Rect(300,300,600,600));//取得画布的大小
        canvas.drawColor(Color.BLACK);//在裁剪后，画布的大小
        canvas.save();//在保存裁剪后的样子，在后面保存多个

        canvas.clipRect(new Rect(400,400,500,500));//取得画布的大小
        canvas.drawColor(Color.WHITE);//在裁剪后，画布的大小
    }
    /**
     * 创建画笔
     * @param color 画笔颜色
     * @param style 画笔填充样式
     * @param width 画笔宽度大小
     * @return 画笔对象
     */
    private Paint getPaint(int color,Paint.Style style,int width){
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStyle(style);
        paint.setStrokeWidth(width);
        return paint;
    }
}

