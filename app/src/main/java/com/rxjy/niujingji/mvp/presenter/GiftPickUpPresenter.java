package com.rxjy.niujingji.mvp.presenter;

import android.util.Log;

import com.rxjy.niujingji.commons.utils.JSONUtils;
import com.rxjy.niujingji.entity.GiftInfoBean;
import com.rxjy.niujingji.entity.GiftPickUpBean;
import com.rxjy.niujingji.mvp.contract.GiftPickUpContract;
import com.rxjy.niujingji.mvp.model.GiftPickUpModel;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by hjh on 2017/11/27.
 */

public class GiftPickUpPresenter extends GiftPickUpContract.Presenter{

    public GiftPickUpPresenter(GiftPickUpContract.View view) {
        this.mView=view;
        mModel=new GiftPickUpModel();
    }

    @Override
    public void getGiftInfo(String cardNo) {

        Subscription subscribe = mModel.getGiftInfo(cardNo)
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
                        Log.e("", "获取礼物详情 = " + e.toString());
                        mView.responseGiftInfoError("网络不给力！");
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e("获取礼物详情", s);
                        GiftInfoBean info = JSONUtils.toObject(s, GiftInfoBean.class);
                        if (info.getStatusCode() == 0) {
                            mView.responseGiftInfo(info);
                        }else {
                            mView.responseGiftInfoError(info.getStatusMsg());
                        }

                    }
                });
        addSubscribe(subscribe);

    }

    @Override
    public void getGiftPickUp(String Id, String GiftName, String Color, String Name, String Phone, String Address) {
        Subscription subscribe = mModel.getGiftPickUp(Id,GiftName,Color,Name,Phone,Address)
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
                        Log.e("", "提交领取礼物 = " + e.toString());
                        mView.responseGiftPickUpError("网络不给力！");
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e("提交领取礼物", s);
                        GiftPickUpBean info = JSONUtils.toObject(s, GiftPickUpBean.class);
                        if (info.getStatusCode() == 0) {
                            mView.responseGiftPickUp();
                        }else {
                            mView.responseGiftPickUpError(info.getStatusMsg());
                        }

                    }
                });
        addSubscribe(subscribe);
    }
}
