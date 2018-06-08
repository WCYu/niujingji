package com.rxjy.niujingji.mvp.contract;



import com.rxjy.niujingji.commons.base.BaseModel;
import com.rxjy.niujingji.commons.base.BasePresenter;
import com.rxjy.niujingji.commons.base.BaseView;
import com.rxjy.niujingji.entity.IsReadStateInfo;

import rx.Observable;

/**
 * Created by Administrator on 2018/5/31.
 */

public interface WalletFragmentContract {

    interface View extends BaseView {

        void responseStateData(IsReadStateInfo.BodyBean data);


        void responseVersionDataError(String msg);

    }
    interface Model extends BaseModel {


        Observable<String> getState(
                String cardNo
        );

    }


    abstract class Presenter extends BasePresenter<View, Model> {


        public abstract void getState(
                String cardNo
        );

    }
}
