package com.example.hank.myappdemo.mveiw.draw;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.hank.myappdemo.R;
import com.example.hank.myappdemo.mveiw.draw.activity.DrawCanvasGoActivity;
import com.example.hank.myappdemo.mveiw.draw.activity.DrawCanvasGoToActivity;
import com.example.hank.myappdemo.mveiw.draw.activity.DrawCanvasTextViewActivity;
import com.example.hank.myappdemo.mveiw.draw.activity.DrawPathViewActivity;
import com.example.hank.myappdemo.mveiw.draw.activity.DrawRegionViewActivity;
import com.example.hank.myappdemo.mveiw.draw.activity.DrawTextPathViewActivity;
import com.example.hank.myappdemo.mveiw.draw.activity.DrawViewActivty;

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
    @Bind(R.id.my_view_draw_path_text)
    Button myViewDrawPathText;
    @Bind(R.id.my_view_draw_canvas_text)
    Button myViewDrawCanvasText;
    @Bind(R.id.my_view_draw_regoin_text)
    Button myViewDrawRegoinText;
    @Bind(R.id.my_view_draw_go_canvas_text)
    Button myViewDrawGoCanvasText;
    @Bind(R.id.my_view_draw_go_canvas_to_text)
    Button myViewDrawGoCanvasToText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_view_draw_activity_layout);
        ButterKnife.bind(this);


    }

    @OnClick({R.id.my_view_draw_paint_circle, R.id.my_view_draw_path_line,
            R.id.my_view_draw_path_text, R.id.my_view_draw_canvas_text,
            R.id.my_view_draw_regoin_text, R.id.my_view_draw_go_canvas_text,
            R.id.my_view_draw_go_canvas_to_text})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_view_draw_paint_circle:
                /*
                 * Paint 与 Canvas 的初步使用
                 */
                Intent startDraw = new Intent(DrawViewStepActivity.this, DrawViewActivty.class);
                startActivity(startDraw);
                break;
            case R.id.my_view_draw_path_line:
                /*
                    画路线与文字
                 */
                Intent startDrawPath = new Intent(DrawViewStepActivity.this, DrawPathViewActivity
                        .class);
                startActivity(startDrawPath);
                break;
            case R.id.my_view_draw_path_text:
                 /*
                    使用Paint画文字
                 */
                Intent startDrawTextPath = new Intent(DrawViewStepActivity.this,
                        DrawTextPathViewActivity.class);
                startActivity(startDrawTextPath);
                break;
            case R.id.my_view_draw_canvas_text:
                 /*
                    使用Canvas画文字
                 */
                Intent startDrawCanvasText = new Intent(DrawViewStepActivity.this,
                        DrawCanvasTextViewActivity.class);
                startActivity(startDrawCanvasText);
                break;
            case R.id.my_view_draw_regoin_text:
                /*
                    使用Region（区域）画图
                 */
                Intent startDrawRegion = new Intent(DrawViewStepActivity.this,
                        DrawRegionViewActivity.class);
                startActivity(startDrawRegion);
                break;
            case R.id.my_view_draw_go_canvas_text:
                /*
                    canvas变换与操作，主要是对canvas画布的深入了解
                 */
                Intent startDrawCanvas = new Intent(DrawViewStepActivity.this,
                        DrawCanvasGoActivity.class);
                startActivity(startDrawCanvas);
                break;
            case R.id.my_view_draw_go_canvas_to_text:
                /*
                    canvas变换与操作，主要展示画布的裁剪与保存还有恢复的操作
                 */
                Intent startDrawCanvasTo = new Intent(DrawViewStepActivity.this,
                        DrawCanvasGoToActivity.class);
                startActivity(startDrawCanvasTo);
                break;
        }
    }


}
