package com.rxjy.niujingji.mvp.contract;

import com.rxjy.niujingji.commons.base.BaseModel;
import com.rxjy.niujingji.commons.base.BasePresenter;
import com.rxjy.niujingji.commons.base.BaseView;
import com.rxjy.niujingji.entity.BuildingDetailInfo;
import com.rxjy.niujingji.entity.PoiInfo;
import com.rxjy.niujingji.entity.RoomListInfo;

import java.util.List;

import rx.Observable;

/**
 * Created by AAA on 2017/9/8.
 */

public interface BuildingDetailContract {

    interface View extends BaseView {

        void responseBuildingInfoData(BuildingDetailInfo.BuildingDetail data);

        void responseBuildingTypeData(List<Integer> dataList);

        void responseBuildingImageList(List<BuildingDetailInfo.BuildingDetail.ImgList> dataList);

        void responseBuildingInfoDataError(String msg);

        void responseRoomListData(List<RoomListInfo.RoomList> dataList);

        void responseMoreRoomListData(List<RoomListInfo.RoomList> dataList);

        void responseRoomListDataError(String msg);

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

        Observable<String> getBuildingInfo(
                int buildingID
        );

        Observable<String> getRoomList(
                int buildingID,
                int pageSize,
                int pageIndex,
                int type
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

        public abstract void getBuildingInfo(
                int buildingID
        );

        public abstract void getRoomList(
                int buildingID,
                int pageSize,
                int pageIndex,
                int type
        );

        public abstract void getMoreRoomList(
                int buildingID,
                int pageSize,
                int pageIndex,
                int type
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
