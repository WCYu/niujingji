package com.rxjy.niujingji.mvp.contract;


import com.rxjy.niujingji.commons.base.BaseModel;
import com.rxjy.niujingji.commons.base.BasePresenter;
import com.rxjy.niujingji.commons.base.BaseView;
import com.rxjy.niujingji.entity.CheckLoginBean;
import com.rxjy.niujingji.entity.LoginInfo;
import com.rxjy.niujingji.entity.SubInfo;
import com.rxjy.niujingji.entity.TokenInfo;
import com.rxjy.niujingji.entity.UserInfo;

import rx.Observable;

/**
 * Created by Administrator on 2017/4/21.
 */
public interface LoginContract {

    interface View extends BaseView {

        void responseToken(TokenInfo.Token data);

        void responseTokenError(String msg);

        void responseLogin(UserInfo.User data);

        void responseLoginError(String msg);

        void showDialog();

        void hideDialog();

        void responseIsCheck(CheckLoginBean subInfo);

         void responseVerityCode(SubInfo data);

        void responseLogin(LoginInfo.BodyBean loginInfo);

    }

    interface Model extends BaseModel {
        Observable<String> getToken(
                String userNo,
                String password
        );

        Observable<String> getLoginUserInfo(
                String userNo,
                String token
        );

        Observable<String> getCheckUserInfo(
                String Phone,
                String AppID
        );

        //
        Observable<String> getInsideVcodeLanding(
                String Phone,
                String AppID
        );

        Observable<String> AppLogin(
                String cardNo,
                String password,
                String vCode,
                String appId
        );
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getToken(
                String userNo,
                String password
        );

        public abstract void getLoginUserInfo(
                String userNo,
                String token
        );
        public abstract void getInsideVcodeLanding(
                String Phone,
                String AppID
        );
        public abstract void getCheckUserInfo(
                String Phone,
                String AppID
        );
        public abstract void AppLogin(
                String cardNo,
                String password,
                String vCode,
                String appId
        );
    }

}
