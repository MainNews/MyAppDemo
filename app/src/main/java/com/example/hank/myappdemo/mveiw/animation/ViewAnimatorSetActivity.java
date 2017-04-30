package com.example.hank.myappdemo.mveiw.animation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.TextView;

import com.example.hank.myappdemo.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Jun on 2017/4/30.
 * 该类用于展示AnimatorSet组合形动画的操作
 */

public class ViewAnimatorSetActivity extends AppCompatActivity {

    @Bind(R.id.my_view_animation_set_playSequentially)
    Button myViewAnimationSetPlaySequentially;
    @Bind(R.id.my_view_animation_set_hello)
    TextView myViewAnimationSetHello;
    @Bind(R.id.my_view_animation_i_am)
    TextView myViewAnimationIAm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_view_animation_set);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.my_view_animation_set_playSequentially,R.id.my_view_animation_set_playTogether,
            R.id.my_view_animation_set_play_builder})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            /*
                    顺序进行动画
             */
            case R.id.my_view_animation_set_playSequentially:
                startAnimatorSetPlaySequentillay();
                break;
            /*
                    动画同时进行
             */
            case R.id.my_view_animation_set_playTogether:
                startAnimatorSetPlayTogether();
                break;
            /*
                    指定动画执行的顺序
             */
            case R.id.my_view_animation_set_play_builder:
                startAnimatorSetPlayBuilder();
                break;
        }
    }

    /**
     * 使用AnimatorSet的Play()方法得到AnimatorSet.Builder 对象来指定动画的执行顺序
     */
    private void startAnimatorSetPlayBuilder() {
        ObjectAnimator helloBaAnimator = ObjectAnimator.ofInt(myViewAnimationSetHello,
                "BackgroundColor",0xffff00ff,0xffffff00,0xffff00ff);
        ObjectAnimator helloTranslateY = ObjectAnimator.ofFloat(myViewAnimationSetHello,
                "translationY",0,300,0);
        ObjectAnimator iamTranslateY = ObjectAnimator.ofFloat(myViewAnimationIAm,
                "translationY",0,400,0);
        AnimatorSet animatorSet = new AnimatorSet();
        //这里直接使用链式调用
        AnimatorSet.Builder builder = animatorSet.play(helloBaAnimator).with(iamTranslateY).after(helloTranslateY);
        /*builder.with(iamTranslateY);
        builder.after(helloTranslateY);*/
        animatorSet.setDuration(1000);
        animatorSet.start();
    }

    /**
     * 使用AnimatorSet的PlayTogether()方法
     *      该方法主要的功能是同时执行多个动画
     */
    private void startAnimatorSetPlayTogether() {
        ObjectAnimator helloBaAnimator = ObjectAnimator.ofInt(myViewAnimationSetHello,
                "BackgroundColor",0xffff00ff,0xffffff00,0xffff00ff);
        ObjectAnimator helloTranslateY = ObjectAnimator.ofFloat(myViewAnimationSetHello,
                "translationY",0,300,0);
        ObjectAnimator iamTranslateY = ObjectAnimator.ofFloat(myViewAnimationIAm,
                "translationY",0,400,0);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(helloBaAnimator,helloTranslateY,iamTranslateY);
        animatorSet.setDuration(1000);
        //如果设置目标控件，那么myViewAnimationSetHello身上的动画将全都执行到myViewAnimationIAm上，并自身不在执行动画
        //animatorSet.setTarget(myViewAnimationIAm);
        animatorSet.start();

    }

    /**
     * 使用AnimatorSet的PlaySequentillay()方法
     *      该方法主要的功能是在执行完一个动画后执行另一个动画，即可以认为是顺序动画
     *          如果第一个动画没有结束，那么下一个动画是无论无何都不会执行的
     */
    private void startAnimatorSetPlaySequentillay() {
        ObjectAnimator helloBgAnimator = ObjectAnimator.ofInt(myViewAnimationSetHello,
                                            "BackgroundColor",0xffff00ff,0xffffff00,0xffff00ff);
        ObjectAnimator helloTranslateY = ObjectAnimator.ofFloat(myViewAnimationSetHello,
                                            "translationY",0,300,0);
        ObjectAnimator iamTranslateY = ObjectAnimator.ofFloat(myViewAnimationIAm,
                                            "translationY",0,400,0);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(helloBgAnimator,helloTranslateY,iamTranslateY);
        animatorSet.setDuration(1000);
        animatorSet.start();

    }
}
/*
  调用AnimatorSet中的play方法是获取AnimatorSet.Builder对象的唯一途径
                    表示要播放哪个动画
                        public Builder play(Animator anim)
                    和前面动画一起执行
                        public Builder with(Animator anim)
                    执行前面的动画后才执行该动画
                        public Builder before(Animator anim)
                    执行先执行这个动画再执行前面动画
                        public Builder after(Animator anim)
                    延迟n毫秒之后执行动画
                        public Builder after(long delay)

                 play(Animator anim)表示当前在播放哪个动画，
                            另外的with(Animator anim)、
                            before(Animator anim)、
                            after(Animator anim)都是以play中的当前所播放的动画为基准的。

                比如，当play(playAnim)与before(beforeAnim)共用，
                                    则表示在播放beforeAnim之前，先播放playAnim动画；
                同样，当play(playAnim)与after(afterAnim)共用时，
                                    则表示在在播放afterAnim动画之后，再播放playAnim动画。


 在AnimatorSet中也可以添加监听器，对应的监听器为：
public static interface AnimatorListener {
     * 当AnimatorSet开始时调用
        void onAnimationStart(Animator animation);

     * 当AnimatorSet结束时调用
        void onAnimationEnd(Animator animation);

     * 当AnimatorSet被取消时调用
        void onAnimationCancel(Animator animation);

     * 当AnimatorSet重复时调用，由于AnimatorSet没有设置repeat的函数，所以这个方法永远不会被调用
        void onAnimationRepeat(Animator animation);
}

添加方法为：
public void addListener(AnimatorListener listener);

所以我们来总结一下AnimatorSet的监听：
1、AnimatorSet的监听函数也只是用来监听AnimatorSet的状态的，与其中的动画无关；
2、AnimatorSet中没有设置循环的函数，所以AnimatorSet监听器中永远无法运行到onAnimationRepeat()中！
有关如何实现无限循环的问题，我们上面已经讲了，就不再赘述


在AnimatorSet中还有几个函数：
//设置单次动画时长
    public AnimatorSet setDuration(long duration);

//设置加速器
    public void setInterpolator(TimeInterpolator interpolator)

//设置ObjectAnimator动画目标控件，如果设置了某个目标控件，那么其它的控件动画将不会执行，而是作用在目标控件上
    public void setTarget(Object target)

在AnimatorSet中设置以后，会覆盖单个ObjectAnimator中的设置；
        即如果AnimatorSet中没有设置，那么就以ObjectAnimator中的设置为准。
        如果AnimatorSet中设置以后，ObjectAnimator中的设置就会无效

/设置延时开始动画时长
    public void setStartDelay(long startDelay)

    setStartDelay函数不会覆盖单个动画的延时，而且仅针对性的延长AnimatorSet的激活时间，
                                             单个动画的所设置的setStartDelay仍对单个动画起作用

 */
