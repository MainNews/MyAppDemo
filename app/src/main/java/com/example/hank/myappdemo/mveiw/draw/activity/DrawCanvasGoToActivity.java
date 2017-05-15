package com.example.hank.myappdemo.mveiw.draw.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.example.hank.myappdemo.R;
import com.example.hank.myappdemo.mveiw.draw.view.MyViewDrawCanvasGoTo;

/**
 * Created by Jun on 2017/5/15.
 * canvas变换与操作，主要展示画布的裁剪与保存还有恢复的操作
 */

public class DrawCanvasGoToActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_view_draw_text_path_layout);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.my_view_text_path_layout);
        frameLayout.addView(new MyViewDrawCanvasGoTo(DrawCanvasGoToActivity.this));
    }
}
