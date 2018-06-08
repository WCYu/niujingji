package com.rxjy.niujingji.mvp.presenter;

import android.util.Log;

import com.rxjy.niujingji.commons.utils.JSONUtils;
import com.rxjy.niujingji.entity.BuildingListInfo;
import com.rxjy.niujingji.entity.HotWordListInfo;
import com.rxjy.niujingji.mvp.contract.SearchHouseContract;
import com.rxjy.niujingji.mvp.model.SearchHouseModel;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by AAA on 2017/9/7.
 */

public class SearchHousePresenter extends SearchHouseContract.Presenter {

    public static final String TAG = "SearchHousePresenter";

    public SearchHousePresenter(SearchHouseContract.View mView) {
        this.mView = mView;
        mModel = new SearchHouseModel();
    }

    @Override
    public void getHotWordList(int cityID) {
        Subscription subscribe = mModel.getHotWordList(cityID)
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
                        Log.e(TAG, "获取热词失败 = " + e.toString());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        HotWordListInfo info = JSONUtils.toObject(s, HotWordListInfo.class);
                        if (info.getStatusCode() == 0) {
                            List<HotWordListInfo.HotWordList> dataList = info.getBody();
                            mView.responseHotWordData(dataList);
                        } else {
                            mView.responseHotWordDataError(info.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }

    @Override
    public void searchBuildingList(int cityID, String keyWord, int pageIndex, int pageSize) {
        Subscription subscribe = mModel.searchBuildingList(cityID, keyWord, pageIndex, pageSize)
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
                            mView.responseBuildingListDataError(info.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }

    @Override
    public void getMoreBuildingList(int cityID, String keyWord, int pageIndex, int pageSize) {

        Subscription subscribe = mModel.searchBuildingList(cityID, keyWord, pageIndex, pageSize)
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
                            mView.responseMoreBuildingListData(dataList);
                        } else {
                            mView.responseMoreBuildingListDataError(info.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }
}
