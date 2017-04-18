package com.example.hank.myappdemo.map.mapModel;

import com.baidu.location.BDLocation;

/**
 * Created by Jun on 2017/4/17.
 */

public interface OnLocationDataListener {
    /**
     * 成功获取到数据，返回给View层
     */
    void getLocationDataSuccess(LocationBean locationBean,BDLocation location);
}
