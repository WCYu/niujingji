package com.rxjy.niujingji.mvp.presenter;

import android.util.Log;

import com.rxjy.niujingji.commons.utils.JSONUtils;
import com.rxjy.niujingji.entity.BuildingDetailInfo;
import com.rxjy.niujingji.entity.PoiInfo;
import com.rxjy.niujingji.entity.RoomListInfo;
import com.rxjy.niujingji.entity.SubInfo;
import com.rxjy.niujingji.mvp.contract.BuildingDetailContract;
import com.rxjy.niujingji.mvp.model.BuildingDetailModel;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by AAA on 2017/9/8.
 */

public class BuildingDetailPresenter extends BuildingDetailContract.Presenter {

    public static final String TAG = "BuildingDetailPresenter";

    public BuildingDetailPresenter(BuildingDetailContract.View mView) {
        this.mView = mView;
        this.mModel = new BuildingDetailModel();
    }

    @Override
    public void getBuildingInfo(int buildingID) {
        Subscription subscribe = mModel.getBuildingInfo(buildingID)
                .subscribe(new Subscriber<String>() {

                    @Override
                    public void onStart() {
                        mView.showDialog();
                    }

                    @Override
                    public void onCompleted() {
                        mView.hideDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "获取楼盘详情失败 = " + e.toString());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        BuildingDetailInfo info = JSONUtils.toObject(s, BuildingDetailInfo.class);
                        if (info.getStatusCode() == 0) {
                            BuildingDetailInfo.BuildingDetail data = info.getBody();
                            List<Integer> typeList = data.getNum();
                            List<BuildingDetailInfo.BuildingDetail.ImgList> imgList = data.getImgList();
                            mView.responseBuildingInfoData(data);
                            mView.responseBuildingTypeData(typeList);
                            mView.responseBuildingImageList(imgList);
                        } else {
                            mView.responseBuildingInfoDataError(info.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }

    @Override
    public void getRoomList(int buildingID, int pageSize, int pageIndex, int type) {
        Subscription subscribe = mModel.getRoomList(buildingID, pageSize, pageIndex, type)
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "获取房源列表失败 = " + e.toString());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        RoomListInfo info = JSONUtils.toObject(s, RoomListInfo.class);
                        if (info.getStatusCode() == 0) {
                            List<RoomListInfo.RoomList> dataList = info.getBody();
                            mView.responseRoomListData(dataList);
                        } else {
                            mView.responseRoomListDataError(info.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }

    @Override
    public void getMoreRoomList(int buildingID, int pageSize, int pageIndex, int type) {
        Subscription subscribe = mModel.getRoomList(buildingID, pageSize, pageIndex, type)
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "获取房源列表失败 = " + e.toString());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        RoomListInfo info = JSONUtils.toObject(s, RoomListInfo.class);
                        if (info.getStatusCode() == 0) {
                            List<RoomListInfo.RoomList> dataList = info.getBody();
                            mView.responseMoreRoomListData(dataList);
                        } else {
                            mView.responseRoomListDataError(info.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }

    @Override
    public void bookingRoom(int id, String name, String phone) {
        Subscription subscribe = mModel.bookingRoom(id, name, phone)
                .subscribe(new Subscriber<String>() {

                    @Override
                    public void onStart() {
                        mView.showDialog();
                    }

                    @Override
                    public void onCompleted() {
                        mView.hideDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "预约看房失败 = " + e.toString());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        SubInfo info = JSONUtils.toObject(s, SubInfo.class);
                        if (info.getStatusCode() == 0) {
                            mView.responseBookingRoom();
                        } else {
                            mView.responseBookingRoomError(info.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }

    @Override
    public void getPoiSubway(String location) {
        Subscription subscribe = mModel.getPoiSubway(location)
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "检索周边失败 = " + e.toString());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        PoiInfo info = JSONUtils.toObject(s, PoiInfo.class);
                        if (info.getStatus() == 0) {
                            List<PoiInfo.SiteInfo> subwayList = info.getResults();
                            mView.responsePoiSubway(subwayList);
                        }
                    }
                });
        addSubscribe(subscribe);
    }

    @Override
    public void getPoiRestaurant(String location) {
        Subscription subscribe = mModel.getPoiRestaurant(location)
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "检索周边失败 = " + e.toString());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        PoiInfo info = JSONUtils.toObject(s, PoiInfo.class);
                        if (info.getStatus() == 0) {
                            List<PoiInfo.SiteInfo> restaurantList = info.getResults();
                            mView.responsePoiRestaurant(restaurantList);
                        }
                    }
                });
        addSubscribe(subscribe);
    }

    @Override
    public void getPoiBank(String location) {
        Subscription subscribe = mModel.getPoiBank(location)
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "检索周边失败 = " + e.toString());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        PoiInfo info = JSONUtils.toObject(s, PoiInfo.class);
                        if (info.getStatus() == 0) {
                            List<PoiInfo.SiteInfo> bankList = info.getResults();
                            mView.responsePoiBank(bankList);
                        }
                    }
                });
        addSubscribe(subscribe);
    }

    @Override
    public void getPoiHotel(String location) {
        Subscription subscribe = mModel.getPoiHotel(location)
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "检索周边失败 = " + e.toString());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        PoiInfo info = JSONUtils.toObject(s, PoiInfo.class);
                        if (info.getStatus() == 0) {
                            List<PoiInfo.SiteInfo> hotelList = info.getResults();
                            mView.responsePoiHotel(hotelList);
                        }
                    }
                });
        addSubscribe(subscribe);
    }

}
