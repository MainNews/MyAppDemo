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
import com.example.hank.myappdemo.map.base.BaseMAPPathFragment;
import com.example.hank.myappdemo.map.com.baidu.mapapi.overlayutil.TransitRouteOverlay;
import com.example.hank.myappdemo.map.mapModel.LocationBean;

import java.util.List;

/**
 * Created by Jun on 2017/4/22.
 * 展示公交路线
 */

public class TransitFragment extends BaseMAPPathFragment {
    /**
     * 发起路线搜索
     */
    @Override
    protected void beginTransitSearch() {
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
    protected void onGetTransitRouteResultListenter(TransitRouteResult transitRouteResult) {
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
}
