package com.rxjy.niujingji.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.rxjy.niujingji.R;
import com.rxjy.niujingji.commons.App;
import com.rxjy.niujingji.commons.base.BaseActivity;
import com.rxjy.niujingji.mvp.contract.SetBankPwdContract;
import com.rxjy.niujingji.mvp.presenter.SetBankPwdPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetBankPwdActivity extends BaseActivity<SetBankPwdPresenter> implements SetBankPwdContract.View {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.et_set_bank_pwd_pwd)
    EditText etPwd;
    @Bind(R.id.tv_set_bank_pwd_pwd_line)
    TextView tvPwdLine;
    @Bind(R.id.et_set_bank_pwd_confirm)
    EditText etConfirm;
    @Bind(R.id.tv_set_bank_pwd_confirm_pwd_line)
    TextView tvConfirmPwdLine;

    public static final String TITLE = "密码设置";

    @Override
    public int getLayout() {
        return R.layout.activity_set_bank_pwd;
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

        EditText[] etArray = new EditText[]{etPwd, etPwd};
        TextView[] tvArray = new TextView[]{tvPwdLine, tvConfirmPwdLine};

        lineSelector(etArray, tvArray);

    }

    @Override
    protected SetBankPwdPresenter onCreatePresenter() {
        return new SetBankPwdPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.btn_set_bank_pwd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_set_bank_pwd:
                String pwd = etPwd.getText().toString().trim();
                String pwdConfirm = etConfirm.getText().toString().trim();
                if (TextUtils.isEmpty(pwd)) {
                    showToast("请输入密码");
                    return;
                }
                if (pwd.length() < 6) {
                    showToast("请输入6位密码");
                    return;
                }
                if (TextUtils.isEmpty(pwdConfirm)) {
                    showToast("请输入确认密码");
                    return;
                }
                if (!pwd.equals(pwdConfirm)) {
                    showToast("两次密码输入不一致");
                }
                mPresenter.getSetPwd(App.cardNo, pwd);
                break;
        }
    }

    @Override
    public void responseSetPwdData() {
        showToast("设置成功");
        finish();
    }

    @Override
    public void responseSetPwdDataError(String msg) {
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
