package com.example.hank.myappdemo.mveiw.draw.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.example.hank.myappdemo.R;
import com.example.hank.myappdemo.mveiw.draw.view.MyViewDrawTextPath;

/**
 * Created by Jun on 2017/4/28.
 * 该类用于展示使用Paint来绘画出文字的效果
 */

public class DrawTextPathViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_view_draw_text_path_layout);
        FrameLayout fragment = (FrameLayout) findViewById(R.id.my_view_text_path_layout);
        fragment.addView(new MyViewDrawTextPath(DrawTextPathViewActivity.this));


    }
}
