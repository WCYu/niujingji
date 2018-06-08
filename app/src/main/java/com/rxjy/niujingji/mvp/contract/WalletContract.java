package com.rxjy.niujingji.mvp.contract;


import com.rxjy.niujingji.commons.base.BaseModel;
import com.rxjy.niujingji.commons.base.BasePresenter;
import com.rxjy.niujingji.commons.base.BaseView;
import com.rxjy.niujingji.entity.WalletInfo;
import com.rxjy.niujingji.entity.WalletRecordInfo;

import java.util.List;

import rx.Observable;

/**
 * Created by Administrator on 2017/6/30.
 */
public interface WalletContract {

    interface View extends BaseView {

        void responseWalletData(WalletInfo.Wallet data);

        void responseWalletDataError(String msg);

        void responseWalletRecordListData(List<WalletRecordInfo.WalletRecord> dataList);

        void responseWalletRecordListDataError(String msg);

        void responseWalletRecordLoadListData(List<WalletRecordInfo.WalletRecord> dataList);

        void responseWalletRecordLoadListDataError(String msg);

        void showDialog();

        void hideDialog();

    }

    interface Model extends BaseModel {

        Observable<String> getWalletInfo(
                String cardNo
        );

        Observable<String> getWalletRecordList(
                String cardNo,
                int pageIndex,
                int pageSize
        );

        Observable<String> getWalletRecordLoadList(
                String cardNo,
                int pageIndex,
                int pageSize
        );

    }

    abstract class Presenter extends BasePresenter<View, Model> {

        public abstract void getWalletInfo(
                String cardNo
        );

        public abstract void getWalletRecordList(
                String cardNo,
                int pageIndex,
                int pageSize
        );

        public abstract void getWalletRecordLoadList(
                String cardNo,
                int pageIndex,
                int pageSize
        );

    }

}
