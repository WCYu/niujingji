package com.rxjy.niujingji.mvp.model;

import com.rxjy.niujingji.api.ApiEngine;
import com.rxjy.niujingji.mvp.contract.NewsListContract;
import com.rxjy.niujingji.rx.RxSchedulers;

import rx.Observable;

/**
 * Created by Administrator on 2017/6/15.
 */
public class NewsListModel implements NewsListContract.Model
{

    @Override
    public Observable<String> getNewsList(String cardNo, int pageIndex, int pageSize)
    {
        return ApiEngine.getInstance().getRxApiService()
                .getNewsList(cardNo, pageIndex, pageSize)
                .compose(RxSchedulers.<String>switchThread());
    }

    @Override
    public Observable<String> getNewsListLoadMore(String cardNo, int pageIndex, int pageSize)
    {
        return ApiEngine.getInstance().getRxApiService()
                .getNewsList(cardNo, pageIndex, pageSize)
                .compose(RxSchedulers.<String>switchThread());
    }


    public Observable<String> getNewsList(String cardNo, int pageIndex, int pageSize,String token)
    {
        return ApiEngine.getInstance().getSwApiService()
                .getHomeList(cardNo, pageIndex, pageSize,token)
                .compose(RxSchedulers.<String>switchThread());
    }


    public Observable<String> getNewsListLoadMore(String cardNo, int pageIndex, int pageSize,String token)
    {
        return ApiEngine.getInstance().getSwApiService()
                .getHomeList(cardNo, pageIndex, pageSize,token)
                .compose(RxSchedulers.<String>switchThread());
    }
}
