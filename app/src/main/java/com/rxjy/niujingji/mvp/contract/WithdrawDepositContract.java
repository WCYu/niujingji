package com.rxjy.niujingji.mvp.contract;

import com.rxjy.niujingji.commons.base.BaseModel;
import com.rxjy.niujingji.commons.base.BasePresenter;
import com.rxjy.niujingji.commons.base.BaseView;

import rx.Observable;

/**
 * Created by AAA on 2017/8/1.
 */

public interface WithdrawDepositContract {

    interface View extends BaseView {

        void responseWithdrawDepositData();

        void responseWithdrawDepositDataError(String msg);

        void showDialog();

        void hideDialog();

    }

    interface Model extends BaseModel {
        Observable<String> getWithdrawDeposit(
                String cardNo,
                double price,
                String cardNum,
                String bankName,
                String memberCard,
                String pwd
        );

    }

    abstract class Presenter extends BasePresenter<View, Model> {

        public abstract void getWithdrawDeposit(
                String cardNo,
                double price,
                String cardNum,
                String bankName,
                String memberCard,
                String pwd
        );

    }

}
