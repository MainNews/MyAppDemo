package com.example.hank.myappdemo.mveiw;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.example.hank.myappdemo.R;
import com.example.hank.myappdemo.base.BaseActivtiy;
import com.example.hank.myappdemo.mveiw.animation.Alpha_Scale_Translate_Rotate_Set;
import com.example.hank.myappdemo.mveiw.animation.ViewAnimationDemo;
import com.example.hank.myappdemo.mveiw.draw.DrawViewStepActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Jun on 2017/4/19.
 * 用于展示自定义三部曲的内容
 */

public class MyViewActivity extends BaseActivtiy {
    @Bind(R.id.my_view_animation)
    Button myViewAnimation;
    @Bind(R.id.my_view_paint)
    Button myViewPaint;
    @Bind(R.id.my_view_group)
    Button myViewGroup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_view_activity_layout);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.my_view_animation, R.id.my_view_paint, R.id.my_view_group})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_view_animation://动画
                Intent startAnimation = new Intent(MyViewActivity.this,
                        ViewAnimationDemo.class);
                startActivity(startAnimation);
                break;
            case R.id.my_view_paint://绘图
                Intent startDraw = new Intent(MyViewActivity.this,
                        DrawViewStepActivity.class);
                startActivity(startDraw);
                break;
            case R.id.my_view_group://视图
                break;
        }
    }
}
