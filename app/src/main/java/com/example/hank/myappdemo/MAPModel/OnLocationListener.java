package com.example.hank.myappdemo.mapModel;

import com.baidu.location.LocationClient;

/**
 * Created by Jun on 2017/4/16.
 * 该接口用于操用获取当前位置信息
 */

public interface OnLocationListener {
    /**
     * 成功获取定位信息
     * @param locationBean
     */
    void getLocationSuccess(LocationBean locationBean);

    /**
     * 获取定位信息失败
     */
    void getLocationFailed();
}
