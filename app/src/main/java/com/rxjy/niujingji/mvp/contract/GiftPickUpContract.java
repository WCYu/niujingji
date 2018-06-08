package com.rxjy.niujingji.mvp.contract;

import com.rxjy.niujingji.commons.base.BaseModel;
import com.rxjy.niujingji.commons.base.BasePresenter;
import com.rxjy.niujingji.commons.base.BaseView;
import com.rxjy.niujingji.entity.GiftInfoBean;

import rx.Observable;

/**
 * Created by hjh on 2017/11/27.
 */

public interface GiftPickUpContract {

    interface View extends BaseView {

        void responseGiftInfo(GiftInfoBean giftInfoBean);

        void responseGiftInfoError(String msg);

        void responseGiftPickUp();

        void responseGiftPickUpError(String msg);

        void showDialog();

        void hideDialog();

    }

    interface Model extends BaseModel {
        Observable<String> getGiftInfo(
                String cardNo
        );
        Observable<String> getGiftPickUp(
                String Id,
                String GiftName,
                String Color,
                String Name,
                String Phone,
                String Address
        );
    }


    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getGiftInfo(
                String cardNo
        );
        public abstract void getGiftPickUp(
                String Id,
                String GiftName,
                String Color,
                String Name,
                String Phone,
                String Address
        );
    }


}
