package com.example.hank.myappdemo.mveiw.draw.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.example.hank.myappdemo.R;
import com.example.hank.myappdemo.mveiw.draw.view.MyViewDrawPatinFunctionTwo;

/**
 * Created by Jun on 2017/7/3.
 * 展示使用Paint对象中带有函数方法
 */

public class DrawCanvasPaintFunctionTwoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_view_draw_text_path_layout);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.my_view_text_path_layout);
        frameLayout.addView(new MyViewDrawPatinFunctionTwo(this));
    }
}
