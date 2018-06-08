package com.rxjy.niujingji.mvp.model;

import com.rxjy.niujingji.api.ApiEngine;
import com.rxjy.niujingji.mvp.contract.GiftPickUpContract;
import com.rxjy.niujingji.rx.RxSchedulers;

import rx.Observable;

/**
 * Created by hjh on 2017/11/27.
 */

public class GiftPickUpModel implements GiftPickUpContract.Model{
    @Override
    public Observable<String> getGiftInfo(String cardNo) {
        return ApiEngine.getInstance().getSwApiService()
                .getGiftInfo(cardNo)
                .compose(RxSchedulers.<String>switchThread());
    }

    @Override
    public Observable<String> getGiftPickUp(String Id, String GiftName, String Color, String Name, String Phone, String Address) {
        return ApiEngine.getInstance().getSwApiService()
                .getGiftPickUp(Id,GiftName,Color,Name,Phone,Address)
                .compose(RxSchedulers.<String>switchThread());
    }
}
