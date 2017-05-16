package com.example.hank.myappdemo.mveiw.animation.draw;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.hank.myappdemo.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Jun on 2017/5/16.
 * <p>
 * 该类用于制作ViewGroup在添加子控件时也带有动画效果
 */

public class ViewAnimateLayoutChanges_LayoutTransition extends AppCompatActivity {

    @Bind(R.id.my_view_changes_layout)
    LinearLayout myViewChangesLayout;
    @Bind(R.id.my_view_transition_layout)
    LinearLayout myViewTransitionLayout;

    @Bind(R.id.my_view_changes_transition_add_data)
    Button myViewChangesTransitionAddData;
    @Bind(R.id.my_view_changes_transition_remove_data)
    Button myViewChangesTransitionRemoveData;

    private int position = 0;
    private int indext = 0;
    private LayoutTransition layoutTransition;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
            该布局文件中添加了android:animateLayoutChanges = "true"属性，用于添加控件时有动画属性
                注意，如果只设置该属性，就只会是默认的动画，该属性在API1的时候就有了
         */
        setContentView(R.layout.my_view_animation_layout_changes_transition);
        ButterKnife.bind(this);

        /*
            setAnimator中的值有以下几种：
                APPEARING —— 元素在容器中出现时所定义的动画。
                DISAPPEARING —— 元素在容器中消失时所定义的动画。
           以下两个必须使用PropertyValuesHolder所构造的动画才会有效果，否则无效
                CHANGE_APPEARING —— 由于容器中要显现一个新的元素，其它需要变化的元素所应用的动画
                CHANGE_DISAPPEARING —— 当容器中某个元素消失，其它需要变化的元素所应用的动画

         */
        layoutTransition = new LayoutTransition();
        //入场动画：View在没有显示的情况下触发
        ObjectAnimator animIn = ObjectAnimator.ofFloat(null,"rotationY",0f,360f,0f);
        layoutTransition.setAnimator(LayoutTransition.APPEARING,animIn);
        layoutTransition.setDuration(2000);

        //出场动画：View在显示的情况下触发
        ObjectAnimator animOut = ObjectAnimator.ofFloat(null,"rotation",0f,90f,0f);
        layoutTransition.setAnimator(LayoutTransition.DISAPPEARING,animOut);
        layoutTransition.setDuration(2000);

        myViewTransitionLayout.setLayoutTransition(layoutTransition);

    }

    /**
     * 使用android:animateLayoutChanges = "true"属性
     * 添加控件，并添加到第一个位置，即0；
     */
    private void addButtonView() {
        position++;
        Button button = new Button(this);
        button.setText("Button" + position);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        button.setLayoutParams(params);
        myViewChangesLayout.addView(button, 0);
    }

    /**
     * 使用android:animateLayoutChanges = "true"属性
     * 删除控件，删除ViewGroup的第一个子控件
     */
    private void removeButtonView() {
        if (position > 0) {
            myViewChangesLayout.removeViewAt(0);
        }
        position--;

    }
    /**
     * 使用LayoutTransition类
     * 添加控件，并添加到第一个位置，即0；
     */
    private void addButtonView_Transition() {
        indext++;
        Button button = new Button(this);
        button.setText("Button" + indext);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        button.setLayoutParams(params);
        myViewTransitionLayout.addView(button,0);
    }

    /**
     * 使用LayoutTransition类
     * 删除控件，删除ViewGroup的第一个子控件
     */
    private void removeButtonView_Transition() {
        if (indext > 0) {
            myViewTransitionLayout.removeViewAt(0);
        }
        indext--;

    }

    @OnClick({R.id.my_view_changes_transition_add_data, R.id
            .my_view_changes_transition_remove_data})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_view_changes_transition_add_data:
                addButtonView();
                addButtonView_Transition();
                break;
            case R.id.my_view_changes_transition_remove_data:
                removeButtonView();
                removeButtonView_Transition();
                break;
        }
    }
}
