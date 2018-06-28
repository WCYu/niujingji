package com.rxjy.niujingji.mvp.model;

import com.rxjy.niujingji.api.ApiEngine;
import com.rxjy.niujingji.mvp.contract.AddBankCardContract;
import com.rxjy.niujingji.rx.RxSchedulers;

import rx.Observable;

/**
 * Created by AAA on 2017/7/28.
 */

public class AddBankCardModel implements AddBankCardContract.Model {
//    @Override
//    public Observable<String> subAddBankCard(String token, String cardNo, String bankCard, String bankName, String bankUserName) {
//        return ApiEngine.getInstance().getRsApiService()
//                .subAddOrUpd(token, cardNo, bankCard, bankName, bankUserName)
//                .compose(RxSchedulers.<String>switchThread());
//    }

    @Override
    public Observable<String> subAddBankCard(String card_no, String BankId, String XingMing, String ZhangHao, String LeiXing, String MingCheng) {
        return ApiEngine.getInstance().getSwApiService()
                .subAddOrUpd(card_no, BankId, XingMing, ZhangHao, LeiXing,MingCheng)
                .compose(RxSchedulers.<String>switchThread());
    }

    @Override
    public Observable<String> getBankListInfo() {
        return ApiEngine.getInstance().getSwApiService()
                .getBankListInfo()
                .compose(RxSchedulers.<String>switchThread());
    }
}
