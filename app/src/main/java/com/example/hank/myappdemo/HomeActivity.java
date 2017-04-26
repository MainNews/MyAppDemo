package com.example.hank.myappdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.example.hank.myappdemo.base.BaseActivtiy;
import com.example.hank.myappdemo.map.MAPActivity;
import com.example.hank.myappdemo.mveiw.MyViewActivity;
import com.example.hank.myappdemo.shareask.ShareSDKActvity;
import com.example.hank.myappdemo.test.TestActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Jun on 2017/4/19.
 * 通过该界面跳转到相应的功能界面
 */

public class HomeActivity extends BaseActivtiy {
    @Bind(R.id.home_map)
    Button homeMap;
    @Bind(R.id.home_my_view)
    Button homeMyView;
    @Bind(R.id.home_test)
    Button homeTest;
    @Bind(R.id.home_share_sdk)
    Button homeShareSdk;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity_layout);
        ButterKnife.bind(this);


    }

    @OnClick({R.id.home_map, R.id.home_my_view, R.id.home_test,R.id.home_share_sdk})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_map://地图
                Intent startMap = new Intent(HomeActivity.this, MAPActivity.class);
                startActivity(startMap);
                break;
            case R.id.home_my_view://自定义控件
                Intent starView = new Intent(HomeActivity.this, MyViewActivity.class);
                startActivity(starView);
                break;
            case R.id.home_test://测试
                Intent starTest = new Intent(HomeActivity.this, TestActivity.class);
                startActivity(starTest);
                break;
            case R.id.home_share_sdk://第三方分享
                Intent starShare = new Intent(HomeActivity.this, ShareSDKActvity.class);
                startActivity(starShare);
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
