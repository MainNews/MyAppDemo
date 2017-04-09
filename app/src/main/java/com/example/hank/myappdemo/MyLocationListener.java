package com.example.hank.myappdemo;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;

/**
 * Created by Jun on 2017/4/9.
 */
public class MyLocationListener implements BDLocationListener {
    @Override
    public void onReceiveLocation(BDLocation location) {
        StringBuilder currentPostition = new StringBuilder();
        currentPostition.append("纬度：").append(location.getLatitude()).append("\n");
        currentPostition.append("经线：").append(location.getLongitude()).append("\n");
        currentPostition.append("定位方式：");
        if (location.getLocType() == BDLocation.TypeGpsLocation) {
            currentPostition.append("GPS");
        } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
            currentPostition.append("网络");
        }

    }

    @Override
    public void onConnectHotSpotMessage(String s, int i) {

    }
}
