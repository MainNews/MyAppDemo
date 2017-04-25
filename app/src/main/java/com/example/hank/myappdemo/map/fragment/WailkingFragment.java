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
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteLine;
import com.baidu.mapapi.search.route.WalkingRoutePlanOption;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.example.hank.myappdemo.R;
import com.example.hank.myappdemo.map.MAPPathActivity;
import com.example.hank.myappdemo.map.base.BaseFragment;
import com.example.hank.myappdemo.map.base.BaseMAPPathFragment;
import com.example.hank.myappdemo.map.com.baidu.mapapi.overlayutil.WalkingRouteOverlay;
import com.example.hank.myappdemo.map.mapModel.LocationBean;

import java.util.List;

/**
 * Created by Jun on 2017/4/22.
 * 展示步行路线
 */

public class WailkingFragment extends BaseMAPPathFragment {

    /**
     * 实现onGetWailkingRouteResultListenter回调方式的方法
     */
    @Override
    protected void onGetWailkingRouteResultListenter(WalkingRouteResult walkingRouteResult) {
        if (walkingRouteResult == null || walkingRouteResult.error == WalkingRouteResult
                .ERRORNO.RESULT_NOT_FOUND) {
            mapPathActivity.showToast("没有搜索结果");
        } else {
            /** 记录路线长度，以“米”为单位  */
            //ArrayList<Integer> routeLineDistanceListDist = new ArrayList<>();
            /** 记录路线耗时，以“秒为单位”*/
            // ArrayList<Integer> routeLinerDurationListDist = new ArrayList<>();
            List<WalkingRouteLine> walkingList = walkingRouteResult.getRouteLines();
            for (int i = 0; i < 1; i++) {
                WalkingRouteOverlay overlay = new WalkingRouteOverlay(baiduMap);
                overlay.setData(walkingList.get(i));//设置路线的数据
                overlay.addToMap();//添加到地图
                overlay.zoomToSpan();//缩放地图
            }
        }
    }

    /**
     * 重写发起路线搜索的方法
     */
    @Override
    protected void beginTransitSearch() {
        WalkingRoutePlanOption walkOption = new WalkingRoutePlanOption();
        PlanNode walkStart = PlanNode.withLocation(startPathLatLng);
        walkOption.from(walkStart);
        PlanNode walkEnd = PlanNode.withLocation(endPathLatLng);
        walkOption.to(walkEnd);//终点
        mRoutePlanSearch.walkingSearch(walkOption);
    }
}
