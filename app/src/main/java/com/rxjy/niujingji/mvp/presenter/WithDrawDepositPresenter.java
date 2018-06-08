package com.rxjy.niujingji.mvp.presenter;

import android.util.Log;

import com.rxjy.niujingji.commons.utils.JSONUtils;
import com.rxjy.niujingji.entity.SubInfo;
import com.rxjy.niujingji.mvp.contract.WithdrawDepositContract;
import com.rxjy.niujingji.mvp.model.WithdrawDepositModel;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by AAA on 2017/8/1.
 */

public class WithDrawDepositPresenter extends WithdrawDepositContract.Presenter {

    public static final String TAG = "WithDrawDeposit";

    public WithDrawDepositPresenter(WithdrawDepositContract.View mView) {
        this.mView = mView;
        mModel = new WithdrawDepositModel();
    }

    @Override
    public void getWithdrawDeposit(String cardNo, double price, String cardNum, String bankName, String memberCard, String pwd) {
        Subscription subscribe = mModel.getWithdrawDeposit(cardNo, price, cardNum, bankName, memberCard, pwd)
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
                        Log.e(TAG, "支款失败 = " + e.toString());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        SubInfo info = JSONUtils.toObject(s, SubInfo.class);
                        if (info.getStatusCode() == 0) {
                            mView.responseWithdrawDepositData();
                        } else {
                            mView.responseWithdrawDepositDataError(info.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }
}
