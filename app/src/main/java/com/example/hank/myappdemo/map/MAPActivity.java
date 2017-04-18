package com.example.hank.myappdemo.map;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.example.hank.myappdemo.R;
import com.example.hank.myappdemo.map.mapModel.LocationBean;
import com.example.hank.myappdemo.map.mapPresenter.LoactionGetPresenter;
import com.example.hank.myappdemo.map.mapView.ILocationView;
import com.example.hank.myappdemo.map.model.MyLocationListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Jun on 2017/4/17.
 */

public class MAPActivity extends AppCompatActivity implements ILocationView {
    @Bind(R.id.baidu_home_activity_map_view)
    MapView baiduHomeActivityMapView;
    @Bind(R.id.baidu_home_activity_map_but_i)
    Button baiduHomeActivityMapButI;

    private BaiduMap baiduMap;
    private boolean isFirstLocate = true;
    private LocationClient mLocationClient;
    private MyLocationListener myLocationListener;

    private GeoCoder geoCoder;

    private RoutePlanSearch mSearch;

    private BDLocation mBDLocation;

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
        //通知Presenter层让Model层去注册监听事件
        loactionGetPresenter.getLoactionData();

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

//        baiduHomeActivityMapView.showZoomControls(false);//设置是否显示缩放按钮
        //获取地图的总控件器
        baiduMap = baiduHomeActivityMapView.getMap();

        //在OnCreate方法里创建地理编码检索实例
        geoCoder = GeoCoder.newInstance();

        //百度地图的限制，要使用定位光标，一定要事先调用该方法来开启功能
//        baiduMap.setMyLocationEnabled(true);

        //初始化搜索模块，注册监听事件
        mSearch = RoutePlanSearch.newInstance();
        mSearch.setOnGetRoutePlanResultListener(new OnGetRoutePlanResultListener() {
            @Override
            public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {

            }

            @Override
            public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {

            }

            @Override
            public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {

            }

            @Override
            public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {

            }

            @Override
            public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {

            }

            @Override
            public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

            }
        });
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
     * 添加标注
     */
    private void addMacker(LatLng latLng, LocationBean locationBean) {
        MarkerOptions mMarkerOptions = new MarkerOptions();
        mMarkerOptions.position(latLng);//标注显示的位置
        BitmapDescriptor mBitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable
                .icon_mark);
        mMarkerOptions.icon(mBitmapDescriptor);//标注显示的图片
        mMarkerOptions.title(locationBean.getLoaction());
        mMarkerOptions.draggable(true);//支持长按拖动位置
        Overlay overlay = baiduMap.addOverlay(mMarkerOptions);


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
     * 拿到定位数据成功后，进行数据处理
     */
    @Override
    public void getLocationDataSuccess(LocationBean locationBean, BDLocation location) {
        if (mBDLocation == null) {
            mBDLocation = location;
        }
        navigateTo(locationBean);
    }

    @Override
    public MyLocationListener getMyLocationListener() {
        return myLocationListener;
    }


    /**
     * 显示位置信息
     */
    private void navigateTo(LocationBean locationBean) {
        LatLng latLng = new LatLng(locationBean.getLatitude(), locationBean.getLongitude());
        if (isFirstLocate) {
            initMapListener();
            MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(latLng);
            baiduMap.animateMapStatus(update);
            update = MapStatusUpdateFactory.zoomTo(16f);//让地图信息显示更加丰富，取值在3~19之间
            baiduMap.animateMapStatus(update);
            isFirstLocate = false;//只在第一次调用时定位到当前位置
            // addCircleOption(latLng);
            //addTextOption(locationBean);
            addMacker(latLng, locationBean);
        }
        //使用百度地图自带当前位置图标
        /*MyLocationData.Builder locationBuilder = new MyLocationData.Builder();
        locationBuilder.latitude(locationBean.getLatitude());
        locationBuilder.longitude(locationBean.getLongitude());
        MyLocationData locationData = locationBuilder.build();
        baiduMap.setMyLocationData(locationData);*/
    }

    /**
     * 设置地图的监听事件
     */
    private void initMapListener() {
        //监听地图标注的监听事件
        baiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                //弹出一个窗口
                latlngToAddress(marker);
                return false;
            }
        });

        //地图点击事件处理
        baiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            /**
             * 点击地图时回调该方法
             * @param latLng 当前位置，该对象根据指定的经纬度来得到
             */
            @Override
            public void onMapClick(LatLng latLng) {
                //隐藏弹出窗口
                baiduMap.hideInfoWindow();
            }

            /**
             * 点击兴趣点时回调该方法
             */
            @Override
            public boolean onMapPoiClick(MapPoi mapPoi) {
                return false;
            }
        });

        /**
         * 标注长按监听事件
         */
        baiduMap.setOnMarkerDragListener(new BaiduMap.OnMarkerDragListener() {
            @Override//正在拖动
            public void onMarkerDrag(Marker marker) {

            }

            @Override//结束拖动
            public void onMarkerDragEnd(Marker marker) {
                //弹出一个窗口
                latlngToAddress(marker);
            }

            @Override//开始拖动
            public void onMarkerDragStart(Marker marker) {
            }
        });

    }

    /**
     * 该方法用于处理权限问题
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

    @OnClick({R.id.baidu_home_activity_map_view,R.id.baidu_home_activity_map_but_i})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.baidu_home_activity_map_view:
                break;
            case R.id.baidu_home_activity_map_but_i:
                //清除已经添加的覆盖物,并隐藏弹出的窗口
                baiduMap.clear();
                baiduMap.hideInfoWindow();
                isFirstLocate = true;//在一次定位到当前位置
                break;
        }
    }

    /**
     * 经纬度或地址相互转换
     */
    private void latlngToAddress(final Marker marker) {
        // 设置反地理经纬度坐标,请求位置时,需要一个经纬度
        geoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(marker.getPosition()));
        //设置地址或经纬度反编译后的监听,这里有两个回调方法,
        geoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
            //经纬度转换成地址
            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
                View layout = View.inflate(MAPActivity.this, R.layout.map_marker_pop_layout, null);
                TextView tvTitle = (TextView) layout.findViewById(R.id.tv_title);
                if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                    tvTitle.setText("找不到该地址!");
                }
                tvTitle.setText("地址:" + result.getAddress());
                /**
                 * 参数一：展示的View
                 * 参数二：显示的地理位置
                 * 参数三：Y 轴的偏移量
                 */
                InfoWindow infoWindow = new InfoWindow(layout, marker.getPosition(), 0);
                //将弹窗添加到地图中，并显示
                baiduMap.showInfoWindow(infoWindow);
            }

            //把地址转换成经纬度
            @Override
            public void onGetGeoCodeResult(GeoCodeResult result) {
                // 详细地址转换在经纬度
                String address = result.getAddress();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
        baiduHomeActivityMapView.onDestroy();
//        baiduMap.setMyLocationEnabled(false);
    }
}
