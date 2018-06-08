package com.rxjy.niujingji.mvp.model;

import com.rxjy.niujingji.api.ApiEngine;
import com.rxjy.niujingji.mvp.contract.BuildingDetailContract;
import com.rxjy.niujingji.rx.RxSchedulers;

import rx.Observable;

/**
 * Created by AAA on 2017/9/8.
 */

public class BuildingDetailModel implements BuildingDetailContract.Model {
    @Override
    public Observable<String> getBuildingInfo(int buildingID) {
        return ApiEngine.getInstance().getSwApiService()
                .getBuildingInfo(buildingID)
                .compose(RxSchedulers.<String>switchThread());
    }

    @Override
    public Observable<String> getRoomList(int buildingID, int pageSize, int pageIndex, int type) {
        return ApiEngine.getInstance().getSwApiService()
                .getHouseList(buildingID, pageSize, pageIndex, type)
                .compose(RxSchedulers.<String>switchThread());
    }

    @Override
    public Observable<String> bookingRoom(int id, String name, String phone) {
        return ApiEngine.getInstance().getSwApiService()
                .bookingRoom(id, name, phone, 1)
                .compose(RxSchedulers.<String>switchThread());
    }

    @Override
    public Observable<String> getPoiSubway(String location) {
        return ApiEngine.getInstance().getBdApiService()
                .getPoiInfo("地铁$公交站", location, 500, "json", 20, "ee4sAHbt5MiasT0tXP9AoxHuT17Unpyj")
                .compose(RxSchedulers.<String>switchThread());
    }

    @Override
    public Observable<String> getPoiRestaurant(String location) {
        return ApiEngine.getInstance().getBdApiService()
                .getPoiInfo("快餐$餐厅", location, 500, "json", 20, "ee4sAHbt5MiasT0tXP9AoxHuT17Unpyj")
                .compose(RxSchedulers.<String>switchThread());
    }

    @Override
    public Observable<String> getPoiBank(String location) {
        return ApiEngine.getInstance().getBdApiService()
                .getPoiInfo("银行$ATM", location, 500, "json", 20, "ee4sAHbt5MiasT0tXP9AoxHuT17Unpyj")
                .compose(RxSchedulers.<String>switchThread());
    }

    @Override
    public Observable<String> getPoiHotel(String location) {
        return ApiEngine.getInstance().getBdApiService()
                .getPoiInfo("酒店$住宿", location, 500, "json", 20, "ee4sAHbt5MiasT0tXP9AoxHuT17Unpyj")
                .compose(RxSchedulers.<String>switchThread());
    }

}
