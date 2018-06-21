package com.rxjy.niujingji.mvp.model;

import com.rxjy.niujingji.api.ApiEngine;
import com.rxjy.niujingji.mvp.contract.NjjContract;
import com.rxjy.niujingji.rx.RxSchedulers;

import rx.Observable;

/**
 * Created by AAA on 2017/8/9.
 */

public class NjjModel implements NjjContract.Model {
    @Override
    public Observable<String> getVersionInfo(int version) {
        return ApiEngine.getInstance().getRsApiService()
                .getVersionInfo(version,2)
                .compose(RxSchedulers.<String>switchThread());
    }

    @Override
    public Observable<String> getState(String cardNo) {
        return ApiEngine.getInstance().getSwApiService()
                .getTabState(cardNo)
                .compose(RxSchedulers.<String>switchThread());
    }
}
