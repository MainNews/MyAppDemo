package com.example.hank.myappdemo.mveiw.animation;

import android.animation.Animator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.hank.myappdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jun on 2017/5/17.
 * 该类用于展示使用ListView来显示图片添加时的动画，解决AndroidAPI版本低带来的问题
 * //实际开发中不会出现这里问题，因为现在一般不会使用API11以下的版本
 */

public class ViewListViewImgActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_view_animation_img_list_layout);
        List<Drawable> mDrawables = new ArrayList<>();
        mDrawables.add(getResources().getDrawable(R.drawable.ssdk_oks_classic_qq));
        mDrawables.add(getResources().getDrawable(R.drawable.ssdk_oks_classic_flickr));
        mDrawables.add(getResources().getDrawable(R.drawable.ssdk_oks_classic_kaixin));
        mDrawables.add(getResources().getDrawable(R.drawable.ssdk_oks_classic_wechat));
        mDrawables.add(getResources().getDrawable(R.drawable.ssdk_oks_classic_email));
        mDrawables.add(getResources().getDrawable(R.drawable.ssdk_oks_classic_dingding));
        mDrawables.add(getResources().getDrawable(R.drawable.ssdk_oks_classic_facebook));
        mDrawables.add(getResources().getDrawable(R.drawable.ssdk_oks_classic_sinaweibo));
        mDrawables.add(getResources().getDrawable(R.drawable.ssdk_oks_classic_tencentweibo));

        ListView mListView = (ListView) findViewById(R.id.my_view_animation_img_list);
        MyListAdapter myListAdapter = new MyListAdapter(this, mListView, mDrawables, 10);
        mListView.setAdapter(myListAdapter);


    }


    public class MyListAdapter extends BaseAdapter {


        private List<Drawable> mDrawables = new ArrayList<>();
        private int mLength = 0;
        private LayoutInflater mInflater;
        private Context mContext;
        private ListView mListView;


        private Animation animation;

        private boolean isScrollDown = false;
        private int mFirstTop, mFirstPosition;

        AbsListView.OnScrollListener mOnScrollListener = new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            /**
             *
             * @param absListView   当前ListView对象
             * @param firstVisibleItem 表示当前Item在速个ListView中的位置
             * @param visibleItemCount 表示当前屏幕有几条可见的item
             * @param totalItemCount 表示当前ListView总共有几条item
             */
            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int
                    visibleItemCount,
                                 int totalItemCount) {
                View firstChild = absListView.getChildAt(0);//获得ListView中显示的第一个item控件
                if (firstChild == null) return;
                int top = firstChild.getTop();
                /**
                 * firstVisibleItem > mFirstPosition表示向下滑动一整个Item
                 * mFirstTop > top表示在当前这个item中滑动
                 */
                isScrollDown = firstVisibleItem > mFirstPosition || mFirstTop > top;
                mFirstTop = top;
                mFirstPosition = firstVisibleItem;
            }
        };

        public MyListAdapter(Context context, ListView listView, List<Drawable> drawables, int
                length) {
            this.mDrawables = drawables;
            this.mLength = length;
            this.mContext = context;
            mInflater = LayoutInflater.from(context);
            this.mListView = listView;
            //在Adapter初始化时加载动画
            animation = AnimationUtils.loadAnimation(mContext, R.anim.list_bottom_in_anim);
            mListView.setOnScrollListener(mOnScrollListener);

        }

        @Override
        public int getCount() {
            return mLength;
        }

        @Override
        public Object getItem(int position) {
            return mDrawables.get(position % mDrawables.size());
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder mViewHolder = null;
            if (convertView == null) {
                mViewHolder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.my_view_animation_img_item, null);
                mViewHolder.mImageView = (ImageView) convertView.findViewById(
                                                                R.id.my_view_animation_item_img);
                convertView.setTag(mViewHolder);
            } else {
                mViewHolder = (ViewHolder) convertView.getTag();
            }
            if (position == mLength - 1) {
                mLength = mLength + 10;
                notifyDataSetChanged();
            }
            //清除当前显示区域中所有item的动画
            for (int i=0;i<mListView.getChildCount();i++){
                View view = mListView.getChildAt(i);
                view.clearAnimation();
            }
            if (isScrollDown) {
                //给每个convertView添加动画
                convertView.startAnimation(animation);
            }
            mViewHolder.mImageView.setImageDrawable(mDrawables.get(position % mDrawables.size()));
            return convertView;
        }
    }

    public class ViewHolder {
        public ImageView mImageView;

    }
}
