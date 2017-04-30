package com.example.hank.myappdemo.mveiw.draw.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.example.hank.myappdemo.R;
import com.example.hank.myappdemo.mveiw.draw.view.MyViewDrawRegion;

/**
 * Created by Jun on 2017/4/30.
 * 该类用于展示Region(区域) 的功能
 */

public class DrawRegionViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_view_draw_noe_view_layout);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.draw_noe_view_layout);
        frameLayout.addView(new MyViewDrawRegion(DrawRegionViewActivity.this));

    }
}
