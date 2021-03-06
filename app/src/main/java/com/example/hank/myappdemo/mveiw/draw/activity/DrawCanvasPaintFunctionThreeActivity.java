package com.example.hank.myappdemo.mveiw.draw.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.example.hank.myappdemo.R;
import com.example.hank.myappdemo.mveiw.draw.view.MyViewDrawPaintFunctionThree;

/**
 * Created by Jun on 2017/7/3.
 * 展示Paint的函数完成印章效果 ，即使用图形做为路径
 */

public class DrawCanvasPaintFunctionThreeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_view_draw_text_path_layout);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.my_view_text_path_layout);
        frameLayout.addView(new MyViewDrawPaintFunctionThree(this));
    }
}
