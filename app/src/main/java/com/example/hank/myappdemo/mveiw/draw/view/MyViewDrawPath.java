package com.example.hank.myappdemo.mveiw.draw.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;

import com.example.hank.myappdemo.Utils.PhoneUtil;

/**
 * Created by Jun on 2017/4/27.
 * 该控件用于展示自定义的路径与文字
 */

public class MyViewDrawPath extends View {

    public MyViewDrawPath(Context context) {
        super(context);
    }

    /**
     * 重写OnDraw()函数，在每次重绘时自主实现绘图
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.RED);//设置画笔的颜色
        paint.setStyle(Paint.Style.STROKE);//设置样式为描边
        paint.setStrokeWidth(10);//设置画笔的宽度

        /*
                直线路径
         */
        Path path = new Path();
        path.moveTo(10, 10);//设置起始点
        path.lineTo(10, 100);//第一条线的终点，也就是第二条线的起点
        path.lineTo(100, 50);//第二条线的终点，也就是第三条线的起点
        path.lineTo(20, 30);//第三条线的终点....
        path.close();//闭环
        canvas.drawPath(path, paint);//画路径

        /*
                距形路径
         */
        //先创建两个大小一样的路径
        //第一个逆向生成
        Path mCCWRectpath = new Path();
        RectF mCCWRectF = new RectF(130,10,300,200);
        mCCWRectpath.addRect(mCCWRectF, Path.Direction.CCW);

        //第二个顺向生成
        Path mCWRectpath = new Path();
        RectF mCWRectF = new RectF(350, 10,550,200);
        mCWRectpath.addRect(mCWRectF, Path.Direction.CW);

        //先画出两个路径
        canvas.drawPath(mCCWRectpath, paint);
        canvas.drawPath(mCWRectpath, paint);

        //依据路径写出文字
        String text = "风萧萧兮易水寒，壮士一去兮不复返";
        paint.setColor(Color.GRAY);
        paint.setTextSize(50);
        canvas.drawTextOnPath(text, mCCWRectpath, 0, 18, paint);//逆时针生成
        canvas.drawTextOnPath(text, mCWRectpath, 0, 18, paint);//顺时针生成

        /*
                圆角距形
         */
        Path pathRoundCCW = new Path();
        RectF roundRectF1 = new RectF(10,280,200,500);
        //指定圆角的中心轴，统一 X 与 Y 轴椭圆的半径
        pathRoundCCW.addRoundRect(roundRectF1,10,15, Path.Direction.CCW);

        RectF roundRectF2 = new RectF(230,280,500,500);
        //通过数组的方式来指定半径，两两为一对，如10,10代表x1,y1(左上角)。。。。。
        float radiip[] = {10,10,20,20,30,30,40,40};
        pathRoundCCW.addRoundRect(roundRectF2,radiip, Path.Direction.CCW);

        canvas.drawPath(pathRoundCCW,paint);

        /*
              圆形路径
                参数一：X 轴圆心点
                参数二：Y 轴圆心点
                参数三：圆的半径
         */
        Path circlePath = new Path();
        circlePath.addCircle(110,610,100, Path.Direction.CCW);
        canvas.drawPath(circlePath,paint);

        /*
                椭圆路径
                    根据矩形来画出圆
         */
        Path ovalPath = new Path();
        RectF ovalRectF = new RectF(220,510,440,710);
        ovalPath.addOval(ovalRectF, Path.Direction.CCW);
        canvas.drawPath(ovalPath,paint);

        /*
                弧形
         */
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);//样式改为描边
        paint.setStrokeWidth(5);//设置画笔的宽度
        Path arcPath = new Path();
        RectF arcRectF = new RectF(460,510,660,710);
        /*
            RectF oval：弧是椭圆的一部分，这个参数就是生成椭圆所对应的矩形；
            float startAngle：开始的角度，X轴正方向为0度
            float sweepAngel：持续的度数；
         */
        path.addArc(arcRectF,0,80);
        canvas.drawPath(path,paint);




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


    圆角矩形路径
        void addRoundRect (RectF rect, float[] radii, Path.Direction dir)
        void addRoundRect (RectF rect, float rx, float ry, Path.Direction dir)

        这里有两个构造函数，部分参数说明如下：
            第一个构造函数：可以定制每个角的圆角大小：
                float[] radii：必须传入8个数值，分四组，分别对应每个角所使用的椭圆的横轴半径和纵轴半径，
                    如｛x1,y1,x2,y2,x3,y3,x4,y4｝，其中，x1,y1对应第一个角的（左上角）用来产生圆角的椭圆的横轴半径和纵轴半径，其它类推……
            第二个构造函数：只能构建统一圆角大小
                float rx：所产生圆角的椭圆的横轴半径；
                float ry：所产生圆角的椭圆的纵轴半径；

    圆形路径
        void addCircle (float x, float y, float radius, Path.Direction dir)
        参数说明：
            float x：圆心X轴坐标
            float y：圆心Y轴坐标
            float radius：圆半径

    弧形路径
        void addArc (RectF oval, float startAngle, float sweepAngle)
            参数：
                RectF oval：弧是椭圆的一部分，这个参数就是生成椭圆所对应的矩形；
                float startAngle：开始的角度，X轴正方向为0度
                float sweepAngel：持续的度数；

 */
