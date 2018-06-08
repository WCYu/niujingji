package com.rxjy.niujingji.mvp.presenter;

import android.util.Log;

import com.rxjy.niujingji.commons.utils.JSONUtils;
import com.rxjy.niujingji.entity.SubInfo;
import com.rxjy.niujingji.mvp.contract.SetBankPwdContract;
import com.rxjy.niujingji.mvp.model.SetBankPwdModel;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by AAA on 2017/8/1.
 */

public class SetBankPwdPresenter extends SetBankPwdContract.Presenter {

    public static final String TAG = "SetBankPwdPresenter";

    public SetBankPwdPresenter(SetBankPwdContract.View mView) {
        this.mView = mView;
        mModel = new SetBankPwdModel();
    }

    @Override
    public void getSetPwd(String cardNo, String pwd) {
        Subscription subscribe = mModel.getSetPwd(cardNo, pwd)
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
                        Log.e(TAG, "设置支付密码失败 = " + e.toString());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        SubInfo info = JSONUtils.toObject(s, SubInfo.class);
                        if (info.getStatusCode() == 0) {
                            mView.responseSetPwdData();
                        } else {
                            mView.responseSetPwdDataError(info.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }
}
