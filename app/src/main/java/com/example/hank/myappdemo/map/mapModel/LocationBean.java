package com.example.hank.myappdemo.map.mapModel;

/**
 * Created by Jun on 2017/4/16.
 * 定位所需要的数据
 */

public class LocationBean {
    private double longitude;// 经度
    private double latitude;// 纬度
    private String loaction;// 详细位置
    private String loactionMode;// 定位方式
    private String province;//省
    private String city;//市

    public String getProvince() {
        return province;
    }
    public String getCity() {
        return city;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getLoaction() {
        return loaction;
    }

    public String getLoactionMode() {
        return loactionMode;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLoaction(String loaction) {
        this.loaction = loaction;
    }

    public void setLoactionMode(String loactionMode) {
        this.loactionMode = loactionMode;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
