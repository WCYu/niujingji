package com.rxjy.niujingji.mvp.contract;

import com.rxjy.niujingji.commons.base.BaseModel;
import com.rxjy.niujingji.commons.base.BasePresenter;
import com.rxjy.niujingji.commons.base.BaseView;
import com.rxjy.niujingji.entity.DownLineBean;

import rx.Observable;


/**
 * Created by Administrator on 2018/5/30.
 */

public interface DownLineContract {

    interface View extends BaseView {


        void lodeDate(DownLineBean downLineBean);

        void lodeDateErr(String s);

        void showDialog();

        void hideDialog();

    }

    interface Model extends BaseModel {

        Observable<String> getClientList(
                String card
        );

    }



    abstract class Presenter extends BasePresenter<View, Model> {

        public abstract void getDownLineList(
                String card
        );


    }


}
