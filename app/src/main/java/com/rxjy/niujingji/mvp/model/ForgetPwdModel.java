package com.rxjy.niujingji.mvp.model;

import com.rxjy.niujingji.api.ApiEngine;
import com.rxjy.niujingji.mvp.contract.ForgetPwdContract;
import com.rxjy.niujingji.rx.RxSchedulers;

import rx.Observable;

/**
 * Created by AAA on 2017/7/27.
 */

public class ForgetPwdModel implements ForgetPwdContract.Model {
    @Override
    public Observable<String> updatePassword(String phoneNum, String newPassword, String vCode) {
        return ApiEngine.getInstance().getRsApiService()
                .updatePasswordByForget(phoneNum, newPassword, vCode)
                .compose(RxSchedulers.<String>switchThread());
    }

    @Override
    public Observable<String> getVCode(String phoneNum) {
        return ApiEngine.getInstance().getRsApiService()
                .getVCodeForgetPassword(phoneNum)
                .compose(RxSchedulers.<String>switchThread());
    }
}
