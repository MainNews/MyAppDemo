package com.example.hank.myappdemo.mapModel;

/**
 * Created by Jun on 2017/4/16.
 * 定位所需要的数据
 */

public class LocationBean {
    private String longitude;// 经度
    private String latitude;// 纬度
    private String loaction;// 详细位置
    private String loactionMode;// 定位方式

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLoaction() {
        return loaction;
    }

    public String getLoactionMode() {
        return loactionMode;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLoaction(String loaction) {
        this.loaction = loaction;
    }

    public void setLoactionMode(String loactionMode) {
        this.loactionMode = loactionMode;
    }
}
