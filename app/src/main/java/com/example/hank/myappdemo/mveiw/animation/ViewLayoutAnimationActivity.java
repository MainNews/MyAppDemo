package com.example.hank.myappdemo.mveiw.animation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.hank.myappdemo.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Jun on 2017/5/10.
 * 该界面用于展示使用LayoutAnimation来实现Group控件的子控件的动画
 */

public class ViewLayoutAnimationActivity extends AppCompatActivity {
    private ListView layoutListView;
    private Button layoutButton;
    private ArrayAdapter listAdater;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_view_layout_animation_activity);
        layoutListView = (ListView) findViewById(R.id.layout_animation_list);
        listAdater = new ArrayAdapter<String>(this, android.R.layout
                .simple_expandable_list_item_1, getData());
        layoutListView.setAdapter(listAdater);
        layoutButton = (Button) findViewById(R.id.layout_animation_add_list);
        //点击添加List数据
        layoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listAdater.addAll(getData());
            }
        });


    }


    /**
     * 给数组Adapter添加数据
     */
    public List<String> getData() {
        List<String> data = new ArrayList<String>();
        data.add("测试数据1");
        data.add("测试数据2");
        data.add("测试数据3");
        data.add("测试数据4");
        return data;
    }
}
