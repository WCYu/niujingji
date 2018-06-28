package com.rxjy.niujingji.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.rxjy.niujingji.R;
import com.rxjy.niujingji.commons.App;
import com.rxjy.niujingji.commons.Constants;
import com.rxjy.niujingji.commons.base.BaseActivity;
import com.rxjy.niujingji.commons.utils.PrefUtils;
import com.rxjy.niujingji.entity.NewBankListInfo;
import com.rxjy.niujingji.mvp.contract.AddBankCardContract;
import com.rxjy.niujingji.mvp.presenter.AddBankCardPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddBankCardActivity extends BaseActivity<AddBankCardPresenter> implements AddBankCardContract.View {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.et_add_bank_card_name)
    EditText etCardName;
    @Bind(R.id.tv_add_bank_card_name_line)
    TextView tvCardNameLine;
    @Bind(R.id.et_add_bank_card_num)
    EditText etCardNum;
    @Bind(R.id.tv_add_bank_card_num_line)
    TextView tvCardNumLine;
    @Bind(R.id.tv_choice_bank)
    TextView tvChoiceBank;
    @Bind(R.id.btn_add_bank_card)
    Button btnWpsAddBankCard;
    @Bind(R.id.lin_add_bank_card_tip)
    LinearLayout linTip;

    public static final String TITLE = "添加银行卡";

    private List<String> bankList;

    private OptionsPickerView bankPicker;

    private String bankName = "";
    private String bankId = "";
    private String name;
    private String cardNum;

    @Override
    public int getLayout() {
        return R.layout.activity_add_bank_card;
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

        bankList = new ArrayList<>();

        bankPicker = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                bankName = bankList.get(options1);
                switch (bankName){
                    case "中国银行":
                        bankId = "1";
                        break;
                    case "中国工商银行":
                        bankId = "2";
                        break;
                    case "中国建设银行":
                        bankId = "3";
                        break;
                    case "农业银行":
                        bankId = "4";
                        break;
                    case "招商银行":
                        bankId = "5";
                        break;
                    case "浦发银行":
                        bankId = "6";
                        break;
                        default:
                            break;
                }
                tvChoiceBank.setText(bankName);
            }
        }).build();

        //获取银行信息
        mPresenter.getBankListInfo();

    }

    private void initLine() {

        EditText[] etArray = new EditText[]{etCardName, etCardNum};
        TextView[] tvArray = new TextView[]{tvCardNameLine, tvCardNumLine};

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

    @OnClick({R.id.iv_back, R.id.iv_add_bank_card_tip, R.id.tv_choice_bank, R.id.btn_add_bank_card})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_add_bank_card_tip:
                if (linTip.isShown()) {
                    linTip.setVisibility(View.GONE);
                } else {
                    linTip.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.tv_choice_bank:
                bankPicker.show();
                break;
            case R.id.btn_add_bank_card:
                name = etCardName.getText().toString().trim();
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
                builder.setMessage("是否添加银行卡");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mPresenter.subAddBankCard(App.cardNo,bankId,name,cardNum,"3",bankName);
                    }
                });
                builder.setNegativeButton("取消", null);
                builder.show();
                break;
        }
    }

    @Override
    public void responseAddBankCard() {
        showToast("添加成功");
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
    public void responseBankListData(List<NewBankListInfo.BodyBean.TableBean> dataList) {
        for (NewBankListInfo.BodyBean.TableBean info : dataList) {
            bankList.add(info.getBankName());
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
