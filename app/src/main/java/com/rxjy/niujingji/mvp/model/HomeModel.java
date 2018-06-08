package com.rxjy.niujingji.mvp.model;

import com.rxjy.niujingji.api.ApiEngine;
import com.rxjy.niujingji.mvp.contract.HomeContract;
import com.rxjy.niujingji.rx.RxSchedulers;

import rx.Observable;

/**
 * Created by AAA on 2017/7/31.
 */

public class HomeModel implements HomeContract.Model {
    @Override
    public Observable<String> getClientList(String cardNo, String key) {
        return ApiEngine.getInstance().getSwApiService()
                .getClientList(cardNo, "0", "2", key)
                .compose(RxSchedulers.<String>switchThread());
    }

    @Override
    public Observable<String> getGift(String cardNo) {
        return ApiEngine.getInstance().getSwApiService()
                .getGift(cardNo)
                .compose(RxSchedulers.<String>switchThread());
    }
}
