package com.example.hank.myappdemo.mveiw.draw.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;

/**
 * Created by Jun on 2017/4/23.
 * 自定义三步曲之绘图篇
 *  绘制第一个任意的图形
 *
 */

public class DrawMyOneView extends View {
    public DrawMyOneView(Context context) {
        super(context);
    }

    /**
     * 重写OnDraw()函数，在每次重绘时自主实现绘图
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        //设置画笔基本属性
        Paint paint = new Paint();
        paint.setAntiAlias(true);//抗锯齿功能
        paint.setColor(Color.RED);//设置画笔的颜色
        paint.setStyle(Paint.Style.FILL);//设置填充样式
        paint.setStrokeWidth(10);//设置画笔的宽度
        paint.setShadowLayer(10,15,15,Color.GREEN);//设置阴影

        canvas.drawRGB(255,255,255);//设置背景颜色

        float[] pts = {30,30,50,50,70,70,100,100};
        /*
            画出多个点
            下面举例说明上面offset与count的含义：
            （跳过第一个点，画出后面两个点，第四个点不画），注意一个点是两个数值！
            同样是上面的四个点：（30，30）、（50，50），（70，70），（100，100），
                drawPoints里路过前两个数值，即第一个点横纵坐标，画出后面四个数值代表的点，
                即第二，第三个点，第四个点没画；
         */
        canvas.drawPoints(pts,2,4,paint);


        /* 画矩形
                根据RectF的变量来画出矩形
         */
        Rect rect = new Rect(100,10,200,100);
        canvas.drawRect(rect,paint);

        /*
            画圆角矩形
            RectF rect:要画的矩形
            float rx:生成圆角的椭圆的X轴半径
            float ry:生成圆角的椭圆的Y轴半径
         */
        RectF rectF = new RectF(220,10,360,100);
        canvas.drawRoundRect(rectF,20,30,paint);

        /*
            画圆
            float cx：圆心点X轴坐标
            float cy：圆心点Y轴坐标
            float radius：圆的半径
         */
        canvas.drawCircle(500,110,100,paint);//画圆

        paint.setStyle(Paint.Style.STROKE);//填充样式改为描边
        RectF ovalRectF = new RectF(630,10,780,100);
        canvas.drawRect(ovalRectF,paint);//画一个空心的矩形

        /*
            画椭圆：
             椭圆是根据矩形生成的，以矩形的长为椭圆的X轴，矩形的宽为椭圆的Y轴，建立的椭圆图形
         */
        paint.setColor(Color.GREEN);//修改画笔颜色
        canvas.drawOval(ovalRectF,paint);//根据上面的矩形画椭圆

        /*
          画弧 drawArc(RectF oval, float startAngle, float sweepAngle, boolean useCenter, Paint paint);
            RectF oval:生成椭圆的矩形
            float startAngle：弧开始的角度，以X轴正方向为0度
            float sweepAngle：弧持续的角度
            boolean useCenter:是否有弧的两边，True，还两边，False，只有一条弧
            弧是椭圆的一部分，而椭圆是根据矩形来生成的，所以弧当然也是根据矩形来生成的
         */
        RectF arcRectF = new RectF(10,250,200,400);
        canvas.drawArc(arcRectF,0,90,true,paint);

        RectF arcRectFFalse = new RectF(210,250,410,400);
        canvas.drawArc(arcRectFFalse,0,90,false,paint);

        paint.setStyle(Paint.Style.FILL);//将画笔改为填充
        RectF arcRectFFill = new RectF(420,250,630,400);
        canvas.drawArc(arcRectFFill,0,90,true,paint);

        RectF arcRectFFalseFill = new RectF(650,250,850,400);
        canvas.drawArc(arcRectFFalseFill,0,90,false,paint);






    }
}
/*
    paint.setAntiAlias(true);//抗锯齿功能

    paint.setColor(Color.RED);  //设置画笔颜色

    paint.setStyle(Style.FILL);//设置填充样式
       void setStyle (Paint.Style style)     设置填充样式
        Paint.Style.FILL    :填充内部
        Paint.Style.FILL_AND_STROKE  ：填充内部和描边
        Paint.Style.STROKE  ：仅描边

    paint.setStrokeWidth(30);//设置画笔宽度

    paint.setShadowLayer(10, 15, 15, Color.GREEN);//设置阴影
        setShadowLayer (float radius, float dx, float dy, int color)    添加阴影
            参数：
            radius:阴影的倾斜度
            dx:水平位移
            dy:垂直位移


Canvas的基本设置：

  画布背景设置：
        canvas.drawColor(Color.BLUE);
        canvas.drawRGB(255, 255, 0);   //这两个功能一样，都是用来设置背景颜色的。

  画圆形：
        void drawCircle (float cx, float cy, float radius, Paint paint)
        参数：
            float cx：圆心点X轴坐标
            float cy：圆心点Y轴坐标
            float radius：圆的半径
  画直线：参数：
            startX:开始点X坐标
            startY:开始点Y坐标
            stopX:结束点X坐标
            stopY:结束点Y坐标
        canvas.drawLine(float startX, float startY, float stopX, float stopY, Paint paint);

   画多条直线：参数：
        float []pts={10,10,100,100,200,200,400,400};
        （上面有四个点：（10，10）、（100，100），（200，200），（400，400）），两两连成一条直线；
        canvas.drawLines (float[] pts, Paint paint);
        canvas.drawLines (float[] pts, int offset, int count, Paint paint);

   画点：参数：
            float X：点的X坐标
            float Y：点的Y坐标
        canvas.drawPoint (float x, float y, Paint paint);

   画多个点：参数：
        float[] pts:点的合集，与上面直线一直，样式为｛x1,y1,x2,y2,x3,y3,……｝
        int offset:集合中跳过的数值个数，注意不是点的个数！一个点是两个数值；
        count:参与绘制的数值的个数，指pts[]里人数值个数，而不是点的个数，因为一个点是两个数值
        canvas.drawPoints (float[] pts, Paint paint);
        canvas.drawPoints (float[] pts, int offset, int count, Paint paint);


矩形工具类RectF与Rect
        这两个都是矩形辅助类，区别不大，用哪个都行，根据四个点构建一个矩形结构；
        在画图时，利用这个矩形结构可以画出对应的矩形或者与其它图形Region相交、相加等等；

RectF：
构造函数有下面四个，但最常用的还是第二个，根据四个点构造出一个矩形；
        RectF()
   传入矩形的四个点
        RectF(float left, float top, float right, float bottom)

        RectF(RectF r)
        RectF(Rect r)
Rect
构造函数如下，最常用的也是根据四个点来构造矩形
        Rect()
    传入矩形的四个点
        Rect(int left, int top, int right, int bottom)
        Rect(Rect r)

矩形
    直接传入矩形的四个点，画出矩形
        void drawRect (float left, float top, float right, float bottom, Paint paint)
    根据传入RectF或者Rect矩形变量来指定所画的矩形的
        void drawRect (RectF rect, Paint paint)
        void drawRect (Rect r, Paint paint)

圆角矩形
    void drawRoundRect (RectF rect, float rx, float ry, Paint paint)
    参数：
        RectF rect:要画的矩形
        float rx:生成圆角的椭圆的X轴半径
        float ry:生成圆角的椭圆的Y轴半径

 椭圆
    椭圆是根据矩形生成的，以矩形的长为椭圆的X轴，矩形的宽为椭圆的Y轴，建立的椭圆图形
        void drawOval (RectF oval, Paint paint)

 弧
    弧是椭圆的一部分，而椭圆是根据矩形来生成的，所以弧当然也是根据矩形来生成的；
        void drawArc (RectF oval, float startAngle, float sweepAngle, boolean useCenter, Paint paint)
    参数：
        RectF oval:生成椭圆的矩形
        float startAngle：弧开始的角度，以X轴正方向为0度
        float sweepAngle：弧持续的角度
        boolean useCenter:是否有弧的两边，True，有两边，False，只有一条弧
 */

