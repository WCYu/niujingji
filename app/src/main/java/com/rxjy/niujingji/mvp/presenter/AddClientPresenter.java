package com.rxjy.niujingji.mvp.presenter;

import android.util.Log;

import com.rxjy.niujingji.commons.utils.JSONUtils;
import com.rxjy.niujingji.entity.AddClientInfo;
import com.rxjy.niujingji.entity.ClientListInfo;
import com.rxjy.niujingji.entity.StatusBean;
import com.rxjy.niujingji.entity.TcInfo;
import com.rxjy.niujingji.mvp.contract.AddClientContract;
import com.rxjy.niujingji.mvp.model.AddClientModel;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by AAA on 2017/7/31.
 */

public class AddClientPresenter extends AddClientContract.Presenter {

    public static final String TAG = "AddClientPresenter";

    public AddClientPresenter(AddClientContract.View mView) {
        this.mView = mView;
        mModel = new AddClientModel();
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
                            List<TcInfo.BodyBean.TC> tcList = data.getPackageType();
                            List<TcInfo.BodyBean.ClientType> clientList = data.getCusTomer();
                            mView.responseTc(tcList);
                            mView.responseClientType(clientList);
                        } else {
                            mView.responseTcError(info.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }

    @Override
    public void subClientInfo(String clientName, String phoneNum, String wx, String acreage, String typeOne, String typeTwo, final String companyName, String measureLocation, String cardNo, String tcType) {
        Subscription subscribe = mModel.subClientInfo(clientName, phoneNum, wx, acreage, typeOne, typeTwo, companyName, measureLocation, cardNo, tcType)
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
                        Log.e(TAG, "提交客户信息失败 = " + e.toString());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        AddClientInfo info = JSONUtils.toObject(s, AddClientInfo.class);
                        if (info.getStatusCode() == 0) {
                            ClientListInfo.ClientInfo data = info.getBody();
                            mView.responseClientData(data);
                        } else {
                            mView.responseClientDataError(info.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }

    @Override
    public void getphone(String phoneNum) {
        Subscription subscribe = mModel.getphone(phoneNum)
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
                        onCompleted();
                        mView.responsephoneError("请检查您的网络！");
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e("验证客户手机号", s);
                        StatusBean info = JSONUtils.toObject(s, StatusBean.class);
                        if (info.getStatusCode() == 0) {
                            mView.responsephone(s);
                        } else if(info.getStatusCode() == 1){
                            mView.responsephoneError("手机号重复！");
                        }else if(info.getStatusCode() == 3){
                            mView.responsephoneError("验证异常！");
                        }
                    }
                });
        addSubscribe(subscribe);
    }
}
