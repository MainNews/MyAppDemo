package com.example.hank.myappdemo.map.mapView;


import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.example.hank.myappdemo.map.mapModel.LocationBean;
import com.example.hank.myappdemo.map.model.MyLocationListener;

import java.util.List;

/**
 * Created by Jun on 2017/4/16.
 * 该接口用于定义获取数据成功与失败时的操作
 */

public interface ILocationView {

    /**
     * 获取定位信息数据成功
     */
    void getLocationDataSuccess(LocationBean locationBean,BDLocation location);
    /**
     * 查找Poi点成功
     */
    void searchPoiSuccess(PoiNearbySearchOption option);
    /**
     * 查找Poi点成功
     */
    void searchPoiError(String mes);
    /**
     * 得到PoiInfo数据的集合
     */
    void showDatasSearcInmapList(List<PoiInfo> poiList);
    /**
     * 经纬度转换地址成功
     */
    void latLngToaddressSuccess(ReverseGeoCodeResult result,Marker marker);

    /**
     * 经纬转换地址失败
     */
    void latLngToAddressError();

    /**
     * 城市搜索成功
     */
    void showParticularsSearchPoiSuccess(PoiCitySearchOption option);

    /**
     * 城市搜索失败
     */
    void showParticularsSearchPoiError();

    /**
     * 显示路线
     */
    void showTransitRoutLine(int position);
    void showWailkingRoutLine(int position);
    void showDrivingRoutLine(int position);
}
