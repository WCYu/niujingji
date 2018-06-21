package com.rxjy.niujingji.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.rxjy.niujingji.R;
import com.rxjy.niujingji.commons.base.BaseActivity;
import com.rxjy.niujingji.commons.utils.StringUtils;
import com.rxjy.niujingji.mvp.contract.ForgetPwdContract;
import com.rxjy.niujingji.mvp.presenter.ForgetPwdPresenter;
import com.rxjy.niujingji.widget.DownTimerButton;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgetPwdActivity extends BaseActivity<ForgetPwdPresenter> implements ForgetPwdContract.View {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.et_phone_num)
    EditText etPhoneNum;
    @Bind(R.id.tv_old_password_line)
    TextView tvPhoneLine;
    @Bind(R.id.btn_verification_code)
    DownTimerButton btnVerificationCode;
    @Bind(R.id.et_verification_code)
    EditText etVerificationCode;
    @Bind(R.id.tv_verification_code_line)
    TextView tvVerificationCodeLine;
    @Bind(R.id.et_new_password)
    EditText etNewPassword;
    @Bind(R.id.tv_new_password_line)
    TextView tvNewPasswordLine;
    @Bind(R.id.et_confirm_password)
    EditText etConfirmPassword;
    @Bind(R.id.tv_confirm_password_line)
    TextView tvConfirmPasswordLine;

    public static final String TITLE = "忘记密码";

    @Override
    public int getLayout() {
        return R.layout.activity_forget_pwd;
    }

    @Override
    public void initData() {
        initTitle();
        initLine();
        //设置倒计时为60秒
        btnVerificationCode.setDuration(60000);
    }

    private void initTitle() {
        tvTitle.setText(TITLE);
    }

    private void initLine() {
        etPhoneNum.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    //此处为得到焦点时
                    tvPhoneLine.setEnabled(true);
                } else {
                    //此处为失去焦点时
                    tvPhoneLine.setEnabled(false);
                }
            }
        });
        etVerificationCode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    //此处为得到焦点时
                    tvVerificationCodeLine.setEnabled(true);
                } else {
                    //此处为失去焦点时
                    tvVerificationCodeLine.setEnabled(false);
                }
            }
        });
        etNewPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    //此处为得到焦点时
                    tvNewPasswordLine.setEnabled(true);
                } else {
                    //此处为失去焦点时
                    tvNewPasswordLine.setEnabled(false);
                }
            }
        });
        etConfirmPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    //此处为得到焦点时
                    tvConfirmPasswordLine.setEnabled(true);
                } else {
                    //此处为失去焦点时
                    tvConfirmPasswordLine.setEnabled(false);
                }
            }
        });
    }

    @Override
    protected ForgetPwdPresenter onCreatePresenter() {
        return new ForgetPwdPresenter(this);
    }



    @OnClick({R.id.iv_back, R.id.btn_verification_code, R.id.btn_forget_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_verification_code:
                String phone = etPhoneNum.getText().toString().trim();
                if (TextUtils.isEmpty(phone)) {
                    showToast("请输入手机号");
                    return;
                }
//                if (!StringUtils.isMobileNO(phone)) {
//                    showToast("请输入正确的手机号");
//                    return;
//                }
                mPresenter.getVCode(phone);
                break;
            case R.id.btn_forget_password:
                String phoneNum = etPhoneNum.getText().toString().trim();
                String verificationCode = etVerificationCode.getText().toString().trim();
                String newPassword = etNewPassword.getText().toString().trim();
                String confirmPassword = etConfirmPassword.getText().toString().trim();
                if (TextUtils.isEmpty(phoneNum)) {
                    showToast("请输入手机号");
                    return;
                }
//                if (!StringUtils.isMobileNO(phoneNum)) {
//                    showToast("请输入正确的手机号");
//                    return;
//                }
                if (TextUtils.isEmpty(verificationCode)) {
                    showToast("请输入验证码");
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
                //密码最少6位
                if (newPassword.length() < 6) {
                    showToast("密码最少6位");
                    return;
                }

                if (!newPassword.equals(confirmPassword)) {
                    showToast("两次密码输入不一致");
                    return;
                }
                //验证码6位
                if (verificationCode.length() != 6) {
                    showToast("验证码不正确");
                    return;
                }

                //密码数相同
                if (StringUtils.isSameChars(newPassword)) {
                    showToast("密码过于简单");
                    return;
                }

                //密码为连续数
                if (newPassword.equals("123456") || newPassword.equals("234567") || newPassword.equals("345678") || newPassword.equals("456789")) {
                    showToast("密码过于简单");
                    return;
                }

                //修改密码
                mPresenter.updatePassword(phoneNum, newPassword, verificationCode);
                break;
        }
    }

    @Override
    public void responseUpdatePassword() {
        showToast("修改成功");
        finish();
    }

    @Override
    public void responseUpdatePasswordError(String msg) {
        showToast(msg);
    }

    @Override
    public void responseVCodeError(String msg) {
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
