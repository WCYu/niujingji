package com.rxjy.niujingji.mvp.model;

import com.rxjy.niujingji.api.ApiEngine;
import com.rxjy.niujingji.mvp.contract.AddClientContract;
import com.rxjy.niujingji.rx.RxSchedulers;

import rx.Observable;

/**
 * Created by AAA on 2017/7/31.
 */

public class AddClientModel implements AddClientContract.Model {
    @Override
    public Observable<String> getTc() {
        return ApiEngine.getInstance().getSwApiService()
                .getTc("2")
                .compose(RxSchedulers.<String>switchThread());
    }

    @Override
    public Observable<String> subClientInfo(String clientName, String phoneNum, String wx, String acreage, String typeOne, String typeTwo, String companyName, String measureLocation, String cardNo, String tcType) {
        return ApiEngine.getInstance().getSwApiService()
                .subClientInfo(clientName, phoneNum, wx, acreage, typeOne, typeTwo, companyName, measureLocation, cardNo, "0", tcType)
                .compose(RxSchedulers.<String>switchThread());
    }

    @Override
    public Observable<String> getphone(String phoneNum) {
        return ApiEngine.getInstance().getSwApiService()
                .postCustomerPhone(phoneNum)
                .compose(RxSchedulers.<String>switchThread());
    }

}
