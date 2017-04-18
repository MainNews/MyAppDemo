package com.example.hank.myappdemo.map.mapView;


import com.baidu.location.BDLocation;
import com.example.hank.myappdemo.map.mapModel.LocationBean;
import com.example.hank.myappdemo.map.model.MyLocationListener;

/**
 * Created by Jun on 2017/4/16.
 * 该接口用于定义获取数据成功与失败时的操作
 */

public interface ILocationView {

    /**
     * 获取定位信息数据成功
     */
    void getLocationDataSuccess(LocationBean locationBean,BDLocation location);

    /**
     * 提供MyLocationListener对象给Presenter层处理
     */
    MyLocationListener getMyLocationListener();
}
