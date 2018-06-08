package com.rxjy.niujingji.mvp.presenter;

import android.util.Log;

import com.rxjy.niujingji.commons.utils.JSONUtils;
import com.rxjy.niujingji.entity.WalletInfo;
import com.rxjy.niujingji.entity.WalletRecordInfo;
import com.rxjy.niujingji.mvp.contract.WalletContract;
import com.rxjy.niujingji.mvp.model.WalletModel;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by Administrator on 2017/6/30.
 */
public class WalletPresenter extends WalletContract.Presenter {

    public static final String TAG = "WalletPresenter";

    public WalletPresenter(WalletContract.View mView) {
        this.mView = mView;
        mModel = new WalletModel();
    }

    @Override
    public void getWalletInfo(String cardNo) {
        Subscription subscribe = mModel.getWalletInfo(cardNo)
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
                        Log.e(TAG, "获取钱包信息失败 = " + e.toString());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        WalletInfo info = JSONUtils.toObject(s, WalletInfo.class);
                        if (info.getStatusCode() == 0) {
                            WalletInfo.Wallet data = info.getBody();
                            mView.responseWalletData(data);
                        } else {
                            mView.responseWalletDataError(info.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }

    @Override
    public void getWalletRecordList(String cardNo, int pageIndex, int pageSize) {
        Subscription subscribe = mModel.getWalletRecordList(cardNo, pageIndex, pageSize)
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
                        Log.e(TAG, "获取收支明细失败 = " + e.toString());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        WalletRecordInfo info = JSONUtils.toObject(s, WalletRecordInfo.class);
                        if (info.getStatusCode() == 0) {
                            List<WalletRecordInfo.WalletRecord> dataList = info.getBody();
                            mView.responseWalletRecordListData(dataList);
                        } else {
                            mView.responseWalletRecordListDataError(info.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }

    @Override
    public void getWalletRecordLoadList(String cardNo, int pageIndex, int pageSize) {
        Subscription subscribe = mModel.getWalletRecordLoadList(cardNo, pageIndex, pageSize)
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
                        Log.e(TAG, "加载更多收支明细失败 = " + e.toString());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        WalletRecordInfo info = JSONUtils.toObject(s, WalletRecordInfo.class);
                        if (info.getStatusCode() == 0) {
                            List<WalletRecordInfo.WalletRecord> dataList = info.getBody();
                            mView.responseWalletRecordLoadListData(dataList);
                        } else {
                            mView.responseWalletRecordLoadListDataError(info.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }
}
