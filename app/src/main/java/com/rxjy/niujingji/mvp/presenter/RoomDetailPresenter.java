package com.rxjy.niujingji.mvp.presenter;

import android.util.Log;

import com.rxjy.niujingji.commons.utils.JSONUtils;
import com.rxjy.niujingji.entity.PoiInfo;
import com.rxjy.niujingji.entity.RoomDetailInfo;
import com.rxjy.niujingji.entity.SubInfo;
import com.rxjy.niujingji.mvp.contract.RoomDetailContract;
import com.rxjy.niujingji.mvp.model.RoomDetailModel;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by AAA on 2017/9/9.
 */

public class RoomDetailPresenter extends RoomDetailContract.Presenter {

    public static final String TAG = "RoomDetailPresenter";

    public RoomDetailPresenter(RoomDetailContract.View mView) {
        this.mView = mView;
        mModel = new RoomDetailModel();
    }

    @Override
    public void getRoomDetailInfo(final int roomID) {
        Subscription subscribe = mModel.getRoomDetailInfo(roomID)
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
                        Log.e(TAG, "获取房屋详情失败 = " + e.toString());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        RoomDetailInfo info = JSONUtils.toObject(s, RoomDetailInfo.class);
                        if (info.getStatusCode() == 0) {
                            RoomDetailInfo.RoomDetail data = info.getBody();
                            List<RoomDetailInfo.RoomDetail.ImgInfo> imgList = data.getImgList();
                            mView.responseRoomDetailInfoData(data);
                            mView.responseRoomDetailImageList(imgList);
                        } else {
                            mView.responseRoomDetailInfoDataError(info.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }

    @Override
    public void bookingRoom(final int id, String name, String phone) {
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
                        Log.e(TAG, "预约房源失败 = " + e.toString());
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
