package com.example.hank.myappdemo.map.mapModel;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.example.hank.myappdemo.map.model.MyLocationListener;

import java.util.List;


/**
 * Created by Jun on 2017/4/16.
 * 处理百度地图数据
 */

public class LocationBeanBiz implements  IMapClass{
    private LocationBean bizLocationBean;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 101:
                    Bundle data = msg.getData();
                    showSearchPoiLocation(data.getString("Keyword"), (OnShowSearchPoiListener)
                            msg.obj);
                    break;
            }
        }
    };


    /**
     * 初始化显示百度地图所需要的对象
     *
     * @param context
     */
    @Override
    public void initMapClassMemberSucceed(Context context, LocationClient mLocationClient, final
    OnLocationDataListener onLocationDataListener) {
        //注册监听器，如果得到数据，进行地图的显示
        MyLocationListener myLocationListener = new MyLocationListener(new MyLocationListener
                .OnMyLocationListener() {
            @Override
            public void onGetDateSuccess(LocationBean locationBean, BDLocation location) {
                //当得到数据后，返回给P层，请P层通知V进入处理操作
                onLocationDataListener.getLocationDataSuccess(locationBean, location);
            }
        });
        mLocationClient.registerLocationListener(myLocationListener);
        SDKInitializer.initialize(context);
    }

    /**
     * 发起搜索Poi结果（范围搜索）
     */
    @Override
    public void showSearchPoi(String locationKeyword, OnShowSearchPoiListener
            mShowSearchPoiListener) {
        Message message = new Message();
        message.what = 101;
        message.obj = mShowSearchPoiListener;
        Bundle data = new Bundle();
        data.putString("Keyword", locationKeyword);
        message.setData(data);
        handler.removeCallbacksAndMessages(null);
        handler.sendMessage(message);
    }

    private void showSearchPoiLocation(String locationKeyword, OnShowSearchPoiListener
            mShowSearchPoiListener) {
        //发起POI搜索
        PoiNearbySearchOption option = new PoiNearbySearchOption();
        if (locationKeyword.isEmpty() || locationKeyword.equals("")) {
            mShowSearchPoiListener.showSearchPoiError("没有找到相应的结果");
        } else {
            option.keyword(locationKeyword);//搜索的关键字
            option.location(getLatLng(bizLocationBean, null));//设置搜索的中心点
            option.radius(2000);//设置搜索半径，这里设定为附近2公里
            mShowSearchPoiListener.showSearchPoiSuccess(option);
        }
    }

    /**
     * 通过POI搜索得到结果返回给P，让P通知View去处理
     */
    @Override
    public void showDatasSearcInMap(PoiResult poiResult, OnShowDatasSearcInMapListener
            inMapListener) {
        if (poiResult != null || poiResult.error != PoiResult.ERRORNO.RESULT_NOT_FOUND) {
            List<PoiInfo> poiList = poiResult.getAllPoi();
            if (poiList != null) {
                inMapListener.showDatasSearcInMap(poiList);
            }
        }
    }

    /**
     * 将经纬度与地址与换
     */
    @Override
    public void latlngToAddress(GeoCoder geoCoder, final Marker marker, final
    OnLatlngToAddressListener
            latlngToAddressListener) {
        // 设置反地理经纬度坐标,请求位置时,需要一个经纬度
        geoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(marker.getPosition()));
        //设置地址或经纬度反编译后的监听,这里有两个回调方法,
        geoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
            //经纬度转换成地址
            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
                if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                    latlngToAddressListener.OnLatLngToAddressError();
                } else {
                    latlngToAddressListener.OnLatLngToAddressSuccess(result, marker);
                }
            }

            //把地址转换成经纬度
            @Override
            public void onGetGeoCodeResult(GeoCodeResult result) {
                // 详细地址转换在经纬度
                String address = result.getAddress();
            }
        });
    }
    /**
     * 全城搜索
     */
    @Override
    public void showParticularsSearchPoi(String locationKeyword,OnShowParticularsSearchPoiListener
                                                 onShowParticularsSearchPoiListener) {
        //发起POI搜索
        PoiCitySearchOption option = new PoiCitySearchOption();
        if (locationKeyword.isEmpty() || locationKeyword.equals("")) {
            onShowParticularsSearchPoiListener.OnShowParticularsSearchPoiError();
        } else {
            option.city(bizLocationBean.getCity());//搜索城市
            option.keyword(locationKeyword);//搜索的关键字
            onShowParticularsSearchPoiListener.OnShowParticularsSearchPoiSuccess(option);
        }
    }
    /**
     * 获取LatLng对象
     */
    private LatLng getLatLng(LocationBean locationBean, LatLng location) {
        if (location == null) {
            return new LatLng(locationBean.getLatitude(), locationBean.getLongitude());
        } else {
            return location;
        }
    }

    /**
     * 清除Handler的引用
     */
    @Override
    public void clear() {
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            handler = null;
        }
    }
}
