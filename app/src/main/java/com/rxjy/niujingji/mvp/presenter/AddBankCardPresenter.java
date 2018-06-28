package com.rxjy.niujingji.mvp.presenter;

import android.util.Log;

import com.rxjy.niujingji.commons.utils.JSONUtils;
import com.rxjy.niujingji.entity.NewBankListInfo;
import com.rxjy.niujingji.entity.SubInfo;
import com.rxjy.niujingji.mvp.contract.AddBankCardContract;
import com.rxjy.niujingji.mvp.model.AddBankCardModel;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by AAA on 2017/7/28.
 */

public class AddBankCardPresenter extends AddBankCardContract.Presenter {

    public static final String TAG = "AddBankCardPresenter";

    public AddBankCardPresenter(AddBankCardContract.View mView) {
        this.mView = mView;
        mModel = new AddBankCardModel();
    }


    @Override
    public void subAddBankCard(String card_no, String BankId, String XingMing, String ZhangHao, String LeiXing, String MingCheng) {
        mModel.subAddBankCard(card_no, BankId, XingMing, ZhangHao, LeiXing, MingCheng).subscribe(new Subscriber<String>() {
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
                Log.e(TAG, "添加银行卡失败 = " + e.toString());
                onCompleted();
            }

                @Override
                public void onNext(String s) {
                    Log.i(TAG,"data>>>>>>>>"+s);
                SubInfo info = JSONUtils.toObject(s, SubInfo.class);
                if (info.getStatusCode() == 0) {
                    mView.responseAddBankCard();
                } else if (info.getStatusCode() == 104) {
                    mView.reLogin(info.getStatusMsg());
                } else {
                    mView.responseAddBankCardError(info.getStatusMsg());
                }
            }
        });
    }

    @Override
    public void getBankListInfo() {
        Subscription subscribe = mModel.getBankListInfo()
                .subscribe(new Subscriber<String>() {

                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "获取银行列表失败 = " + e.toString());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        Log.i(TAG,"银行卡列表>>>>>>>>"+s);
                        NewBankListInfo info = JSONUtils.toObject(s, NewBankListInfo.class);
                        if (info.getStatusCode() == 0) {
                            List<NewBankListInfo.BodyBean.TableBean> dataList = info.getBody().getTable();
                            mView.responseBankListData(dataList);
                        } else if (info.getStatusCode() == 104) {
                            mView.reLogin(info.getStatusMsg());
                        } else {
                            mView.responseAddBankCardError(info.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }
}
