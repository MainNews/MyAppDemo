package com.example.hank.myappdemo.map.model;


import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.example.hank.myappdemo.map.mapModel.LocationBean;

/**
 * Created by Jun on 2017/4/16.
 * 该类用于获取定位信息
 */

public class MyLocationListener implements BDLocationListener {
    private OnMyLocationListener onMyLocationListener;
    private  LocationBean locationBean;

    public MyLocationListener(OnMyLocationListener onMyLocationListener){
        this.onMyLocationListener = onMyLocationListener;
    }

    /**
     * 当用户同意所有权限后，开始获取定位数据
     */
    @Override
    public void onReceiveLocation(BDLocation location) {
        StringBuilder currentPostition = new StringBuilder();
        locationBean = new LocationBean();
            /*
                通过location的getLatitude()与getLongitude()分别获取纬度与经度
                通过DBLocation类获取连网方式
             */
        locationBean.setLatitude(location.getLatitude());
        locationBean.setLongitude(location.getLongitude());

//        currentPostition.append(location.getCountry());//国家
        locationBean.setProvince(location.getProvince());//省
        locationBean.setCity(location.getCity());//市
        currentPostition.append(location.getDistrict());
        currentPostition.append(location.getStreet());
        locationBean.setLoaction(currentPostition.toString());

        if (location.getLocType() == BDLocation.TypeGpsLocation) {
            locationBean.setLoactionMode("GPS");
        } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
            locationBean.setLoactionMode("网络");
        } else {
            locationBean.setLoactionMode("wifi");
        }

        if (location.getLocType() == BDLocation.TypeGpsLocation || location.getLocType() ==
                BDLocation.TypeNetWorkLocation) {
            if (onMyLocationListener != null) {
                onMyLocationListener.onGetDateSuccess(locationBean,location);
            }
        }
    }

    @Override
    public void onConnectHotSpotMessage(String s, int i) {

    }

    /**
     * 用于监听得到数据信息后回调onGetDateSuccess()方法
     */
    public interface OnMyLocationListener {
        void onGetDateSuccess(LocationBean locationBean,BDLocation location);
    }

    public void setOnLocationListener(OnMyLocationListener onMyLocationListener) {
        this.onMyLocationListener = onMyLocationListener;
    }
}
