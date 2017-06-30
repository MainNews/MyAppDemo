package com.example.hank.myappdemo.mveiw.viewgroup.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by Jun on 2017/6/30.
 * 该自定义控件，用于在多个子控件下，根据子控件的高宽进行自动设置该控件的高宽
 */

public class MyFlowLayout extends ViewGroup {
    public MyFlowLayout(Context context) {
        super(context);
    }

    public MyFlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyFlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 布局所有子控件
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        int lineWidth = 0;//累加当前行的宽度
        int lineHeight = 0;//累加当前行的高度
        int top = 0, left = 0;//当前坐标的top坐标和left坐标

        for (int i = 0; i < count; i++) {
            /** 计算出当前子控件的高宽 */
            View child = getChildAt(i);
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;

            /** 根据是否要换行来计算left和top的坐标 */
            if (childWidth + lineWidth > getMeasuredWidth()) {
                //如果换行
                top += lineHeight;
                left = 0;
                lineHeight = childHeight;
                lineWidth = childWidth;
            } else {
                lineHeight = Math.max(lineHeight, childHeight);
                lineWidth += childWidth;
            }
            /**
             * 计算好left和top之后，然后在计算出控件应该在布局的上下左右四个坐标点
             * 计算childView的left，top，right,bottom */
            int lc = left + lp.leftMargin;
            int tc = top + lp.topMargin;
            int rc = lc + child.getMeasuredWidth();
            int bc = tc + child.getMeasuredHeight();
            child.layout(lc, tc, rc, bc);
            //将left设置为下一子控件的起始点
            left += childWidth;

        }
    }

    /**
     * 测量
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        /** 获取系统建议的高宽和模式 */
        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        int measureWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int measureHeightMode = MeasureSpec.getMode(heightMeasureSpec);

        /**计算MyFlowLayout所占用的空间大小*/
        int lineWidth = 0;//记录每一行的宽度
        int lineHeight = 0;//记录每一行的高度
        int width = 0;//记录整个MyFlowLayout的宽度
        int height = 0;//记录整个MyFlowLayout的高度

        int count = getChildCount();//获取子节点个数
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
//在强制转换时就会出错，此时我们把左右间距设置为0，但由于在计算布局宽高时没有加上间距值，就是计算出的宽高要比实际小，所以是onLayout时就会出错
            MarginLayoutParams lp = null;
            if (child.getLayoutParams() instanceof MarginLayoutParams) {
                lp = (MarginLayoutParams) child.getLayoutParams();
            }else {
                lp = new MarginLayoutParams(0,0);
            }
            int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;

            if (lineWidth + childWidth > measureWidth) {
                //需要换行
                width = Math.max(lineWidth, width);
                height += lineHeight;
                //由于装不下当前控件，而将此控件调用到下一行，所以将此控件的高度和宽度初始化给lineWidth,lineHeight
                lineHeight = childHeight;
                lineWidth = childWidth;
            } else {
                /** 否则累加值lineWidth,lineHeight取最大值 */
                lineHeight = Math.max(lineHeight, childHeight);
                lineWidth += childWidth;
            }
            /** 最后一行不会起出width范围，所以单独处理 */
            if (i == count - 1) {
                height += lineHeight;
                width = Math.max(width, lineWidth);
            }
        }
        /** 当属性是MeasureSpec.EXACTLY时，那么它的宽高就是确的
         * 只有当是wrap_content时，根据内部控件的大小来确定它的大小时，大小是不确定的，属性是AT_MOST
         *          此时，就需要我们自己计算它的应当的大小，并设置进去
         * */
        setMeasuredDimension((measureWidthMode == MeasureSpec.EXACTLY) ? measureWidth : width,
                (measureHeightMode == MeasureSpec.EXACTLY) ? measureHeight : height);

    }
    /**
     * 如果要在测量时，得到margin的值，必须重写下面的三个方法
     */
    /************************************************************************/
    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }
    /************************************************************************/
}
