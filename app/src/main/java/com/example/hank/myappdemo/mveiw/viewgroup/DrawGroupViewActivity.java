package com.example.hank.myappdemo.mveiw.viewgroup;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.hank.myappdemo.R;
import com.example.hank.myappdemo.mveiw.viewgroup.activity.MyViewFlowLayoutActivity;
import com.example.hank.myappdemo.mveiw.viewgroup.activity.MyViewLinearLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Jun on 2017/4/23.
 * 该类用于展示自定义控件的入口
 */

public class DrawGroupViewActivity extends AppCompatActivity {

    @Bind(R.id.my_view_group_one_view)
    Button myViewGroupOneView;
    @Bind(R.id.my_view_group_two_view)
    Button myViewGroupTwoView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_view_group_activity);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.my_view_group_one_view, R.id.my_view_group_two_view})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_view_group_one_view://自定义ViewGroup，显示多个子控件，如LinearLayout
                Intent startMyLinearLayout = new Intent(DrawGroupViewActivity.this, MyViewLinearLayout.class);
                startActivity(startMyLinearLayout);
                break;
            case R.id.my_view_group_two_view://自定义ViewGroup，根据子控件的大小，将子控件放在相应的位置
                Intent startMyFlowLayout = new Intent(DrawGroupViewActivity.this, MyViewFlowLayoutActivity.class);
                startActivity(startMyFlowLayout);
                break;
        }
    }
}
