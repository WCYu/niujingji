package com.rxjy.niujingji.mvp.model;


import com.rxjy.niujingji.api.ApiEngine;
import com.rxjy.niujingji.mvp.contract.WalletFragmentContract;
import com.rxjy.niujingji.rx.RxSchedulers;

import rx.Observable;


/**
 * Created by Administrator on 2018/5/31.
 */

public class WalletFragmentModel implements WalletFragmentContract.Model {

    @Override
    public Observable<String> getState(String cardNo) {
        return ApiEngine.getInstance().getSwApiService()
                .getTabState(cardNo)
                .compose(RxSchedulers.<String>switchThread());
    }
}
