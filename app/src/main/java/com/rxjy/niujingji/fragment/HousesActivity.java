package com.rxjy.niujingji.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.rxjy.niujingji.R;
import com.rxjy.niujingji.activity.BuildingDetailActivity;
import com.rxjy.niujingji.activity.SearchHouseActivity;
import com.rxjy.niujingji.adapter.BuildingAdapter;
import com.rxjy.niujingji.commons.Constants;
import com.rxjy.niujingji.commons.base.BaseActivity;
import com.rxjy.niujingji.commons.utils.AutoUtils;
import com.rxjy.niujingji.entity.AcreageInfo;
import com.rxjy.niujingji.entity.AreaListInfo;
import com.rxjy.niujingji.entity.BuildingListInfo;
import com.rxjy.niujingji.entity.CityListInfo;
import com.rxjy.niujingji.entity.PriceInfo;
import com.rxjy.niujingji.entity.TradingListInfo;
import com.rxjy.niujingji.mvp.contract.HouseContract;
import com.rxjy.niujingji.mvp.presenter.HousePresenter;
import com.rxjy.niujingji.pop.AcreagePop;
import com.rxjy.niujingji.pop.AreaPop;
import com.rxjy.niujingji.pop.CityPop;
import com.rxjy.niujingji.pop.PricePop;
import com.rxjy.niujingji.widget.xlistview.XListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by AAA on 2017/9/5.
 */

public class HousesActivity extends BaseActivity<HousePresenter> implements HouseContract.View, XListView.IXListViewListener, AdapterView.OnItemClickListener {

    @Bind(R.id.tv_location)
    TextView tvLocation;
    @Bind(R.id.lin_location)
    ImageView linLocation;
    @Bind(R.id.lin_search)
    LinearLayout linSearch;
    @Bind(R.id.cb_houses_area)
    CheckBox cbArea;
    @Bind(R.id.cb_houses_acreage)
    CheckBox cbAcreage;
    @Bind(R.id.cb_houses_price)
    CheckBox cbPrice;
    @Bind(R.id.lin_houses)
    LinearLayout linHouses;
    @Bind(R.id.iv_houses_sort)
    ImageView ivSort;
    @Bind(R.id.lv_houses)
    XListView lvHouses;
    @Bind(R.id.iv_back)
    ImageView ivBack;

    private CityPop cityPop;

    private AreaPop areaPop;

    private AcreagePop acreagePop;

    private PricePop pricePop;

    private List<BuildingListInfo.BuildingList> buildList;

    private BuildingAdapter mAdapter;

    private Integer pageSize = 10;
    private Integer pageIndex = 1;
    private Integer sortBy = 1;

    private Integer cityID;
    private Integer areaID;
    private String tradingName;
    private Integer areaStart;
    private Integer areaEnd;
    private Integer priceStart;
    private Integer priceEnd;

    private String areaName;


    private void initCityPop() {

        cityPop = new CityPop(this, R.layout.pop_city_layout, AutoUtils.getDisplayWidthValue(144), AutoUtils.getDisplayHeightValue(420));

        cityPop.setOnCityPopClickListener(new CityPop.OnCityPopClickListener() {
            @Override
            public void selectorCityData(CityListInfo.CityList data) {
                cityID = data.getId();
                //清空商圈数据
                areaPop.clearTradingList();
                //初始化商圈数据
                areaID = null;
                areaName = null;
                tradingName = null;
                //初始化页面索引
                pageIndex = 1;
                cbArea.setText("区域");
                tvLocation.setText(data.getName());
                //获取楼盘列表
                mPresenter.getBuildingList(cityID, areaID, tradingName, areaStart, areaEnd, priceStart, priceEnd, "", pageSize, pageIndex, sortBy);
                //获取区域数据
                mPresenter.getAreaList(cityID);
            }
        });

        //获取城市列表
        mPresenter.getCityList();
    }

    private void initAreaPop() {

        areaPop = new AreaPop(this, R.layout.pop_area_layout, AutoUtils.getDisplayWidthValue(720), AutoUtils.getDisplayHeightValue(480));

        areaPop.setOnAreaPopClickListener(new AreaPop.OnAreaPopClickListener() {
            @Override
            public void selectorAreaData(AreaListInfo.AreaList data) {
                areaID = data.getId();
                areaName = data.getName();
                mPresenter.getTradingAreaList(areaID);
            }

            @Override
            public void selectorAreaUnlimitedData() {
                areaID = null;
                areaName = null;
                cbArea.setText("区域");
                mPresenter.getBuildingList(cityID, areaID, tradingName, areaStart, areaEnd, priceStart, priceEnd, "", pageSize, pageIndex, sortBy);
            }

            @Override
            public void selectorTradingData(TradingListInfo.TradingList data) {
                tradingName = data.getName();
                cbArea.setText(areaName + "-" + tradingName);
                mPresenter.getBuildingList(cityID, areaID, tradingName, areaStart, areaEnd, priceStart, priceEnd, "", pageSize, pageIndex, sortBy);
            }

            @Override
            public void selectorTradingUnlimitedData() {
                tradingName = null;
                cbArea.setText(areaName);
                mPresenter.getBuildingList(cityID, areaID, tradingName, areaStart, areaEnd, priceStart, priceEnd, "", pageSize, pageIndex, sortBy);
            }
        });

        areaPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                radioCheckBox(null);
            }
        });

    }

    private void initAcreagePop() {

        List<AcreageInfo> acreageList = new ArrayList<>();

        acreageList.add(new AcreageInfo("不限", null, null));
        acreageList.add(new AcreageInfo("0-100㎡", 0, 100));
        acreageList.add(new AcreageInfo("100-200㎡", 100, 200));
        acreageList.add(new AcreageInfo("200-300㎡", 200, 300));
        acreageList.add(new AcreageInfo("300-400㎡", 300, 400));
        acreageList.add(new AcreageInfo("400-500㎡", 400, 500));
        acreageList.add(new AcreageInfo("500-600㎡", 500, 600));
        acreageList.add(new AcreageInfo("600-700㎡", 600, 700));
        acreageList.add(new AcreageInfo("700-800㎡", 700, 800));
        acreageList.add(new AcreageInfo("800-900㎡", 800, 900));
        acreageList.add(new AcreageInfo("900-1000㎡", 900, 1000));
        acreageList.add(new AcreageInfo("1000㎡以上", 1000, null));

        acreagePop = new AcreagePop(this, R.layout.pop_acreage_layout, AutoUtils.getDisplayWidthValue(720), AutoUtils.getDisplayHeightValue(480));

        acreagePop.setOnAcreagePopClickListener(new AcreagePop.OnAcreagePopClickListener() {
            @Override
            public void customAcreageData(AcreageInfo data) {
                areaStart = data.getMin();
                areaEnd = data.getMax();
                cbAcreage.setText(data.getValue());
                mPresenter.getBuildingList(cityID, areaID, tradingName, areaStart, areaEnd, priceStart, priceEnd, "", pageSize, pageIndex, sortBy);
            }

            @Override
            public void selectorAcreageData(AcreageInfo data) {
                areaStart = data.getMin();
                areaEnd = data.getMax();
                if (data.getValue().equals("不限")) {
                    cbAcreage.setText("面积");
                } else {
                    cbAcreage.setText(data.getValue());
                }
                mPresenter.getBuildingList(cityID, areaID, tradingName, areaStart, areaEnd, priceStart, priceEnd, "", pageSize, pageIndex, sortBy);
            }
        });

        acreagePop.setAcreageList(acreageList);

        acreagePop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                radioCheckBox(null);
            }
        });

    }

    private void initPricePop() {

        List<PriceInfo> priceList = new ArrayList<>();

        priceList.add(new PriceInfo("不限", null, null));
        priceList.add(new PriceInfo("¥0-3", 0, 3));
        priceList.add(new PriceInfo("¥3-4", 3, 4));
        priceList.add(new PriceInfo("¥4-5", 4, 5));
        priceList.add(new PriceInfo("¥5-7", 5, 7));
        priceList.add(new PriceInfo("¥7-9", 7, 9));
        priceList.add(new PriceInfo("¥9-12", 9, 12));
        priceList.add(new PriceInfo("¥12㎡以上", 12, null));

        pricePop = new PricePop(this, R.layout.pop_price_layout, AutoUtils.getDisplayWidthValue(720), AutoUtils.getDisplayHeightValue(480));

        pricePop.setOnPricePopClickListener(new PricePop.OnPricePopClickListener() {
            @Override
            public void customPriceData(PriceInfo data) {
                priceStart = data.getMin();
                priceEnd = data.getMax();
                cbPrice.setText(data.getValue());
                mPresenter.getBuildingList(cityID, areaID, tradingName, areaStart, areaEnd, priceStart, priceEnd, "", pageSize, pageIndex, sortBy);
            }

            @Override
            public void selectorPriceData(PriceInfo data) {
                priceStart = data.getMin();
                priceEnd = data.getMax();
                if (data.getValue().equals("不限")) {
                    cbPrice.setText("价格");
                } else {
                    cbPrice.setText(data.getValue());
                }
                mPresenter.getBuildingList(cityID, areaID, tradingName, areaStart, areaEnd, priceStart, priceEnd, "", pageSize, pageIndex, sortBy);
            }
        });

        pricePop.setAcreageList(priceList);

        pricePop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                radioCheckBox(null);
            }
        });

    }

    private void initBuilding() {

        buildList = new ArrayList<>();

        mAdapter = new BuildingAdapter(this, buildList);

        lvHouses.setAdapter(mAdapter);

        lvHouses.setXListViewListener(this);

        lvHouses.setOnItemClickListener(this);

    }

    //将CheckBox模拟单选效果
    private void radioCheckBox(CheckBox cb) {
        if (cb == null) {
            for (int i = 0; i < linHouses.getChildCount(); i++) {
                if (linHouses.getChildAt(i) instanceof CheckBox)
                    ((CheckBox) linHouses.getChildAt(i)).setChecked(false);
            }
            return;
        }
        if (cb.isChecked()) {
            for (int i = 0; i < linHouses.getChildCount(); i++) {
                if (linHouses.getChildAt(i) instanceof CheckBox)
                    ((CheckBox) linHouses.getChildAt(i)).setChecked(false);
            }
            cb.setChecked(true);
        }
    }

    //停止刷新
    private void onLoad() {
        lvHouses.stopRefresh();
        lvHouses.stopLoadMore();
        lvHouses.setRefreshTime("刚刚");
    }

    //是否显示加载更多
    private void isShowLoad(int size) {
        if (size < pageSize) {
            lvHouses.setPullLoadEnable(false);
        } else {
            lvHouses.setPullLoadEnable(true);
        }
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_houses;
    }

    @Override
    public void initData() {
        initCityPop();
        initAreaPop();
        initAcreagePop();
        initPricePop();
        initBuilding();
    }

    @Override
    protected HousePresenter onCreatePresenter() {
        return new HousePresenter(this);
    }


    @OnClick({R.id.lin_location, R.id.lin_search, R.id.iv_houses_sort, R.id.cb_houses_area, R.id.cb_houses_acreage, R.id.cb_houses_price})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_location:
                //设置默认获取焦点
                cityPop.setFocusable(true);
                //以某个控件的x和y的偏移量位置开始显示窗口
                cityPop.showAsDropDown(linLocation, 0, 0);
                //如果窗口存在，则更新
                cityPop.update();
                break;
            case R.id.lin_search:
                Intent intent = new Intent(this, SearchHouseActivity.class);
                intent.putExtra(Constants.ACTION_TO_SEARCH_HOUSE_CITY_ID, cityID);
                startActivity(intent);
                break;
            case R.id.iv_houses_sort:
                if (sortBy == 1) {
                    sortBy = 3;
                } else if (sortBy == 3) {
                    sortBy = 4;
                } else if (sortBy == 4) {
                    sortBy = 1;
                }
                switch (sortBy) {
                    case 1:
                        ivSort.setImageResource(R.mipmap.sort_normal);
                        showToast("默认排序");
                        break;
                    case 3:
                        ivSort.setImageResource(R.mipmap.sort_up);
                        showToast("价格正序");
                        break;
                    case 4:
                        ivSort.setImageResource(R.mipmap.sort_down);
                        showToast("价格倒序");
                        break;
                }
                mPresenter.getBuildingList(cityID, areaID, tradingName, areaStart, areaEnd, priceStart, priceEnd, "", pageSize, pageIndex, sortBy);
                break;
            case R.id.cb_houses_area:
                radioCheckBox(cbArea);
                //设置默认获取焦点
                areaPop.setFocusable(true);
                //以某个控件的x和y的偏移量位置开始显示窗口
                areaPop.showAsDropDown(cbArea, 0, 0);
                //如果窗口存在，则更新
                areaPop.update();
                break;
            case R.id.cb_houses_acreage:
                radioCheckBox(cbAcreage);
                //设置默认获取焦点
                acreagePop.setFocusable(true);
                //以某个控件的x和y的偏移量位置开始显示窗口
                acreagePop.showAsDropDown(cbArea, 0, 0);
                //如果窗口存在，则更新
                acreagePop.update();
                break;
            case R.id.cb_houses_price:
                radioCheckBox(cbPrice);
                //设置默认获取焦点
                pricePop.setFocusable(true);
                //以某个控件的x和y的偏移量位置开始显示窗口
                pricePop.showAsDropDown(cbArea, 0, 0);
                //如果窗口存在，则更新
                pricePop.update();
                break;
        }
    }

    @Override
    public void responseCityListData(List<CityListInfo.CityList> dataList) {
        if (dataList.size() != 0) {
            cityID = dataList.get(0).getId();
        }
        cityPop.setCityList(dataList);
        //获取楼盘列表
        mPresenter.getBuildingList(cityID, null, tradingName, null, null, null, null, "", pageSize, pageIndex, sortBy);
        //获取区域列表
        mPresenter.getAreaList(cityID);
    }

    @Override
    public void responseCityListDataError(String msg) {
        showToast(msg);
    }

    @Override
    public void responseAreaListData(List<AreaListInfo.AreaList> dataList) {
        areaPop.setAreaList(dataList);
    }

    @Override
    public void responseAreaListDataError(String msg) {
        showToast(msg);
    }

    @Override
    public void responseTradingAreaListData(List<TradingListInfo.TradingList> dataList) {
        areaPop.setTradingList(dataList);
    }

    @Override
    public void responseTradingAreaListDataError(String msg) {
        showToast(msg);
    }

    @Override
    public void responseBuildingListData(List<BuildingListInfo.BuildingList> dataList) {
        onLoad();
        buildList.clear();
        buildList.addAll(dataList);
        mAdapter.notifyDataSetChanged();
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
        mAdapter.notifyDataSetChanged();
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
        mPresenter.getBuildingList(cityID, areaID, tradingName, areaStart, areaEnd, priceStart, priceEnd, "", pageSize, pageIndex, sortBy);
    }

    @Override
    public void onLoadMore() {
        pageIndex++;
        mPresenter.getMoreBuildingList(cityID, areaID, tradingName, areaStart, areaEnd, priceStart, priceEnd, "", pageSize, pageIndex, sortBy);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        BuildingListInfo.BuildingList info = buildList.get(position - 1);
        Intent intent = new Intent(this, BuildingDetailActivity.class);
        intent.putExtra(Constants.ACTION_TO_BUILDING_DETAIL_BUILDING_ID, info.getID());
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
