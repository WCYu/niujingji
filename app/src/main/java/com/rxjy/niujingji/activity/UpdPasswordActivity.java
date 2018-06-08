package com.rxjy.niujingji.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.rxjy.niujingji.R;
import com.rxjy.niujingji.commons.App;
import com.rxjy.niujingji.commons.Constants;
import com.rxjy.niujingji.commons.base.BaseActivity;
import com.rxjy.niujingji.commons.utils.PrefUtils;
import com.rxjy.niujingji.mvp.contract.UpdPasswordContract;
import com.rxjy.niujingji.mvp.presenter.UpdPasswordPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdPasswordActivity extends BaseActivity<UpdPasswordPresenter> implements UpdPasswordContract.View {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.et_old_password)
    EditText etOldPassword;
    @Bind(R.id.tv_old_password_line)
    TextView tvOldPasswordLine;
    @Bind(R.id.et_new_password)
    EditText etNewPassword;
    @Bind(R.id.tv_new_password_line)
    TextView tvNewPasswordLine;
    @Bind(R.id.et_confirm_password)
    EditText etConfirmPassword;
    @Bind(R.id.tv_confirm_password_line)
    TextView tvConfirmPasswordLine;

    public static final String TITLE = "修改密码";

    @Override
    public int getLayout() {
        return R.layout.activity_upd_password;
    }

    @Override
    public void initData() {
        initTitle();
        initLine();
    }

    private void initTitle() {
        tvTitle.setText(TITLE);
    }

    private void initLine() {

        EditText[] etArray = {etOldPassword, etNewPassword, etConfirmPassword};
        TextView[] tvArray = {tvOldPasswordLine, tvNewPasswordLine, tvConfirmPasswordLine};

        lineSelector(etArray, tvArray);

    }

    @Override
    protected UpdPasswordPresenter onCreatePresenter() {
        return new UpdPasswordPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_submit:
                String oldPassword = etOldPassword.getText().toString().trim();
                String newPassword = etNewPassword.getText().toString().trim();
                String confirmPassword = etConfirmPassword.getText().toString().trim();
                if (TextUtils.isEmpty(oldPassword)) {
                    showToast("请输入原始密码");
                    return;
                }
                if (TextUtils.isEmpty(newPassword)) {
                    showToast("请输入新密码");
                    return;
                }
                if (TextUtils.isEmpty(confirmPassword)) {
                    showToast("请输入确认密码");
                    return;
                }
                if (!newPassword.equals(confirmPassword)) {
                    showToast("两次密码输入不一致");
                    return;
                }
                if (oldPassword.equals(newPassword)) {
                    showToast("新密码不能与原始密码相同");
                    return;
                }
                //修改密码
                mPresenter.updatePwd(App.cardNo, oldPassword, newPassword, App.token);
                break;
        }
    }

    @Override
    public void responsePwd() {
        showToast("修改成功");
        PrefUtils.RemoveValue(this, Constants.IS_LOGIN);
        PrefUtils.RemoveValue(this, Constants.CARD_NO);
        PrefUtils.RemoveValue(this, Constants.TOKEN);
        App.getApp().exitApp();
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void responsePwdError(String msg) {
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
