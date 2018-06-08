package com.rxjy.niujingji.mvp.model;

import com.rxjy.niujingji.api.ApiEngine;
import com.rxjy.niujingji.mvp.contract.RoomDetailContract;
import com.rxjy.niujingji.rx.RxSchedulers;

import rx.Observable;

/**
 * Created by AAA on 2017/9/9.
 */

public class RoomDetailModel implements RoomDetailContract.Model {
    @Override
    public Observable<String> getRoomDetailInfo(int roomID) {
        return ApiEngine.getInstance().getSwApiService()
                .getHouseInfo(roomID)
                .compose(RxSchedulers.<String>switchThread());
    }

    @Override
    public Observable<String> bookingRoom(int id, String name, String phone) {
        return ApiEngine.getInstance().getSwApiService()
                .bookingRoom(id, name, phone, 2)
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
