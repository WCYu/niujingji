package com.rxjy.niujingji.mvp.contract;

import com.rxjy.niujingji.commons.base.BaseModel;
import com.rxjy.niujingji.commons.base.BasePresenter;
import com.rxjy.niujingji.commons.base.BaseView;
import com.rxjy.niujingji.entity.IsReadStateInfo;
import com.rxjy.niujingji.entity.VersionInfo;

import rx.Observable;

/**
 * Created by AAA on 2017/8/9.
 */

public interface NjjContract {

    interface View extends BaseView {

        void responseStateData(IsReadStateInfo.BodyBean data);

        void responseVersionData(VersionInfo.Version data);

        void responseVersionDataError(String msg);

    }

    interface Model extends BaseModel {

        Observable<String> getVersionInfo(

        );

        Observable<String> getState(
                String cardNo
        );

    }

    abstract class Presenter extends BasePresenter<View, Model> {

        public abstract void getVersionInfo(

        );

        public abstract void getState(
                String cardNo
        );

    }

}
