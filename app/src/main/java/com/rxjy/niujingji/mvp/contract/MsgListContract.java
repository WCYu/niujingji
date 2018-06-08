package com.rxjy.niujingji.mvp.contract;

import com.rxjy.niujingji.commons.base.BaseModel;
import com.rxjy.niujingji.commons.base.BasePresenter;
import com.rxjy.niujingji.commons.base.BaseView;
import com.rxjy.niujingji.entity.MsgInfo;

import java.util.List;

import rx.Observable;

/**
 * Created by AAA on 2017/8/15.
 */

public interface MsgListContract {

    interface View extends BaseView {

        void responseMsgListData(List<MsgInfo.Msg> dataList);

        void responseMsgListDataError(String msg);

        void showDialog();

        void hideDialog();

    }

    interface Model extends BaseModel {

        Observable<String> getMsgList(
                String cardNo
        );

    }

    abstract class Presenter extends BasePresenter<View, Model> {

        public abstract void getMsgList(
                String cardNo
        );

    }

}
