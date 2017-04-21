package com.example.hank.myappdemo.mveiw.animation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.hank.myappdemo.R;
import com.example.hank.myappdemo.base.BaseActivtiy;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Jun on 2017/4/19.
 * 该类用于展示
 *          Alpha（透明度）
 *          Scale（缩放）
 *          Translate（平移）
 *          Rotate（旋转）
 *          Set（动画集合）
 *          Interpolator（插值器）
 * 基础用法与效果，以及以代码方式创建的对象Class
 */

public class Alpha_Scale_Translate_Rotate_Set extends BaseActivtiy {

    @Bind(R.id.alpha)
    Button alpha;
    @Bind(R.id.scale)
    Button scale;
    @Bind(R.id.rotate)
    Button rotate;
    @Bind(R.id.set)
    Button set;
    @Bind(R.id.tv)
    TextView tv;
    @Bind(R.id.translate)
    Button translate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_view_animation_basis);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.alpha, R.id.scale, R.id.rotate, R.id.translate, R.id.set})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.alpha:
                /*代码对应的构造函数
                AlphaAnimation(Context context, AttributeSet attrs)  同样，从本地XML加载动画，基本不用
                AlphaAnimation(float fromAlpha, float toAlpha)*/
                startAnimation(R.anim.alpha_anim);
                break;
            case R.id.scale:
                /*代码对应的构造函数
                ScaleAnimation(Context context, AttributeSet attrs)  从XML文件加载动画，基本用不到
                ScaleAnimation(float fromX, float toX, float fromY, float toY)
                ScaleAnimation(float fromX, float toX, float fromY, float toY, float pivotX,
                float pivotY)
                ScaleAnimation(float fromX, float toX, float fromY, float toY, int pivotXType,
                float pivotXValue, int pivotYType, float pivotYValue)*/
                startAnimation(R.anim.scaleanim);
                break;
            case R.id.rotate:
                /* 代码对应的构造函数
                *   RotateAnimation(Context context, AttributeSet attrs)　　从本地XML文档加载动画，同样，基本不用
                    RotateAnimation(float fromDegrees, float toDegrees)
                    RotateAnimation(float fromDegrees, float toDegrees, float pivotX, float pivotY)
                    RotateAnimation(float fromDegrees, float toDegrees, int pivotXType, float
                    pivotXValue, int pivotYType, float pivotYValue)
                * */
                startAnimation(R.anim.rotate_anim);
                break;
            case R.id.translate:
                /*代码对应的构造函数
                TranslateAnimation(Context context, AttributeSet attrs)  同样，基本不用
                TranslateAnimation(float fromXDelta, float toXDelta, float fromYDelta, float toYDelta)
                TranslateAnimation(int fromXType, float fromXValue, int toXType, float toXValue,
                int fromYType,floatfromYValue, int toYType, float toYValue)
                 */
                startAnimation(R.anim.translate_anim);
                break;
            case R.id.set:
                /*代码对应的构造函数
                setAnim = new AnimationSet(true);
                setAnim.addAnimation(Animation a)
                public void addAnimation (Animation a)
                 */
                startAnimation(R.anim.set_anim);
                break;
        }
    }
    /* 插值器类对比

    在使用过程中，通过动画类.setInterpolator(Interpolator);
        如 AnimationSet.setInterpolator(new AccelerateDecelerateInterpolato());
                Interpolator class	                            Resource ID
        AccelerateDecelerateInterpolato         @android:anim/accelerate_decelerate_interpolator
        AccelerateInterpolator	                @android:anim/accelerate_interpolator
        AnticipateInterpolator	                @android:anim/anticipate_interpolator
        AnticipateOvershootInterpolator	        @android:anim/anticipate_overshoot_interpolator
        BounceInterpolator	                    @android:anim/bounce_interpolator
        CycleInterpolator	                    @android:anim/cycle_interpolator
        DecelerateInterpolator	                @android:anim/decelerate_interpolator
        LinearInterpolator	                    @android:anim/linear_interpolator
        OvershootInterpolator	                @android:anim/overshoot_interpolator
     */

    private void startAnimation(int animationLayout) {
        Animation animation = AnimationUtils.loadAnimation(this, animationLayout);
        tv.startAnimation(animation);
    }
}
