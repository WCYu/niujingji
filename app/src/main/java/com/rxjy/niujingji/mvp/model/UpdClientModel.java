package com.rxjy.niujingji.mvp.model;

import com.rxjy.niujingji.api.ApiEngine;
import com.rxjy.niujingji.mvp.contract.UpdClientContract;
import com.rxjy.niujingji.rx.RxSchedulers;

import rx.Observable;

/**
 * Created by AAA on 2017/8/9.
 */

public class UpdClientModel implements UpdClientContract.Model {
    @Override
    public Observable<String> getTc() {
        return ApiEngine.getInstance().getSwApiService()
                .getTc("2")
                .compose(RxSchedulers.<String>switchThread());
    }

    @Override
    public Observable<String> getClientInfo(int clientID) {
        return ApiEngine.getInstance().getSwApiService()
                .getClientInfo(clientID)
                .compose(RxSchedulers.<String>switchThread());
    }

    @Override
    public Observable<String> updClientInfo(String clientName, String wx, String acreage, String typeOne, String typeTwo, String companyName, String measureLocation, String cardNo, String type, String tcType, int clientID) {
        return ApiEngine.getInstance().getSwApiService()
                .updClientInfo(clientName, wx, acreage, typeOne, typeTwo, companyName, measureLocation, cardNo, type, tcType, clientID)
                .compose(RxSchedulers.<String>switchThread());
    }
}
