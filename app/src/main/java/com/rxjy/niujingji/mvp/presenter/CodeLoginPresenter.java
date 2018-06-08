package com.rxjy.niujingji.mvp.presenter;

import android.util.Log;

import com.rxjy.niujingji.commons.utils.JSONUtils;
import com.rxjy.niujingji.entity.SubInfo;
import com.rxjy.niujingji.entity.TokenInfo;
import com.rxjy.niujingji.entity.UserInfo;
import com.rxjy.niujingji.mvp.contract.CodeLoginContract;
import com.rxjy.niujingji.mvp.model.CodeLoginModel;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by AAA on 2017/7/27.
 */

public class CodeLoginPresenter extends CodeLoginContract.Presenter {

    public static final String TAG = "CodeLoginPresenter";

    public CodeLoginPresenter(CodeLoginContract.View mView) {
        this.mView = mView;
        mModel = new CodeLoginModel();
    }

    @Override
    public void getTokenByCode(String userNo, String vCode) {
        Subscription subscribe = mModel.getTokenByCode(userNo, vCode)
                .subscribe(new Subscriber<String>() {

                    @Override
                    public void onStart() {
                        mView.showDialog();
                    }

                    @Override
                    public void onCompleted() {
                        mView.hideDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "验证码获取Token失败 = " + e.toString());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        TokenInfo info = JSONUtils.toObject(s, TokenInfo.class);
                        if (info.getStatusCode() == 0) {
                            TokenInfo.Token data = info.getBody();
                            mView.responseTokenByCode(data);
                        } else {
                            mView.responseTokenByCodeError(info.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }

    @Override
    public void getLoginUserInfoByCode(String userNo, String token) {
        Subscription subscribe = mModel.getLoginUserInfoByCode(userNo, token)
                .subscribe(new Subscriber<String>() {

                    @Override
                    public void onStart() {
                        mView.showDialog();
                    }

                    @Override
                    public void onCompleted() {
                        mView.hideDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "验证码获取用户信息失败 = " + e.toString());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        UserInfo info = JSONUtils.toObject(s, UserInfo.class);
                        if (info.getStatusCode() == 0) {
                            UserInfo.User data = info.getBody();
                            mView.responseLoginByCode(data);
                        } else {
                            mView.responseLoginByCodeError(info.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }

    @Override
    public void getVCode(String phoneNum) {
        Subscription subscribe = mModel.getVCode(phoneNum)
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "获取验证码失败 = " + e.toString());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        SubInfo info = JSONUtils.toObject(s, SubInfo.class);
                        if (info.getStatusCode() == 0) {
                            mView.responseVCcode();
                        } else {
                            mView.responseVCodeError(info.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }
}
