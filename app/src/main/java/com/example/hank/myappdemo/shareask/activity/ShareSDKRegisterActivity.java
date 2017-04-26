package com.example.hank.myappdemo.shareask.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.hank.myappdemo.R;
import com.example.hank.myappdemo.shareask.PublicStaticData;
import com.example.hank.myappdemo.shareask.Utils.ShareSDKUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;

/**
 * Created by Jun on 2017/4/26.
 * 用于展示登录功能
 */

public class ShareSDKRegisterActivity extends AppCompatActivity {
    @Bind(R.id.share_sdk_register_qq)
    Button shareSdkRegisterQq;
    @Bind(R.id.share_sdk_register_xina)
    Button shareSdkRegisterXina;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PublicStaticData.myShareSDK= new ShareSDK();
        PublicStaticData.myShareSDK.initSDK(getApplicationContext());
        setContentView(R.layout.share_sdk_register_activity_layout);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.share_sdk_register_qq, R.id.share_sdk_register_xina})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.share_sdk_register_qq://进入QQ登录
                ShareSDKUtils.Login(QQ.NAME);
                break;
            case R.id.share_sdk_register_xina://进入新浪登录
                ShareSDKUtils.Login(SinaWeibo.NAME);
                break;
        }
    }
}
