package com.rxjy.niujingji.mvp.presenter;

import android.util.Log;

import com.rxjy.niujingji.commons.utils.JSONUtils;
import com.rxjy.niujingji.entity.PoiInfo;
import com.rxjy.niujingji.mvp.contract.PoiContract;
import com.rxjy.niujingji.mvp.model.PoiModel;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by AAA on 2017/9/12.
 */

public class PoiPresenter extends PoiContract.Presenter {

    public static final String TAG = "PoiPresenter";

    public PoiPresenter(PoiContract.View mView) {
        this.mView = mView;
        mModel = new PoiModel();
    }

    @Override
    public void getPoiSubway(String location) {
        Subscription subscribe = mModel.getPoiSubway(location)
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "检索周边失败 = " + e.toString());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        PoiInfo info = JSONUtils.toObject(s, PoiInfo.class);
                        if (info.getStatus() == 0) {
                            List<PoiInfo.SiteInfo> subwayList = info.getResults();
                            mView.responsePoiSubway(subwayList);
                        }
                    }
                });
        addSubscribe(subscribe);
    }

    @Override
    public void getPoiRestaurant(String location) {
        Subscription subscribe = mModel.getPoiRestaurant(location)
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "检索周边失败 = " + e.toString());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        PoiInfo info = JSONUtils.toObject(s, PoiInfo.class);
                        if (info.getStatus() == 0) {
                            List<PoiInfo.SiteInfo> restaurantList = info.getResults();
                            mView.responsePoiRestaurant(restaurantList);
                        }
                    }
                });
        addSubscribe(subscribe);
    }

    @Override
    public void getPoiBank(String location) {
        Subscription subscribe = mModel.getPoiBank(location)
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "检索周边失败 = " + e.toString());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        PoiInfo info = JSONUtils.toObject(s, PoiInfo.class);
                        if (info.getStatus() == 0) {
                            List<PoiInfo.SiteInfo> bankList = info.getResults();
                            mView.responsePoiBank(bankList);
                        }
                    }
                });
        addSubscribe(subscribe);
    }

    @Override
    public void getPoiHotel(String location) {
        Subscription subscribe = mModel.getPoiHotel(location)
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "检索周边失败 = " + e.toString());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        PoiInfo info = JSONUtils.toObject(s, PoiInfo.class);
                        if (info.getStatus() == 0) {
                            List<PoiInfo.SiteInfo> hotelList = info.getResults();
                            mView.responsePoiHotel(hotelList);
                        }
                    }
                });
        addSubscribe(subscribe);
    }
}
