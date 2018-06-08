package com.rxjy.niujingji.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;

import com.rxjy.niujingji.R;
import com.rxjy.niujingji.commons.App;
import com.rxjy.niujingji.commons.Constants;
import com.rxjy.niujingji.commons.base.BaseActivity;
import com.rxjy.niujingji.commons.utils.NetUtil;
import com.rxjy.niujingji.commons.utils.PrefUtils;
import com.rxjy.niujingji.entity.LoginInfo;
import com.rxjy.niujingji.entity.UserInfo;
import com.rxjy.niujingji.mvp.contract.LogoContract;
import com.rxjy.niujingji.mvp.presenter.LogoPresenter;

import java.util.Timer;
import java.util.TimerTask;

public class LogoActivity extends BaseActivity<LogoPresenter> implements LogoContract.View {

    @Override
    public int getLayout() {
        return R.layout.activity_logo;
    }

    @Override
    public void initData() {
        // 判断是否是第一次开启应用
        boolean isFirstOpen = PrefUtils.getBooleanValue(this, Constants.IS_FIRST_LOGIN);
        // 如果是第一次启动，则先进入功能引导页
        if (!isFirstOpen) {
            Intent intent = new Intent(this, WelcomeActivity.class);
            startActivity(intent);
            finish();
            return;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        Timer mTimer = new Timer();

        Boolean isLogin = PrefUtils.getBooleanValue(this, Constants.IS_LOGIN);
        if (!NetUtil.isConnected(this)) {
            showDialogTip(1);
        } else {
            if (!isLogin) {
                mTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(LogoActivity.this, TextLoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, 2000);
            } else {

                mTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        String cardNo = PrefUtils.getValue(LogoActivity.this, Constants.CARD_NO);
                        String token = PrefUtils.getValue(LogoActivity.this, Constants.TOKEN);
//
                        mPresenter.getLoginUserInfo(cardNo,token);
                       // mPresenter.AppLogin(phone,password,"","2");
                    }
                }, 2000);
            }

        }
    }

    private void showDialogTip(int type) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        switch (type) {
            case 1:
                builder.setTitle("提示");
                builder.setMessage("没有网络，请链接网络");
                builder.setCancelable(false);
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent wifiSettingsIntent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                        startActivity(wifiSettingsIntent);
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.show();
                break;
            case 2:
                builder.setTitle("提示");
                builder.setMessage("网络超时，请检查网络");
                builder.setCancelable(false);
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent wifiSettingsIntent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                        startActivity(wifiSettingsIntent);
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.show();
                break;
        }
    }

    @Override
    protected LogoPresenter onCreatePresenter() {
        return new LogoPresenter(this);
    }


    @Override
    public void responseLogin(UserInfo.User data) {
        App.cardNo = PrefUtils.getValue(this, Constants.CARD_NO);
        App.token = PrefUtils.getValue(this, Constants.TOKEN);
        App.baseInfo = data.getBaseinfo();
        App.personnelInfo = data.getPersonnelInfo();
        startActivity(new Intent(this, NjjActivity.class));
        finish();
    }

    @Override
    public void responseLoginError(String msg) {
        showToast(msg);
        showDialogTip(2);
    }

    @Override
    public void reLogin(String msg) {
        showToast(msg);
        PrefUtils.RemoveValue(this, Constants.IS_LOGIN);
        PrefUtils.RemoveValue(this, Constants.CARD_NO);
        PrefUtils.RemoveValue(this, Constants.TOKEN);
        startActivity(new Intent(this, TextLoginActivity.class));
        finish();
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void hideDialog() {

    }

    @Override
    public void responseLogin(LoginInfo.BodyBean loginInfo) {
        App.cardNo = loginInfo.getCardNo();
        App.token = loginInfo.getToken();
        PrefUtils.putBooleanValue(this, Constants.IS_LOGIN, true);
        mPresenter.getLoginUserInfo(App.cardNo, App.token);
    }
}
