package com.example.hank.myappdemo.map;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.percent.PercentRelativeLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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
import com.baidu.mapapi.map.MapViewLayoutParams;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.example.hank.myappdemo.R;
import com.example.hank.myappdemo.base.BaseActivtiy;
import com.example.hank.myappdemo.map.adapter.SearchPoiAdapter;
import com.example.hank.myappdemo.map.com.baidu.mapapi.overlayutil.PoiOverlay;
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
 * 展示百度地图
 */

public class MAPActivity extends BaseActivtiy implements ILocationView {
    @Bind(R.id.baidu_home_activity_map_view)
    MapView baiduHomeActivityMapView;
    @Bind(R.id.baidu_home_activity_map_but_i)
    Button baiduHomeActivityMapButI;
    @Bind(R.id.baidu_home_activity_search_poi_layout)
    PercentRelativeLayout baiduHomeActivitySearchPoiLayout;
    @Bind(R.id.mListView)
    ListView mListView;
    @Bind(R.id.baidu_home_activity_search_poi_text)
    EditText baiduHomeActivitySearchPoiText;

    private List<PoiInfo> poiList = new ArrayList<>();

    private BaiduMap baiduMap;
    /**
     * 用于判断是否进行定位到指定的经纬度
     */
    private boolean isFirstLocate = true;
    private LocationClient mLocationClient;
    private MyLocationListener myLocationListener;
    private SearchPoiAdapter searchPoiAdapter;
    private LatLng presentLocation;
    private GeoCoder geoCoder;
    private RoutePlanSearch mSearch;
    private BDLocation mBDLocation;
    private PoiSearch poiSearch;
    private LoactionGetPresenter loactionGetPresenter = new LoactionGetPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLocationClient = new LocationClient(getApplicationContext());
        myLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(myLocationListener);
        SDKInitializer.initialize(getApplicationContext());


        setContentView(R.layout.activity_main);
        //隐藏标题栏
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager
                .LayoutParams.FLAG_FULLSCREEN);
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

        //创建搜索对象（兴趣搜索）
        poiSearch = PoiSearch.newInstance();
        //添适配器
        searchPoiAdapter = new SearchPoiAdapter(poiList);
        mListView.setAdapter(searchPoiAdapter);

        initChangedListener();
    }

    private void initChangedListener() {
        baiduHomeActivitySearchPoiText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                if (editable.length() != 0) {
                    /**
                     * 发送信息，加载搜索数据
                     */
                    Message message = new Message();
                    message.what = 101;
                    handler.sendMessage(message);
                }
            }
        });
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 101:
                    //发起搜索Poi结果
                    showSearchPoi();
                    break;
                case 102://添加多个覆盖物
                    PoiResult poiResult =  (PoiResult) msg.obj;
                    showSearchDatas(poiResult);
                    showDatasInMap(poiResult);
                    break;
            }
        }
    };

    /**
     * 发起搜索Poi结果（范围搜索）
     */
    private void showSearchPoi() {
        //发起POI搜索
        PoiNearbySearchOption option = new PoiNearbySearchOption();
        String locationKeyword = baiduHomeActivitySearchPoiText.getText().toString();
        if (locationKeyword.isEmpty() || locationKeyword.equals("")) {
            showToast("没有找到相应的结果");
        } else {
            option.keyword(locationKeyword);//搜索的关键字
            option.location(presentLocation);//设置搜索的中心点
            option.radius(2000);//设置搜索半径，这里设定为附近2公里
            poiSearch.searchNearby(option);
        }
    }

    /**
     * 全城搜索
     */
    private void showParticularsSearchPoi() {
        //发起POI搜索
        PoiCitySearchOption option = new PoiCitySearchOption();
        String locationKeyword = baiduHomeActivitySearchPoiText.getText().toString();
        if (locationKeyword.isEmpty() || locationKeyword.equals("")) {
            showToast("没有找到相应的结果");
        } else {
            option.city("广州");//搜索城市
            option.keyword(locationKeyword);//搜索的关键字
            poiSearch.searchInCity(option);
        }
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

    private void addMacker(LatLng latLng) {
        addMacker(latLng, null);
    }

    private void addMacker(LatLng latLng, LocationBean locationBean) {
        //清除已经显示的View
        removeWindowView();

        baiduMap.hideInfoWindow();
        baiduMap.clear();
        MarkerOptions mMarkerOptions = new MarkerOptions();
        mMarkerOptions.position(latLng);//标注显示的位置
        BitmapDescriptor mBitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable
                .icon_mark);
        mMarkerOptions.icon(mBitmapDescriptor);//标注显示的图片
        if (locationBean != null) {
            mMarkerOptions.title(locationBean.getLoaction());
        }
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
        navigateTo(locationBean, null);
    }

    @Override
    public MyLocationListener getMyLocationListener() {
        return myLocationListener;
    }


    /**
     * 显示位置信息
     */
    private void navigateTo(LocationBean locationBean, LatLng location) {
        LatLng latLng;
        if (location == null) {
            latLng = new LatLng(locationBean.getLatitude(), locationBean.getLongitude());
        } else {
            latLng = location;
        }
        if (isFirstLocate) {
            initMapListener();
            MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(latLng);
            baiduMap.animateMapStatus(update);
            update = MapStatusUpdateFactory.zoomTo(16f);//让地图信息显示更加丰富，取值在3~19之间
            baiduMap.animateMapStatus(update);
            isFirstLocate = false;//只在第一次调用时定位到当前位置
            presentLocation = latLng;
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
                removeWindowView();
                //添加覆盖物
                addMacker(latLng);
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
        //注册监听器，接收搜索结果
        poiSearch.setOnGetPoiSearchResultListener(new OnGetPoiSearchResultListener() {
            @Override//返回多条数据（列表数据）
            public void onGetPoiResult(PoiResult poiResult) {
                if (poiResult != null || poiResult.error != PoiResult.ERRORNO.RESULT_NOT_FOUND) {
                    poiList = (poiResult.getAllPoi());
                    if (poiList != null) {
                        searchPoiAdapter.setPoiInfosList(poiList);
                    }

                }
                if (isPoiLocation) {
                    handler.removeCallbacksAndMessages(null);
                    Message message = new Message();
                    message.obj = poiResult;

                    message.what = 102;
                    handler.sendMessage(message);
                }
            }

            @Override//搜索某一条数据的详情
            public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
            }

            @Override
            public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                PoiInfo poiInfo = poiList.get(position);
                addMacker(poiInfo.location);
                isFirstLocate = true;
                navigateTo(null, poiInfo.location);
                baiduHomeActivitySearchPoiLayout.setVisibility(View.GONE);
            }
        });
    }

    private void removeWindowView() {
        if (windowViews.size() != 0) {
            for (TextView params : windowViews) {
                baiduHomeActivityMapView.removeView(params);
            }
        }
    }
    private boolean isPoiLocation = false;

    /**
     * 在地图上显示所有搜索结果
     *
     * @param poiResult
     */
    private void showSearchDatas(PoiResult poiResult) {
        PoiOverlay overlay = new PoiOverlay(baiduMap) {
            @Override
            public boolean onPoiClick(int position) {
                return super.onPoiClick(position);
            }
        };
        //设置搜索覆盖物点击事件
        baiduMap.setOnMarkerClickListener(overlay);
        isPoiLocation = false;

        overlay.setData(poiResult);
        overlay.addToMap();
        overlay.zoomToSpan();//给地图进行缩放，看到所显示的覆盖物
    }

    /**
     * 用于删除添加的地址提示框
     */
    private List<TextView> windowViews = new ArrayList<>();

    /**
     * 给搜索到的结果添加窗口，显示具体名称
     */
    private void showDatasInMap(PoiResult poiResult) {
        //清除已经显示的View
        removeWindowView();
        List<PoiInfo> allPoi = poiResult.getAllPoi();
        for (PoiInfo poiInfo : allPoi) {
            TextView window = new TextView(this);
            window.setText(poiInfo.name);
            window.setGravity(Gravity.CENTER);
            window.setBackgroundResource(R.drawable.popup_bg);

            ViewGroup.LayoutParams param = new MapViewLayoutParams.Builder()
                    .width(MapViewLayoutParams.WRAP_CONTENT)
                    .height(MapViewLayoutParams.WRAP_CONTENT)
                    //设置根据经纬度确定位置
                    .layoutMode(MapViewLayoutParams.ELayoutMode.mapMode)
                    .yOffset(-20)
                    .position(poiInfo.location)
                    .build();
            windowViews.add(window);
            baiduHomeActivityMapView.addView(window, param);
        }


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

    @OnClick({R.id.baidu_home_activity_map_view, R.id.baidu_home_activity_map_but_i, R.id
            .baidu_home_activity_ed_search_for, R.id.baidu_home_activity_search,
            R.id.baidu_home_activity_search_exit, R.id.baidu_home_activity_but_search_for,})
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
            case R.id.baidu_home_activity_ed_search_for:
                baiduHomeActivitySearchPoiLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.baidu_home_activity_search://点击搜索POI关键字,POI结果
                isPoiLocation = true;
                showParticularsSearchPoi();
                baiduHomeActivitySearchPoiText.setText("");
                baiduHomeActivitySearchPoiLayout.setVisibility(View.GONE);
                break;

            case R.id.baidu_home_activity_search_exit://隐藏搜索
                baiduHomeActivitySearchPoiLayout.setVisibility(View.GONE);
                break;
            case R.id.baidu_home_activity_but_search_for://退出当前界面
                finish();
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
        //销毁搜索对象
        poiSearch.destroy();
//        baiduMap.setMyLocationEnabled(false);
    }

    private Toast mToast = null;

    private void showToast(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        }
        mToast.setText(msg);
        mToast.show();
    }
}
