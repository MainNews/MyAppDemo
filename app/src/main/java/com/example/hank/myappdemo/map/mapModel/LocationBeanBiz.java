package com.example.hank.myappdemo.map.mapModel;

import com.baidu.location.BDLocation;
import com.example.hank.myappdemo.map.model.MyLocationListener;


/**
 * Created by Jun on 2017/4/16.
 */

public class LocationBeanBiz implements ILocationBean {

    /**
     * 注册监听事件，当获取到定位信息后，通知View层，获取到了数据
     */
    @Override
    public void getLocationData(MyLocationListener myLocationListener, final OnLocationDataListener
            onLocationDataListener) {
        myLocationListener.setOnLocationListener(new MyLocationListener.OnMyLocationListener() {
            @Override
            public void onGetDateSuccess(LocationBean locationBean, BDLocation location) {
                onLocationDataListener.getLocationDataSuccess(locationBean, location);
            }
        });
    }
}
