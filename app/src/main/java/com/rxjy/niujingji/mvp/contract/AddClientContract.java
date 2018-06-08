package com.rxjy.niujingji.mvp.contract;

import com.rxjy.niujingji.commons.base.BaseModel;
import com.rxjy.niujingji.commons.base.BasePresenter;
import com.rxjy.niujingji.commons.base.BaseView;
import com.rxjy.niujingji.entity.ClientListInfo;
import com.rxjy.niujingji.entity.TcInfo;

import java.util.List;

import rx.Observable;

/**
 * Created by AAA on 2017/7/31.
 */

public interface AddClientContract {

    interface View extends BaseView {

        void responseTc(List<TcInfo.BodyBean.TC> dataList);

        void responseClientType(List<TcInfo.BodyBean.ClientType> dataList);

        void responseTcError(String msg);

        void responseClientData(ClientListInfo.ClientInfo data);

        void responseClientDataError(String msg);

        void responsephone(String msg);

        void responsephoneError(String msg);

        void showDialog();

        void hideDialog();

    }

    interface Model extends BaseModel {

        Observable<String> getTc(

        );

        Observable<String> subClientInfo(
                String clientName,
                String phoneNum,
                String wx,
                String acreage,
                String typeOne,
                String typeTwo,
                String companyName,
                String measureLocation,
                String cardNo,
                String tcType
        );

        Observable<String> getphone(
                String phoneNum
        );
    }

    abstract class Presenter extends BasePresenter<View, Model> {

        public abstract void getTc(

        );

        public abstract void subClientInfo(
                String clientName,
                String phoneNum,
                String wx,
                String acreage,
                String typeOne,
                String typeTwo,
                String companyName,
                String measureLocation,
                String cardNo,
                String tcType
        );

        public abstract void getphone(
                String phoneNum
        );

    }

}
