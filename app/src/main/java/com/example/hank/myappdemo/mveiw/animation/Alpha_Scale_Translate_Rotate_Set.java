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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_view_animation_basis);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.alpha, R.id.scale, R.id.rotate, R.id.set})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.alpha:
                break;
            case R.id.scale:
                Animation animation = AnimationUtils.loadAnimation(this, R.anim.scaleanim);
                tv.startAnimation(animation);
                break;
            case R.id.rotate:
                break;
            case R.id.set:
                break;
        }
    }
}
