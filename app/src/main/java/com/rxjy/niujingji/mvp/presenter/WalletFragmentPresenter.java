package com.rxjy.niujingji.mvp.presenter;

import android.util.Log;

import com.rxjy.niujingji.commons.utils.JSONUtils;
import com.rxjy.niujingji.entity.IsReadStateInfo;
import com.rxjy.niujingji.mvp.contract.WalletFragmentContract;
import com.rxjy.niujingji.mvp.model.WalletFragmentModel;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by Administrator on 2018/5/31.
 */

public class WalletFragmentPresenter extends WalletFragmentContract.Presenter {
    public static final String TAG = "NjjPresenter";

    public WalletFragmentPresenter(WalletFragmentContract.View mView) {
        this.mView = mView;
        mModel = new WalletFragmentModel();
    }
    @Override
    public void getState(String cardNo) {
        Subscription subscribe = mModel.getState(cardNo)
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "获取状态失败 = " + e.toString());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e("lrj++",s);
                        IsReadStateInfo info = JSONUtils.toObject(s, IsReadStateInfo.class);
                        if (info.getStatusCode() == 0) {
                            IsReadStateInfo.BodyBean data = info.getBody();

                            mView.responseStateData(data);
                        }else {
                            mView.responseVersionDataError(info.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }
}
