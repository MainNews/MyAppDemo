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
 * 该类用于展示Alpha_Scale_Translate_Rotate_Set的基础用法与效果
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

    @OnClick({R.id.alpha, R.id.scale, R.id.rotate, R.id.translate,R.id.set})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.alpha:
                startAnimation(R.anim.alpha_anim);
                break;
            case R.id.scale:
                startAnimation(R.anim.scaleanim);
                break;
            case R.id.rotate:
                startAnimation(R.anim.rotate_anim);
                break;
            case R.id.translate:
                startAnimation(R.anim.translate_anim);

                break;
            case R.id.set:
                startAnimation(R.anim.set_anim);
                break;
        }
    }

    private void startAnimation(int animationLayout) {
        Animation animation = AnimationUtils.loadAnimation(this, animationLayout);
        tv.startAnimation(animation);
    }
}
