package com.example.hank.myappdemo.map.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.example.hank.myappdemo.R;
import com.example.hank.myappdemo.map.MAPPathActivity;
import com.example.hank.myappdemo.map.mapModel.LocationBean;


/**
 * Created by Jun on 2017/4/25.
 */

public class BaseMAPPathFragment extends Fragment {
    protected MAPPathActivity mapPathActivity;
    protected View mViewContent;//缓存视图
    /**
     * 显示地图
     */
    protected MapView mapView;
    /**
     * 路线搜索对象
     */
    protected RoutePlanSearch mRoutePlanSearch;
    /**
     * 控制地图
     */
    protected BaiduMap baiduMap;
    /**
     * 记录开始位置
     */
    protected LatLng startPathLatLng;
    /**
     * 记录结束位置
     */
    protected LatLng endPathLatLng;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapPathActivity = (MAPPathActivity) getActivity();
        mRoutePlanSearch = RoutePlanSearch.newInstance();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        Bundle drivingBundle = getArguments();
        LocationBean startLocationBean = (LocationBean) drivingBundle.getSerializable
                ("PATH_START_LOCATION_BEAN");
        LocationBean endLocationBean = (LocationBean) drivingBundle.getSerializable
                ("PATH_END_LOCATION_BEAN");
        startPathLatLng = new LatLng(startLocationBean.getLatitude(), startLocationBean
                .getLongitude());
        endPathLatLng = new LatLng(endLocationBean.getLatitude(), endLocationBean.getLongitude());
        if (mViewContent == null) {
            mViewContent = inflater.inflate(R.layout.map_rout_wailking_result_fragment_layout, container, false);
        }
        // 缓存View判断是否含有parent, 如果有需要从parent删除, 否则发生已有parent的错误.
        ViewGroup parent = (ViewGroup) mViewContent.getParent();
        if (parent != null) {
            parent.removeView(mViewContent);
        }
        return mViewContent;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView = (MapView) mViewContent.findViewById(R.id.map_activity_path_map);
        baiduMap = mapView.getMap();
        beginTransitSearch();
        mRoutePlanSearch.setOnGetRoutePlanResultListener(new OnGetRoutePlanResultListener() {
            @Override
            public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {

                onGetWailkingRouteResultListenter(walkingRouteResult);
            }

            @Override
            public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {
                onGetTransitRouteResultListenter(transitRouteResult);
            }

            @Override
            public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {
                onGetMassTransitRouteResultListenter(massTransitRouteResult);
            }

            @Override
            public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {
                onGetDrivingRouteResultListener(drivingRouteResult);
            }

            @Override
            public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {
                onGetIndoorRouteResultListener(indoorRouteResult);
            }

            @Override
            public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {
                onGetBikingRouteResultListener(bikingRouteResult);
            }
        });
    }
    /**
     * 让子类去实现搜索回调的监听方法方法怎么处理
     */
    protected void onGetBikingRouteResultListener(BikingRouteResult bikingRouteResult) {
    }
    protected void onGetIndoorRouteResultListener(IndoorRouteResult indoorRouteResult) {
    }
    protected void onGetDrivingRouteResultListener(DrivingRouteResult drivingRouteResult) {
    }
    protected void onGetMassTransitRouteResultListenter(MassTransitRouteResult massTransitRouteResult) {
    }
    protected void onGetTransitRouteResultListenter(TransitRouteResult transitRouteResult) {
    }
    protected void onGetWailkingRouteResultListenter(WalkingRouteResult walkingRouteResult) {
    }

    /**
     * 发起路线搜索
     */
    protected void beginTransitSearch() {
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause(); // 关联MapView与Activity的生命周期
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume(); // 关联MapView与Activity的生命周期
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy(); // 关联MapView与Activity的生命周期
        mRoutePlanSearch.destroy();
    }
}
