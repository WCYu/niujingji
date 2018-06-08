package com.rxjy.niujingji.mvp.presenter;

import android.util.Log;

import com.rxjy.niujingji.commons.utils.JSONUtils;
import com.rxjy.niujingji.entity.IsHaveBankCardInfo;
import com.rxjy.niujingji.mvp.contract.BalanceContract;
import com.rxjy.niujingji.mvp.model.BalanceModel;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by AAA on 2017/8/1.
 */

public class BalancePresenter extends BalanceContract.Presenter {

    public static final String TAG = "BalancePresenter";

    public BalancePresenter(BalanceContract.View mView) {
        this.mView = mView;
        mModel = new BalanceModel();
    }

    @Override
    public void getIsHavePwd(String cardNo) {
        Subscription subscribe = mModel.getIsHavePwd(cardNo)
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
                        Log.e(TAG, "获取是否有支付密码失败 = " + e.toString());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        IsHaveBankCardInfo info = JSONUtils.toObject(s, IsHaveBankCardInfo.class);
                        if (info.getStatusCode() == 0) {
                            IsHaveBankCardInfo.IsHaveBankCard data = info.getBody();
                            mView.responseIsHavePwdData(data);
                        } else {
                            mView.responseIsHavePwdDataError(info.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }
}
