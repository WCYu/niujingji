package com.rxjy.niujingji.mvp.contract;

import com.rxjy.niujingji.commons.base.BaseModel;
import com.rxjy.niujingji.commons.base.BasePresenter;
import com.rxjy.niujingji.commons.base.BaseView;
import com.rxjy.niujingji.entity.PoiInfo;

import java.util.List;

import rx.Observable;

/**
 * Created by AAA on 2017/9/12.
 */

public interface PoiContract {

    interface View extends BaseView {

        void responsePoiSubway(List<PoiInfo.SiteInfo> subwayList);

        void responsePoiRestaurant(List<PoiInfo.SiteInfo> restaurantList);

        void responsePoiBank(List<PoiInfo.SiteInfo> bankList);

        void responsePoiHotel(List<PoiInfo.SiteInfo> hotelList);

    }

    interface Model extends BaseModel {

        Observable<String> getPoiSubway(
                String location
        );

        Observable<String> getPoiRestaurant(
                String location
        );

        Observable<String> getPoiBank(
                String location
        );

        Observable<String> getPoiHotel(
                String location
        );

    }

    abstract class Presenter extends BasePresenter<View, Model> {

        public abstract void getPoiSubway(
                String location
        );

        public abstract void getPoiRestaurant(
                String location
        );

        public abstract void getPoiBank(
                String location
        );

        public abstract void getPoiHotel(
                String location
        );

    }

}
