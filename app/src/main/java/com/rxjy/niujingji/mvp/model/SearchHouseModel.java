package com.rxjy.niujingji.mvp.model;

import com.rxjy.niujingji.api.ApiEngine;
import com.rxjy.niujingji.mvp.contract.SearchHouseContract;
import com.rxjy.niujingji.rx.RxSchedulers;

import rx.Observable;

/**
 * Created by AAA on 2017/9/7.
 */

public class SearchHouseModel implements SearchHouseContract.Model {
    @Override
    public Observable<String> getHotWordList(int cityID) {
        return ApiEngine.getInstance().getSwApiService()
                .getHotKeyWordList(cityID)
                .compose(RxSchedulers.<String>switchThread());
    }

    @Override
    public Observable<String> searchBuildingList(int cityID, String keyWord, int pageIndex, int pageSize) {
        return ApiEngine.getInstance().getSwApiService()
                .searchBuildingList(cityID,keyWord, pageSize, pageIndex, 1)
                .compose(RxSchedulers.<String>switchThread());
    }
}
