package com.example.hank.myappdemo.map.mapModel;

import com.example.hank.myappdemo.map.model.MyLocationListener;

/**
 * Created by Jun on 2017/4/16.
 * 该接口用于处理位置信息的回调方法
 */

public interface ILocationBean {

    /**
     * 通知数据层，注册获取数据监听
     */
    void getLocationData(MyLocationListener myLocationListener, OnLocationDataListener
            onLocationDataListener);
}
