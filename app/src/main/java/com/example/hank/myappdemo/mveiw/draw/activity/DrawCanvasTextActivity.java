package com.example.hank.myappdemo.mveiw.draw.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.example.hank.myappdemo.R;
import com.example.hank.myappdemo.mveiw.draw.view.MyViewDrawCanvasTextDetailed;

/**
 * Created by Jun on 2017/5/21.
 * 该类主要是对于Canvas.drawText()绘制文字进行一个展示
 */

public class DrawCanvasTextActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_view_draw_noe_view_layout);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.draw_noe_view_layout);
        frameLayout.addView(new MyViewDrawCanvasTextDetailed(DrawCanvasTextActivity.this));
    }
}
