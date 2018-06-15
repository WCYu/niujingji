package com.rxjy.niujingji.mvp.presenter;

import android.util.Log;

import com.rxjy.niujingji.commons.utils.JSONUtils;
import com.rxjy.niujingji.entity.HomeBean;
import com.rxjy.niujingji.entity.NewsListInfo;
import com.rxjy.niujingji.mvp.contract.NewsListContract;
import com.rxjy.niujingji.mvp.model.NewsListModel;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by Administrator on 2017/6/15.
 */
public class NewsListPresenter extends NewsListContract.Presenter {

    public static final String TAG = "NewsListPresenter";

    private NewsListContract.View mView;

    public NewsListPresenter(NewsListContract.View mView) {
        this.mView = mView;
        mModel = new NewsListModel();
    }

    @Override
    public void getNewsList(String cardNo, int pageIndex, int pageSize) {
        Subscription subscribe = mModel.getNewsList(cardNo, pageIndex, pageSize)
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
                        Log.e(TAG, "获取新闻列表失败 = " + e.toString());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        NewsListInfo info = JSONUtils.toObject(s, NewsListInfo.class);
                        if (info.getStatusCode() == 0) {
                            NewsListInfo.NewsList data = info.getBody();
                            List<NewsListInfo.NewsList.BannerInfo> bannerList = data.getTopList();
                            List<NewsListInfo.NewsList.NewsInfo> newsList = data.getList();
                            mView.responseBannerData(bannerList);
                            mView.responseNewsListData(newsList);
                        } else {
                            mView.responseNewsListDataError(info.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }

    @Override
    public void getNewsListLoadMore(String cardNo, int pageIndex, int pageSize) {
        Subscription subscribe = mModel.getNewsListLoadMore(cardNo, pageIndex, pageSize)
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
                        Log.e(TAG, "获取新闻列表失败 = " + e.toString());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        NewsListInfo info = JSONUtils.toObject(s, NewsListInfo.class);
                        if (info.getStatusCode() == 0) {
                            NewsListInfo.NewsList data = info.getBody();
                            List<NewsListInfo.NewsList.BannerInfo> bannerList = data.getTopList();
                            List<NewsListInfo.NewsList.NewsInfo> newsList = data.getList();
                            mView.responseBannerData(bannerList);
                            mView.responseNewsListLoadMoreData(newsList);
                        } else {
                            mView.responseNewsListLoadMoreDataError(info.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }

    @Override
    public void getNewsList(String cardNo, int pageIndex, int pageSize,String token) {
        Subscription subscribe = mModel.getNewsList(cardNo, pageIndex, pageSize,token)
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
                        Log.e(TAG, "获取首页列表失败 list= " + e.toString());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e(TAG, "获取首页列表失败 list= " + s);
                        NewsListInfo info = JSONUtils.toObject(s, NewsListInfo.class);
                        HomeBean homeBean = JSONUtils.toObject(s, HomeBean.class);
                        if (info.getStatusCode() == 0) {
                            NewsListInfo.NewsList data = info.getBody();
                            List<NewsListInfo.NewsList.BannerInfo> bannerList = data.getTopList();
                            List<NewsListInfo.NewsList.NewsInfo> newsList = data.getList();
                            mView.responseBannerData(bannerList);
                            mView.responseNewsListDataHome(homeBean.getBody().getList());
                        } else {
                            mView.responseNewsListDataError(info.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }

    @Override
    public void getNewsListLoadMore(String cardNo, int pageIndex, int pageSize,String token) {
        Subscription subscribe = mModel.getNewsListLoadMore(cardNo, pageIndex, pageSize,token)
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
                        Log.e(TAG, "获取首页列表失败 img= " + e.toString());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        NewsListInfo info = JSONUtils.toObject(s, NewsListInfo.class);
                        if (info.getStatusCode() == 0) {
                            NewsListInfo.NewsList data = info.getBody();
                            List<NewsListInfo.NewsList.BannerInfo> bannerList = data.getTopList();
                            List<NewsListInfo.NewsList.NewsInfo> newsList = data.getList();
                            mView.responseBannerData(bannerList);
                            mView.responseNewsListLoadMoreData(newsList);
                        } else {
                            mView.responseNewsListLoadMoreDataError(info.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }

}
