package com.example.hank.myappdemo.map;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.hank.myappdemo.R;
import com.example.hank.myappdemo.map.base.MAPBaseActivity;
import com.example.hank.myappdemo.map.fragment.MainTab;

/**
 * Created by Jun on 2017/4/24.
 * 该类用于展示百度地图的路线规划
 */

public class MAPPathActivity extends MAPBaseActivity {
    private FragmentTabHost pathTabHost;
    private Bundle bundle;
    private TextView backText;
    @Override
    public int getLayoutRes() {
        return R.layout.map_activity_path_layout;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取点击的开始点与结束点
        Intent pathIntent = getIntent();
        bundle = pathIntent.getExtras();

        /** 通过传递过来的Intent对象，获取路线规划的起点与终点 */
        pathTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        pathTabHost.setup(this, getSupportFragmentManager(), R.id.flt_realcontent);
        pathTabHost.setBackgroundColor(Color.TRANSPARENT);
        backText = (TextView) findViewById(R.id.map_activity_path_text_back);
        initListener();
        initTab();
    }

    private void initListener() {
        backText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    /**
     * 初始化标签
     */
    private void initTab() {
        MainTab[] values = MainTab.values();
        for (int i = 0; i < values.length; i++) {
            MainTab mainTab = values[i];
            String tag = mainTab.getTag();
            TabHost.TabSpec tabSpec = pathTabHost.newTabSpec(tag);//添加标识Tag
            tabSpec.setIndicator(getIndicatorView(mainTab.getTitle(), R.layout.test_indicator));//添加标签
            pathTabHost.addTab(tabSpec, mainTab.getClz(), bundle);
        }
    }
    private View getIndicatorView(String name, int layoutId) {
        View v = getLayoutInflater().inflate(layoutId, null);
        TextView tv = (TextView) v.findViewById(R.id.tabText);
        tv.setText(name);
        return v;
    }

    @Override
    public void initMap() {

    }

    @Override
    public void initMapLsetener() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
