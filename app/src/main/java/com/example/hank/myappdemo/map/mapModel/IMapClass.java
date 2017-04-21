package com.example.hank.myappdemo.map.mapModel;

import android.content.Context;

import com.baidu.location.LocationClient;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.poi.PoiResult;

/**
 * Created by Jun on 2017/4/21.
 */

public interface IMapClass {
    void initMapClassMemberSucceed(Context context, LocationClient mLocationClient, OnLocationDataListener onLocationDataListener);

    void showSearchPoi(String locationKeyword, OnShowSearchPoiListener mShowSearchPoiListener);

    void showDatasSearcInMap(PoiResult poiResult, OnShowDatasSearcInMapListener inMapListener);
    /**
     * 将经纬度与地址互换
     */
    void latlngToAddress(GeoCoder geoCoder,Marker marker, OnLatlngToAddressListener latlngToAddressListener);
    /**
     * 发起城市搜索
     */
    void showParticularsSearchPoi(String locationKeyword, OnShowParticularsSearchPoiListener
            onShowParticularsSearchPoiListener);
    /**
     * 清除引用
     */
    void clear();
}
