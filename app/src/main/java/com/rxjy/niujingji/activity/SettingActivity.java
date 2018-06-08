package com.rxjy.niujingji.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.rxjy.niujingji.R;
import com.rxjy.niujingji.commons.App;
import com.rxjy.niujingji.commons.Constants;
import com.rxjy.niujingji.commons.base.BaseActivity;
import com.rxjy.niujingji.commons.base.BasePresenter;
import com.rxjy.niujingji.commons.utils.PrefUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_vision_name)
    TextView tvVisionName;

    public static final String TITLE = "设置";

    @Override
    public int getLayout() {
        return R.layout.activity_setting;
    }

    @Override
    public void initData() {
        initTitle();
        initVersionData();
    }

    private void initTitle() {
        tvTitle.setText(TITLE);
    }

    private void initVersionData() {
        tvVisionName.setText("V " + App.getVersionName());
    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }


    @OnClick({R.id.iv_back, R.id.rl_upd_password, R.id.btn_quit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_upd_password:
                startActivity(new Intent(this, UpdPasswordActivity.class));
                break;
            case R.id.btn_quit:
                App.getApp().exitApp();
                PrefUtils.RemoveValue(this, Constants.IS_LOGIN);
                PrefUtils.RemoveValue(this, Constants.CARD_NO);
                PrefUtils.RemoveValue(this, Constants.TOKEN);
                startActivity(new Intent(this, TextLoginActivity.class));
                break;
        }
    }
}
