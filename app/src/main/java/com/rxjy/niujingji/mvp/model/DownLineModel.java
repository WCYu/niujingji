package com.rxjy.niujingji.mvp.model;

import com.rxjy.niujingji.api.ApiEngine;
import com.rxjy.niujingji.mvp.contract.DownLineContract;
import com.rxjy.niujingji.rx.RxSchedulers;

import rx.Observable;


/**
 * Created by Administrator on 2018/5/30.
 */

public class DownLineModel implements DownLineContract.Model{

    @Override
    public Observable<String> getClientList(String card) {
        return ApiEngine.getInstance().getSwApiService()
                .GetSubordinateList(card)
                .compose(RxSchedulers.<String>switchThread());
    }
}
