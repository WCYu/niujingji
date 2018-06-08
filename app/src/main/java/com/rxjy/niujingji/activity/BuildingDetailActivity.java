package com.rxjy.niujingji.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
import com.rxjy.niujingji.R;
import com.rxjy.niujingji.adapter.BuildingTypeAdapter;
import com.rxjy.niujingji.adapter.RoomAdapter;
import com.rxjy.niujingji.commons.Constants;
import com.rxjy.niujingji.commons.base.BaseActivity;
import com.rxjy.niujingji.commons.utils.AutoUtils;
import com.rxjy.niujingji.commons.utils.Utility;
import com.rxjy.niujingji.entity.BuildingDetailInfo;
import com.rxjy.niujingji.entity.BuildingTypeInfo;
import com.rxjy.niujingji.entity.PoiInfo;
import com.rxjy.niujingji.entity.RoomListInfo;
import com.rxjy.niujingji.mvp.contract.BuildingDetailContract;
import com.rxjy.niujingji.mvp.presenter.BuildingDetailPresenter;
import com.rxjy.niujingji.pop.AppointmentPop;
import com.rxjy.niujingji.pop.ConsultPop;
import com.rxjy.niujingji.widget.HeadZoomScrollView;
import com.rxjy.niujingji.widget.HorizontalListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BuildingDetailActivity extends BaseActivity<BuildingDetailPresenter> implements BuildingDetailContract.View, AdapterView.OnItemClickListener {

    @Bind(R.id.iv_building)
    ImageView ivBuilding;
    @Bind(R.id.tv_building_name)
    TextView tvBuildingName;
    @Bind(R.id.tv_building_price)
    TextView tvBuildingPrice;
    @Bind(R.id.tv_building_location)
    TextView tvBuildingLocation;
    @Bind(R.id.tv_building_type)
    TextView tvBuildingType;
    @Bind(R.id.hsv_building)
    HeadZoomScrollView hsvBuilding;
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.iv_share)
    ImageView ivShare;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.rl_title)
    RelativeLayout rlTitle;
    @Bind(R.id.tb_building)
    Toolbar tbBuilding;
    @Bind(R.id.hlv_type)
    HorizontalListView hlvType;
    @Bind(R.id.lin_building_more)
    LinearLayout linBuildingMore;
    @Bind(R.id.lv_building_house)
    ListView lvRoom;
    @Bind(R.id.tv_building_intro)
    TextView tvBuildingIntro;
    @Bind(R.id.tv_building_acreage)
    TextView tvBuildingAcreage;
    @Bind(R.id.tv_building_address)
    TextView tvBuildingAddress;
    @Bind(R.id.tv_building_floor_count)
    TextView tvBuildingFloorCount;
    @Bind(R.id.tv_building_tenement)
    TextView tvBuildingTenement;
    @Bind(R.id.tv_building_tenement_price)
    TextView tvBuildingTenementPrice;
    @Bind(R.id.tv_building_carport)
    TextView tvBuildingCarport;
    @Bind(R.id.rl_type)
    RelativeLayout rlType;
    @Bind(R.id.btn_building_consult)
    Button btnBuildingConsult;
    @Bind(R.id.tv_building_unit)
    TextView tvUnit;
    @Bind(R.id.tv_traffic_count)
    TextView tvTrafficCount;
    @Bind(R.id.tv_catering_count)
    TextView tvCateringCount;
    @Bind(R.id.tv_bank_count)
    TextView tvBankCount;
    @Bind(R.id.tv_hotel_count)
    TextView tvHotelCount;
    @Bind(R.id.iv_building_location)
    ImageView ivBuildingLocation;

    public static final String TITLE = "楼盘详情";

    private List<BuildingTypeInfo> typeList;

    private BuildingTypeAdapter typeAdapter;

    private List<RoomListInfo.RoomList> roomList;

    private RoomAdapter roomAdapter;

    private List<BuildingDetailInfo.BuildingDetail.ImgList> imgList;

    private ConsultPop consultPop;

    private AppointmentPop appointmentPop;

    private String location;

    private double latitude;

    private double longitude;

    private String name;

    private int roomType;

    private int pageSize = 5;

    private int pageIndex = 1;

    private int buildingID;

    @Override
    public int getLayout() {
        return R.layout.activity_building_detail;
    }

    @Override
    public void initData() {
        initIntent();
        initTitle();
        initBuildingInfo();
        initBuildingType();
        initRoomData();
        initBuildingImg();
        initConsultPop();
        initAppointment();
    }

    private void initIntent() {
        buildingID = getIntent().getIntExtra(Constants.ACTION_TO_BUILDING_DETAIL_BUILDING_ID, 0);
    }

    private void initTitle() {
        //初始化状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // Translucent status bar
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            tvTitle.setHeight(AutoUtils.getDisplayHeightValue(132));
        }

        tvTitle.setText(TITLE);
        hsvBuilding.setTitleView(tbBuilding);
        hsvBuilding.setOnTitleShowListener(new HeadZoomScrollView.OnTitleShowListener() {
            @Override
            public void onTitleShow() {
                tbBuilding.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            }

            @Override
            public void onTitleHide() {
                tbBuilding.setBackgroundColor(getResources().getColor(R.color.colorTransparency));
            }
        });
    }

    private void initBuildingInfo() {

        mPresenter.getBuildingInfo(buildingID);

    }

    private void initBuildingType() {

        typeList = new ArrayList<>();

        typeAdapter = new BuildingTypeAdapter(this, typeList);

        hlvType.setAdapter(typeAdapter);

        hlvType.setOnItemClickListener(this);

    }

    private void initRoomData() {

        roomList = new ArrayList<>();

        roomAdapter = new RoomAdapter(this, roomList);

        lvRoom.setAdapter(roomAdapter);

        lvRoom.setOnItemClickListener(this);

    }

    private void initBuildingImg() {

        imgList = new ArrayList<>();

    }

    private void initConsultPop() {

        consultPop = new ConsultPop(this, R.layout.pop_consult_layout, AutoUtils.getDisplayWidthValue(720), AutoUtils.getDisplayHeightValue(320));

        consultPop.setOnConsultPopClickListener(new ConsultPop.OnConsultPopClickListener() {
            @Override
            public void consultQQ() {
                //打开QQ并跳转到该联系人的临时会话
                String url = "mqqwpa://im/chat?chat_type=wpa&uin=2515341286";
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            }

            @Override
            public void consultCall() {
                //调用拨号盘
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + "4006169688"));
                startActivity(intent);
            }
        });

    }

    private void initAppointment() {

        appointmentPop = new AppointmentPop(this, R.layout.pop_appointment_layout, AutoUtils.getDisplayWidthValue(720), AutoUtils.getDisplayHeightValue(368));

        appointmentPop.setOnAppointmentPopClickListener(new AppointmentPop.OnAppointmentPopClickListener() {
            @Override
            public void subAppointment(String name, String phone) {
                mPresenter.bookingRoom(buildingID, name, phone);
            }

            @Override
            public void tipInputName() {
                showToast("请输入姓名");
            }

            @Override
            public void tipInputPhone() {
                showToast("请输入电话");
            }

            @Override
            public void tipInputRightPhone() {
                showToast("请输入正确的电话");
            }
        });

    }

    private void initPoi() {

        mPresenter.getPoiSubway(location);
        mPresenter.getPoiRestaurant(location);
        mPresenter.getPoiBank(location);
        mPresenter.getPoiHotel(location);

    }

    private String getType(int typeID) {
        String type = "";
        switch (typeID) {
            case 0:
                type = "全部户型";
                break;
            case 1:
                type = "0-100㎡";
                break;
            case 2:
                type = "100-300㎡";
                break;
            case 3:
                type = "300-500㎡";
                break;
            case 4:
                type = "500-800㎡";
                break;
            case 5:
                type = "大于800㎡";
                break;
        }
        return type;
    }

    //是否显示加载更多
    private void isShowLoad(int size) {
        if (size < pageSize) {
            linBuildingMore.setVisibility(View.GONE);
        } else {
            linBuildingMore.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected BuildingDetailPresenter onCreatePresenter() {
        return new BuildingDetailPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_building, R.id.iv_back, R.id.iv_share, R.id.lin_building_more, R.id.btn_building_consult, R.id.btn_building_appointment, R.id.iv_building_location, R.id.lin_building_map})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_building:
                if (imgList.size() != 0) {
                    List<LocalMedia> list = new ArrayList<>();
                    for (BuildingDetailInfo.BuildingDetail.ImgList imgInfo : imgList) {
                        LocalMedia localMedia = new LocalMedia();
                        localMedia.setPath(imgInfo.getThumbnail());
                        list.add(localMedia);
                    }
                    PictureSelector.create(this).externalPicturePreview(0, list);
                } else {
                    showToast("暂无图片");
                }
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_share:
                break;
            case R.id.lin_building_more:
                pageIndex++;
                mPresenter.getMoreRoomList(buildingID, pageSize, pageIndex, roomType);
                break;
            case R.id.btn_building_consult:
                //设置默认获取焦点
                consultPop.setFocusable(true);
                //显示在布局最下边
                consultPop.showAtLocation(view, Gravity.BOTTOM, 0, 0);
                //如果窗口存在，则更新
                consultPop.update();
                break;
            case R.id.btn_building_appointment:
                appointmentPop.setFocusable(true);
                appointmentPop.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
                appointmentPop.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                appointmentPop.showAtLocation(view, Gravity.BOTTOM, 0, 0);
                appointmentPop.update();
                break;
            case R.id.iv_building_location:
                Intent intent = new Intent(this, MapActivity.class);
                intent.putExtra(Constants.ACTION_TO_MAP_TITLE, name);
                intent.putExtra(Constants.ACTION_TO_MAP_LATITUDE, latitude);
                intent.putExtra(Constants.ACTION_TO_MAP_LONGITUDE, longitude);
                startActivity(intent);
                break;
            case R.id.lin_building_map:
                Intent linIntent = new Intent(this, MapActivity.class);
                linIntent.putExtra(Constants.ACTION_TO_MAP_TITLE, name);
                linIntent.putExtra(Constants.ACTION_TO_MAP_LATITUDE, latitude);
                linIntent.putExtra(Constants.ACTION_TO_MAP_LONGITUDE, longitude);
                startActivity(linIntent);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.hlv_type:
                for (BuildingTypeInfo typeInfo : typeList) {
                    if (typeInfo.getIsSelector() != 0)
                        typeInfo.setIsSelector(0);
                }
                typeList.get(position).setIsSelector(1);
                typeAdapter.notifyDataSetChanged();
                pageIndex = 1;
                roomType = typeList.get(position).getTypeID();
                mPresenter.getRoomList(buildingID, pageSize, pageIndex, roomType);
                break;
            case R.id.lv_building_house:
                RoomListInfo.RoomList info = this.roomList.get(position);
                Intent intent = new Intent(this, RoomDetailActivity.class);
                intent.putExtra(Constants.ACTION_TO_ROOM_DETAIL_ROOM_ID, info.getID());
                startActivity(intent);
                break;
        }
    }

    @Override
    public void responseBuildingInfoData(BuildingDetailInfo.BuildingDetail data) {
        if (!data.getAvgDailyRent().equals("面议")) {
            tvBuildingPrice.setText("¥" + data.getAvgDailyRent());
            tvUnit.setVisibility(View.VISIBLE);
        } else {
            tvBuildingPrice.setText(data.getAvgDailyRent());
            tvUnit.setVisibility(View.INVISIBLE);
        }
        tvBuildingName.setText(data.getName());
        tvBuildingPrice.setText("¥" + data.getAvgDailyRent());
        tvBuildingLocation.setText(data.getCountyName() + "-" + data.getBussiness());
        tvBuildingType.setText(data.getBuildType());
        tvBuildingIntro.setText(data.getDescription());
        tvBuildingAcreage.setText(data.getStandardArea() + "㎡");
        tvBuildingAddress.setText(data.getAddress());
        tvBuildingFloorCount.setText(data.getAllFloor() + "层");
        tvBuildingTenement.setText(data.getPropertyName());
        tvBuildingTenementPrice.setText(data.getPropertyFee() + "元/㎡/月");
        tvBuildingCarport.setText("地上" + data.getGroundParking() + "," + "地下" + data.getUndergroundParking());

        name = data.getName();
        latitude = data.getLatitude();
        longitude = data.getLongitude();

        String unLocation = data.getLongitude() + "," + data.getLatitude();
        //标记小地图
        Glide.with(this).load("http://api.map.baidu.com/staticimage/v2?ak=ee4sAHbt5MiasT0tXP9AoxHuT17Unpyj&mcode=com.rxjy.niujingji&center=" + unLocation + "&width=118&height=118&zoom=15&markers=" + unLocation + "&copyright=1&markerStyles=-1").into(ivBuildingLocation);

        //检索周边
        location = data.getLatitude() + "," + data.getLongitude();
        initPoi();
    }

    @Override
    public void responseBuildingTypeData(List<Integer> dataList) {
        for (int i = 0; i < dataList.size(); i++) {
            int count = dataList.get(i);
            if (count != 0) {
                if (i == 0)
                    typeList.add(new BuildingTypeInfo(getType(i), count, i, 1));
                else
                    typeList.add(new BuildingTypeInfo(getType(i), count, i, 0));
            }
        }
        typeAdapter.notifyDataSetChanged();
        mPresenter.getRoomList(buildingID, pageSize, pageIndex, 0);
        //判断是否显示房源类型
        if (typeList.size() == 0)
            rlType.setVisibility(View.GONE);
    }

    @Override
    public void responseBuildingImageList(List<BuildingDetailInfo.BuildingDetail.ImgList> dataList) {
        imgList.clear();
        imgList.addAll(dataList);
        if (imgList.size() != 0) {
            BuildingDetailInfo.BuildingDetail.ImgList imgInfo = this.imgList.get(0);
            RequestOptions options = new RequestOptions();
            options.placeholder(R.mipmap.building_icon);
            options.error(R.mipmap.building_icon);
            Glide.with(this).load(imgInfo.getThumbnail()).apply(options).into(ivBuilding);
        }
    }

    @Override
    public void responseBuildingInfoDataError(String msg) {
        showToast(msg);
    }

    @Override
    public void responseRoomListData(List<RoomListInfo.RoomList> dataList) {
        roomList.clear();
        roomList.addAll(dataList);
        roomAdapter.notifyDataSetChanged();
        Utility.setListViewHeightBasedOnChildren(lvRoom);
        isShowLoad(dataList.size());
    }

    @Override
    public void responseMoreRoomListData(List<RoomListInfo.RoomList> dataList) {
        roomList.addAll(dataList);
        roomAdapter.notifyDataSetChanged();
        Utility.setListViewHeightBasedOnChildren(lvRoom);
        isShowLoad(dataList.size());
    }

    @Override
    public void responseRoomListDataError(String msg) {
        showToast(msg);
    }

    @Override
    public void responseBookingRoom() {
        showToast("预约成功，稍后我们会联系您");
    }

    @Override
    public void responseBookingRoomError(String msg) {
        showToast(msg);
    }

    @Override
    public void responsePoiSubway(List<PoiInfo.SiteInfo> subwayList) {
        tvTrafficCount.setText("约" + subwayList.size() + "个站点");
    }

    @Override
    public void responsePoiRestaurant(List<PoiInfo.SiteInfo> restaurantList) {
        tvCateringCount.setText("约" + restaurantList.size() + "家");
    }

    @Override
    public void responsePoiBank(List<PoiInfo.SiteInfo> bankList) {
        tvBankCount.setText("约" + bankList.size() + "家");
    }

    @Override
    public void responsePoiHotel(List<PoiInfo.SiteInfo> hotelList) {
        tvHotelCount.setText("约" + hotelList.size() + "家");
    }

    @Override
    public void showDialog() {
        showLoading();
    }

    @Override
    public void hideDialog() {
        dismissLoading();
    }
}
