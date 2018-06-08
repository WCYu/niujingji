package com.rxjy.niujingji.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.rxjy.niujingji.R;
import com.rxjy.niujingji.commons.App;
import com.rxjy.niujingji.commons.Constants;
import com.rxjy.niujingji.commons.base.BaseActivity;
import com.rxjy.niujingji.mvp.contract.WithdrawDepositContract;
import com.rxjy.niujingji.mvp.presenter.WithDrawDepositPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WithdrawDepositActivity extends BaseActivity<WithDrawDepositPresenter> implements WithdrawDepositContract.View {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_to_bank)
    TextView tvToBank;
    @Bind(R.id.et_withdraw_deposit_price)
    EditText etWithdrawDepositPrice;
    @Bind(R.id.tv_withdraw_deposit_price_line)
    TextView tvWithdrawDepositPriceLine;
    @Bind(R.id.et_withdraw_deposit_pwd)
    EditText etWithdrawDepositPwd;
    @Bind(R.id.tv_withdraw_deposit_pwd_line)
    TextView tvWithdrawDepositPwdLine;

    public static final String TITLE = "提现";

    private double balancePrice;

    @Override
    public int getLayout() {
        return R.layout.activity_withdraw_deposit;
    }

    @Override
    public void initData() {
        initIntent();
        initTitle();
        initLine();
        initBankData();
    }

    private void initIntent() {
        balancePrice = getIntent().getDoubleExtra(Constants.ACTION_TO_WITHDRAW_DEPOSIT_INFO, 0);
    }

    private void initTitle() {
        tvTitle.setText(TITLE);
    }

    private void initBankData() {
        String bankCardNum = App.baseInfo.getBankCard().replace(" ", "");
        if (bankCardNum.length() > 4)
            tvToBank.setText(App.baseInfo.getBankName().trim() + "(" + bankCardNum.substring(bankCardNum.length() - 4, bankCardNum.length()) + ")");
    }

    private void initLine() {

        EditText[] etArray = new EditText[]{etWithdrawDepositPrice, etWithdrawDepositPwd};
        TextView[] tvArray = new TextView[]{tvWithdrawDepositPriceLine, tvWithdrawDepositPwdLine};

        lineSelector(etArray, tvArray);

    }

    @Override
    protected WithDrawDepositPresenter onCreatePresenter() {
        return new WithDrawDepositPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.btn_withdraw_deposit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_withdraw_deposit:
                final String price = etWithdrawDepositPrice.getText().toString().trim();
                final String pwd = etWithdrawDepositPwd.getText().toString().trim();
                if (price.equals("")) {
                    showToast("提现金额不能为空");
                    return;
                }
                if (pwd.equals("")) {
                    showToast("请输入密码");
                    return;
                }
                if (Double.parseDouble(price) > balancePrice) {
                    showToast("提现金额不能大于余额");
                    return;
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("提示");
                builder.setMessage("确认提现");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mPresenter.getWithdrawDeposit(App.cardNo, Double.parseDouble(price), App.baseInfo.getBankCard(), App.baseInfo.getBankName(), App.baseInfo.getBankUserName(), pwd);
                    }
                });
                builder.setNegativeButton("取消", null);
                builder.show();
                break;
        }
    }

    @Override
    public void responseWithdrawDepositData() {

    }

    @Override
    public void responseWithdrawDepositDataError(String msg) {
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
