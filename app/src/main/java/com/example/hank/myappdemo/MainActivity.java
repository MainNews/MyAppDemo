package com.example.hank.myappdemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.example.hank.myappdemo.mapModel.LocationBean;
import com.example.hank.myappdemo.mapPresenter.LoactionGetPresenter;
import com.example.hank.myappdemo.mapView.ILocationView;
import com.example.hank.myappdemo.model.MyLocationListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 首页，进入百度地图展示
 */
public class MainActivity extends AppCompatActivity implements ILocationView {

    @Bind(R.id.baidu_home_activity_tv_map)
    TextView baiduHomeActivityTvMap;
    @Bind(R.id.baidu_home_activity_map_view)
    MapView baiduHomeActivityMapView;

    private BaiduMap baiduMap;
    private boolean isFirstLocate = true;
    private LocationClient mLocationClient;
    private MyLocationListener myLocationListener;

    private LoactionGetPresenter loactionGetPresenter = new LoactionGetPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLocationClient = new LocationClient(getApplicationContext());
        myLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(myLocationListener);
        SDKInitializer.initialize(getApplicationContext());

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        List<String> permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager
                .PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission
                .READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission
                .WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionList.isEmpty()) {
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(this, permissions, 1);
        } else {
            requestLocation();
        }

        //获取地图的总控件器
        baiduMap = baiduHomeActivityMapView.getMap();
        //百度地图的限制，要使用定位光标，一定要事先调用该方法来开启功能
        baiduMap.setMyLocationEnabled(true);

        loactionGetPresenter.getLoactionData();

    }

    @Override
    protected void onResume() {
        super.onResume();
        baiduHomeActivityMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        baiduHomeActivityMapView.onPause();
    }

    /**
     * 调用LocationClient.start()方法，开始定位
     */
    private void requestLocation() {
        initLocation();
        mLocationClient.start();
    }

    /**
     * 通过LocationClienOption中的setScanSpan()方法，设置指定时间，通过指定时间更新一次位置
     * setIsNeedAddress(true):表示我们需要获取当前位置的详细信息
     */
    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setScanSpan(3000);
        /*
            由于百度地图生成的坐标是火星坐标，这里只需要设置下option中的坐标类型，默认是gcj02
            重点是bd09ll中09后面是英文的L而不是数字一，就这么坑爹
         */
        option.setCoorType("bd09ll");
        option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);
    }

    /**
     * 获取定位信息成功
     */
    @Override
    public void toLocationSuccess(LocationBean locationBean) {
        baiduHomeActivityTvMap.setText(locationBean.getLoaction());
    }

    /**
     * 获取定位信息失败
     */
    @Override
    public void showLocationFailed() {
        baiduHomeActivityTvMap.setText("数据获取失败");
    }

    /**
     * 获取LocationListener对象
     */
    @Override
    public MyLocationListener getMyLocationListener() {
        return myLocationListener;
    }

    /**
     * 当拿到定位信息数据后，才开始进行数据的填充操作
     */
    @Override
    public void getLocationDataSuccess(BDLocation location) {
        loactionGetPresenter.uploadLocation();
        navigateTo(location);
    }

    private void navigateTo(BDLocation location) {
        if (isFirstLocate) {
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(latLng);
            baiduMap.animateMapStatus(update);
            update = MapStatusUpdateFactory.zoomTo(16f);//让地图信息显示更加丰富，取值在3~19之间
            baiduMap.animateMapStatus(update);
            isFirstLocate = false;//只在第一次调用时定位到当前位置
        }
        MyLocationData.Builder locationBuilder = new MyLocationData.Builder();
        locationBuilder.latitude(location.getLatitude());
        locationBuilder.longitude(location.getLongitude());
        MyLocationData locationData = locationBuilder.build();
        baiduMap.setMyLocationData(locationData);
    }

    /**
     * 该方法用于处理权限问题
     *
     * @param requestCode
     * @param permissions
     * @param grantResults 权限数组
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    for (int result : grantResults) {
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(this, "必须同意所有权限才能使用本程序", Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                } else {
                    Toast.makeText(this, "发生未知错误", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }

    @OnClick({R.id.baidu_home_activity_tv_map, R.id.baidu_home_activity_map_view})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.baidu_home_activity_tv_map:
                break;
            case R.id.baidu_home_activity_map_view:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
        baiduHomeActivityMapView.onDestroy();
        baiduMap.setMyLocationEnabled(false);
    }
}
