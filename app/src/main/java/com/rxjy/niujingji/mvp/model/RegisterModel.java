package com.rxjy.niujingji.mvp.model;

import com.rxjy.niujingji.api.ApiEngine;
import com.rxjy.niujingji.mvp.contract.RegisterContract;
import com.rxjy.niujingji.rx.RxSchedulers;

import rx.Observable;

/**
 * Created by AAA on 2017/7/27.
 */

public class RegisterModel implements RegisterContract.Model {

    @Override
    public Observable<String> getRegister(String code, String phoneNum, String password, String invitationCode) {
        return ApiEngine.getInstance().getRsApiService()
                .getRegister(code, phoneNum, password, 2, "牛经纪", 100, invitationCode)
                .compose(RxSchedulers.<String>switchThread());
    }

    @Override
    public Observable<String> getVerificationCode(String phoneNum) {
        return ApiEngine.getInstance().getSwApiService()
                .getVerificationCode(phoneNum)
                .compose(RxSchedulers.<String>switchThread());
    }

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
}
