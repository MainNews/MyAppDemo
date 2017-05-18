package com.example.hank.myappdemo.mveiw.viewgroup.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.hank.myappdemo.R;

/**
 * Created by Jun on 2017/5/18.
 *
 * 第一个自定义控件，类似LinearLayout相同的效果
 */

public class MyViewLinearLayout extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_view_group_my_linear_layout);
    }
}
