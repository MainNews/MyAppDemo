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
import com.example.hank.myappdemo.shareask.PublicStaticData;
import com.example.hank.myappdemo.shareask.Utils.ShareSDKUtils;
import com.example.hank.myappdemo.shareask.activity.ShareSDKRegisterActivity;
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
        PublicStaticData.myShareSDK= new ShareSDK();
        PublicStaticData.myShareSDK.initSDK(getApplicationContext());
        setContentView(R.layout.home_activity_layout);
        ButterKnife.bind(this);


    }

    @OnClick({R.id.home_map, R.id.home_my_view, R.id.home_test,R.id.home_share_sdk,
    R.id.home_share_sdk_register})
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
                String picurl = "http://7sby7r.com1.z0.glb.clouddn.com/CYSJ_02.jpg";
                showShare("Sina分享测试sharesdk","这里测试分享的内容",null);
                break;
            case R.id.home_share_sdk_register:
                Intent startRegisterIntent = new Intent(HomeActivity.this, ShareSDKRegisterActivity.class);
                startActivity(startRegisterIntent);
                break;
        }
    }
    /**
     * 启动ShareSDK自带的分享九宫格，这里不使用
     *  title 标题
     *  text 内容
     *  picurl 图片链接 *
     * QQ和QQ空间设置分享链接使用setTitleUrl();
     * 设置标题：setTitle
     * 设置内容：setText
     * 设置网络图片：oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
     * 设置本地图片： //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片 *
     * 微信 * url仅在微信（包括好友和朋友圈）中使用 * oks.setUrl("http://qq.com"); */
    private void showShare(String title,String text,String picurl) {
        OnekeyShare oks = new OnekeyShare(); //关闭sso授权
        oks.disableSSOWhenAuthorize();
        oks.setTitle(title);
        oks.setText(text);
        if(picurl!=null){
            oks.setImageUrl(picurl);
        }
        oks.setCallback(ShareSDKUtils.mPlatformActionListener);
        // 启动分享
        oks.show(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
