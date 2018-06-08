package com.rxjy.niujingji.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rxjy.niujingji.R;
import com.rxjy.niujingji.adapter.BuildingAdapter;
import com.rxjy.niujingji.adapter.HotWordAdapter;
import com.rxjy.niujingji.commons.Constants;
import com.rxjy.niujingji.commons.base.BaseActivity;
import com.rxjy.niujingji.entity.BuildingListInfo;
import com.rxjy.niujingji.entity.HotWordListInfo;
import com.rxjy.niujingji.mvp.contract.SearchHouseContract;
import com.rxjy.niujingji.mvp.presenter.SearchHousePresenter;
import com.rxjy.niujingji.widget.xlistview.XListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchHouseActivity extends BaseActivity<SearchHousePresenter> implements SearchHouseContract.View, XListView.IXListViewListener, AdapterView.OnItemClickListener {

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.et_search)
    EditText etSearch;
    @Bind(R.id.tv_cancel)
    TextView tvCancel;
    @Bind(R.id.gv_hot_word)
    GridView gvHotWord;
    @Bind(R.id.lin_hot_word)
    LinearLayout linHotWord;
    @Bind(R.id.xlv_hot_word)
    XListView xlvHotWord;

    private List<HotWordListInfo.HotWordList> hotList;

    private HotWordAdapter hotAdapter;

    private List<BuildingListInfo.BuildingList> buildList;

    private BuildingAdapter buildAdapter;

    private Integer cityID;

    private Integer pageIndex = 1;

    private Integer pageSize = 10;

    private String keyWord;

    @Override
    public int getLayout() {
        return R.layout.activity_search_house;
    }

    @Override
    public void initData() {
        initIntent();
        initTitle();
        initHotKeyWord();
        initBuild();
    }

    private void initIntent() {
        cityID = getIntent().getIntExtra(Constants.ACTION_TO_SEARCH_HOUSE_CITY_ID, 1);
    }

    private void initTitle() {
        etSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    ivBack.setVisibility(View.VISIBLE);
                    tvCancel.setVisibility(View.INVISIBLE);
                } else {
                    ivBack.setVisibility(View.GONE);
                    tvCancel.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    private void initHotKeyWord() {

        hotList = new ArrayList<>();

        hotAdapter = new HotWordAdapter(this, hotList);

        gvHotWord.setAdapter(hotAdapter);

        gvHotWord.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                etSearch.setText(hotList.get(position).getBusinessName());
                keyWord = hotList.get(position).getBusinessName();
                mPresenter.searchBuildingList(cityID, keyWord, pageIndex, pageSize);
            }
        });

        //获取热词列表
        mPresenter.getHotWordList(cityID);

    }

    private void initBuild() {

        buildList = new ArrayList<>();

        buildAdapter = new BuildingAdapter(this, buildList);

        xlvHotWord.setPullLoadEnable(false);

        xlvHotWord.setAdapter(buildAdapter);

        xlvHotWord.setXListViewListener(this);

        xlvHotWord.setOnItemClickListener(this);

    }

    //是否显示加载更多
    private void isShowLoad(int size) {
        if (size < pageSize) {
            xlvHotWord.setPullLoadEnable(false);
        } else {
            xlvHotWord.setPullLoadEnable(true);
        }
    }

    //停止刷新
    private void onLoad() {
        xlvHotWord.stopRefresh();
        xlvHotWord.stopLoadMore();
        xlvHotWord.setRefreshTime("刚刚");
    }

    @Override
    protected SearchHousePresenter onCreatePresenter() {
        return new SearchHousePresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.tv_cancel, R.id.iv_key_word_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_cancel:
                finish();
                break;
            case R.id.iv_key_word_search:
                pageIndex = 1;
                keyWord = etSearch.getText().toString().trim();
                if (keyWord.equals("")) {
                    showToast("请输入搜索内容");
                    return;
                }
                mPresenter.searchBuildingList(cityID, keyWord, pageIndex, pageSize);
                break;
        }
    }

    @Override
    public void responseHotWordData(List<HotWordListInfo.HotWordList> dataList) {
        hotList.clear();
        hotList.addAll(dataList);
        hotAdapter.notifyDataSetChanged();
    }

    @Override
    public void responseHotWordDataError(String msg) {
        showToast(msg);
    }

    @Override
    public void responseBuildingListData(List<BuildingListInfo.BuildingList> dataList) {
        onLoad();
        buildList.clear();
        buildList.addAll(dataList);
        buildAdapter.notifyDataSetChanged();
        linHotWord.setVisibility(View.GONE);
        isShowLoad(dataList.size());
    }

    @Override
    public void responseBuildingListDataError(String msg) {
        onLoad();
        showToast(msg);
    }

    @Override
    public void responseMoreBuildingListData(List<BuildingListInfo.BuildingList> dataList) {
        onLoad();
        buildList.addAll(dataList);
        buildAdapter.notifyDataSetChanged();
        isShowLoad(dataList.size());
    }

    @Override
    public void responseMoreBuildingListDataError(String msg) {
        onLoad();
        showToast(msg);
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
    public void onRefresh() {
        pageIndex = 1;
        mPresenter.searchBuildingList(cityID, keyWord, pageIndex, pageSize);
    }

    @Override
    public void onLoadMore() {
        pageIndex++;
        mPresenter.getMoreBuildingList(cityID, keyWord, pageIndex, pageSize);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        BuildingListInfo.BuildingList info = buildList.get(position - 1);
        Intent intent = new Intent(this, BuildingDetailActivity.class);
        intent.putExtra(Constants.ACTION_TO_BUILDING_DETAIL_BUILDING_ID, info.getID());
        startActivity(intent);
    }
}
