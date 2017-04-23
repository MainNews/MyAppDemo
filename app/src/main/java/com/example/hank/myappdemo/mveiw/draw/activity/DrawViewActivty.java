package com.example.hank.myappdemo.mveiw.draw.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.example.hank.myappdemo.R;
import com.example.hank.myappdemo.mveiw.draw.view.DrawMyOneView;

/**
 * Created by Jun on 2017/4/23.
 * 初步使用 Paint(画笔) 与 Canvas(画布) 的使用
 */

public class DrawViewActivty extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_view_draw_noe_view_layout);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.draw_noe_view_layout);
        frameLayout.addView(new DrawMyOneView(DrawViewActivty.this));
    }
}
