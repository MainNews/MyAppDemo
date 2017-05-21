package com.example.hank.myappdemo.mveiw.draw.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by Jun on 2017/5/21.
 * 使用Canvas.drawText()方法进行文字的绘画
 */

public class MyViewDrawCanvasTextDetailed extends View {
    public MyViewDrawCanvasTextDetailed(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int baseLineX = 0;
        int baseLineY = 200;

        Paint paintLine = getPaint(Color.RED, Paint.Style.FILL,10);

        //画出基线
        canvas.drawLine(baseLineX,baseLineY,600,baseLineY,paintLine);

        //写文字
        Paint paintText = getPaint(Color.GREEN, Paint.Style.FILL,10);
        paintText.setTextSize(80);//以px为单位
        /*
            该方法有在三个值 ：Paint.Align.LEFT : 从设定的x y点的左边开始
                               Paint.Align.CENTER ：从中间开始画
                               Paint.Align.RIGHT ：画出右部份
         */
        paintText.setTextAlign(Paint.Align.LEFT);
        /*
            这里的y为基线点,即起始点，而这个点以四线格中的第三条线为起始点位置
            而x指所要绘制的相对位置，相对位置中有：左，中，右
         */
        canvas.drawText("hello my hanker",baseLineX,baseLineY,paintText);

        /*
            计算各线所在的位置
         */
        Paint.FontMetrics fm = paintText.getFontMetrics();
        float ascent = baseLineY + fm.ascent;
        float descent = baseLineY + fm.descent;
        float top = baseLineY + fm.top;
        float bottom = baseLineY + fm.bottom;

        //将其余四条线画出来
        //画Top线
        paintText.setColor(Color.RED);
        canvas.drawLine(baseLineX,top,600,top,paintText);

        //画ascent
        paintText.setColor(Color.GREEN);
        canvas.drawLine(baseLineX,ascent,600,ascent,paintText);

        //画descent
        paintText.setColor(Color.BLUE);
        canvas.drawLine(baseLineX,descent,600,descent,paintText);

        //画bottom
        paintText.setColor(Color.YELLOW);
        canvas.drawLine(baseLineX,bottom,600,bottom,paintText);
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
    private Paint getPaint(int color){
        return getPaint(color, Paint.Style.FILL,10);
    }
}
/*
        总共有五种线：
                    baseline ：基线所在线
                    ascent: 系统建议的，绘制单个字符时，字符应当的最高高度所在线
                    descent:系统建议的，绘制单个字符时，字符应当的最低高度所在线
                    top: 可绘制的最高高度所在线
                    bottom: 可绘制的最低高度所在线
其中ascent,descent,top,bottom这些线的位置计算方法与意义分别如下：
    使用FontMetrics类，使用该的类：
        FontMetrics::ascent;    ascent = ascent线的y坐标 - baseline线的y坐标；
        FontMetrics::descent;   descent = descent线的y坐标 - baseline线的y坐标；
        FontMetrics::top;       top = top线的y坐标 - baseline线的y坐标；
        FontMetrics::bottom;    bottom = bottom线的y坐标 - baseline线的y坐标；
        注意：以上的四条线只是通过FontMetrics类得到该线的位置

*/
