package com.example.hank.myappdemo.mveiw.draw.activity;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.example.hank.myappdemo.R;
import com.example.hank.myappdemo.mveiw.draw.view.MyViewDrawPaintFunction;

/**
 * Created by Jun on 2017/7/2.
 * 该界面展示Paint的函数方法显示效果
 */

public class DrawCanvasPaintFunctionActivity extends AppCompatActivity {
    private ValueAnimator valueAnimator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_view_draw_text_path_layout);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.my_view_text_path_layout);
        MyViewDrawPaintFunction paintFunction = new MyViewDrawPaintFunction(DrawCanvasPaintFunctionActivity.this);
        frameLayout.addView(paintFunction);
       valueAnimator =  paintFunction.startAnim();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (valueAnimator != null){
            valueAnimator.cancel();
            valueAnimator = null;
        }
    }
}
