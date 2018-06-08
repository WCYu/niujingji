package com.rxjy.niujingji.mvp.contract;

import com.rxjy.niujingji.commons.base.BaseModel;
import com.rxjy.niujingji.commons.base.BasePresenter;
import com.rxjy.niujingji.commons.base.BaseView;
import com.rxjy.niujingji.entity.CounselorInfo;

import rx.Observable;

/**
 * Created by AAA on 2017/8/9.
 */

public interface CounselorContract {

    interface View extends BaseView {

        void responseCounselorData(CounselorInfo.Counselor data);

        void responseCounselorDataError(String msg);

        void showDialog();

        void hideDialog();

    }

    interface Model extends BaseModel {

        Observable<String> getCounselorInfo(
                String cardNo
        );

    }

    abstract class Presenter extends BasePresenter<View, Model> {

        public abstract void getCounselorInfo(
                String cardNo
        );

    }

}
