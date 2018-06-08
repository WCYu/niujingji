package com.rxjy.niujingji.mvp.contract;

import com.rxjy.niujingji.commons.base.BaseModel;
import com.rxjy.niujingji.commons.base.BasePresenter;
import com.rxjy.niujingji.commons.base.BaseView;
import com.rxjy.niujingji.entity.UserInfo;

import rx.Observable;

/**
 * Created by AAA on 2017/8/23.
 */

public interface BankCardContract {

    interface View extends BaseView {

        void responseLogin(UserInfo.User data);

        void showDialog();

        void hideDialog();

    }

    interface Model extends BaseModel {
        Observable<String> getLoginUserInfo(
                String userNo,
                String token
        );
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getLoginUserInfo(
                String userNo,
                String token
        );
    }

}
