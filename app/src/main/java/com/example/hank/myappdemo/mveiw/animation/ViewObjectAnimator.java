package com.example.hank.myappdemo.mveiw.animation;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.hank.myappdemo.R;
import com.example.hank.myappdemo.mveiw.animation.draw.MyPointView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Jun on 2017/4/28.
 * 使用ViewObjectAnimator对象进行动画的实现
 */

public class ViewObjectAnimator extends AppCompatActivity {

    @Bind(R.id.my_view_animation_object_property)
    Button myViewAnimationObjectProperty;
    @Bind(R.id.my_view_animation_object)
    Button myViewAnimationObject;
    @Bind(R.id.my_object_point_view)
    MyPointView myObjectPointView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_view_animtion_object_layout);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.my_view_animation_object_property, R.id.my_view_animation_object})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_view_animation_object_property:
                startObjectAnimatorProperty();
                break;
            case R.id.my_view_animation_object:
                startMyObjectAnimatorPointView();
                break;
        }
    }

    /**
     * 使用ObjectAnimator类对自定义的控件做动画效果
     *  ObjectAnimator.ofInt()中，会根据第二个参数与查找指定控件的set方法，
     *              这里为pointRadius,找到的方法为：setPointRadius(),所以指定的控件中必需要有这个方法，否则会报错
     */
    private void startMyObjectAnimatorPointView() {
        /*
            也可以这样去改变控件,这里改变TextView的背景色，设定了Evaluator数据转换类
                        注意：第二个参数必须存在对应的set方法，否则会报错
                ObjectAnimator animator = ObjectAnimator.ofInt(textView, "BackgroundColor", 0xffff00ff, 0xffffff00, 0xffff00ff);
                animator.setDuration(8000);
                animator.setEvaluator(new ArgbEvaluator());
                animator.start();
         */
        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(myObjectPointView,"pointRadius",0,300,100);

        objectAnimator.setDuration(2000);
        objectAnimator.start();


    }

    /**
     * 通过ObjectAnimator.ofFloat()方法，设置参数，改变控件动画属性
     * 该类与ValueAnimator的区别在于：ValueAnimator只能进行数据的改变与监听，而ObjectAnimator可以改变基础动画的属性值
     */
    private void startObjectAnimatorProperty() {
        /*
            第一个参数用于指定这个动画要操作的是哪个控件
            第二个参数用于指定这个动画要操作这个控件的哪个属性
            第三个参数是可变长参数，这个就跟ValueAnimator中的可变长参数的意义一样了，就是指这个属性值是从哪变到哪

            在这里需要特别注意一点：
                第二个参数对应着相应的基础动画方法，如果只设置 X 轴的参数，则：alphaX,后面的x为大写，
                            如果是小写会找不到相应的方法，其它基础方法与属性都与之相同的语法来设置。
         */
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(myViewAnimationObjectProperty, "alpha", 1, 0, 1);
        //ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(myViewAnimationObjectProperty,
        // "rotation",1,0,1);
        //ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(myViewAnimationObjectProperty,
        // "rotationY",0,180,);
        //ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(myViewAnimationObjectProperty,
        // "translationX",0,200,-200,0);
        objectAnimator.setDuration(2000);
        objectAnimator.start();
    }
}
/*

   ObjectAnimator是在ValueAnimator的基础上开发的，所以也有ValueAnimator所有的方法：
常用函数：
    * 设置动画时长，单位是毫秒
        ValueAnimator setDuration(long duration)
     * 获取ValueAnimator在运动时，当前运动点的值
        Object getAnimatedValue();
     * 开始动画
        void start()
     * 设置循环次数,设置为INFINITE表示无限循环
        void setRepeatCount(int value)
     * 设置循环模式（正序，倒序）
        value取值有 RESTART，REVERSE，
        void setRepeatMode(int value)
     * 取消动画
        void cancel()

监听器：
 * 监听器一：监听动画变化时的实时值
    public static interface AnimatorUpdateListener {
        void onAnimationUpdate(ValueAnimator animation);
    }
//添加方法为：public void addUpdateListener(AnimatorUpdateListener listener)

 * 监听器二：监听动画变化时四个状态
    public static interface AnimatorListener {
        void onAnimationStart(Animator animation);
        void onAnimationEnd(Animator animation);
        void onAnimationCancel(Animator animation);
        void onAnimationRepeat(Animator animation);
    }
//添加方法为：public void addListener(AnimatorListener listener)

 * 设置插值器
        public void setInterpolator(TimeInterpolator value)
 * 设置Evaluator
        public void setEvaluator(TypeEvaluator value)
*/
