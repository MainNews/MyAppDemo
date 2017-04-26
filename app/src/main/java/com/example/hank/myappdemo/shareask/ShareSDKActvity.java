package com.example.hank.myappdemo.shareask;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.hank.myappdemo.HomeActivity;
import com.example.hank.myappdemo.R;
import com.example.hank.myappdemo.shareask.Utils.ShareSDKUtils;
import com.example.hank.myappdemo.shareask.activity.ShareSDKRegisterActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by Jun on 2017/4/26.
 * 用于进入ShareSDK功能界面
 */

public class ShareSDKActvity extends AppCompatActivity {
    @Bind(R.id.share_sdk_register)
    Button shareSdkRegister;
    @Bind(R.id.share_sdk_share)
    Button shareSdkShare;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PublicStaticData.myShareSDK= new ShareSDK();
        PublicStaticData.myShareSDK.initSDK(getApplicationContext());
        setContentView(R.layout.share_sdk_activity_layout);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.share_sdk_register, R.id.share_sdk_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.share_sdk_register://登录
                Intent startRegisterIntent = new Intent(ShareSDKActvity.this, ShareSDKRegisterActivity.class);
                startActivity(startRegisterIntent);

                break;
            case R.id.share_sdk_share://分享
                String picurl = "http://7sby7r.com1.z0.glb.clouddn.com/CYSJ_02.jpg";
                showShare("Sina分享测试sharesdk","这里测试分享的内容",null);
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
}
