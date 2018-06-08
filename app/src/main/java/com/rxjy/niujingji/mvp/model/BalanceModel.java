package com.rxjy.niujingji.mvp.model;

import com.rxjy.niujingji.api.ApiEngine;
import com.rxjy.niujingji.mvp.contract.BalanceContract;
import com.rxjy.niujingji.rx.RxSchedulers;

import rx.Observable;

/**
 * Created by AAA on 2017/8/1.
 */

public class BalanceModel implements BalanceContract.Model {
    @Override
    public Observable<String> getIsHavePwd(String cardNo) {
        return ApiEngine.getInstance().getSwApiService()
                .getIsHavePwd(cardNo)
                .compose(RxSchedulers.<String>switchThread());
    }
}
