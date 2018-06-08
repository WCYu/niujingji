package com.rxjy.niujingji.mvp.presenter;

import android.util.Log;

import com.rxjy.niujingji.commons.utils.JSONUtils;
import com.rxjy.niujingji.entity.AreaListInfo;
import com.rxjy.niujingji.entity.BuildingListInfo;
import com.rxjy.niujingji.entity.CityListInfo;
import com.rxjy.niujingji.entity.TradingListInfo;
import com.rxjy.niujingji.mvp.contract.HouseContract;
import com.rxjy.niujingji.mvp.model.HouseModel;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by AAA on 2017/9/6.
 */

public class HousePresenter extends HouseContract.Presenter {

    public static final String TAG = "HousePresenter";

    public HousePresenter(HouseContract.View mView) {
        this.mView = mView;
        mModel = new HouseModel();
    }

    @Override
    public void getCityList() {
        Subscription subscribe = mModel.getCityList()
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "获取城市列表失败  = " + e.toString());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        CityListInfo info = JSONUtils.toObject(s, CityListInfo.class);
                        if (info.getStatusCode() == 0) {
                            List<CityListInfo.CityList> dataList = info.getBody();
                            mView.responseCityListData(dataList);
                        } else {
                            mView.responseCityListDataError(info.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }

    @Override
    public void getAreaList(int cityID) {
        Subscription subscribe = mModel.getAreaList(cityID)
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "获取区域列表失败 = " + e.toString());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        AreaListInfo info = JSONUtils.toObject(s, AreaListInfo.class);
                        if (info.getStatusCode() == 0) {
                            List<AreaListInfo.AreaList> dataList = info.getBody();
                            mView.responseAreaListData(dataList);
                        } else {
                            mView.responseAreaListDataError(info.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }

    @Override
    public void getTradingAreaList(int areaID) {
        Subscription subscribe = mModel.getTradingAreaList(areaID)
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "获取商圈列表失败 = " + e.toString());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        TradingListInfo info = JSONUtils.toObject(s,TradingListInfo.class);
                        if (info.getStatusCode() == 0){
                            List<TradingListInfo.TradingList> dataList = info.getBody();
                            mView.responseTradingAreaListData(dataList);
                        }else {
                            mView.responseTradingAreaListDataError(info.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }

    @Override
    public void getBuildingList(Integer cityID, Integer areaID, String tradingAreaName, Integer areaStart, Integer areaEnd, Integer priceStart, Integer priceEnd, String keyWord, Integer pageSize, Integer pageIndex, Integer sortBy) {
        Subscription subscribe = mModel.getBuildingList(cityID, areaID, tradingAreaName, areaStart, areaEnd, priceStart, priceEnd, keyWord, pageSize, pageIndex, sortBy)
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
                        Log.e(TAG, "获取楼盘列表失败 = " + e.toString());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        BuildingListInfo info = JSONUtils.toObject(s, BuildingListInfo.class);
                        if (info.getStatusCode() == 0) {
                            List<BuildingListInfo.BuildingList> dataList = info.getBody();
                            mView.responseBuildingListData(dataList);
                        } else {
                            mView.responseAreaListDataError(info.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }

    @Override
    public void getMoreBuildingList(Integer cityID, Integer areaID, String tradingAreaName, Integer areaStart, Integer areaEnd, Integer priceStart, Integer priceEnd, String keyWord, Integer pageSize, Integer pageIndex, Integer sortBy) {
        Subscription subscribe = mModel.getBuildingList(cityID, areaID, tradingAreaName, areaStart, areaEnd, priceStart, priceEnd, keyWord, pageSize, pageIndex, sortBy)
                .subscribe(new Subscriber<String>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "获取楼盘列表失败 = " + e.toString());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        BuildingListInfo info = JSONUtils.toObject(s, BuildingListInfo.class);
                        if (info.getStatusCode() == 0) {
                            List<BuildingListInfo.BuildingList> dataList = info.getBody();
                            mView.responseMoreBuildingListData(dataList);
                        } else {
                            mView.responseMoreBuildingListDataError(info.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }
}
