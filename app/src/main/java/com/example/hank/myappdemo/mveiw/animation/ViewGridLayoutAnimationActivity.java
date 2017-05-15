package com.example.hank.myappdemo.mveiw.animation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.example.hank.myappdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jun on 2017/5/12.
 * 该类使用GridLayoutAnimation，以XML的方式进行ViewGroup子控件动画
 */

public class ViewGridLayoutAnimationActivity extends AppCompatActivity {

    private GridAdapter gridAdapter;
    private List<String> mDatas = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_view_grid_layout_animation);
        /*
            填充GridView
         */
        GridView gridView = (GridView) findViewById(R.id.my_view_grid_layout);
        mDatas.addAll(getData());
        gridAdapter = new GridAdapter();
        gridView.setAdapter(gridAdapter);

        /*
            按钮点击事件响应
         */
        Button button = (Button) findViewById(R.id.my_view_grid_add_data);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addData();
            }
        });

    }

    private List<String> getData() {

        List<String> data = new ArrayList<String>();
        for (int i = 1; i < 35; i++) {
            data.add("DATA " + i);
        }
        return data;
    }


    public void addData() {
        mDatas.addAll(mDatas);
        gridAdapter.notifyDataSetChanged();
    }

    public class GridAdapter extends BaseAdapter {

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            TextView textView = new TextView(ViewGridLayoutAnimationActivity.this);
            textView.setText(mDatas.get(position));
            textView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));

            return textView;
        }

        @Override
        public int getCount() {
            return mDatas.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

    }
}
 /*
 以代码的方式进行GridLayoutAnimationController动画
        构造方法:
            public GridLayoutAnimationController(Animation animation)
            public GridLayoutAnimationController(Animation animation, float columnDelay, float rowDelay)
        * 设置列动画开始延迟
            public void setColumnDelay(float columnDelay)
        * 设置行动画开始延迟
            public void setRowDelay(float rowDelay)
        * 设置gridview动画的入场方向。取值有：DIRECTION_BOTTOM_TO_TOP、DIRECTION_TOP_TO_BOTTOM、DIRECTION_LEFT_TO_RIGHT、DIRECTION_RIGHT_TO_LEFT
            public void setDirection(int direction)
        * 动画开始优先级，取值有PRIORITY_COLUMN、PRIORITY_NONE、PRIORITY_ROW
            public void setDirectionPriority(int directionPriority)
        如果使用代码的方式进行动画，那么在XML布局中就不用设置animation动画属性，而是直接使用如下代码：
            Animation animation = AnimationUtils.ladAnimation(ViewGridLayoutAnimation.this,R.anim.slide_in_left);
            GridLayoutAnimationController controller = new GridLayoutAnimationController(animation);
            controller.setColumnDelay(0.75f);
            controller.setRowDelay(0.5f);
            controller.setDirection(GridLayoutAnimationController.DIRECTION_BOTTOM_TO_TOP
                                        |GridLayoutAnimationController.DIRECTION_LEFT_TO_RIGHT);
            controller.serDirectionPriority(GridLayoutAnimationController.PRIORITY_NONE);
            gridView.setLayoutAnimation(controller);
            gridView.startLayoutAnimation();

         */