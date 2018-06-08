package com.rxjy.niujingji.mvp.contract;


import com.rxjy.niujingji.commons.base.BaseModel;
import com.rxjy.niujingji.commons.base.BasePresenter;
import com.rxjy.niujingji.commons.base.BaseView;
import com.rxjy.niujingji.entity.TokenInfo;
import com.rxjy.niujingji.entity.UserInfo;

import rx.Observable;

/**
 * Created by Administrator on 2017/4/21.
 */
public interface CodeLoginContract {

    interface View extends BaseView {

        void responseTokenByCode(TokenInfo.Token data);

        void responseTokenByCodeError(String msg);

        void responseLoginByCode(UserInfo.User data);

        void responseLoginByCodeError(String msg);

        void responseVCcode();

        void responseVCodeError(String msg);

        void showDialog();

        void hideDialog();

    }

    interface Model extends BaseModel {
        Observable<String> getTokenByCode(
                String userNo,
                String vCode
        );

        Observable<String> getLoginUserInfoByCode(
                String userNo,
                String token
        );

        Observable<String> getVCode(
                String phoneNum
        );

    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getTokenByCode(
                String userNo,
                String vCode
        );

        public abstract void getLoginUserInfoByCode(
                String userNo,
                String token
        );

        public abstract void getVCode(
                String phoneNum
        );
    }

}
