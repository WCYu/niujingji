package com.rxjy.niujingji.mvp.contract;

import com.rxjy.niujingji.commons.base.BaseModel;
import com.rxjy.niujingji.commons.base.BasePresenter;
import com.rxjy.niujingji.commons.base.BaseView;
import com.rxjy.niujingji.entity.IsHaveBankCardInfo;

import rx.Observable;

/**
 * Created by AAA on 2017/8/1.
 */

public interface BalanceContract {

    interface View extends BaseView {

        void responseIsHavePwdData(IsHaveBankCardInfo.IsHaveBankCard data);

        void responseIsHavePwdDataError(String msg);

        void showDialog();

        void hideDialog();

    }

    interface Model extends BaseModel {

        Observable<String> getIsHavePwd(
                String cardNo
        );

    }

    abstract class Presenter extends BasePresenter<View, Model> {

        public abstract void getIsHavePwd(
                String cardNo
        );

    }

}
