package com.example.hank.myappdemo.mveiw.animation;


import android.animation.Animator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.Toast;

import com.example.hank.myappdemo.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Jun on 2017/4/23.
 * <p>
 * 该类用于展示AndroidAPI 1.0 自带的ViewAnimation动画，其中带有
 * TweenAnimation 补间动画，
 * FramAnimation   帧动画
 * 该动画只能改变控件动，而无法改变控件属性，看以下例子
 */

public class ViewAnimation extends AppCompatActivity {

    @Bind(R.id.btn)
    Button btn;
    @Bind(R.id.btn_value_animator)
    Button btnValueAnimator;
    @Bind(R.id.btn_value_animator_of_float)
    Button btnValueAnimatorOfFloat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_view_animation_layout);
        ButterKnife.bind(this);
    }

    private ValueAnimator valueAnimator;

    @OnClick({R.id.btn, R.id.btn_value_animator, R.id.btn_value_animator_of_float, R.id
            .cancel_animation})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn:
                showToast("点击了TextView的点击事件");
                /*
                    位移动画，只移动位置，但不改变属性位置
                 */
                TranslateAnimation translateAnimation = new TranslateAnimation(Animation
                        .ABSOLUTE, 0, Animation.ABSOLUTE, 300, Animation.ABSOLUTE, 0, Animation
                        .ABSOLUTE, 300);
                translateAnimation.setFillAfter(true);
                translateAnimation.setDuration(5000);
                btn.startAnimation(translateAnimation);
                break;
            /*
                    使用ValueAnimator属性动来，该动画主要操作数字区间进行动画运算
                    使int整数
             */
            case R.id.btn_value_animator:
                startValueAnimator();
                break;
            /*
                    使用ValueAnimator属性动来，该动画主要操作数字区间进行动画运算
                    使用float浮点数
             */
            case R.id.btn_value_animator_of_float:
               startValueAnimatorOnFloat();
                break;
            /*
                结束动画
             */
            case R.id.cancel_animation:
                if (valueAnimator != null) {
                    count = 0;
                    valueAnimator.cancel();
                }
                break;
        }
    }

    /**
     * 使用ValueAnimator属性动来，该动画主要操作数字区间进行动画运算
     * 通过ofFloat()传入多个float浮点数，来控制动画的位置
     */
    private void startValueAnimatorOnFloat() {
        if (valueAnimator == null) {
            valueAnimator = ValueAnimator.ofFloat(0f, 400f, 100);
        }
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                Float curValueFloat = (Float) valueAnimator.getAnimatedValue();
                int curValue = curValueFloat.intValue();
                btnValueAnimatorOfFloat.layout(curValue, curValue, curValue +
                        btnValueAnimatorOfFloat.getWidth
                        (), curValue + btnValueAnimatorOfFloat.getHeight());
            }
        });
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override//动画开始
            public void onAnimationStart(Animator animator) {
                showToast("开始动画");
            }

            @Override//动画结束
            public void onAnimationEnd(Animator animator) {
                animator.cancel();
                showToast("结束动画");
            }

            @Override//动画取消
            public void onAnimationCancel(Animator animator) {
                showToast("取消动画");
            }

            @Override//动画重复
            public void onAnimationRepeat(Animator animator) {
                count++;
                showToast("开始重复动画" + count);
            }
        });

        valueAnimator.setStartDelay(1000);//延时多久时间开始动画，单位为毫秒
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);//循环模式
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);//循环次数
        valueAnimator.setDuration(1000);
        valueAnimator.start();
    }

    private int count = 0;

    /**
     * 使用ValueAnimator属性动来，该动画主要操作数字区间进行动画运算
     * 通过ofInt()传入多个int整数，来控制动画的位置
     */
    private void startValueAnimator() {
        ValueAnimator animator = ValueAnimator.ofInt(0, 400, 200);
        animator.setDuration(3000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int curValue = (int) valueAnimator.getAnimatedValue();
                btnValueAnimator.layout(curValue, curValue, curValue + btnValueAnimator.getWidth(),
                        curValue + btnValueAnimator.getHeight());
            }
        });
        animator.start();
    }

    private Toast toast;

    private void showToast(String msg) {
        if (toast == null) {
            toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        }
        toast.setText(msg);
        toast.show();
    }
}
/*
        * 设置动画时长，单位是毫秒
        ValueAnimator setDuration(long duration)

        * 获取ValueAnimator在运动时，当前运动点的值
        Object getAnimatedValue();

        * 开始动画
        void start()

        * 设置循环次数,设置为INFINITE表示无限循环
        void setRepeatCount(int value)

        * 设置循环模式
        * value取值有RESTART，REVERSE，
        * ValueAnimation.RESTART 表示正序循环
        * ValueAnimation.REVERSE 表示倒序循环
        void setRepeatMode(int value)

        * 取消动画
        void cancel()


 * 监听器一：监听动画变化时的实时值
//添加方法为：public void addUpdateListener(AnimatorUpdateListener listener)

public static interface AnimatorUpdateListener {
    void onAnimationUpdate(ValueAnimator animation);
}

* 监听器二：监听动画变化时四个状态
//添加方法为：public void addListener(AnimatorListener listener)

public static interface AnimatorListener {
    void onAnimationStart(Animator animation);  动画开始时调用
    void onAnimationEnd(Animator animation);    动画结束时调用
    void onAnimationCancel(Animator animation); 动画取消时调用
    void onAnimationRepeat(Animator animation); 动画重复时调用
}


 * 移除AnimatorUpdateListener
    void removeUpdateListener(AnimatorUpdateListener listener);//移除指定监听器
    void removeAllUpdateListeners();    //移除所有监听器


 * 移除AnimatorListener
    void removeListener(AnimatorListener listener); //移除指定监听器
    void removeAllListeners();  //移除所有监听


 * 延时多久时间开始，单位是毫秒
    public void setStartDelay(long startDelay)

 * 完全克隆一个ValueAnimator实例，包括它所有的设置以及所有对监听器代码的处理
    public ValueAnimator clone()
*/

