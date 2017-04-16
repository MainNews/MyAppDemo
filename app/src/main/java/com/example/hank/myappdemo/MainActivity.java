package com.example.hank.myappdemo;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.hank.myappdemo.mapModel.LocationBean;
import com.example.hank.myappdemo.mapPresenter.LoactionGetPresenter;
import com.example.hank.myappdemo.mapView.ILocationView;
import com.example.hank.myappdemo.model.MyLocationListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 首页，进入百度地图展示
 */
public class MainActivity extends AppCompatActivity implements ILocationView {

    @Bind(R.id.baidu_home_activity_tv_map)
    TextView baiduHomeActivityTvMap;

    private LocationClient mLocationClient;
    private MyLocationListener myLocationListener;

    private LoactionGetPresenter loactionGetPresenter = new LoactionGetPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLocationClient = new LocationClient(getApplicationContext());
        myLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(myLocationListener);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        List<String> permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager
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
        loactionGetPresenter.getLoactionData();

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
        option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);
    }

    @OnClick(R.id.baidu_home_activity_tv_map)
    public void onViewClicked() {
    }

    /**
     * 获取定位信息成功
     */
    @Override
    public void toLocationSuccess(LocationBean locationBean) {
        baiduHomeActivityTvMap.setText(locationBean.getLoaction());
    }

    /**
     * 获取定位信息失败
     */
    @Override
    public void showLocationFailed() {
        baiduHomeActivityTvMap.setText("数据获取失败");
    }

    /**
     * 获取LocationListener对象
     */
    @Override
    public MyLocationListener getMyLocationListener() {
        return myLocationListener;
    }

    /**
     * 当拿到定位信息数据后，才开始进行数据的填充操作
     */
    @Override
    public void getLocationDataSuccess() {
        loactionGetPresenter.uploadLocation();
    }

    /**
     * 该方法用于处理权限问题
     *
     * @param requestCode
     * @param permissions
     * @param grantResults 权限数组
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
   @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
    }
}
