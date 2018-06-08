package com.rxjy.niujingji.mvp.presenter;

import android.util.Log;

import com.rxjy.niujingji.commons.utils.JSONUtils;
import com.rxjy.niujingji.entity.ClientInfo;
import com.rxjy.niujingji.entity.SubInfo;
import com.rxjy.niujingji.entity.TcInfo;
import com.rxjy.niujingji.mvp.contract.UpdClientContract;
import com.rxjy.niujingji.mvp.model.UpdClientModel;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by AAA on 2017/8/9.
 */

public class UpdClientPresenter extends UpdClientContract.Presenter {

    public static final String TAG = "UpdClientPresenter";

    public UpdClientPresenter(UpdClientContract.View mView) {
        this.mView = mView;
        mModel = new UpdClientModel();
    }

    @Override
    public void getTc() {
        Subscription subscribe = mModel.getTc()
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
                        Log.e(TAG, "获取套餐类型信息失败 = " + e.toString());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        TcInfo info = JSONUtils.toObject(s, TcInfo.class);
                        if (info.getStatusCode() == 0) {
                            TcInfo.BodyBean data = info.getBody();
                            List<TcInfo.BodyBean.ClientType> clientList = data.getCusTomer();
                            mView.responseClientType(clientList);
                        } else {
                            mView.responseTcError(info.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }

    @Override
    public void getClientInfo(int clientID) {
        Subscription subscribe = mModel.getClientInfo(clientID)
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
                        Log.e(TAG, "获取客户信息失败 = " + e.toString());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        ClientInfo info = JSONUtils.toObject(s, ClientInfo.class);
                        if (info.getStatusCode() == 0) {
                            ClientInfo.Client data = info.getBody();
                            mView.responseClientData(data);
                        } else {
                            mView.responseClientDataError(info.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }

    @Override
    public void updClientInfo(String clientName, String wx, String acreage, String typeOne, String typeTwo, String companyName, String measureLocation, String cardNo, String type, String tcType, int clientID) {
        Subscription subscribe = mModel.updClientInfo(clientName, wx, acreage, typeOne, typeTwo, companyName, measureLocation, cardNo, type, tcType, clientID)
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
                        Log.e(TAG, "修改客户信息失败 = " + e.toString());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        SubInfo info = JSONUtils.toObject(s, SubInfo.class);
                        if (info.getStatusCode() == 0) {
                            mView.responseUpdClientData();
                        } else {
                            mView.responseUpdClientDataError(info.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }
}
