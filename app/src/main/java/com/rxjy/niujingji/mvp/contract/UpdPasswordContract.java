package com.rxjy.niujingji.mvp.contract;


import com.rxjy.niujingji.commons.base.BaseModel;
import com.rxjy.niujingji.commons.base.BasePresenter;
import com.rxjy.niujingji.commons.base.BaseView;

import rx.Observable;

/**
 * Created by qindd on 2017/6/29.
 */
public interface UpdPasswordContract {

    interface View extends BaseView {

        void responsePwd();

        void responsePwdError(String msg);

        void reLogin(String msg);

        void showDialog();

        void hideDialog();

    }

    interface Model extends BaseModel {
        Observable<String> updatePwd(
                String cardNo,
                String password,
                String newPassword,
                String token
        );
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void updatePwd(
                String cardNo,
                String password,
                String newPassword,
                String token
        );

    }

}
