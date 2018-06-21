package com.rxjy.niujingji.mvp.presenter;

import android.util.Log;

import com.rxjy.niujingji.commons.utils.JSONUtils;
import com.rxjy.niujingji.entity.LoginInfo;
import com.rxjy.niujingji.entity.UserInfo;
import com.rxjy.niujingji.mvp.contract.LogoContract;
import com.rxjy.niujingji.mvp.model.LogoModel;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by AAA on 2017/8/1.
 */

public class LogoPresenter extends LogoContract.Presenter {

    public static final String TAG = "LogoPresenter";

    public LogoPresenter(LogoContract.View mView) {
        this.mView = mView;
        mModel = new LogoModel();
    }

    @Override
    public void getLoginUserInfo(String userNo, String token) {
        Subscription subscribe = mModel.getLoginUserInfo(userNo, token)
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
                        Log.e(TAG, "获取用户信息失败 = " + e.toString());
                        onCompleted();
                        mView.toLogin();
                    }

                    @Override
                    public void onNext(String s) {

                        UserInfo info = JSONUtils.toObject(s, UserInfo.class);
                        if (info.getStatusCode() == 0) {
                            UserInfo.User data = info.getBody();
                            mView.responseLogin(data);
                        } else if (info.getStatusCode() == 104) {
                            mView.reLogin(info.getStatusMsg());
                        } else {
                            mView.responseLoginError(info.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }

    @Override
    public void AppLogin(String cardNo, String password, String vCode, String appId) {
        Subscription subscribe = mModel.AppLogin(cardNo, password,vCode,appId)
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
                        Log.e(TAG, "获取用户信息失败 = " + e.toString());
                        onCompleted();
                        mView.toLogin();
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e("tag",s);
                        LoginInfo info = JSONUtils.toObject(s, LoginInfo.class);
                        if (info.getStatusCode() == 0) {
                            LoginInfo.BodyBean data = info.getBody();
                            mView.responseLogin(data);
                        } else {
                            mView.responseLoginError(info.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }
}
