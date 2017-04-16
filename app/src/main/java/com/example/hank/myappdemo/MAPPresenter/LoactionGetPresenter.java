package com.example.hank.myappdemo.mapPresenter;

import android.os.Handler;

import com.example.hank.myappdemo.mapModel.ILocationBean;
import com.example.hank.myappdemo.mapModel.LocationBean;
import com.example.hank.myappdemo.mapModel.LocationBeanBiz;
import com.example.hank.myappdemo.mapModel.OnLocationDataListener;
import com.example.hank.myappdemo.mapModel.OnLocationListener;
import com.example.hank.myappdemo.mapView.ILocationView;

/**
 * Created by Jun on 2017/4/16.
 * <p>
 * 该类用于操作View与Model之间的交互
 */

public class LoactionGetPresenter {
    private ILocationBean locationBean;
    private ILocationView locationView;


    public LoactionGetPresenter(ILocationView locationView) {
        this.locationView = locationView;
        this.locationBean = new LocationBeanBiz();
    }

    public void uploadLocation() {
        locationBean.upload(locationView.getMyLocationListener(), new OnLocationListener() {
            @Override
            public void getLocationSuccess(final LocationBean locationBean) {
                locationView.toLocationSuccess(locationBean);
            }

            @Override
            public void getLocationFailed() {
                locationView.showLocationFailed();
            }
        });
    }

    public void getLoactionData() {
        locationBean.getLocationData(locationView.getMyLocationListener(), new
                OnLocationDataListener() {
            @Override
            public void getLocationDataSuccess() {
                locationView.getLocationDataSuccess();
            }
        });
    }
}
