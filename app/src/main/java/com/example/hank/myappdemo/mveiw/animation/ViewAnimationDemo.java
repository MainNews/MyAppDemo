package com.example.hank.myappdemo.mveiw.animation;

import android.content.Intent;
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
 * Created by Jun on 2017/4/23.
 */

public class ViewAnimationDemo extends AppCompatActivity {
    @Bind(R.id.alpha_scale_translate_rotate)
    Button alphaScaleTranslateRotate;
    @Bind(R.id.view_animation)
    Button viewAnimation;
    @Bind(R.id.value_animator)
    Button valueAnimator;
    @Bind(R.id.object_animator)
    Button objectAnimator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_view_animation);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.alpha_scale_translate_rotate, R.id.view_animation, R.id.value_animator, R.id
            .object_animator})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.alpha_scale_translate_rotate:
                Intent startAnimation = new Intent(ViewAnimationDemo.this,
                        Alpha_Scale_Translate_Rotate_Set.class);
                startActivity(startAnimation);
                break;
            case R.id.view_animation:
                Intent startViewAnimation = new Intent(ViewAnimationDemo.this,
                        ViewAnimation.class);
                startActivity(startViewAnimation);
                break;
            case R.id.value_animator:
                break;
            case R.id.object_animator:
                break;
        }
    }
}
