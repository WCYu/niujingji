package com.rxjy.niujingji.service;

import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.rxjy.niujingji.commons.App;
import com.rxjy.niujingji.commons.Constants;
import com.rxjy.niujingji.commons.utils.NetUtil;
import com.rxjy.niujingji.mvp.contract.CoreContract;
import com.rxjy.niujingji.mvp.presenter.CorePresenter;

/**
 * Created by qindd on 2017/6/14.
 */
public class CoreService extends Service implements CoreContract.View, BDLocationListener {

    private CorePresenter mPresenter;

    private String netStatus;
    private String ip = "";
    private String model = "";
    private String province = "";
    private String city = "";

    public LocationClient mLocationClient = null;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        init();
        initLocation();
        initFacilityInfo();
    }

    private void init() {
        String model = Build.MODEL;

    }

    //初始化设备信息
    private void initFacilityInfo() {
        mPresenter = new CorePresenter(this);
        //获取IP
        mPresenter.getIP();
        //获取设备信息
        model = Build.MODEL;
        //获取网络状态
        switch (NetUtil.getNetWorkStatus(this)) {
            case Constants.NETWORK_CLASS_UNKNOWN:
                netStatus = "无网络";
                break;
            case Constants.NETWORK_WIFI:
                netStatus = "WIFI";
                break;
            case Constants.NETWORK_CLASS_2_G:
                netStatus = "2G";
                break;
            case Constants.NETWORK_CLASS_3_G:
                netStatus = "3G";
                break;
            case Constants.NETWORK_CLASS_4_G:
                netStatus = "4G";
                break;
        }
    }

    //初始化位置信息
    private void initLocation() {

        mLocationClient = new LocationClient(getApplicationContext());

        //声明LocationClient类
        mLocationClient.registerLocationListener(this);

        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备

        option.setCoorType("bd09ll");
        //可选，默认gcj02，设置返回的定位结果坐标系

        int span = 1000 * 60;
        option.setScanSpan(span);
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的

        option.setIsNeedAddress(true);
        //可选，设置是否需要地址信息，默认不需要

        option.setOpenGps(true);
        //可选，默认false,设置是否使用gps

        option.setLocationNotify(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果

        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”

        option.setIsNeedLocationPoiList(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到

        option.setIgnoreKillProcess(false);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死

        option.setEnableSimulateGps(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要

        mLocationClient.setLocOption(option);

        mLocationClient.start();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        mPresenter = new RecordServicePresenter();

        return Service.START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void responseIP(String ip) {
        this.ip = ip;
        //上传设备信息
        mPresenter.subAppInfo(App.cardNo, province, city, model, ip, netStatus);
    }

    @Override
    public void onReceiveLocation(BDLocation bdLocation) {

        province = bdLocation.getProvince();
        city = bdLocation.getCity();

        //把位置信息给全局变量
        App.city = bdLocation.getCity();
        App.district = bdLocation.getDistrict();
        App.street = bdLocation.getStreet();
        //经纬度
        App.latitude = bdLocation.getLatitude();
        App.longitude = bdLocation.getLongitude();

    }

}
