package com.rxjy.niujingji.mvp.model;

import com.rxjy.niujingji.api.ApiEngine;
import com.rxjy.niujingji.mvp.contract.WalletContract;
import com.rxjy.niujingji.rx.RxSchedulers;

import rx.Observable;

/**
 * Created by Administrator on 2017/6/30.
 */
public class WalletModel implements WalletContract.Model {
    @Override
    public Observable<String> getWalletInfo(String cardNo) {
        return ApiEngine.getInstance().getSwApiService()
                .getWalletInfo(cardNo)
                .compose(RxSchedulers.<String>switchThread());
    }

    @Override
    public Observable<String> getWalletRecordList(String cardNo, int pageIndex, int pageSize) {
        return ApiEngine.getInstance().getSwApiService()
                .getWalletRecord(cardNo, pageIndex, pageSize)
                .compose(RxSchedulers.<String>switchThread());
    }

    @Override
    public Observable<String> getWalletRecordLoadList(String cardNo, int pageIndex, int pageSize) {
        return ApiEngine.getInstance().getSwApiService()
                .getWalletRecord(cardNo, pageIndex, pageSize)
                .compose(RxSchedulers.<String>switchThread());
    }
}
