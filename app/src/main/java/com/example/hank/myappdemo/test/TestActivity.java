package com.example.hank.myappdemo.test;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.hank.myappdemo.R;

/**
 * Created by Jun on 2017/4/19.
 * 用于代码模似测试时使用
 */

public class TestActivity extends AppCompatActivity {

    private FragmentTabHost mTabHost = null;
    private View indicator = null;

    private int[] datas = new int[]{102,120,1210};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_fragment);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(),R.id.flt_realcontent);

        Bundle bundle = new Bundle();
        bundle.putIntArray("datas",datas);
        // 添加tab名称和图标
        indicator = getIndicatorView("我的联系人", R.layout.test_indicator);
        mTabHost.addTab(mTabHost.newTabSpec("myContact")
                .setIndicator(indicator), TestFragment.class, bundle);

        indicator = getIndicatorView("陌生人", R.layout.test_indicator);
        mTabHost.addTab(
                mTabHost.newTabSpec("stranger").setIndicator(indicator),
                TestFragment.class, bundle);

        indicator = getIndicatorView("常联系人", R.layout.test_indicator);
        mTabHost.addTab(
                mTabHost.newTabSpec("alwaysContact").setIndicator(indicator),
                TestFragment.class, bundle);
    }
    private View getIndicatorView(String name, int layoutId) {
        View v = getLayoutInflater().inflate(layoutId, null);
        TextView tv = (TextView) v.findViewById(R.id.tabText);
        tv.setText(name);
        return v;
    }
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        mTabHost = null;
    }
}
