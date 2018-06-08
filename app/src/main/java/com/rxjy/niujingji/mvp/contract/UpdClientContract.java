package com.rxjy.niujingji.mvp.contract;

import com.rxjy.niujingji.commons.base.BaseModel;
import com.rxjy.niujingji.commons.base.BasePresenter;
import com.rxjy.niujingji.commons.base.BaseView;
import com.rxjy.niujingji.entity.ClientInfo;
import com.rxjy.niujingji.entity.TcInfo;

import java.util.List;

import rx.Observable;

/**
 * Created by AAA on 2017/8/9.
 */

public interface UpdClientContract {

    interface View extends BaseView {

        void responseClientType(List<TcInfo.BodyBean.ClientType> dataList);

        void responseTcError(String msg);

        void responseUpdClientData();

        void responseUpdClientDataError(String msg);

        void responseClientData(ClientInfo.Client data);

        void responseClientDataError(String msg);

        void showDialog();

        void hideDialog();

    }

    interface Model extends BaseModel {

        Observable<String> getTc(

        );

        Observable<String> getClientInfo(
                int clientID
        );

        Observable<String> updClientInfo(
                String clientName,
                String wx,
                String acreage,
                String typeOne,
                String typeTwo,
                String companyName,
                String measureLocation,
                String cardNo,
                String type,
                String tcType,
                int clientID
        );
    }

    abstract class Presenter extends BasePresenter<View, Model> {

        public abstract void getTc(

        );

        public abstract void getClientInfo(
                int clientID
        );

        public abstract void updClientInfo(
                String clientName,
                String wx,
                String acreage,
                String typeOne,
                String typeTwo,
                String companyName,
                String measureLocation,
                String cardNo,
                String type,
                String tcType,
                int clientID
        );

    }

}
