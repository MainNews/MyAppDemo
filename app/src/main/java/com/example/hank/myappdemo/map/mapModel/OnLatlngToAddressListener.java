package com.example.hank.myappdemo.map.mapModel;

import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;

/**
 * Created by Jun on 2017/4/21.
 * 该接口用于将经纬度进行转换
 */

public interface OnLatlngToAddressListener {
    /**
     * 转换成功
     */
    void OnLatLngToAddressSuccess(ReverseGeoCodeResult result,Marker marker);

    /**
     * 转换失败
     */
    void OnLatLngToAddressError();
}
