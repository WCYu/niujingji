package com.rxjy.niujingji.mvp.model;

import com.rxjy.niujingji.api.ApiEngine;
import com.rxjy.niujingji.mvp.contract.LoginContract;
import com.rxjy.niujingji.rx.RxSchedulers;

import rx.Observable;

/**
 * Created by AAA on 2017/7/27.
 */

public class LoginModel implements LoginContract.Model {
    @Override
    public Observable<String> getToken(String userNo, String password) {
        return ApiEngine.getInstance().getRsApiService()
                .getToken(userNo, password, 100)
                .compose(RxSchedulers.<String>switchThread());
    }

    @Override
    public Observable<String> getLoginUserInfo(String userNo, String token) {
        return ApiEngine.getInstance().getRsApiService()
                .getLoginUserInfo(userNo, token)
                .compose(RxSchedulers.<String>switchThread());
    }

    @Override
    public Observable<String> getCheckUserInfo(String Phone, String AppID) {
        return ApiEngine.getInstance().getRsApiService()
                .getCheckUserInfo(Phone, AppID)
                .compose(RxSchedulers.<String>switchThread());
    }

    @Override
    public Observable<String> getInsideVcodeLanding(String Phone, String AppID) {
        return ApiEngine.getInstance().getRsApiService()
                .getInsideVcodeLanding(Phone, AppID)
                .compose(RxSchedulers.<String>switchThread());
    }

    @Override
    public Observable<String> AppLogin(String cardNo, String password, String vCode, String appId) {
        return ApiEngine.getInstance().getRsApiService()
                .getAppLogin(cardNo, password,vCode,appId)
                .compose(RxSchedulers.<String>switchThread());
    }
}
