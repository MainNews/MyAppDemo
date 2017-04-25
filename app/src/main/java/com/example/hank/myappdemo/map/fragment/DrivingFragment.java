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
import com.baidu.mapapi.search.route.DrivingRouteLine;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.example.hank.myappdemo.R;
import com.example.hank.myappdemo.map.MAPPathActivity;
import com.example.hank.myappdemo.map.base.BaseFragment;
import com.example.hank.myappdemo.map.base.BaseMAPPathFragment;
import com.example.hank.myappdemo.map.com.baidu.mapapi.overlayutil.DrivingRouteOverlay;
import com.example.hank.myappdemo.map.mapModel.LocationBean;

import java.util.List;

/**
 * Created by Jun on 2017/4/22.
 * 展示自驾路线
 */

public class DrivingFragment extends BaseMAPPathFragment {
    /**
     * 发起路线搜索
     */
    @Override
    protected void beginTransitSearch() {
            //驾车
            DrivingRoutePlanOption drOption = new DrivingRoutePlanOption();
            PlanNode drivStart = PlanNode.withLocation(startPathLatLng);
            drOption.from(drivStart);
            PlanNode drivEnd = PlanNode.withLocation(endPathLatLng);
            drOption.to(drivEnd);//终点
            mRoutePlanSearch.drivingSearch(drOption);
    }

    @Override
    protected void onGetDrivingRouteResultListener(DrivingRouteResult drivingRouteResult) {
        if (drivingRouteResult == null || drivingRouteResult.error == DrivingRouteResult
                .ERRORNO.RESULT_NOT_FOUND) { mapPathActivity.showToast("没有搜索结果");
        } else {
            /** 记录路线长度，以“米”为单位  */
            //ArrayList<Integer> routeLineDistanceListDist = new ArrayList<>();
            /** 记录路线耗时，以“秒为单位”*/
            // ArrayList<Integer> routeLinerDurationListDist = new ArrayList<>();
            List<DrivingRouteLine> drivList = drivingRouteResult.getRouteLines();
            for (int i = 0 ;i < 1 ; i++) {
                DrivingRouteOverlay overlay = new DrivingRouteOverlay(baiduMap);
                overlay.setData(drivList.get(i));//设置路线的数据
                overlay.addToMap();//添加到地图
                overlay.zoomToSpan();//缩放地图
            }
        }
    }
}
