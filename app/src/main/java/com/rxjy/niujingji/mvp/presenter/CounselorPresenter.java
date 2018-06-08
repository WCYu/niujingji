package com.rxjy.niujingji.mvp.presenter;

import android.util.Log;

import com.rxjy.niujingji.commons.utils.JSONUtils;
import com.rxjy.niujingji.entity.CounselorInfo;
import com.rxjy.niujingji.mvp.contract.CounselorContract;
import com.rxjy.niujingji.mvp.model.CounselorModel;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by AAA on 2017/8/9.
 */

public class CounselorPresenter extends CounselorContract.Presenter {

    public static final String TAG = "CounselorPresenter";

    public CounselorPresenter(CounselorContract.View mView) {
        this.mView = mView;
        mModel = new CounselorModel();
    }

    @Override
    public void getCounselorInfo(String cardNo) {
        Subscription subscribe = mModel.getCounselorInfo(cardNo)
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
                        Log.e(TAG, "获取顾问信息失败 = " + e.toString());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        CounselorInfo info = JSONUtils.toObject(s, CounselorInfo.class);
                        if (info.getStatusCode() == 0) {
                            CounselorInfo.Counselor data = info.getBody();
                            mView.responseCounselorData(data);
                        } else {
                            mView.responseCounselorDataError(info.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }
}
