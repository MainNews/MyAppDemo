package com.example.hank.myappdemo.Utils;


import android.content.Context;

import com.example.hank.myappdemo.base.ExampleApplication;

/**
 * Created by Jun on 2017/4/28.
 * 该类用于提供一些常用的方法
 */

public class PhoneUtil {
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(float dpValue) {
        final float scale = ExampleApplication.getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(float pxValue) {
        final float scale =  ExampleApplication.getContext().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
