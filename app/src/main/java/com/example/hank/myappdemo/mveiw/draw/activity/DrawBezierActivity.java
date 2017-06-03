package com.example.hank.myappdemo.mveiw.draw.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.hank.myappdemo.R;
import com.example.hank.myappdemo.mveiw.draw.view.MyViewDrawBezier;

/**
 * Created by Jun on 2017/5/31.
 * 该界面用于展示绘制的贝赛尔曲线
 */

public class DrawBezierActivity extends AppCompatActivity {

    private MyViewDrawBezier myViewDrawBezier;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_view_draw_activity_bezier_layout);

        myViewDrawBezier = (MyViewDrawBezier) findViewById(R.id.view_draw_bezier_my_draw_bezier);
        findViewById(R.id.view_draw_bezier_but_reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewDrawBezier.reset();
            }
        });

    }
}
