package com.rxjy.niujingji.mvp.model;

import com.rxjy.niujingji.api.ApiEngine;
import com.rxjy.niujingji.mvp.contract.LogoContract;
import com.rxjy.niujingji.rx.RxSchedulers;

import rx.Observable;

/**
 * Created by AAA on 2017/8/1.
 */

public class LogoModel implements LogoContract.Model {
    @Override
    public Observable<String> getLoginUserInfo(String userNo, String token) {
        return ApiEngine.getInstance().getRsApiService()
                .getLoginUserInfo(userNo, token)
                .compose(RxSchedulers.<String>switchThread());
    }

    @Override
    public Observable<String> AppLogin(String cardNo, String password, String vCode, String appId) {
        return ApiEngine.getInstance().getRsApiService()
                .getAppLogin(cardNo, password,vCode,appId)
                .compose(RxSchedulers.<String>switchThread());
    }
}
