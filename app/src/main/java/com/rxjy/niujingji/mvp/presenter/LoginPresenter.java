package com.rxjy.niujingji.mvp.presenter;

import android.util.Log;

import com.rxjy.niujingji.commons.utils.JSONUtils;
import com.rxjy.niujingji.entity.CheckLoginBean;
import com.rxjy.niujingji.entity.LoginInfo;
import com.rxjy.niujingji.entity.SubInfo;
import com.rxjy.niujingji.entity.TokenInfo;
import com.rxjy.niujingji.entity.UserInfo;
import com.rxjy.niujingji.mvp.contract.LoginContract;
import com.rxjy.niujingji.mvp.model.LoginModel;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by AAA on 2017/7/27.
 */

public class LoginPresenter extends LoginContract.Presenter {

    public static final String TAG = "";

    public LoginPresenter(LoginContract.View mView) {
        this.mView = mView;
        mModel = new LoginModel();
    }

    @Override
    public void getToken(String userNo, String password) {
        Subscription subscribe = mModel.getToken(userNo, password)
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
                        Log.e(TAG, "获取Token失败 = " + e.toString());
                        mView.showLoadMessage(e.getMessage());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        TokenInfo info = JSONUtils.toObject(s, TokenInfo.class);
                        if (info.getStatusCode() == 0) {
                            TokenInfo.Token data = info.getBody();
                            mView.responseToken(data);
                        } else {
                            mView.responseTokenError(info.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
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
                        mView.showLoadMessage(e.getMessage());
                        onCompleted();

                    }

                    @Override
                    public void onNext(String s) {
                        UserInfo info = JSONUtils.toObject(s, UserInfo.class);
                        if (info.getStatusCode() == 0) {
                            UserInfo.User data = info.getBody();
                            mView.responseLogin(data);
                        } else {
                            mView.responseLoginError(info.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }

    @Override
    public void getInsideVcodeLanding(String Phone, String AppID) {
        Subscription subscribe = mModel.getInsideVcodeLanding(Phone, AppID)
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
                        mView.showLoadMessage(e.getMessage());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        SubInfo info = JSONUtils.toObject(s, SubInfo.class);

                          mView.responseVerityCode(info);


                    }
                });
        addSubscribe(subscribe);
    }

    @Override
    public void getCheckUserInfo(String Phone, String AppID) {
        Subscription subscribe = mModel.getCheckUserInfo(Phone, AppID)
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
                        mView.showLoadMessage(e.getMessage());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e(TAG,s);
                        CheckLoginBean info = JSONUtils.toObject(s, CheckLoginBean.class);

                           mView.responseIsCheck(info);

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
                        mView.showLoadMessage(e.getMessage());
                        onCompleted();
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
