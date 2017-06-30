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
    private MyViewDrawWave drawWave;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_view_draw_wave_layout);
         drawWave = (MyViewDrawWave) findViewById(R.id.my_view_draw_wave);
        drawWave.startAnim();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //当退出界面时，清除所有动画，否则会出现OOM
        drawWave.stopAnim();

    }
}
