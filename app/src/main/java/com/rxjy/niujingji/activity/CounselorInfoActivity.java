package com.rxjy.niujingji.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rxjy.niujingji.R;
import com.rxjy.niujingji.commons.App;
import com.rxjy.niujingji.commons.base.BaseActivity;
import com.rxjy.niujingji.entity.CounselorInfo;
import com.rxjy.niujingji.mvp.contract.CounselorContract;
import com.rxjy.niujingji.mvp.presenter.CounselorPresenter;
import com.rxjy.niujingji.widget.RoundAngleImageView;
import com.umeng.analytics.MobclickAgent;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CounselorInfoActivity extends BaseActivity<CounselorPresenter> implements CounselorContract.View {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.riv_counselor_head_photo)
    RoundAngleImageView rivHeadPhoto;
    @Bind(R.id.tv_counselor_name)
    TextView tvName;
    @Bind(R.id.tv_counselor_referral_code)
    TextView tvReferralCode;
    @Bind(R.id.tv_counselor_wx)
    TextView tvWx;
    @Bind(R.id.tv_counselor_phone)
    TextView tvPhone;
    @Bind(R.id.tv_counselor_mailbox)
    TextView tvMailbox;

    public static final String TITLE = "顾问";

    private String phoneNum;

    @Override
    public int getLayout() {
        return R.layout.activity_counselor_info;
    }

    @Override
    public void initData() {
        initTitle();
        initCounselorData();
    }

    private void initTitle() {
        tvTitle.setText(TITLE);
    }

    private void initCounselorData() {

        //获取顾问信息接口
        mPresenter.getCounselorInfo(App.cardNo);

    }

    @Override
    protected CounselorPresenter onCreatePresenter() {
        return new CounselorPresenter(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("顾问");
        MobclickAgent.onResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("顾问");
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.tv_counselor_phone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_counselor_phone:
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + phoneNum));
                startActivity(intent);
                break;
        }
    }

    @Override
    public void responseCounselorData(CounselorInfo.Counselor data) {
        phoneNum = data.getPhone();
        tvName.setText(data.getName());
        tvReferralCode.setText(data.getCardNo());
        tvWx.setText(data.getWeChat());
        tvPhone.setText(data.getPhone());
        tvMailbox.setText(data.getEmail());
        RequestOptions options = new RequestOptions();
        options.placeholder(R.mipmap.head_portrait_icon);
        options.error(R.mipmap.head_portrait_icon);
        Glide.with(this).load(data.getHeadImg()).apply(options).into(rivHeadPhoto);
    }

    @Override
    public void responseCounselorDataError(String msg) {
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
}
