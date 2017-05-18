package com.example.hank.myappdemo.mveiw.viewgroup.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Jun on 2017/5/18.
 * 自定义一个与LinearLayout类似的控件
 */

public class MyLinearLayout  extends ViewGroup{

    public MyLinearLayout(Context context) {
        super(context);
    }

    public MyLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override//测量
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //分别拿到父类建议的高宽，然后利用MeasureSpec.getMode()获得对应的模式
        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        int measureWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int measureHeightMode = MeasureSpec.getMode(heightMeasureSpec);

        int height = 0;
        int width = 0;
        int count = getChildCount();//获如子控件的个数
        for (int i=0;i<count;i++){
            //测量子控件
            View child = getChildAt(i);
            measureChild(child,widthMeasureSpec,heightMeasureSpec);
            //获得子控件的高与宽
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();
            /*
                因为我们是垂直排列其内部所有的VIEW，
                      所以container所占宽度应该是各个TextVIew中的最大宽度，
                      所占高度应该是所有控件的高度和
             */
            //得到最大的高宽进行累加，这样就知道ViewGroup需要多少空间
            height += childHeight;
            width = Math.max(childWidth,width);
        }
        /*
            wrap_content-> MeasureSpec.AT_MOST
            match_parent -> MeasureSpec.EXACTLY
            具体值 -> MeasureSpec.EXACTLY
        */
        //如果用户设置了具体的大小，那就不需要使用我们测量出来的值了，即，只有在用户没有确定值时，使用我们测量的值
        setMeasuredDimension((measureWidthMode == MeasureSpec.EXACTLY) ? measureWidth : width,
                measureHeightMode == MeasureSpec.EXACTLY ? measureHeight : height);


    }

    /**
     * @param l 左
     * @param t 上
     * @param r 右
     * @param b 下
     */
    @Override//给子控件设置大小
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int top = 0;
        int count = getChildCount();
        for (int i=0;i<count;i++){
            View child = getChildAt(i);
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();

            child.layout(0,top,childWidth,top + childHeight);
            top += childHeight;

        }




    }
    @Override//将控件绘制出来
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
