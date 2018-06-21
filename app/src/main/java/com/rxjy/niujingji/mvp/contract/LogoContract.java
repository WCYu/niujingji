package com.rxjy.niujingji.mvp.contract;

import com.rxjy.niujingji.commons.base.BaseModel;
import com.rxjy.niujingji.commons.base.BasePresenter;
import com.rxjy.niujingji.commons.base.BaseView;
import com.rxjy.niujingji.entity.LoginInfo;
import com.rxjy.niujingji.entity.UserInfo;

import rx.Observable;

/**
 * Created by AAA on 2017/8/1.
 */

public interface LogoContract {

    interface View extends BaseView {

        void responseLogin(UserInfo.User data);

        void responseLoginError(String msg);

        void reLogin(String msg);

        void showDialog();

        void hideDialog();

        void responseLogin(LoginInfo.BodyBean loginInfo);

        void toLogin();

    }

    interface Model extends BaseModel {

        Observable<String> getLoginUserInfo(
                String userNo,
                String token
        );
        Observable<String> AppLogin(
                String cardNo,
                String password,
                String vCode,
                String appId
        );
    }

    abstract class Presenter extends BasePresenter<View, Model> {

        public abstract void getLoginUserInfo(
                String userNo,
                String token
        );
        public abstract void AppLogin(
                String cardNo,
                String password,
                String vCode,
                String appId
        );
    }

}
