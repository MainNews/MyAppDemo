package com.example.hank.myappdemo.map.mapModel;

import com.baidu.mapapi.search.poi.PoiNearbySearchOption;

/**
 * Created by Jun on 2017/4/21.
 */

public interface OnShowSearchPoiListener {
    /**
     * 通过搜索关键字找到POI点
     */
    void showSearchPoiSuccess(PoiNearbySearchOption option);
    /**
     * 通过搜索关键字没有找到POI点
     */
    void showSearchPoiError(String msg);

}
