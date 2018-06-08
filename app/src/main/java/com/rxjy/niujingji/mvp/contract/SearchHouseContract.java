package com.rxjy.niujingji.mvp.contract;

import com.rxjy.niujingji.commons.base.BaseModel;
import com.rxjy.niujingji.commons.base.BasePresenter;
import com.rxjy.niujingji.commons.base.BaseView;
import com.rxjy.niujingji.entity.BuildingListInfo;
import com.rxjy.niujingji.entity.HotWordListInfo;

import java.util.List;

import rx.Observable;

/**
 * Created by AAA on 2017/9/7.
 */

public interface SearchHouseContract {

    interface View extends BaseView {

        void responseHotWordData(List<HotWordListInfo.HotWordList> dataList);

        void responseHotWordDataError(String msg);

        void responseBuildingListData(List<BuildingListInfo.BuildingList> dataList);

        void responseBuildingListDataError(String msg);

        void responseMoreBuildingListData(List<BuildingListInfo.BuildingList> dataList);

        void responseMoreBuildingListDataError(String msg);

        void showDialog();

        void hideDialog();

    }

    interface Model extends BaseModel {

        Observable<String> getHotWordList(
                int cityID
        );

        Observable<String> searchBuildingList(
                int cityID,
                String keyWord,
                int pageIndex,
                int pageSize
        );

    }

    abstract class Presenter extends BasePresenter<View, Model> {

        public abstract void getHotWordList(
                int cityID
        );

        public abstract void searchBuildingList(
                int cityID,
                String keyWord,
                int pageIndex,
                int pageSize
        );

        public abstract void getMoreBuildingList(
                int cityID,
                String keyWord,
                int pageIndex,
                int pageSize
        );

    }

}
