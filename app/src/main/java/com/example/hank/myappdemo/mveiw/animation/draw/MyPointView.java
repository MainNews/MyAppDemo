package com.example.hank.myappdemo.mveiw.animation.draw;

import android.animation.PointFEvaluator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.BounceInterpolator;

import com.baidu.location.Poi;
import com.example.hank.myappdemo.mveiw.animation.bean.Point;

/**
 * Created by Jun on 2017/4/27.
 * 该类用于在使用OfObject()方法时，画的一个圆，该回带有动画效果
 */

public class MyPointView extends View {

    private Point mCurPoint;

    public MyPointView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mCurPoint != null) {
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setColor(Color.RED);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(300, 300, mCurPoint.getRadius(), paint);
        }
    }

    public void doPointAnim() {
        final ValueAnimator animator = ValueAnimator.ofObject(new PointEvaluator(), new Point(20),
                new Point(200));
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mCurPoint = (Point) animator.getAnimatedValue();
                invalidate();//强制刷新，在强制刷新后，会走onDraw()方法
            }
        });
        animator.setDuration(1000);
        animator.setInterpolator(new BounceInterpolator());
        animator.start();
    }
    public class PointEvaluator implements TypeEvaluator<Point>{

        @Override
        public Point evaluate(float fraction, Point startValue, Point endValue) {
            int start = startValue.getRadius();
            int end = endValue.getRadius();
            int curValue = (int) (start + fraction * (end - start));
            return new Point(curValue);
        }
    }
}
