package com.rxjy.niujingji.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.rxjy.niujingji.R;
import com.rxjy.niujingji.commons.App;
import com.rxjy.niujingji.commons.Constants;
import com.rxjy.niujingji.commons.base.BaseActivity;
import com.rxjy.niujingji.commons.utils.PrefUtils;
import com.rxjy.niujingji.entity.BankListInfo;
import com.rxjy.niujingji.mvp.contract.AddBankCardContract;
import com.rxjy.niujingji.mvp.presenter.AddBankCardPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.rxjy.niujingji.commons.App.cardNo;

public class UpdBankCardActivity extends BaseActivity<AddBankCardPresenter> implements AddBankCardContract.View {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.et_upd_bank_card_name)
    EditText etName;
    @Bind(R.id.tv_upd_bank_card_name_line)
    TextView tvNameLine;
    @Bind(R.id.et_upd_bank_card_num)
    EditText etCardNum;
    @Bind(R.id.tv_upd_bank_card_num_line)
    TextView tvNumLine;
    @Bind(R.id.tv_choice_bank)
    TextView tvChoiceBank;
    @Bind(R.id.lin_upd_bank_card_tip)
    LinearLayout linTip;

    public static final String TITLE = "修改银行卡";

    private List<String> bankList;

    private OptionsPickerView bankPicker;

    private String bankName = "";
    private String name;
    private String cardNum;

    @Override
    public int getLayout() {
        return R.layout.activity_upd_bank_card;
    }

    @Override
    public void initData() {
        initTitle();
        initBankData();
        initLine();
    }

    private void initTitle() {
        tvTitle.setText(TITLE);
    }

    private void initBankData() {

        name = App.baseInfo.getBankUserName();
        cardNum = App.baseInfo.getBankCard();
        bankName = App.baseInfo.getBankName();

        //姓名不可修改
        etName.setEnabled(false);
        etName.setText(name);
        etCardNum.setText(cardNum);
        tvChoiceBank.setText(bankName);

        bankList = new ArrayList<>();

        bankPicker = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                bankName = bankList.get(options1);
                tvChoiceBank.setText(bankName);
            }
        }).build();

        //获取银行信息
        mPresenter.getBankListInfo();

    }

    private void initLine() {

        EditText[] etArray = new EditText[]{etName, etCardNum};
        TextView[] tvArray = new TextView[]{tvNameLine, tvNumLine};

        lineSelector(etArray, tvArray);

    }

    @Override
    protected AddBankCardPresenter onCreatePresenter() {
        return new AddBankCardPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.iv_upd_bank_card_tip, R.id.tv_choice_bank, R.id.btn_upd_bank_card})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_upd_bank_card_tip:
                if (linTip.isShown()) {
                    linTip.setVisibility(View.GONE);
                } else {
                    linTip.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.tv_choice_bank:
                bankPicker.show();
                break;
            case R.id.btn_upd_bank_card:
                name = etName.getText().toString().trim();
                cardNum = etCardNum.getText().toString().trim();
                if (name.equals("")) {
                    showToast("请填写持卡人姓名");
                    return;
                }
                if (cardNum.equals("")) {
                    showToast("请填写银行卡卡号");
                    return;
                }
                if (bankName.equals("")) {
                    showToast("请选择开户行");
                    return;
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("提示");
                builder.setMessage("是否修改银行卡");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mPresenter.subAddBankCard(App.token, cardNo, cardNum, bankName, name);
                    }
                });
                builder.setNegativeButton("取消", null);
                builder.show();
                break;
        }
    }

    @Override
    public void responseAddBankCard() {
        showToast("修改成功");
        App.baseInfo.setBankUserName(name);
        App.baseInfo.setBankCard(cardNum);
        App.baseInfo.setBankName(bankName);
        finish();
    }

    @Override
    public void responseAddBankCardError(String msg) {
        showToast(msg);
    }

    @Override
    public void responseBankListData(List<BankListInfo.BankInfo> dataList) {
        for (BankListInfo.BankInfo info : dataList) {
            bankList.add(info.getBank_name());
        }
        bankPicker.setPicker(bankList);
    }

    @Override
    public void responseBankListDataError(String msg) {
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
