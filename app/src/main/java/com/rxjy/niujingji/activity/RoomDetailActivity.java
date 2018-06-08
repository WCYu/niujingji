package com.rxjy.niujingji.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
import com.rxjy.niujingji.R;
import com.rxjy.niujingji.commons.Constants;
import com.rxjy.niujingji.commons.base.BaseActivity;
import com.rxjy.niujingji.commons.utils.AutoUtils;
import com.rxjy.niujingji.entity.PoiInfo;
import com.rxjy.niujingji.entity.RoomDetailInfo;
import com.rxjy.niujingji.mvp.contract.RoomDetailContract;
import com.rxjy.niujingji.mvp.presenter.RoomDetailPresenter;
import com.rxjy.niujingji.pop.AppointmentPop;
import com.rxjy.niujingji.pop.ConsultPop;
import com.rxjy.niujingji.widget.HeadZoomScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RoomDetailActivity extends BaseActivity<RoomDetailPresenter> implements RoomDetailContract.View {

    @Bind(R.id.iv_room_detail)
    ImageView ivRoomDetail;
    @Bind(R.id.iv_room_detail_status)
    ImageView ivRoomDetailStatus;
    @Bind(R.id.tv_room_detail_name)
    TextView tvRoomDetailName;
    @Bind(R.id.tv_room_detail_price)
    TextView tvRoomDetailPrice;
    @Bind(R.id.tv_room_detail_month_price)
    TextView tvRoomDetailMonthPrice;
    @Bind(R.id.tv_room_detail_location)
    TextView tvRoomDetailLocation;
    @Bind(R.id.tv_room_detail_type)
    TextView tvRoomDetailType;
    @Bind(R.id.tv_room_detail_info_price)
    TextView tvRoomDetailInfoPrice;
    @Bind(R.id.tv_room_detail_info_user_acreage)
    TextView tvRoomDetailInfoUserAcreage;
    @Bind(R.id.tv_room_detail_info_type)
    TextView tvRoomDetailInfoType;
    @Bind(R.id.tv_room_detail_info_floor_count)
    TextView tvRoomDetailInfoFloorCount;
    @Bind(R.id.tv_room_detail_info_floor_count_sum)
    TextView tvRoomDetailInfoFloorCountSum;
    @Bind(R.id.tv_room_detail_info_build_level)
    TextView tvRoomDetailInfoBuildLevel;
    @Bind(R.id.tv_room_detail_info_face_to)
    TextView tvRoomDetailInfoFaceTo;
    @Bind(R.id.tv_room_detail_info_address)
    TextView tvRoomDetailInfoAddress;
    @Bind(R.id.tv_room_detail_info_tenement)
    TextView tvRoomDetailInfoTenement;
    @Bind(R.id.tv_room_detail_info_tenement_price)
    TextView tvRoomDetailInfoTenementPrice;
    @Bind(R.id.tv_traffic_count)
    TextView tvTrafficCount;
    @Bind(R.id.tv_catering_count)
    TextView tvCateringCount;
    @Bind(R.id.tv_bank_count)
    TextView tvBankCount;
    @Bind(R.id.tv_hotel_count)
    TextView tvHotelCount;
    @Bind(R.id.btn_room_detail_consult)
    Button btnRoomDetailConsult;
    @Bind(R.id.btn_room_detail_appointment)
    Button btnRoomDetailAppointment;
    @Bind(R.id.hsv_room_detail)
    HeadZoomScrollView hsvRoomDetail;
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.iv_share)
    ImageView ivShare;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.rl_title)
    RelativeLayout rlTitle;
    @Bind(R.id.tb_room_detail)
    Toolbar tbRoomDetail;
    @Bind(R.id.tv_room_detail_price_unit)
    TextView tvPriceUnit;
    @Bind(R.id.tv_room_detail_month_price_unit)
    TextView tvMonthPriceUnit;
    @Bind(R.id.iv_room_detail_location)
    ImageView ivRoomDetailLocation;

    public static final String TITLE = "房源详情";

    private List<RoomDetailInfo.RoomDetail.ImgInfo> imgList;

    private ConsultPop consultPop;

    private AppointmentPop appointmentPop;

    private double lat;

    private double lon;

    private String name;

    private String location;

    private int roomID;

    @Override
    public int getLayout() {
        return R.layout.activity_room_detail;
    }

    @Override
    public void initData() {
        initIntent();
        initTitle();
        initRoomData();
        initImgData();
        initConsultPop();
        initAppointment();
    }

    private void initIntent() {
        roomID = getIntent().getIntExtra(Constants.ACTION_TO_ROOM_DETAIL_ROOM_ID, 0);
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
        hsvRoomDetail.setTitleView(tbRoomDetail);
        hsvRoomDetail.setOnTitleShowListener(new HeadZoomScrollView.OnTitleShowListener() {
            @Override
            public void onTitleShow() {
                tbRoomDetail.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            }

            @Override
            public void onTitleHide() {
                tbRoomDetail.setBackgroundColor(getResources().getColor(R.color.colorTransparency));
            }
        });
    }

    private void initRoomData() {

        mPresenter.getRoomDetailInfo(roomID);

    }

    private void initImgData() {

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
                mPresenter.bookingRoom(roomID, name, phone);
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

    @Override
    protected RoomDetailPresenter onCreatePresenter() {
        return new RoomDetailPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_room_detail, R.id.tv_room_detail_info_price, R.id.btn_room_detail_consult, R.id.btn_room_detail_appointment, R.id.iv_back, R.id.iv_share,R.id.iv_room_detail_location,R.id.lin_room_detail_map})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_room_detail:
                if (imgList.size() != 0) {
                    List<LocalMedia> list = new ArrayList<>();
                    for (RoomDetailInfo.RoomDetail.ImgInfo imgInfo : imgList) {
                        LocalMedia localMedia = new LocalMedia();
                        localMedia.setPath(imgInfo.getThumbnail());
                        list.add(localMedia);
                    }
                    PictureSelector.create(this).externalPicturePreview(0, list);
                } else {
                    showToast("暂无图片");
                }
                break;
            case R.id.tv_room_detail_info_price:
                break;
            case R.id.btn_room_detail_consult:
                //设置默认获取焦点
                consultPop.setFocusable(true);
                //显示在最下边
                consultPop.showAtLocation(view, Gravity.BOTTOM, 0, 0);
                //如果窗口存在，则更新
                consultPop.update();
                break;
            case R.id.btn_room_detail_appointment:
                appointmentPop.setFocusable(true);
                appointmentPop.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
                appointmentPop.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                appointmentPop.showAtLocation(view, Gravity.BOTTOM, 0, 0);
                appointmentPop.update();
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_share:
                break;
            case R.id.iv_room_detail_location:
                Intent ivIntent = new Intent(this, MapActivity.class);
                ivIntent.putExtra(Constants.ACTION_TO_MAP_TITLE, name);
                ivIntent.putExtra(Constants.ACTION_TO_MAP_LATITUDE, lat);
                ivIntent.putExtra(Constants.ACTION_TO_MAP_LONGITUDE, lon);
                startActivity(ivIntent);
                break;
            case R.id.lin_room_detail_map:
                Intent linIntent = new Intent(this, MapActivity.class);
                linIntent.putExtra(Constants.ACTION_TO_MAP_TITLE, name);
                linIntent.putExtra(Constants.ACTION_TO_MAP_LATITUDE, lat);
                linIntent.putExtra(Constants.ACTION_TO_MAP_LONGITUDE, lon);
                startActivity(linIntent);
                break;
        }
    }

    @Override
    public void responseRoomDetailInfoData(RoomDetailInfo.RoomDetail data) {
        switch (data.getTypes()) {
            case "租":
                ivRoomDetailStatus.setImageResource(R.mipmap.rent_big_icon);
                tvRoomDetailPrice.setText("¥" + data.getDayRent());
                tvPriceUnit.setText("元/㎡/天");
                tvRoomDetailMonthPrice.setText("¥" + data.getMonthRent());
                tvMonthPriceUnit.setText("元/㎡/月");
                tvRoomDetailInfoPrice.setText(data.getDayRent() + "元/㎡/天");
                break;
            case "售":
                ivRoomDetailStatus.setImageResource(R.mipmap.sell_big_icon);
                tvRoomDetailPrice.setText("¥" + data.getSalePrice());
                tvPriceUnit.setText("元/㎡");
                tvRoomDetailMonthPrice.setText("¥" + data.getTotalSale());
                tvMonthPriceUnit.setText("元");
                tvRoomDetailInfoPrice.setText(data.getSalePrice() + "元/㎡");
                break;
        }
        tvRoomDetailName.setText(data.getFloor() + "·" + data.getFit() + "·" + data.getHousingArea() + "㎡");
        tvRoomDetailLocation.setText(data.getAreaName() + "-" + data.getBusinessName());
        tvRoomDetailType.setText(data.getBuildType());
        tvRoomDetailInfoUserAcreage.setText(data.getHousingArea() + "㎡");
        tvRoomDetailInfoType.setText(data.getBuildType());
        tvRoomDetailInfoFloorCount.setText(data.getOnFloor() + "楼");
        tvRoomDetailInfoFloorCountSum.setText(data.getFloorNum() + "层");
        tvRoomDetailInfoBuildLevel.setText(data.getFit());
        tvRoomDetailInfoFaceTo.setText(data.getToward());
        tvRoomDetailInfoAddress.setText(data.getDetailedAddress() == null ? "暂无" : data.getDetailedAddress());
        tvRoomDetailInfoTenement.setText(data.getPropertyName());
        tvRoomDetailInfoTenementPrice.setText(data.getRentFreePeriod() + "元/㎡/月");

        name = data.getFloor() + "·" + data.getFit() + "·" + data.getHousingArea() + "㎡";

        lat = data.getLatitude();

        lon = data.getLongitude();

        String unLocation = data.getLongitude() + "," + data.getLatitude();
        //标记小地图
        Glide.with(this).load("http://api.map.baidu.com/staticimage/v2?ak=ee4sAHbt5MiasT0tXP9AoxHuT17Unpyj&mcode=com.rxjy.niujingji&center=" + unLocation + "&width=118&height=118&zoom=15&markers=" + unLocation + "&copyright=1&markerStyles=-1").into(ivRoomDetailLocation);

        //检索周边
        location = data.getLatitude() + "," + data.getLongitude();
        initPoi();

    }

    @Override
    public void responseRoomDetailImageList(List<RoomDetailInfo.RoomDetail.ImgInfo> dataList) {
        imgList.clear();
        imgList.addAll(dataList);
        if (imgList.size() != 0) {
            RoomDetailInfo.RoomDetail.ImgInfo imgInfo = imgList.get(0);
            RequestOptions options = new RequestOptions();
            options.placeholder(R.mipmap.building_icon);
            options.error(R.mipmap.building_icon);
            Glide.with(this).load(imgInfo.getThumbnail()).apply(options).into(ivRoomDetail);
        }
    }

    @Override
    public void responseRoomDetailInfoDataError(String msg) {
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
