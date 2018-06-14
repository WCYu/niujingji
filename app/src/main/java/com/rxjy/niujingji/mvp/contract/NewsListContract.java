package com.rxjy.niujingji.mvp.contract;


import com.rxjy.niujingji.commons.base.BaseModel;
import com.rxjy.niujingji.commons.base.BasePresenter;
import com.rxjy.niujingji.commons.base.BaseView;
import com.rxjy.niujingji.entity.HomeBean;
import com.rxjy.niujingji.entity.NewsListInfo;

import java.util.List;

import rx.Observable;

/**
 * Created by Lei on 2017/6/12.
 */
public interface NewsListContract {

    interface View extends BaseView {

        void responseNewsListData(List<NewsListInfo.NewsList.NewsInfo> dataList);
        void responseNewsListDataHome(List<HomeBean.BodyBean.ListBean> dataList);
        void responseNewsListDataError(String msg);

        void responseNewsListLoadMoreData(List<NewsListInfo.NewsList.NewsInfo> dataList);

        void responseNewsListLoadMoreDataError(String msg);

        void responseBannerData(List<NewsListInfo.NewsList.BannerInfo> dataList);

        void showDialog();

        void hideDialog();

    }

    interface Model extends BaseModel {

        Observable<String> getNewsList(
                String cardNo,
                int pageIndex,
                int pageSize
        );

        Observable<String> getNewsListLoadMore(
                String cardNo,
                int pageIndex,
                int pageSize
        );

        Observable<String> getNewsList(
                String cardNo,
                int pageIndex,
                int pageSize,
                String token
        );

        Observable<String> getNewsListLoadMore(
                String cardNo,
                int pageIndex,
                int pageSize,
                String token
        );

    }

    abstract class Presenter extends BasePresenter<View, Model> {

        public abstract void getNewsList(
                String cardNo,
                int pageIndex,
                int pageSize
        );

        public abstract void getNewsList(
                String cardNo,
                int pageIndex,
                int pageSize,
                String token
        );

        public abstract void getNewsListLoadMore(
                String cardNo,
                int pageIndex,
                int pageSize
        );

        public abstract void getNewsListLoadMore(
                String cardNo,
                int pageIndex,
                int pageSize,
                String token
        );
    }

}
