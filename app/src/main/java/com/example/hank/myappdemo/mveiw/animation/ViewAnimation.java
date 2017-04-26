package com.example.hank.myappdemo.mveiw.animation;


import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.TimeInterpolator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;
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
    @Bind(R.id.cancel_animation)
    Button cancelAnimation;
    @Bind(R.id.btn_value_animator_argb_evaluator)
    TextView btnValueAnimatorArgbEvaluator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_view_animation_layout);
        ButterKnife.bind(this);
    }

    private ValueAnimator valueAnimator;

    @OnClick({R.id.btn, R.id.btn_value_animator, R.id.btn_value_animator_of_float, R.id
            .cancel_animation,R.id.btn_value_animator_argb_evaluator})
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
                    使用ArgbEvaluator改变背景颜色动画
             */
            case R.id.btn_value_animator_argb_evaluator:
                startValueAnimatorArgbEvaluator();
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
     * 在这段代码中，我们将动画的数据范围定义为(0xffffff00,0xff0000ff)，即从黄色，变为蓝色。
     * 在监听中，我们根据当前传回来的颜色值，将其设置为button的背景色
     */
    private void startValueAnimatorArgbEvaluator() {
        ValueAnimator animator = ValueAnimator.ofInt(0xffffff00, 0xff0000ff);
        animator.setEvaluator(new ArgbEvaluator());
        animator.setDuration(3000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int curValue = (int) valueAnimator.getAnimatedValue();
                btnValueAnimatorArgbEvaluator.setBackgroundColor(curValue);
            }
        });
        animator.start();
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
     * 这里测试添加一个插值器，使用弹跳插值器BounceInterpolator（）；
     * 在监听中，我们只改变button的top和bottom的位置，让它跟着当前动画的值来改变它当前的top和bottom的位置。
     * 然后我们利用setDuration(1000)给它设置上做一次动画所需要的时长，
     * 然后通过setInterpolator（）给它设置插值器，也就是过渡值变化的规则
     */
    private void startValueAnimator() {
        ValueAnimator animator = ValueAnimator.ofInt(0, 600);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int curValue = (int) valueAnimator.getAnimatedValue();
                btnValueAnimator.layout(curValue, curValue, curValue + btnValueAnimator.getWidth(),
                        curValue + btnValueAnimator.getHeight());
            }
        });
        animator.setDuration(1000);
//        animator.setInterpolator(new BounceInterpolator());//弹跳插值器
        /*
        我们可以通过重写加速器改变数值进度来改变数值位置，
        也可以通过改变Evaluator中进度所对应的数值来改变数值位置。
         */
        // animator.setInterpolator(new MyInterpolator());
        animator.setEvaluator(new MyEvalutor());
        animator.start();
    }

    /**
     * 自定义一个插值器，只需要实现TimeInterpolator接口就可以了
     * 在getInterpolation函数中，我们将进度反转过来，
     * 当传0的时候，我们让它数值进度在完成的位置，当完成的时候，我们让它在开始的位置
     */
    public class MyInterpolator implements TimeInterpolator {

        @Override
        public float getInterpolation(float v) {
            return 1 - v;
        }
    }

    /**
     * 自定义一个Evalutor，用于在动画运行时，对指定的数进行转换
     * 该类型的返回值对应ValueAnimator.ofInt（）所需要的数据类型，否则为强转报错
     */
    public class MyEvalutor implements TypeEvaluator<Integer> {
        @Override
        public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
            return (int) (startValue + fraction * (endValue - startValue));
        }
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

