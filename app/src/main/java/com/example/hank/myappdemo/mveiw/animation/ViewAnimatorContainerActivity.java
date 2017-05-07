package com.example.hank.myappdemo.mveiw.animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.hank.myappdemo.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Jun on 2017/5/2.
 * 该类用于展示使用XML实现联合动画
 */

public class ViewAnimatorContainerActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.my_view_container_xml_animator)
    Button myViewContainerXmlAnimator;
    @Bind(R.id.my_view_container_xml_animator_obj)
    Button myViewContainerXmlAnimatorObj;
    @Bind(R.id.my_view_container_xml_animator_set)
    Button myViewContainerXmlAnimatorSet;
    private Button menu;
    private Button item0;
    private Button item1;
    private Button item2;
    private Button item3;
    private Button item4;

    //记录菜单是否弹出
    private boolean isMenuOpen = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_view_activity_container_layout);
        ButterKnife.bind(this);
        menu = (Button) findViewById(R.id.menu);
        menu.setOnClickListener(this);

        item0 = (Button) findViewById(R.id.item0);
        item0.setOnClickListener(this);

        item1 = (Button) findViewById(R.id.item1);
        item1.setOnClickListener(this);

        item2 = (Button) findViewById(R.id.item2);
        item2.setOnClickListener(this);

        item3 = (Button) findViewById(R.id.item3);
        item3.setOnClickListener(this);

        item4 = (Button) findViewById(R.id.item4);
        item4.setOnClickListener(this);
    }

    @OnClick({R.id.my_view_container_xml_animator, R.id.my_view_container_xml_animator_obj,
            R.id.my_view_container_xml_animator_set})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_view_container_xml_animator:
                //valueAniamtor
                startContainerXmlAnimator();
                break;
            case R.id.my_view_container_xml_animator_obj:
                //ObjectAnimator
                startContainerXmlObjectAnimator();
                break;
            case R.id.my_view_container_xml_animator_set:
                //AnimatorSet
                startContainerXmlAnimatorSet();
                break;
        }
    }
    @Override
    public void onClick(View view) {
        if (view == menu){
            if (!isMenuOpen){
                isMenuOpen = true;
                doAnimateOpen(item0,0,5,300);
                doAnimateOpen(item1,1,5,300);
                doAnimateOpen(item2,2,5,300);
                doAnimateOpen(item3,3,5,300);
                doAnimateOpen(item4,4,5,300);
            }else {
                isMenuOpen = false;
                doAnimateClose(item0,0,5,300);
                doAnimateClose(item1,1,5,300);
                doAnimateClose(item2,2,5,300);
                doAnimateClose(item3,3,5,300);
                doAnimateClose(item4,4,5,300);
            }
        }else {
            Toast.makeText(this, "你点击了" + view, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 该方法用于将控件收回
     * @param view 弹出的控件
     * @param index 当前菜单的坐标
     * @param total 控件的总数，用来获取每个夹角的度数值
     * @param radius 半径值，可以理解为控件与menu控件的移动距离
     *  收回菜单就是把弹出菜单的动画反过来，让它从translateX,translateY的位置上回到0点，scaleX、scaleY、alpha的值从1变到0即可
     */
    private void doAnimateClose(final View view, int index, int total, int radius) {

        double degree = Math.PI * index / ((total - 1) * 2);//求得夹角，只是换了一种方式
        int translationX = -(int) (radius * Math.sin(degree));
        int translationY = -(int) (radius * Math.cos(degree));
        AnimatorSet set = new AnimatorSet();
        //包含平移、缩放和透明度动画
        set.playTogether(
                ObjectAnimator.ofFloat(view, "translationX", translationX, 0),
                ObjectAnimator.ofFloat(view, "translationY", translationY, 0),
                ObjectAnimator.ofFloat(view, "scaleX", 1f, 0f),
                ObjectAnimator.ofFloat(view, "scaleY", 1f, 0f),
                ObjectAnimator.ofFloat(view, "alpha", 1f, 0f));

        set.addListener(new Animator.AnimatorListener() {
            @Override//在动画结束时将控件放大，解决移动控件事属性还在的问题
            public void onAnimationEnd(Animator animator) {
                if (view.getVisibility() == View.VISIBLE) {
                    view.setVisibility(View.GONE);
                }

            }
            @Override
            public void onAnimationStart(Animator animator) {
            }


            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });

        set.setDuration(1 * 500).start();

    }

    /**
     * 该方法用于将控件弹出
     * @param view 弹出的控件
     * @param index 当前菜单的坐标
     * @param total 控件的总数，用来获取每个夹角的度数值
     * @param radius 半径值，可以理解为控件与menu控件的移动距离
     */
    private void doAnimateOpen(View view, int index, int total, int radius) {
        //如果控件显示，则显示
        if (view.getVisibility() != View.VISIBLE){
            view.setVisibility(View.VISIBLE);
        }
        /*
        首先，是求得两个菜单的夹角，即公式里的a值。Math.toRadians(90)/(total - 1)表示90度被分成了total-1份，其中每一份的弧度值；
        我们前面讲过，假设每一份的弧度值是22度，那么当前菜单与Y轴的夹角就是22 * index度。
                这里类似，当前菜单与y轴的弧度值就是Math.toRadians(90)/(total - 1) * index
        在求得夹角以后，直接利用translationX = radius * sin(a)就可以得到x轴的移动距离，
                但又因为菜单是向左移动了translationX距离。所以根据坐标系向下为正，向右为正的原则。
                这里的移动距离translationX应该是负值。我们需要的translationY，因为是向上移动，所以也是负值：
         */
        double degree = Math.toRadians(90)/(total -1) * index;//得到角度，即夹角
        int translationX = -(int) (radius * Math.sin(degree));
        int translationY = -(int) (radius * Math.cos(degree));

        AnimatorSet animatorSet = new AnimatorSet();
        //包含平移，缩放和透明度动画
        animatorSet.playTogether(
                ObjectAnimator.ofFloat(view,"translationX",0,translationX),
                ObjectAnimator.ofFloat(view,"translationY",0,translationY),
                ObjectAnimator.ofFloat(view,"scaleX",0f,1f),
                ObjectAnimator.ofFloat(view,"scaleY",0f,1f),
                ObjectAnimator.ofFloat(view,"alpha",0f,1)
        );

        //设置动画时间
        animatorSet.setDuration(1 * 500).start();


    }

    /**
     * 使用AnimatorSet.xml 的形式来实现动画的显示
     */
    private void startContainerXmlAnimatorSet() {
        AnimatorSet animatorSet = (AnimatorSet) AnimatorInflater.loadAnimator
                (ViewAnimatorContainerActivity.this,
                R.animator.container_set_animator);
        animatorSet.setTarget(myViewContainerXmlAnimatorSet);
        animatorSet.start();

    }

    /**
     * 使用XML的形式实现ObjectAnimator动画
     */
    private void startContainerXmlObjectAnimator() {
        ObjectAnimator animator = (ObjectAnimator) AnimatorInflater.loadAnimator(
                ViewAnimatorContainerActivity.this, R.animator.container_object_animator);
        animator.setTarget(myViewContainerXmlAnimatorObj);
        animator.start();
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
