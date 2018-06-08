package com.rxjy.niujingji.mvp.contract;

import com.rxjy.niujingji.commons.base.BaseModel;
import com.rxjy.niujingji.commons.base.BasePresenter;
import com.rxjy.niujingji.commons.base.BaseView;
import com.rxjy.niujingji.entity.PoiInfo;
import com.rxjy.niujingji.entity.RoomDetailInfo;

import java.util.List;

import rx.Observable;

/**
 * Created by AAA on 2017/9/9.
 */

public interface RoomDetailContract {

    interface View extends BaseView {

        void responseRoomDetailInfoData(RoomDetailInfo.RoomDetail data);

        void responseRoomDetailImageList(List<RoomDetailInfo.RoomDetail.ImgInfo> dataList);

        void responseRoomDetailInfoDataError(String msg);

        void responseBookingRoom();

        void responseBookingRoomError(String msg);

        void responsePoiSubway(List<PoiInfo.SiteInfo> subwayList);

        void responsePoiRestaurant(List<PoiInfo.SiteInfo> restaurantList);

        void responsePoiBank(List<PoiInfo.SiteInfo> bankList);

        void responsePoiHotel(List<PoiInfo.SiteInfo> hotelList);

        void showDialog();

        void hideDialog();

    }

    interface Model extends BaseModel {

        Observable<String> getRoomDetailInfo(
                int roomID
        );

        Observable<String> bookingRoom(
                int id,
                String name,
                String phone
        );

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

        public abstract void getRoomDetailInfo(
                int roomID
        );

        public abstract void bookingRoom(
                int id,
                String name,
                String phone
        );

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
