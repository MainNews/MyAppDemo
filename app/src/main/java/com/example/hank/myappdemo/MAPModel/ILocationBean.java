package com.example.hank.myappdemo.mapModel;

import com.example.hank.myappdemo.model.MyLocationListener;

/**
 * Created by Jun on 2017/4/16.
 * 该接口用于处理位置信息的回调方法
 */

public interface ILocationBean {

    void upload(MyLocationListener locationListener, OnLocationListener onLocationListener);

    void getLocationData(MyLocationListener myLocationListener, OnLocationDataListener
            onLocationDataListener);
}
