package com.example.hank.myappdemo.model;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;

/**
 * Created by Jun on 2017/4/16.
 * 该类用于获取定位信息
 */

public class MyLocationListener implements BDLocationListener {
    StringBuilder currentPostition;
    OnMyLocationListener onMyLocationListener;
    @Override
    public void onReceiveLocation(BDLocation location) {
        currentPostition = new StringBuilder();
            /*
                通过location的getLatitude()与getLongitude()分别获取纬度与经度
                通过DBLocation类获取连网方式
             */
        currentPostition.append("纬度：").append(location.getLatitude()).append("\n");
        currentPostition.append("经线：").append(location.getLongitude()).append("\n");

        currentPostition.append("国家：").append(location.getCountry()).append("\n");
        currentPostition.append("省：").append(location.getProvince()).append("\n");
        currentPostition.append("市：").append(location.getCity()).append("\n");
        currentPostition.append("区：").append(location.getDistrict()).append("\n");
        currentPostition.append("街道：").append(location.getStreet()).append("\n");

        currentPostition.append("定位方式：");
        if (location.getLocType() == BDLocation.TypeGpsLocation) {
            currentPostition.append("GPS");
        } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
            currentPostition.append("网络");
        } else {
            currentPostition.append("wifi");
        }
        if (onMyLocationListener != null){
            onMyLocationListener.onGetDateSuccess();
        }
    }
    public StringBuilder getCurrentPostition(){
        return  currentPostition;
    }

    @Override
    public void onConnectHotSpotMessage(String s, int i) {

    }
    public interface OnMyLocationListener{
        void onGetDateSuccess();
    }
    public void setOnLocationListener(OnMyLocationListener onMyLocationListener){
        this.onMyLocationListener = onMyLocationListener;
    }
}
