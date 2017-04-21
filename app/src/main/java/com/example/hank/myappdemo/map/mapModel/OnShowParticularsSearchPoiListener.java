package com.example.hank.myappdemo.map.mapModel;

import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;

/**
 * Created by Jun on 2017/4/21.
 */

public interface OnShowParticularsSearchPoiListener {
    /**
     * 搜索成功
     */
    void OnShowParticularsSearchPoiSuccess(PoiCitySearchOption option);

    /**
     * 搜索失败
     */
    void OnShowParticularsSearchPoiError();
}
