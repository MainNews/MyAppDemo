package com.example.hank.myappdemo.map.mapPresenter;


import com.baidu.location.BDLocation;
import com.example.hank.myappdemo.map.mapModel.ILocationBean;
import com.example.hank.myappdemo.map.mapModel.LocationBean;
import com.example.hank.myappdemo.map.mapModel.LocationBeanBiz;
import com.example.hank.myappdemo.map.mapModel.OnLocationDataListener;
import com.example.hank.myappdemo.map.mapView.ILocationView;

/**
 * Created by Jun on 2017/4/16.
 * <p>
 * 该类用于操作View与Model之间的交互
 */

public class LoactionGetPresenter {
    private ILocationBean locationBiz;
    private ILocationView locationView;


    public LoactionGetPresenter(ILocationView locationView) {
        this.locationView = locationView;
        this.locationBiz = new LocationBeanBiz();
    }

    /**
     * 通知数据层，获如位置信息，并返回给View处理
     */
    public void getLoactionData() {
        locationBiz.getLocationData(locationView.getMyLocationListener(), new
                OnLocationDataListener() {
                    @Override
                    public void getLocationDataSuccess(LocationBean locationBean, BDLocation
                            location) {
                        locationView.getLocationDataSuccess(locationBean, location);
                    }
                });
    }
}
