package com.rxjy.niujingji.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.rxjy.niujingji.R;
import com.rxjy.niujingji.commons.App;
import com.rxjy.niujingji.commons.Constants;
import com.rxjy.niujingji.commons.base.BaseActivity;
import com.rxjy.niujingji.entity.IsHaveBankCardInfo;
import com.rxjy.niujingji.mvp.contract.BalanceContract;
import com.rxjy.niujingji.mvp.presenter.BalancePresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BalanceActivity extends BaseActivity<BalancePresenter> implements BalanceContract.View {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_balance_price)
    TextView tvBalancePrice;

    public static final String TITLE = "余额";

    private double balance;

    @Override
    public int getLayout() {
        return R.layout.activity_balance;
    }

    @Override
    public void initData() {
        initIntent();
        initTitle();
        initBalanceData();
    }

    private void initIntent() {
        balance = getIntent().getDoubleExtra(Constants.ACTION_TO_BALANCE_BALANCE_INFO, 0);
    }

    private void initTitle() {
        tvTitle.setText(TITLE);
    }

    private void initBalanceData() {

        tvBalancePrice.setText("¥ " + balance);

    }

    @Override
    protected BalancePresenter onCreatePresenter() {
        return new BalancePresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.btn_balance})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_balance:
                if (App.baseInfo.getBankCard() == null) {
                    startActivity(new Intent(this, AddBankCardActivity.class));
                    return;
                }
                mPresenter.getIsHavePwd(App.cardNo);
                break;
        }
    }

    @Override
    public void responseIsHavePwdData(IsHaveBankCardInfo.IsHaveBankCard data) {
        switch (data.getStatus()) {
            case 0:
                Intent intent = new Intent(this, WithdrawDepositActivity.class);
                intent.putExtra(Constants.ACTION_TO_WITHDRAW_DEPOSIT_INFO, balance);
                startActivity(intent);
                break;
            case 1:
                startActivity(new Intent(this, SetBankPwdActivity.class));
                break;
        }
    }

    @Override
    public void responseIsHavePwdDataError(String msg) {
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
