package com.rxjy.niujingji.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.rxjy.niujingji.R;
import com.rxjy.niujingji.commons.Constants;
import com.rxjy.niujingji.commons.base.BaseActivity;
import com.rxjy.niujingji.commons.utils.AutoUtils;
import com.rxjy.niujingji.entity.PoiButtonInfo;
import com.rxjy.niujingji.entity.PoiInfo;
import com.rxjy.niujingji.mvp.contract.PoiContract;
import com.rxjy.niujingji.mvp.presenter.PoiPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MapActivity extends BaseActivity<PoiPresenter> implements PoiContract.View {


    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.mv_map)
    MapView mvMap;
    @Bind(R.id.iv_traffic)
    ImageView ivTraffic;
    @Bind(R.id.tv_traffic)
    TextView tvTraffic;
    @Bind(R.id.tv_traffic_count)
    TextView tvTrafficCount;
    @Bind(R.id.lin_traffic)
    LinearLayout linTraffic;
    @Bind(R.id.iv_catering)
    ImageView ivCatering;
    @Bind(R.id.tv_catering)
    TextView tvCatering;
    @Bind(R.id.tv_catering_count)
    TextView tvCateringCount;
    @Bind(R.id.lin_catering)
    LinearLayout linCatering;
    @Bind(R.id.iv_bank)
    ImageView ivBank;
    @Bind(R.id.tv_bank)
    TextView tvBank;
    @Bind(R.id.tv_bank_count)
    TextView tvBankCount;
    @Bind(R.id.lin_bank)
    LinearLayout linBank;
    @Bind(R.id.iv_hotel)
    ImageView ivHotel;
    @Bind(R.id.tv_hotel)
    TextView tvHotel;
    @Bind(R.id.tv_hotel_count)
    TextView tvHotelCount;
    @Bind(R.id.lin_hotel)
    LinearLayout linHotel;

    private List<PoiInfo.SiteInfo> subwayList;
    private List<PoiInfo.SiteInfo> restaurantList;
    private List<PoiInfo.SiteInfo> bankList;
    private List<PoiInfo.SiteInfo> hotelList;

    private List<PoiButtonInfo> poiButtonList;

    private String name;

    private double latitude;

    private double longitude;

    private BaiduMap mBaiduMap;

    private InfoWindow mInfoWindow;

    @Override
    public int getLayout() {
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());
        return R.layout.activity_map;
    }

    @Override
    public void initData() {
        initIntent();
        initTitle();
        initMap();
        initPoi();
        initMarks();
        initPoiButton();
    }

    private void initIntent() {
        name = getIntent().getStringExtra(Constants.ACTION_TO_MAP_TITLE);
        latitude = getIntent().getDoubleExtra(Constants.ACTION_TO_MAP_LATITUDE, 0);
        longitude = getIntent().getDoubleExtra(Constants.ACTION_TO_MAP_LONGITUDE, 0);
    }

    private void initTitle() {
        tvTitle.setText(name);
    }

    private void initMap() {

        mBaiduMap = mvMap.getMap();

        //设置普通地图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);

    }

    private void showMark(LatLng point, String title) {
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.mipmap.location_red_icon);
        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(point)
                .title(title)
                .icon(bitmap);

        //在地图上添加Marker，并显示
        mBaiduMap.addOverlay(option);
    }

    private void initPoi() {
        String location = latitude + "," + longitude;

        subwayList = new ArrayList<>();
        restaurantList = new ArrayList<>();
        bankList = new ArrayList<>();
        hotelList = new ArrayList<>();

        mPresenter.getPoiSubway(location);
        mPresenter.getPoiRestaurant(location);
        mPresenter.getPoiBank(location);
        mPresenter.getPoiHotel(location);
    }

    private void initMarks() {

        //定义Maker坐标点
        LatLng point = new LatLng(latitude, longitude);
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.mipmap.location_blue_icon);
        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(point)
                .title(name)
                .icon(bitmap);

        //在地图上添加Marker，并显示
        mBaiduMap.addOverlay(option);

        // 改变地图状态，使地图显示在恰当的缩放大小
        MapStatus mMapStatus = new MapStatus.Builder().target(point).zoom(17).build();
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        mBaiduMap.setMapStatus(mMapStatusUpdate);

        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                showLocation(marker);
                return false;
            }
        });
    }

    private void initPoiButton() {

        poiButtonList = new ArrayList<>();

        poiButtonList.add(new PoiButtonInfo(linTraffic, ivTraffic, tvTraffic, tvTrafficCount, R.mipmap.traffic_icon, R.mipmap.traffic_icon_checked));
        poiButtonList.add(new PoiButtonInfo(linCatering, ivCatering, tvCatering, tvCateringCount, R.mipmap.catering_icon, R.mipmap.catering_icon_checked));
        poiButtonList.add(new PoiButtonInfo(linBank, ivBank, tvBank, tvBankCount, R.mipmap.bank_icon, R.mipmap.bank_icon_checked));
        poiButtonList.add(new PoiButtonInfo(linHotel, ivHotel, tvHotel, tvHotelCount, R.mipmap.hotel_icon, R.mipmap.hotel_icon_checked));


    }

    private void selectorPoi(int index) {
        for (PoiButtonInfo info : poiButtonList) {
            info.setState(0);
        }
        poiButtonList.get(index).setState(1);
        for (PoiButtonInfo info : poiButtonList) {
            switch (info.getState()) {
                case 0:
                    info.getLin().setBackgroundResource(R.drawable.shape_radio_button_normal);
                    info.getTvName().setTextColor(getResources().getColor(R.color.colorBlackLight));
                    info.getTvCount().setTextColor(getResources().getColor(R.color.colorGray));
                    info.getIv().setImageResource(info.getIconNormal());
                    break;
                case 1:
                    info.getLin().setBackgroundResource(R.drawable.shape_radio_button_checked);
                    info.getTvName().setTextColor(getResources().getColor(R.color.colorWhite));
                    info.getTvCount().setTextColor(getResources().getColor(R.color.colorWhite));
                    info.getIv().setImageResource(info.getIconChecked());
                    break;
            }
        }
    }

    private void showLocation(final Marker marker) {  //显示气泡
        // 创建InfoWindow展示的view
        LatLng pt = null;
        double latitude, longitude;
        latitude = marker.getPosition().latitude;
        longitude = marker.getPosition().longitude;

        View view = LayoutInflater.from(this).inflate(R.layout.pop_map_item, null); //自定义气泡形状
        AutoUtils.auto(view);
        TextView textView = (TextView) view.findViewById(R.id.tv_pop_title);
        pt = new LatLng(latitude + 0.0004, longitude + 0.00005);
        textView.setText(marker.getTitle());

        // 定义用于显示该InfoWindow的坐标点
        // 创建InfoWindow的点击事件监听者
        InfoWindow.OnInfoWindowClickListener listener = new InfoWindow.OnInfoWindowClickListener() {
            public void onInfoWindowClick() {
                mBaiduMap.hideInfoWindow();//影藏气泡
            }
        };
        // 创建InfoWindow
        mInfoWindow = new InfoWindow((BitmapDescriptorFactory.fromView(view)), pt, 1, listener);
        mBaiduMap.showInfoWindow(mInfoWindow); //显示气泡

    }

    private void showBuildingMark() {
        //定义Maker坐标点
        LatLng point = new LatLng(latitude, longitude);
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.mipmap.location_blue_icon);
        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(point)
                .title(name)
                .icon(bitmap);

        //在地图上添加Marker，并显示
        mBaiduMap.addOverlay(option);
    }

    /**
     * 重置标记
     */
    private void resetMarks(List<PoiInfo.SiteInfo> dataList) {
        showBuildingMark();
        for (PoiInfo.SiteInfo info : dataList) {
            showMark(new LatLng(info.getLocation().getLat(), info.getLocation().getLng()), info.getName());
        }
    }

    @Override
    protected PoiPresenter onCreatePresenter() {
        return new PoiPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.lin_traffic, R.id.lin_catering, R.id.lin_bank, R.id.lin_hotel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.lin_traffic:
                mBaiduMap.clear();
                resetMarks(subwayList);
                selectorPoi(0);
                break;
            case R.id.lin_catering:
                mBaiduMap.clear();
                resetMarks(restaurantList);
                selectorPoi(1);
                break;
            case R.id.lin_bank:
                mBaiduMap.clear();
                resetMarks(bankList);
                selectorPoi(2);
                break;
            case R.id.lin_hotel:
                mBaiduMap.clear();
                resetMarks(hotelList);
                selectorPoi(3);
                break;
        }
    }

    @Override
    public void responsePoiSubway(List<PoiInfo.SiteInfo> subwayList) {
        this.subwayList.addAll(subwayList);
        tvTrafficCount.setText("约" + subwayList.size() + "个站点");
    }

    @Override
    public void responsePoiRestaurant(List<PoiInfo.SiteInfo> restaurantList) {
        this.restaurantList.addAll(restaurantList);
        tvCateringCount.setText("约" + restaurantList.size() + "家");
    }

    @Override
    public void responsePoiBank(List<PoiInfo.SiteInfo> bankList) {
        this.bankList.addAll(bankList);
        tvBankCount.setText("约" + bankList.size() + "家");
    }

    @Override
    public void responsePoiHotel(List<PoiInfo.SiteInfo> hotelList) {
        this.hotelList.addAll(hotelList);
        tvHotelCount.setText("约" + hotelList.size() + "家");
    }
}
