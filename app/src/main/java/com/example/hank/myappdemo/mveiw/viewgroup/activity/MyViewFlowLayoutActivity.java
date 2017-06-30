package com.example.hank.myappdemo.mveiw.viewgroup.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.hank.myappdemo.R;


/**
 * Created by Jun on 2017/6/30.
 * 该界面用于展示，根据子控件的大小，来设置FlowLayout控件内部子控件的布局
 */

public class MyViewFlowLayoutActivity  extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_view_group_flow_layout);
    }
}
