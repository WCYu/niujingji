package com.rxjy.niujingji.mvp.presenter;

import android.util.Log;

import com.rxjy.niujingji.commons.utils.JSONUtils;
import com.rxjy.niujingji.entity.ClientListInfo;
import com.rxjy.niujingji.entity.GiftGetBean;
import com.rxjy.niujingji.mvp.contract.HomeContract;
import com.rxjy.niujingji.mvp.model.HomeModel;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by AAA on 2017/7/31.
 */

public class HomePresenter extends HomeContract.Presenter {

    public static final String TAG = "HomePresenter";

    public HomePresenter(HomeContract.View mView) {
        this.mView = mView;
        mModel = new HomeModel();
    }

    @Override
    public void getClientList(String cardNo, String key) {
        Subscription subscribe = mModel.getClientList(cardNo, key)
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
                        Log.e(TAG, "获取客户列表失败 = " + e.toString());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        ClientListInfo info = JSONUtils.toObject(s, ClientListInfo.class);
                        if (info.getStatusCode() == 0) {
                            List<ClientListInfo.ClientInfo> dataList = info.getBody();
                            mView.responseClientListData(dataList);
                        } else {
                            mView.responseClientListDataError(info.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }

    @Override
    public void getGift(String cardNo) {

        Subscription subscribe = mModel.getGift(cardNo)
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
                        Log.e(TAG, "获取礼物失败 = " + e.toString());
                        onCompleted();
                        mView.responseGiftError("网络不给力！");
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e("获取礼物。。。",s.toString());
                        GiftGetBean giftGetBean=JSONUtils.toObject(s,GiftGetBean.class);
                        if (giftGetBean.getStatusCode() == 0) {
                            mView.responseGiftData(giftGetBean);
                        } else {
                            mView.responseGiftError(giftGetBean.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);

    }

}
