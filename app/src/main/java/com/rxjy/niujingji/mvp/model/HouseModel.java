package com.rxjy.niujingji.mvp.model;

import com.rxjy.niujingji.api.ApiEngine;
import com.rxjy.niujingji.mvp.contract.HouseContract;
import com.rxjy.niujingji.rx.RxSchedulers;

import rx.Observable;

/**
 * Created by AAA on 2017/9/6.
 */

public class HouseModel implements HouseContract.Model {
    @Override
    public Observable<String> getCityList() {
        return ApiEngine.getInstance().getSwApiService()
                .getCityList()
                .compose(RxSchedulers.<String>switchThread());
    }

    @Override
    public Observable<String> getAreaList(int cityID) {
        return ApiEngine.getInstance().getSwApiService()
                .getAreaList(cityID)
                .compose(RxSchedulers.<String>switchThread());
    }

    @Override
    public Observable<String> getTradingAreaList(int areaID) {
        return ApiEngine.getInstance().getSwApiService()
                .getTradingAreaList(areaID)
                .compose(RxSchedulers.<String>switchThread());
    }

    @Override
    public Observable<String> getBuildingList(Integer cityID, Integer areaID, String tradingAreaName, Integer areaStart, Integer areaEnd, Integer priceStart, Integer priceEnd, String keyWord, Integer pageSize, Integer pageIndex, Integer sortBy) {
        return ApiEngine.getInstance().getSwApiService()
                .getBuildingList(cityID,areaID,tradingAreaName,areaStart,areaEnd,priceStart,priceEnd,keyWord,pageSize,pageIndex,sortBy)
                .compose(RxSchedulers.<String>switchThread());
    }
}
