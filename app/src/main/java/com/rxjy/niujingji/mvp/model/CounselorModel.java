package com.rxjy.niujingji.mvp.model;

import com.rxjy.niujingji.api.ApiEngine;
import com.rxjy.niujingji.mvp.contract.CounselorContract;
import com.rxjy.niujingji.rx.RxSchedulers;

import rx.Observable;

/**
 * Created by AAA on 2017/8/9.
 */

public class CounselorModel implements CounselorContract.Model {
    @Override
    public Observable<String> getCounselorInfo(String cardNo) {
        return ApiEngine.getInstance().getSwApiService()
                .getCounselorInfo(cardNo)
                .compose(RxSchedulers.<String>switchThread());
    }
}
