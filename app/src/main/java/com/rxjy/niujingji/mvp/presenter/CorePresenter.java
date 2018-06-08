package com.rxjy.niujingji.mvp.presenter;

import android.util.Log;

import com.rxjy.niujingji.mvp.contract.CoreContract;
import com.rxjy.niujingji.mvp.model.CoreModel;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by Administrator on 2017/6/15.
 */
public class CorePresenter extends CoreContract.Presenter {

    public static final String TAG = "CorePresenter";

    private CoreContract.View mView;

    public CorePresenter(CoreContract.View mView) {
        this.mView = mView;
        mModel = new CoreModel();
    }

    @Override
    public void getIP() {
        Subscription subscribe = mModel.getIP()
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "获取IP失败 = " + e.toString());
                    }

                    @Override
                    public void onNext(String s) {
                        mView.responseIP(s);
                    }
                });
        addSubscribe(subscribe);
    }

    @Override
    public void subAppInfo(String cardNo, String province, String city, String equipment, String ip, String netWorkStatus) {
        Subscription subscribe = mModel.subAppInfo(cardNo, province, city, equipment, ip, netWorkStatus)
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "上传设备信息失败 = " + e.toString());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e(TAG, s);
                    }
                });
        addSubscribe(subscribe);
    }
}
