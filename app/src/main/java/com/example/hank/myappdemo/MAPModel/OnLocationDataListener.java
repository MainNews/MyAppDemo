package com.example.hank.myappdemo.mapModel;

import com.baidu.location.BDLocation;

/**
 * Created by Jun on 2017/4/17.
 */

public interface OnLocationDataListener {
    /**
     * 成功获取到数据
     */
    void getLocationDataSuccess(BDLocation location);
}
