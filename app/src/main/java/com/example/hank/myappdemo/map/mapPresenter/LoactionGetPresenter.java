package com.example.hank.myappdemo.map.mapPresenter;


import android.content.Context;
import android.widget.EditText;

import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.example.hank.myappdemo.map.mapModel.IMapClass;
import com.example.hank.myappdemo.map.mapModel.LocationBean;
import com.example.hank.myappdemo.map.mapModel.LocationBeanBiz;
import com.example.hank.myappdemo.map.mapModel.OnLatlngToAddressListener;
import com.example.hank.myappdemo.map.mapModel.OnLocationDataListener;
import com.example.hank.myappdemo.map.mapModel.OnShowDatasSearcInMapListener;
import com.example.hank.myappdemo.map.mapModel.OnShowParticularsSearchPoiListener;
import com.example.hank.myappdemo.map.mapModel.OnShowSearchPoiListener;
import com.example.hank.myappdemo.map.mapView.ILocationView;

import java.util.List;

/**
 * Created by Jun on 2017/4/16.
 * <p>
 * 该类用于操作View与Model之间的交互
 */

public class LoactionGetPresenter {
    private ILocationView locationView;
    private IMapClass mIMapClass;


    public LoactionGetPresenter(ILocationView locationView) {
        this.locationView = locationView;
        this.mIMapClass = new LocationBeanBiz();
    }

    /**
     * 通知数据初始化集成百度地图所需要的对象
     */
    public void initMapClassMember(Context context,LocationClient mLocationClient) {
        mIMapClass.initMapClassMemberSucceed(context, mLocationClient,new OnLocationDataListener() {
            @Override
            public void getLocationDataSuccess(LocationBean locationBean, BDLocation location) {
                locationView.getLocationDataSuccess(locationBean, location);
            }
        });
    }

    /**
     * 发起POI范围搜索
     */
    public void showSearchPoi(String locationKeyword) {
        mIMapClass.showSearchPoi(locationKeyword, new OnShowSearchPoiListener() {
            @Override
            public void showSearchPoiSuccess(PoiNearbySearchOption option) {
                locationView.searchPoiSuccess(option);
            }
            @Override
            public void showSearchPoiError(String msg) {
                locationView.searchPoiError(msg);
            }
        });
    }

    /**
     * 通过Poi搜索的结果，添加覆盖物，与缩放
     */
    public void showDatasSearcInMap(PoiResult poiResult) {
        mIMapClass.showDatasSearcInMap( poiResult, new OnShowDatasSearcInMapListener() {
            @Override
            public void showDatasSearcInMap(List<PoiInfo> poiList) {
                locationView.showDatasSearcInmapList(poiList);
            }
        });
    }

    /**
     * 将获取到的经纬度进入互换
     */
    public void latlngToAddress(GeoCoder geoCoder, Marker marker){
        mIMapClass.latlngToAddress(geoCoder, marker, new OnLatlngToAddressListener() {
            @Override
            public void OnLatLngToAddressSuccess(ReverseGeoCodeResult result,Marker marker) {
                locationView.latLngToaddressSuccess(result,marker);
            }
            @Override
            public void OnLatLngToAddressError() {
                locationView.latLngToAddressError();
            }
        });
    }
    /**
     * 通过关键字全城查找
     */
    public void showParticularsSearchPoi(String locationKeyword) {
        mIMapClass.showParticularsSearchPoi(locationKeyword, new
                OnShowParticularsSearchPoiListener() {
            @Override
            public void OnShowParticularsSearchPoiSuccess(PoiCitySearchOption option) {
                locationView.showParticularsSearchPoiSuccess(option);
            }
            @Override
            public void OnShowParticularsSearchPoiError() {
                locationView.showParticularsSearchPoiError();
            }
        });
    }
    /**
     * 清除引用
     */
    public void clear(){
        locationView = null;
        mIMapClass.clear();
    }


}
