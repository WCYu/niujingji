package com.rxjy.niujingji.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rxjy.niujingji.R;
import com.rxjy.niujingji.activity.NewsDetailActivity;
import com.rxjy.niujingji.adapter.HomeListAdapter;
import com.rxjy.niujingji.adapter.NewsListAdapter;
import com.rxjy.niujingji.commons.App;
import com.rxjy.niujingji.commons.Constants;
import com.rxjy.niujingji.commons.base.BaseFragment;
import com.rxjy.niujingji.commons.utils.AutoUtils;
import com.rxjy.niujingji.entity.HomeBean;
import com.rxjy.niujingji.entity.NewsListInfo;
import com.rxjy.niujingji.mvp.contract.NewsListContract;
import com.rxjy.niujingji.mvp.presenter.NewsListPresenter;
import com.rxjy.niujingji.widget.xlistview.XListView;
import com.stx.xhb.xbanner.XBanner;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewHomeFragment extends BaseFragment<NewsListPresenter> implements NewsListContract.View, AdapterView.OnItemClickListener, XListView.IXListViewListener  {


    public NewHomeFragment() {
        // Required empty public constructor
    }
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.xlv_find)
    XListView xlvFind;

    public static final String TITLE = "首页";

    private HomeListAdapter mAdapter;

    private List<HomeBean.BodyBean.ListBean> newsList;

    private List<NewsListInfo.NewsList.BannerInfo> bannerList;

    private int pageIndex = 1;

    private int pageSize = 10;

    private XBanner banner;

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_new_home;
    }

    @Override
    protected void FragmentInitData() {
        initTitle();
        initNewsData();
        initBannerData();
    }

    private void initTitle() {
        ivBack.setVisibility(View.INVISIBLE);
        tvTitle.setText(TITLE);
    }

    private void initNewsData() {

        newsList = new ArrayList<>();

        mAdapter = new HomeListAdapter(getActivity(), newsList);

        xlvFind.setAdapter(mAdapter);

        xlvFind.setPullLoadEnable(false);

        xlvFind.setXListViewListener(this);

        xlvFind.setOnItemClickListener(this);

        //获取新闻列表接口https://api.niujingji.cn:8183/AppAgent/APP_GetAppNews?cardNo=%2001012480&page=%201&rows=%2010&token=%2010F0CC6F16010F62A8F11170D939E84F
//        mPresenter.getNewsList(App.cardNo, pageIndex, pageSize);
        mPresenter.getNewsList(App.cardNo, pageIndex, pageSize,App.token);

    }

    private void initBannerData() {

        bannerList = new ArrayList<>();

    }

    @Override
    protected NewsListPresenter onCreatePresenter() {
        return new NewsListPresenter(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("首页");
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("首页");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void responseNewsListData(List<NewsListInfo.NewsList.NewsInfo> dataList) {
        onLoad();
//        newsList.clear();
//        newsList.addAll(dataList);
//        mAdapter.notifyDataSetChanged();
//        isShowLoad(dataList.size());
    }

    @Override
    public void responseNewsListDataHome(List<HomeBean.BodyBean.ListBean> dataList) {
        onLoad();
        newsList.clear();
        newsList.addAll(dataList);
        mAdapter.notifyDataSetChanged();
        isShowLoad(dataList.size());
    }

    @Override
    public void responseNewsListDataError(String msg) {
        onLoad();
        showToast(msg);
    }

    @Override
    public void responseNewsListLoadMoreData(List<NewsListInfo.NewsList.NewsInfo> dataList) {
//        onLoad();
//        newsList.addAll(dataList);
//        mAdapter.notifyDataSetChanged();
//        isShowLoad(dataList.size());
    }

    @Override
    public void responseNewsListLoadMoreDataError(String msg) {
        onLoad();
        showToast(msg);
    }

    @Override
    public void responseBannerData(List<NewsListInfo.NewsList.BannerInfo> dataList) {
        if (bannerList.size() == 0) {
            View view = View.inflate(getActivity(), R.layout.banner, null);

            AutoUtils.auto(view);

            banner = (XBanner) view.findViewById(R.id.banner);

            bannerList.addAll(dataList);
            final List<String> imgUrlList = new ArrayList<>();
            List<String> textList = new ArrayList<>();
            for (NewsListInfo.NewsList.BannerInfo info : bannerList) {
                imgUrlList.add(info.getCover());
                textList.add(info.getName());
            }
            //添加广告数据
            banner.setData(imgUrlList, textList);//第二个参数为提示文字资源集合
            banner.setmAdapter(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, Object model, View view, int position) {
                    if (imgUrlList != null && getActivity() != null)
                        Glide.with(getActivity()).load(imgUrlList.get(position)).into((ImageView) view);
                }
            });
            banner.setOnItemClickListener(new XBanner.OnItemClickListener() {
                @Override
                public void onItemClick(XBanner banner, int position) {
                    NewsListInfo.NewsList.BannerInfo info = bannerList.get(position);
                    Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
                    intent.putExtra(Constants.ACTION_TO_NEWS_DETAIL_NEWS_ID, info.getId());
                    startActivity(intent);
                }
            });

            xlvFind.addHeaderView(view);
        }
    }

    @Override
    public void showDialog() {
        showLoading();
    }

    @Override
    public void hideDialog() {
        dismissLoading();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        HomeBean.BodyBean.ListBean info = newsList.get(position - 2);
        Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
        intent.putExtra(Constants.ACTION_TO_NEWS_DETAIL_NEWS_ID, info.getId());
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        pageIndex = 1;
        //获取新闻列表接口
        mPresenter.getNewsList(App.cardNo, pageIndex, pageSize,"2010F0CC6F16010F62A8F11170D939E84F");
//        mPresenter.getNewsList("00000010", pageIndex, pageSize);
    }

    @Override
    public void onLoadMore() {
        pageIndex++;
        //获取新闻列表接口
        mPresenter.getNewsListLoadMore(App.cardNo, pageIndex, pageSize,"2010F0CC6F16010F62A8F11170D939E84F");
//        mPresenter.getNewsListLoadMore("00000010", pageIndex, pageSize);
    }

    //停止刷新
    private void onLoad() {
        xlvFind.stopRefresh();
        xlvFind.stopLoadMore();
        xlvFind.setRefreshTime("刚刚");
    }

    //是否显示加载更多
    private void isShowLoad(int size) {
        if (size < pageSize) {
            xlvFind.setPullLoadEnable(false);
        } else {
            xlvFind.setPullLoadEnable(true);
        }
    }

}
