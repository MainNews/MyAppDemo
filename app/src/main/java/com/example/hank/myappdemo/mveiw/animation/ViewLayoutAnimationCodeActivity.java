package com.example.hank.myappdemo.mveiw.animation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.hank.myappdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jun on 2017/5/12.
 * 该界面用于展示使用LayoutAnimation的代码形式来实现ViewGroup子控件的动画效果
 */

public class ViewLayoutAnimationCodeActivity extends AppCompatActivity{

    private ListView listView;
    private ArrayAdapter codeAdapter;
    private Button codeButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_view_layout_animation_code_activity);

        listView = (ListView) findViewById(R.id.layout_animation_code_list);
        codeButton = (Button) findViewById(R.id.layout_animation_code_add_list);
        codeAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,
                                                getData());
        listView.setAdapter(codeAdapter);

        codeButton.setOnClickListener(new View.OnClickListener() {//监听添加数据
            @Override
            public void onClick(View view) {
                codeAdapter.addAll(getData());
            }
        });

        //通过加载XML文件动画得到Animation动画对象
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.slid_in_left);
        //得到LayoutAnimation在代码中对应的LayoutAnimationController对象
        LayoutAnimationController codeLayoutAniamtion = new LayoutAnimationController(animation);
        /*
            设置动画顺序
            取值有：normal(正常)，reverse(倒序)，random(随机)
         */
        codeLayoutAniamtion.setOrder(LayoutAnimationController.ORDER_NORMAL);
        codeLayoutAniamtion.setDelay(0.3f);//设置动画间隔时间
        //为ListView设置LayoutAnimationController属性
        listView.setLayoutAnimation(codeLayoutAniamtion);
        listView.startLayoutAnimation();



    }

    public List<String> getData() {
        List<String> data = new ArrayList<>();
        for (int i = 0;i < 10 ;i++){
            data.add("代码测试"+i);
        }
        return data;
    }
}
