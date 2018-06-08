package com.rxjy.niujingji.mvp.contract;

import com.rxjy.niujingji.commons.base.BaseModel;
import com.rxjy.niujingji.commons.base.BasePresenter;
import com.rxjy.niujingji.commons.base.BaseView;
import com.rxjy.niujingji.entity.ClientListInfo;
import com.rxjy.niujingji.entity.GiftGetBean;

import java.util.List;

import rx.Observable;

/**
 * Created by AAA on 2017/7/31.
 */

public interface HomeContract {

    interface View extends BaseView {

        void responseClientListData(List<ClientListInfo.ClientInfo> dataList);

        void responseClientListDataError(String msg);

        void responseGiftData(GiftGetBean giftGetBean);

        void responseGiftError(String msg);

        void showDialog();

        void hideDialog();

    }

    interface Model extends BaseModel {

        Observable<String> getClientList(
                String cardNo,
                String key
        );
        Observable<String> getGift(
                String cardNo
        );

    }

    abstract class Presenter extends BasePresenter<View, Model> {

        public abstract void getClientList(
                String cardNo,
                String key
        );

        public abstract void getGift(
                String cardNo
        );

    }

}
