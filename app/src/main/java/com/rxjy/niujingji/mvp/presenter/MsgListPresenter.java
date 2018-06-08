package com.rxjy.niujingji.mvp.presenter;

import android.util.Log;

import com.rxjy.niujingji.commons.utils.JSONUtils;
import com.rxjy.niujingji.entity.MsgInfo;
import com.rxjy.niujingji.mvp.contract.MsgListContract;
import com.rxjy.niujingji.mvp.model.MsgListModel;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by AAA on 2017/8/15.
 */

public class MsgListPresenter extends MsgListContract.Presenter {

    public static final String TAG = "MsgListPresenter";

    public MsgListPresenter(MsgListContract.View mView) {
        this.mView = mView;
        mModel = new MsgListModel();
    }

    @Override
    public void getMsgList(String cardNo) {
        Subscription subscribe = mModel.getMsgList(cardNo)
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
                        Log.e(TAG, "获取消息列表失败 = " + e.toString());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        MsgInfo info = JSONUtils.toObject(s, MsgInfo.class);
                        if (info.getStatusCode() == 0) {
                            List<MsgInfo.Msg> dataList = info.getBody();
                            mView.responseMsgListData(dataList);
                        } else {
                            mView.responseMsgListDataError(info.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }
}
