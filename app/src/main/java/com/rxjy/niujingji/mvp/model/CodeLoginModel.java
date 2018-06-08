package com.rxjy.niujingji.mvp.model;

import com.rxjy.niujingji.api.ApiEngine;
import com.rxjy.niujingji.mvp.contract.CodeLoginContract;
import com.rxjy.niujingji.rx.RxSchedulers;

import rx.Observable;

/**
 * Created by AAA on 2017/7/27.
 */

public class CodeLoginModel implements CodeLoginContract.Model {
    @Override
    public Observable<String> getTokenByCode(String userNo, String vCode) {
        return ApiEngine.getInstance().getRsApiService()
                .getTokenByCode(userNo, vCode, 100)
                .compose(RxSchedulers.<String>switchThread());
    }

    @Override
    public Observable<String> getLoginUserInfoByCode(String userNo, String token) {
        return ApiEngine.getInstance().getRsApiService()
                .getLoginUserInfo(userNo, token)
                .compose(RxSchedulers.<String>switchThread());
    }

    @Override
    public Observable<String> getVCode(String phoneNum) {
        return ApiEngine.getInstance().getRsApiService()
                .getVCodeLogin(phoneNum, 100)
                .compose(RxSchedulers.<String>switchThread());
    }
}
