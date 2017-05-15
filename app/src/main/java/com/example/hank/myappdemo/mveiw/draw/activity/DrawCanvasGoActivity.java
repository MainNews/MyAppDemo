package com.example.hank.myappdemo.mveiw.draw.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.example.hank.myappdemo.R;
import com.example.hank.myappdemo.mveiw.draw.view.MyViewDrawCanvasGo;

/**
 * Created by Jun on 2017/5/15.
 * 该界面主要用于展示对canvas画布的深入了解的效果
 */

public class DrawCanvasGoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_view_draw_text_path_layout);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.my_view_text_path_layout);
        frameLayout.addView(new MyViewDrawCanvasGo(DrawCanvasGoActivity.this));
    }
}
