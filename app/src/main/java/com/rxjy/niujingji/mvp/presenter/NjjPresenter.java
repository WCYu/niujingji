package com.rxjy.niujingji.mvp.presenter;

import android.util.Log;

import com.rxjy.niujingji.commons.utils.JSONUtils;
import com.rxjy.niujingji.entity.IsReadStateInfo;
import com.rxjy.niujingji.entity.VersionInfo;
import com.rxjy.niujingji.mvp.contract.NjjContract;
import com.rxjy.niujingji.mvp.model.NjjModel;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by AAA on 2017/8/9.
 */

public class NjjPresenter extends NjjContract.Presenter {

    public static final String TAG = "NjjPresenter";

    public NjjPresenter(NjjContract.View mView) {
        this.mView = mView;
        mModel = new NjjModel();
    }

    @Override
    public void getVersionInfo() {
        Subscription subscribe = mModel.getVersionInfo()
                .subscribe(new Subscriber<String>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "获取版本信息失败 = " + e.toString());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        VersionInfo info = JSONUtils.toObject(s, VersionInfo.class);
                        if (info.getStatusCode() == 0) {
                            VersionInfo.Version data = info.getBody();
                            mView.responseVersionData(data);
                        } else {
                            mView.responseVersionDataError(info.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
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
