package com.rxjy.niujingji.mvp.model;

import com.rxjy.niujingji.api.ApiEngine;
import com.rxjy.niujingji.mvp.contract.MsgListContract;
import com.rxjy.niujingji.rx.RxSchedulers;

import rx.Observable;

/**
 * Created by AAA on 2017/8/15.
 */

public class MsgListModel implements MsgListContract.Model {
    @Override
    public Observable<String> getMsgList(String cardNo) {
        return ApiEngine.getInstance().getSwApiService()
                .getMsgList(cardNo)
                .compose(RxSchedulers.<String>switchThread());
    }
}
