package com.rxjy.niujingji.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.rxjy.niujingji.R;
import com.rxjy.niujingji.commons.App;
import com.rxjy.niujingji.commons.Constants;
import com.rxjy.niujingji.commons.base.BaseActivity;
import com.rxjy.niujingji.commons.utils.PrefUtils;
import com.rxjy.niujingji.mvp.contract.UpdUserInfoContract;
import com.rxjy.niujingji.mvp.presenter.UpdUserInfoPresenter;
import com.rxjy.niujingji.widget.RoundAngleImageView;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.DatePicker;

public class UserInfoActivity extends BaseActivity<UpdUserInfoPresenter> implements UpdUserInfoContract.View {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.riv_head_photo)
    RoundAngleImageView rivHeadPhoto;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_gender)
    TextView tvGender;
    @Bind(R.id.tv_birthday)
    TextView tvBirthday;
    @Bind(R.id.tv_phone)
    TextView tvPhone;
    @Bind(R.id.tv_mailbox)
    TextView tvMailbox;
    @Bind(R.id.tv_area)
    TextView tvArea;

    public static final String TITLE = "基本信息";

    private DatePicker picker;

    //性别
    List<String> sexList;
    //性别
    private OptionsPickerView sexPicker;

    private String mSex;

    private String time;

    private String key;

    private String path;

    @Override
    public int getLayout() {
        return R.layout.activity_user_info;
    }

    @Override
    public void initData() {
        initTitle();
        initUpData();
        initSexData();
    }

    private void initTitle() {
        tvTitle.setText(TITLE);
    }

    private void initUpData() {
        //初始化时间选择器
        picker = new DatePicker(this);
        picker.setRange(1900, 2100);
    }

    private void initSexData() {

        sexList = new ArrayList<>();

        sexList.add("男");
        sexList.add("女");

        sexPicker = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                key = "sex";
                tvGender.setText(sexList.get(options1));
                mSex = sexList.get(options1);
                mPresenter.updateUserInfo(App.token, App.cardNo, "sex", mSex);
            }
        }).build();

        sexPicker.setPicker(sexList);

    }

    private void initUserInfo() {
        tvName.setText(App.baseInfo.getNickName() == null ? "点击添加" : App.baseInfo.getNickName());
        tvGender.setText(App.baseInfo.getSex() == null ? "点击添加" : App.baseInfo.getSex());
        tvBirthday.setText(App.baseInfo.getBirthday() == "" ? "点击添加" : App.baseInfo.getBirthday());
        tvPhone.setText(App.baseInfo.getPhone() == null ? "点击添加" : App.baseInfo.getPhone());
        tvMailbox.setText(App.baseInfo.getEmail() == null ? "点击添加" : App.baseInfo.getEmail());
        tvArea.setText(App.personnelInfo.getArea() == null ? "点击添加" : App.personnelInfo.getArea());
        RequestOptions options = new RequestOptions();
        options.placeholder(R.mipmap.head_portrait_icon);
        options.error(R.mipmap.head_portrait_icon);
        Glide.with(this).load(App.baseInfo.getImage()).apply(options).into(rivHeadPhoto);
    }

    private void startUpdUserInfo(String keyValue, String key, String value) {
        Intent nickName = new Intent(this, UpdUserInfoActivity.class);
        nickName.putExtra(Constants.ACTION_TO_UPD_USER_INFO_KEY_VALUE, keyValue);
        nickName.putExtra(Constants.ACTION_TO_UPD_USER_INFO_KEY, key);
        nickName.putExtra(Constants.ACTION_TO_UPD_USER_INFO_VALUE, value == null ? "" : value);
        startActivity(nickName);
    }

    @Override
    protected UpdUserInfoPresenter onCreatePresenter() {
        return new UpdUserInfoPresenter(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("个人资料");
        MobclickAgent.onResume(this);
        initUserInfo();
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("个人资料");
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.riv_head_photo, R.id.tv_name, R.id.tv_gender, R.id.tv_birthday, R.id.tv_phone, R.id.tv_mailbox, R.id.tv_area})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.riv_head_photo:
                // 进入相册 以下是例子：用不到的api可以不写
                PictureSelector.create(this)
                        .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()
                        .imageSpanCount(3)// 每行显示个数 int
                        .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                        .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                        .enableCrop(true)// 是否裁剪 true or false
                        .withAspectRatio(1, 1)// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                        .scaleEnabled(false)// 裁剪是否可放大缩小图片 true or false
                        .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code 
                break;
            case R.id.tv_name:
                startUpdUserInfo("姓名", "nick_name", App.baseInfo.getNickName());
                break;
            case R.id.tv_gender:
                sexPicker.show();
                break;
            case R.id.tv_birthday:
                picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
                    @Override
                    public void onDatePicked(String year, String month, String day) {
                        key = "birthday";
                        time = year + "-" + month + "-" + day;
                        tvBirthday.setText(time);
                        mPresenter.updateUserInfo(App.token, App.cardNo, "birthday", time);
                    }
                });
                picker.show();
                break;
            case R.id.tv_phone:
                break;
            case R.id.tv_mailbox:
                startUpdUserInfo("邮箱", "email", App.baseInfo.getEmail());
                break;
            case R.id.tv_area:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    List<LocalMedia> localMedias = PictureSelector.obtainMultipleResult(data);
                    path = localMedias.get(0).getCutPath();
                    mPresenter.upHeaderPicture(App.token, App.cardNo, path);
                    break;
            }
        }
    }

    @Override
    public void responseUpdateData() {
        switch (key) {
            case "birthday":
                App.baseInfo.setBirthday(time);
                break;
            case "sex":
                App.baseInfo.setSex(mSex);
                break;
        }
        showToast("上传成功");
    }

    @Override
    public void responseUpdateDataError(String msg) {
        showToast(msg);
    }

    @Override
    public void responseUpPicture() {
        App.baseInfo.setImage(path);
        RequestOptions options = new RequestOptions();
        options.placeholder(R.mipmap.head_portrait_icon);
        options.error(R.mipmap.head_portrait_icon);
        Glide.with(this).load(App.baseInfo.getImage()).apply(options).into(rivHeadPhoto);
    }

    @Override
    public void responseUpPictureError(String msg) {
        showToast(msg);
    }

    @Override
    public void reLogin(String msg) {
        showToast(msg);
        PrefUtils.RemoveValue(this, Constants.IS_LOGIN);
        PrefUtils.RemoveValue(this, Constants.CARD_NO);
        PrefUtils.RemoveValue(this, Constants.TOKEN);
        startActivity(new Intent(this, LoginActivity.class));
        finish();
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
