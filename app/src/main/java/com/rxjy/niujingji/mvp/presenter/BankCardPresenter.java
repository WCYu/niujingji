package com.rxjy.niujingji.mvp.presenter;

import android.util.Log;

import com.rxjy.niujingji.commons.utils.JSONUtils;
import com.rxjy.niujingji.entity.UserInfo;
import com.rxjy.niujingji.mvp.contract.BankCardContract;
import com.rxjy.niujingji.mvp.model.BankCardModel;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by AAA on 2017/8/23.
 */

public class BankCardPresenter extends BankCardContract.Presenter {

    public static final String TAG = "BankCardPresenter";

    public BankCardPresenter(BankCardContract.View mView) {
        this.mView = mView;
        mModel = new BankCardModel();
    }

    @Override
    public void getLoginUserInfo(final String userNo, String token) {
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
                        Log.e(TAG, "获取个人信息失败 = " + e.toString());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        UserInfo info = JSONUtils.toObject(s, UserInfo.class);
                        if (info.getStatusCode() == 0) {
                            UserInfo.User data = info.getBody();
                            mView.responseLogin(data);
                        }
                    }
                });
        addSubscribe(subscribe);
    }
}
