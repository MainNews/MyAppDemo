package com.example.hank.myappdemo.mveiw.animation;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.widget.Button;

import com.example.hank.myappdemo.R;
import com.example.hank.myappdemo.mveiw.animation.draw.MyCharTextView;
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
    @Bind(R.id.my_view_animation_object_property_values_holder)
    Button myViewAnimationObjectPropertyValuesHolder;
    @Bind(R.id.my_animator_text_evaluator)
    MyCharTextView myAnimatorTextEvaluator;
    @Bind(R.id.my_animator_btn_keyframe_property_holder)
    Button myAnimatorBtnKeyframePropertyHolder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_view_animtion_object_layout);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.my_view_animation_object_property, R.id.my_view_animation_object,
            R.id.my_view_animation_object_property_values_holder, R.id.my_animator_text_evaluator,
            R.id.my_animator_btn_keyframe_property_holder})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_view_animation_object_property:
                startObjectAnimatorProperty();
                break;
            case R.id.my_view_animation_object:
                startMyObjectAnimatorPointView();
                break;
            case R.id.my_view_animation_object_property_values_holder:
                startMyObjectAnimatorPropertyValuesHolder();
                break;
            case R.id.my_animator_text_evaluator:
                startMyPropertyEvaluator();
                break;
            case R.id.my_animator_btn_keyframe_property_holder:
                startMyObjectAnimatorPointKeyframe();
                break;
        }
    }

    /**
     * 使用Keyframe类，设定动画帧的效果
     * 凡是使用ofObject来做动画的时候，都必须调用frameHolder.setEvaluator显示设置Evaluator，
     *              因为系统根本是无法知道，你动画的中间值Object真正是什么类型的。
     */
    private void startMyObjectAnimatorPointKeyframe() {
        /*
                 在这些keyframe中，首先指定在开始和结束时，旋转角度为0，即恢复原样：
                    Keyframe frame0 = Keyframe.ofFloat(0f, 0);
                    Keyframe frame10 = Keyframe.ofFloat(1, 0);
                 然后在过程中，让它左右旋转，比如在进度为0.2时，旋转到左边20度位置：
                    Keyframe frame1 = Keyframe.ofFloat(0.1f, -20f);
                 然后在进度为0.3时，旋转到右边20度位置:
                    Keyframe frame3 = Keyframe.ofFloat(0.3f, -20f);
                 其它类似。正是因为来回左右的旋转，所以我们看起来就表现为在震动
         */
        Keyframe keyframe0 = Keyframe.ofFloat(0f, 0);
//        Keyframe keyframe1 = Keyframe.ofFloat(0.1f, -20f);
//        Keyframe keyframe2 = Keyframe.ofFloat(0.2f, 20f);
//        Keyframe keyframe3 = Keyframe.ofFloat(0.3f, -20f);
//        Keyframe keyframe4 = Keyframe.ofFloat(0.4f, 20f);
//        Keyframe keyframe5 = Keyframe.ofFloat(0.5f, -20f);
//        Keyframe keyframe6 = Keyframe.ofFloat(0.6f, 20f);
//        Keyframe keyframe7 = Keyframe.ofFloat(0.7f, -20f);
//        Keyframe keyframe8 = Keyframe.ofFloat(0.8f, 20f);
        Keyframe keyframe1 = Keyframe.ofFloat(0.5f, -100f);
        Keyframe keyframe2 = Keyframe.ofFloat(1, 0);
        //如果没有设置插值器，会使用默认的线性插值器（LinearInterpolator,在第一帧设置是无效的
        keyframe2.setValue(0f);
        keyframe2.setInterpolator(new BounceInterpolator());
        /*PropertyValuesHolder propertyHolder = PropertyValuesHolder.ofKeyframe("rotation", keyframe0,
                keyframe1, keyframe2, keyframe3, keyframe4, keyframe5,
                keyframe6, keyframe7, keyframe8, keyframe9);*/
        PropertyValuesHolder propertyHolder = PropertyValuesHolder.ofKeyframe("rotation", keyframe0,keyframe1, keyframe2);
        ObjectAnimator animation = ObjectAnimator.ofPropertyValuesHolder(myAnimatorBtnKeyframePropertyHolder,
                propertyHolder);
        animation.setDuration(3000);
        animation.start();

    }

    /**
     * 该方法使用自定义的TextView控件与自定义的Evaluator类来实现动画的数据转换
     */
    private void startMyPropertyEvaluator() {
        PropertyValuesHolder textPropertyHolder = PropertyValuesHolder.ofObject("CharText",
                new CharEvaluator(), new Character('A'), new Character('Z'));
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(myAnimatorTextEvaluator,
                textPropertyHolder);
        animator.setDuration(3000);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.start();
    }

    /**
     * 使用ObjectAnimator中的PropertyValuesHolder()方法来执行控件的动画效果
     * 使用ofFloat函数创建，动画属性值是Rotation(这里可以是基础动画的任意一种)，对应的是View类中SetRotation(float rotation)函数。
     * 后面传进去很多值，让其左右摆动。
     * 第二是动画是改变背景色的colorHolder
     */
    private void startMyObjectAnimatorPropertyValuesHolder() {
        PropertyValuesHolder rotationPropertyHolder = PropertyValuesHolder
                .ofFloat("Rotation", 0.0f, -60f, 40f, -40f, -20f, 20f, 10f, -10f, 0f);
        PropertyValuesHolder colorProperyHolder = PropertyValuesHolder
                .ofInt("BackgroundColor", 0xffffffff, 0xffff00ff, 0xffffff00, 0xffffffff);
        ObjectAnimator animator = ObjectAnimator
                .ofPropertyValuesHolder(myViewAnimationObjectPropertyValuesHolder,
                        rotationPropertyHolder,
                        colorProperyHolder);
        animator.setDuration(2000);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.start();

    }


    /**
     * 使用ObjectAnimator类对自定义的控件做动画效果
     * ObjectAnimator.ofInt()中，会根据第二个参数与查找指定控件的set方法，
     * 这里为pointRadius,找到的方法为：setPointRadius(),所以指定的控件中必需要有这个方法，否则会报错
     */
    private void startMyObjectAnimatorPointView() {
        /*
            也可以这样去改变控件,这里改变TextView的背景色，设定了Evaluator数据转换类
                        注意：第二个参数必须存在对应的set方法，否则会报错
                ObjectAnimator animator = ObjectAnimator.ofInt(textView, "BackgroundColor",
                0xffff00ff, 0xffffff00, 0xffff00ff);
                animator.setDuration(8000);
                animator.setEvaluator(new ArgbEvaluator());
                animator.start();
         */
        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(myObjectPointView, "pointRadius", 0,
                300, 100);

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
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(myViewAnimationObjectProperty,
                "alpha", 1, 0, 1);
        //ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(myViewAnimationObjectProperty,
        // "rotation",1,0,1);
        //ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(myViewAnimationObjectProperty,
        // "rotationY",0,180,);
        //ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(myViewAnimationObjectProperty,
        // "translationX",0,200,-200,0);
        objectAnimator.setDuration(2000);
        objectAnimator.start();
    }

    /**
     * 该类为自定义的一个Evaluator类，用于转换动画过程中的数据方式
     */
    public class CharEvaluator implements TypeEvaluator<Character> {

        @Override
        public Character evaluate(float fraction, Character startValue, Character endValue) {
            int start = (int) startValue;
            int end = (int) endValue;
            int curInt = (int) (start + fraction * (end - start));
            char result = (char) curInt;
            return result;
        }
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
/*
PropertyValuesHolder之其它函数
PropertyValuesHolder除了上面的讲到的ofInt,ofFloat,ofObject,ofKeyframe以外，api 11的还有几个函数：
 * 设置动画的Evaluator
        public void setEvaluator(TypeEvaluator evaluator)
            * 用于设置ofFloat所对应的动画值列表
        public void setFloatValues(float... values)
            * 用于设置ofInt所对应的动画值列表
        public void setIntValues(int... values)
            * 用于设置ofKeyframe所对应的动画值列表
        public void setKeyframes(Keyframe... values)
            * 用于设置ofObject所对应的动画值列表
        public void setObjectValues(Object... values)
            * 设置动画属性名
        public void setPropertyName(String propertyName)
        这些函数都比较好理解，setFloatValues(float… values)对应PropertyValuesHolder.ofFloat()，用于动态设置动画中的数值。
                        setIntValues、setKeyframes、setObjectValues同理；
                        setPropertyName用于设置PropertyValuesHolder所需要操作的动画属性名;
        最重要的是setEvaluator(TypeEvaluator evaluator)
        * 设置动画的Evaluator
            public void setEvaluator(TypeEvaluator evaluator)
            如果是利用PropertyValuesHolder.ofObject()来创建动画实例的话，我们是一定要显示调用 PropertyValuesHolder.setEvaluator()来设置Evaluator的。
            在上面的字母转换的例子中，我们已经用过这个函数了。这里也就没什么好讲的了。

 */
