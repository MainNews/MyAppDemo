package com.example.hank.myappdemo.map.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteLine;
import com.baidu.mapapi.search.route.TransitRoutePlanOption;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.example.hank.myappdemo.R;
import com.example.hank.myappdemo.map.MAPPathActivity;
import com.example.hank.myappdemo.map.base.BaseFragment;
import com.example.hank.myappdemo.map.com.baidu.mapapi.overlayutil.TransitRouteOverlay;
import com.example.hank.myappdemo.map.mapModel.LocationBean;

import java.util.List;

/**
 * Created by Jun on 2017/4/22.
 * 展示公交路线
 */

public class TransitFragment extends BaseFragment {
    private MAPPathActivity mapPathActivity;
    private View mViewContent;//缓存视图
    /**
     * 显示地图
     */
    protected MapView mapView;
    /**
     * 路线搜索对象
     */
    private RoutePlanSearch mRoutePlanSearch;
    /**
     * 控制地图
     */
    private BaiduMap baiduMap;
    /**
     * 记录开始位置
     */
    private LatLng startPathLatLng;
    /**
     * 记录结束位置
     */
    private LatLng endPathLatLng;

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
            mViewContent = inflater.inflate(R.layout.map_rout_transit_result_fragment_layout, container, false);
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

            }

            @Override
            public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {
                if (transitRouteResult == null || transitRouteResult.error == TransitRouteResult
                        .ERRORNO.RESULT_NOT_FOUND) {
                    mapPathActivity.showToast("没有搜索结果");
                } else {
                    /** 记录路线长度，以“米”为单位  */
                    //ArrayList<Integer> routeLineDistanceListDist = new ArrayList<>();
                    /** 记录路线耗时，以“秒为单位”*/
                    // ArrayList<Integer> routeLinerDurationListDist = new ArrayList<>();
                    List<TransitRouteLine> reansitList = transitRouteResult.getRouteLines();
                    for (int i = 0; i < 1; i++) {
                        TransitRouteOverlay overlay = new TransitRouteOverlay(baiduMap);
                        overlay.setData(reansitList.get(i));//设置路线的数据
                        overlay.addToMap();//添加到地图
                        overlay.zoomToSpan();//缩放地图
                    }
                }
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

    /**
     * 发起路线搜索
     */
    private void beginTransitSearch() {
        //公交
        TransitRoutePlanOption drOption = new TransitRoutePlanOption();
        drOption.city("广州");//设置搜索城市
        PlanNode drivStart = PlanNode.withLocation(startPathLatLng);
        drOption.from(drivStart);
        PlanNode drivEnd = PlanNode.withLocation(endPathLatLng);
        // 路线策略: 时间最短
        drOption.policy(TransitRoutePlanOption.TransitPolicy.EBUS_TIME_FIRST);
        drOption.to(drivEnd);//终点
        mRoutePlanSearch.transitSearch(drOption);
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
