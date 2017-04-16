package com.example.hank.myappdemo.mapView;

import android.app.Activity;

import com.baidu.location.LocationClient;
import com.example.hank.myappdemo.mapModel.LocationBean;
import com.example.hank.myappdemo.model.MyLocationListener;

/**
 * Created by Jun on 2017/4/16.
 * 该接口用于定义获取数据成功与失败时的操作
 */

public interface ILocationView {
    /**
     * 获取位置信息成功处理方法
     */
    void toLocationSuccess(LocationBean locationBean);

    /**
     * 获取位置信息失败处理方法
     */
    void showLocationFailed();

    /**
     * 获取MyLocationListener对象
     * @return
     */
    MyLocationListener getMyLocationListener();

    /**
     * 获取定位信息数据成功
     */
    void getLocationDataSuccess();
}
