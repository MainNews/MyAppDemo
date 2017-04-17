package com.example.hank.myappdemo.mapModel;

import com.baidu.location.BDLocation;
import com.example.hank.myappdemo.model.MyLocationListener;


/**
 * Created by Jun on 2017/4/16.
 */

public class LocationBeanBiz implements ILocationBean {
    /**
     * 将获取到的数据信息发送给
     */
    @Override
    public void upload(MyLocationListener myLocationListener, OnLocationListener
            onLocationListener) {
        //将获取的定位信息传递出去
        StringBuilder currentPostition = myLocationListener.getCurrentPostition();
        if (currentPostition != null) {
            LocationBean locationBean = new LocationBean();
            locationBean.setLoaction(currentPostition.toString());
            onLocationListener.getLocationSuccess(locationBean);
        } else {
            onLocationListener.getLocationFailed();
        }
    }

    /**
     * 注册监听事件，当获取到定位信息后，通知View层，获取到了数据
     */
    @Override
    public void getLocationData(MyLocationListener myLocationListener, final OnLocationDataListener
            onLocationDataListener) {
        myLocationListener.setOnLocationListener(new MyLocationListener.OnMyLocationListener() {
            @Override
            public void onGetDateSuccess(BDLocation location) {
                onLocationDataListener.getLocationDataSuccess(location);
            }
        });
    }
}
