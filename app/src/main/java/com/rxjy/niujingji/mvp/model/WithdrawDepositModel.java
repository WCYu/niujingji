package com.rxjy.niujingji.mvp.model;

import com.rxjy.niujingji.api.ApiEngine;
import com.rxjy.niujingji.mvp.contract.WithdrawDepositContract;
import com.rxjy.niujingji.rx.RxSchedulers;

import rx.Observable;

/**
 * Created by AAA on 2017/8/1.
 */

public class WithdrawDepositModel implements WithdrawDepositContract.Model {
    @Override
    public Observable<String> getWithdrawDeposit(String cardNo, double price, String cardNum, String bankName, String memberCard, String pwd) {
        return ApiEngine.getInstance().getSwApiService()
                .getWithdrawals(cardNo, price, cardNum, bankName, memberCard, pwd)
                .compose(RxSchedulers.<String>switchThread());
    }
}
