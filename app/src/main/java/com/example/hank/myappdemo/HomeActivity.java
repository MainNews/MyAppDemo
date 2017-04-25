package com.example.hank.myappdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.hank.myappdemo.base.BaseActivtiy;
import com.example.hank.myappdemo.map.MAPActivity;
import com.example.hank.myappdemo.mveiw.MyViewActivity;
import com.example.hank.myappdemo.test.TestActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

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
        ShareSDK.initSDK(getApplicationContext());
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
                Toast.makeText(this,"我被点击了",Toast.LENGTH_SHORT).show();
                OnekeyShare oks = new OnekeyShare();
                //关闭sso授权
                oks.disableSSOWhenAuthorize();
                // title标题：微信、QQ（新浪微博不需要标题）
                // oks.setTitle("我是分享标题");  //最多30个字符

                // text是分享文本，所有平台都需要这个字段
                oks.setText("我是分享文本,我也不知道能不能分享");

                // imagePath是图片的本地路径：除Linked-In以外的平台都支持此参数
                //oks.setImagePath(Environment.getExternalStorageDirectory() + "/meinv.jpg");//确保SDcard下面存在此张图片

                //网络图片的url：所有平台
                oks.setImageUrl("http://7sby7r.com1.z0.glb.clouddn.com/CYSJ_02.jpg");//网络图片rul

                // url：仅在微信（包括好友和朋友圈）中使用
               // oks.setUrl("http://sharesdk.cn");   //网友点进链接后，可以看到分享的详情

                // Url：仅在QQ空间使用
                //oks.setTitleUrl("http://www.baidu.com");  //网友点进链接后，可以看到分享的详情

                oks.show(this);


                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ShareSDK.stopSDK();
    }
}
