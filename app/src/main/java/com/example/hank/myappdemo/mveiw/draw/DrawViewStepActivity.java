package com.example.hank.myappdemo.mveiw.draw;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.hank.myappdemo.R;
import com.example.hank.myappdemo.mveiw.MyViewActivity;
import com.example.hank.myappdemo.mveiw.animation.ViewAnimationDemo;
import com.example.hank.myappdemo.mveiw.draw.activity.DrawViewActivty;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Jun on 2017/4/23.
 * 该类用于展示进入绘图入口，选择进入哪一个绘图效果
 */

public class DrawViewStepActivity extends AppCompatActivity {

    @Bind(R.id.my_view_draw_paint_circle)
    Button myViewDrawPaintCircle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_view_draw_activity_layout);
        ButterKnife.bind(this);


    }

    /** Paint 与 Canvas 的初步使用 */
    @OnClick(R.id.my_view_draw_paint_circle)
    public void onViewClicked() {
        Intent startDraw = new Intent(DrawViewStepActivity.this,
                DrawViewActivty.class);
        startActivity(startDraw);
    }
}
