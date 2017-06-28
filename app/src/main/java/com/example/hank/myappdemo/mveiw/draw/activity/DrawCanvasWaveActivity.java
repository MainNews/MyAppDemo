package com.example.hank.myappdemo.mveiw.draw.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.hank.myappdemo.R;
import com.example.hank.myappdemo.mveiw.draw.view.MyViewDrawWave;

/**
 * Created by Jun on 2017/6/28.
 * 该类用于展示水波纹效果
 */

public class DrawCanvasWaveActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_view_draw_wave_layout);
        MyViewDrawWave drawWave = (MyViewDrawWave) findViewById(R.id.my_view_draw_wave);
        drawWave.startAnim();
    }
}
