package com.example.hank.myappdemo.mveiw.draw.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

/**
 * Created by Jun on 2017/5/15.
 * 该类用于对Canvas的变换与操作，即对Canvas画布进行深入了解
 */

public class MyViewDrawCanvasGo  extends View{

    public MyViewDrawCanvasGo(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            /*
                创建画笔 Paint
             */
            Paint paint_green = getPaint(Color.GREEN, Paint.Style.FILL,5);

            Rect rect = new Rect(0,0,150,150);
            canvas.drawRect(rect,paint_green);

            /*
                画布平移后的效果(Translate)
                float dx：水平方向平移的距离，正数指向正方向（向右）平移的量，负数为向负方向（向左）平移的量
                flaot dy：垂直方向平移的距离，正数指向正方向（向下）平移的量，负数为向负方向（向上）平移的量
             */
            Paint paint_red = getPaint(Color.RED, Paint.Style.STROKE,5);
            canvas.translate(100,100);
            canvas.drawRect(rect,paint_red);

            /*
                画布旋转后的效果(Rotate)
                void rotate(float degrees)
                void rotate (float degrees, float px, float py)
                第一个构造函数直接输入旋转的度数，正数是顺时针旋转，负数指逆时针旋转，它的旋转中心点是原点（0，0）
                第二个构造函数除了度数以外，还可以指定旋转的中心点坐标（px,py）
             */

            Rect rectRotate = new Rect(300,0,500,150);
            canvas.rotate(30);//顺时针旋转画布，度数
            canvas.drawRect(rectRotate,paint_red);//画出旋转后的矩形

            /*
                缩放画布后的效果(scale)
                public void scale (float sx, float sy)
                public final void scale (float sx, float sy, float px, float py)
                其实我也没弄懂第二个构造函数是怎么使用的，我就先讲讲第一个构造函数的参数吧
                float sx:水平方向伸缩的比例，假设原坐标轴的比例为n,不变时为1，在变更的X轴密度为n*sx;所以，sx为小数为缩小，sx为整数为放大
                float sy:垂直方向伸缩的比例，同样，小数为缩小，整数为放大
            注意：这里有X、Y轴的密度的改变，显示到图形上就会正好相同，比如X轴缩小，那么显示的图形也会缩小。一样的。
             */
            Rect rectScale = new Rect(0,200,150,400);
            canvas.rotate(330);//因为上面将画布旋转，这里改回来，旋转一圈
            canvas.drawRect(rectScale,paint_green);

            canvas.scale(0.5f,1);//进行缩放
            canvas.drawRect(rectScale,paint_red);

            /*
                扭曲画布后的效果(skew)
                    void skew (float sx, float sy)
                参数说明：
                    float sx:将画布在x方向上倾斜相应的角度，sx倾斜角度的tan值，
                    float sy:将画布在y轴方向上倾斜相应的角度，sy为倾斜角度的tan值，
                注意，这里全是倾斜角度的tan值哦，比如我们打算在X轴方向上倾斜60度，tan60=根号3，小数对应1.732
             */
            Rect rectSkew = new Rect(0,450,200,550);
            canvas.drawRect(rectSkew,paint_green);

            canvas.skew(1.732f,0);//画布进行扭曲
            canvas.drawRect(rectSkew,paint_red);

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
