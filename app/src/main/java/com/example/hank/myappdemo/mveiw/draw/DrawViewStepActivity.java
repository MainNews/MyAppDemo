package com.example.hank.myappdemo.mveiw.draw;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.hank.myappdemo.R;
import com.example.hank.myappdemo.mveiw.draw.activity.DrawPathViewActivity;
import com.example.hank.myappdemo.mveiw.draw.activity.DrawViewActivty;
import com.example.hank.myappdemo.mveiw.draw.view.MyViewDrawPath;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Jun on 2017/4/23.
 * 该类用于展示进入绘图入口，选择进入哪一个绘图效果
 */

public class DrawViewStepActivity extends AppCompatActivity {

    @Bind(R.id.my_view_draw_paint_circle)
    Button myViewDrawPaintCircle;
    @Bind(R.id.my_view_draw_path_line)
    Button myViewDrawPathLine;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_view_draw_activity_layout);
        ButterKnife.bind(this);


    }

    @OnClick({R.id.my_view_draw_paint_circle, R.id.my_view_draw_path_line})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_view_draw_paint_circle:
                /*
                 * Paint 与 Canvas 的初步使用
                 */
                Intent startDraw = new Intent(DrawViewStepActivity.this,DrawViewActivty.class);
                startActivity(startDraw);
                break;
            case R.id.my_view_draw_path_line:
                /*
                    画路线与文字
                 */
                Intent startDrawPath = new Intent(DrawViewStepActivity.this,DrawPathViewActivity.class);
                startActivity(startDrawPath);
                break;
        }
    }


}
