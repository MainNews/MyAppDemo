package com.example.hank.myappdemo.map.mapModel;

import com.baidu.mapapi.search.core.PoiInfo;

import java.util.List;

/**
 * Created by Jun on 2017/4/21.
 * 监听百度地图，添加位置坐标与显示坐标信息
 */

public interface OnShowDatasSearcInMapListener {
    //返回得到的List集合
    void showDatasSearcInMap(List<PoiInfo> poiList);
}
