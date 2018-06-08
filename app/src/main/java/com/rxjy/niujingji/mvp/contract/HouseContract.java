package com.rxjy.niujingji.mvp.contract;

import com.rxjy.niujingji.commons.base.BaseModel;
import com.rxjy.niujingji.commons.base.BasePresenter;
import com.rxjy.niujingji.commons.base.BaseView;
import com.rxjy.niujingji.entity.AreaListInfo;
import com.rxjy.niujingji.entity.BuildingListInfo;
import com.rxjy.niujingji.entity.CityListInfo;
import com.rxjy.niujingji.entity.TradingListInfo;

import java.util.List;

import rx.Observable;

/**
 * Created by AAA on 2017/9/6.
 */

public interface HouseContract {

    interface View extends BaseView {

        void responseCityListData(List<CityListInfo.CityList> dataList);

        void responseCityListDataError(String msg);

        void responseAreaListData(List<AreaListInfo.AreaList> dataList);

        void responseAreaListDataError(String msg);

        void responseTradingAreaListData(List<TradingListInfo.TradingList> dataList);

        void responseTradingAreaListDataError(String msg);

        void responseBuildingListData(List<BuildingListInfo.BuildingList> dataList);

        void responseBuildingListDataError(String msg);

        void responseMoreBuildingListData(List<BuildingListInfo.BuildingList> dataList);

        void responseMoreBuildingListDataError(String msg);

        void showDialog();

        void hideDialog();

    }

    interface Model extends BaseModel {
        Observable<String> getCityList(

        );

        Observable<String> getAreaList(
                int cityID
        );

        Observable<String> getTradingAreaList(
                int areaID
        );

        Observable<String> getBuildingList(
                Integer cityID,
                Integer areaID,
                String tradingAreaName,
                Integer areaStart,
                Integer areaEnd,
                Integer priceStart,
                Integer priceEnd,
                String keyWord,
                Integer pageSize,
                Integer pageIndex,
                Integer sortBy
        );

    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getCityList(

        );

        public abstract void getAreaList(
                int cityID
        );

        public abstract void getTradingAreaList(
                int areaID
        );

        public abstract void getBuildingList(
                Integer cityID,
                Integer areaID,
                String tradingAreaName,
                Integer areaStart,
                Integer areaEnd,
                Integer priceStart,
                Integer priceEnd,
                String keyWord,
                Integer pageSize,
                Integer pageIndex,
                Integer sortBy
        );

        public abstract void getMoreBuildingList(
                Integer cityID,
                Integer areaID,
                String tradingAreaName,
                Integer areaStart,
                Integer areaEnd,
                Integer priceStart,
                Integer priceEnd,
                String keyWord,
                Integer pageSize,
                Integer pageIndex,
                Integer sortBy
        );

    }

}
