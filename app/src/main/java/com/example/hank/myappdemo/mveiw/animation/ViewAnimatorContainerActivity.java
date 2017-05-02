package com.example.hank.myappdemo.mveiw.animation;

import android.animation.AnimatorInflater;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.hank.myappdemo.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Jun on 2017/5/2.
 * 该类用于展示使用XML实现联合动画
 */

public class ViewAnimatorContainerActivity extends AppCompatActivity {

    @Bind(R.id.my_view_container_xml_animator)
    Button myViewContainerXmlAnimator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_view_activity_container_layout);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.my_view_container_xml_animator})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_view_container_xml_animator:
                startContainerXmlAnimator();
                break;
        }
    }

    /**
     * 该方法用于演示使用XML来完成连合动画
     */
    private void startContainerXmlAnimator() {
        ValueAnimator valueAnimator = (ValueAnimator) AnimatorInflater.loadAnimator(
                ViewAnimatorContainerActivity.this, R.animator.container_animator);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                /*
                    由于我们xml中根属性是<animator/>所以它对应的是ValueAnimator，所以在加载后，将其强转为valueAnimator
                 */
                int offset = (int) animator.getAnimatedValue();
                myViewContainerXmlAnimator.layout(offset, offset, myViewContainerXmlAnimator
                        .getWidth() + offset, myViewContainerXmlAnimator.getHeight() + offset);
            }
        });
        valueAnimator.start();
    }
}
