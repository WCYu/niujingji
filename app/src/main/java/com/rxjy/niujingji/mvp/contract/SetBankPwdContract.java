package com.rxjy.niujingji.mvp.contract;

import com.rxjy.niujingji.commons.base.BaseModel;
import com.rxjy.niujingji.commons.base.BasePresenter;
import com.rxjy.niujingji.commons.base.BaseView;

import rx.Observable;

/**
 * Created by AAA on 2017/8/1.
 */

public interface SetBankPwdContract {

    interface View extends BaseView {

        void responseSetPwdData();

        void responseSetPwdDataError(String msg);

        void showDialog();

        void hideDialog();

    }

    interface Model extends BaseModel {

        Observable<String> getSetPwd(
                String cardNo,
                String pwd
        );

    }

    abstract class Presenter extends BasePresenter<View, Model> {

        public abstract void getSetPwd(
                String cardNo,
                String pwd
        );

    }

}
