package com.rxjy.niujingji.mvp.contract;

import com.rxjy.niujingji.commons.base.BaseModel;
import com.rxjy.niujingji.commons.base.BasePresenter;
import com.rxjy.niujingji.commons.base.BaseView;
import com.rxjy.niujingji.entity.BankListInfo;

import java.util.List;

import rx.Observable;

/**
 * Created by AAA on 2017/7/28.
 */

public interface AddBankCardContract {

    interface View extends BaseView {

        void responseAddBankCard();

        void responseAddBankCardError(String msg);

        void responseBankListData(List<BankListInfo.BankInfo> dataList);

        void responseBankListDataError(String msg);

        void reLogin(String msg);

        void showDialog();

        void hideDialog();

    }

    interface Model extends BaseModel {
        Observable<String> subAddBankCard(
                String token,
                String cardNo,
                String bankCard,
                String bankName,
                String bankUserName
        );

        Observable<String> getBankListInfo(

        );
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void subAddBankCard(
                String token,
                String cardNo,
                String bankCard,
                String bankName,
                String bankUserName
        );

        public abstract void getBankListInfo(

        );
    }

}
