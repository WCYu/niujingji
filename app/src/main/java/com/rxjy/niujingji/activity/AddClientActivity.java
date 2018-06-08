package com.rxjy.niujingji.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.rxjy.niujingji.R;
import com.rxjy.niujingji.commons.App;
import com.rxjy.niujingji.commons.Constants;
import com.rxjy.niujingji.commons.base.BaseActivity;
import com.rxjy.niujingji.commons.utils.StringUtils;
import com.rxjy.niujingji.entity.ClientListInfo;
import com.rxjy.niujingji.entity.TcInfo;
import com.rxjy.niujingji.mvp.contract.AddClientContract;
import com.rxjy.niujingji.mvp.presenter.AddClientPresenter;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddClientActivity extends BaseActivity<AddClientPresenter> implements AddClientContract.View {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.et_add_client_client_name)
    EditText etClientName;
    @Bind(R.id.tv_add_client_client_name_lin)
    TextView tvClientNameLin;
    @Bind(R.id.et_add_client_phone_num)
    EditText etPhoneNum;
    @Bind(R.id.tv_add_client_phone_num_line)
    TextView tvPhoneNumLine;
    @Bind(R.id.et_add_client_company_name)
    EditText etCompanyName;
    @Bind(R.id.tv_add_client_company_name_line)
    TextView tvCompanyNameLine;
    @Bind(R.id.et_add_client_acreage)
    EditText etAcreage;
    @Bind(R.id.tv_add_client_acreage_line)
    TextView tvAcreageLine;
    @Bind(R.id.et_add_client_location)
    EditText etLocation;
    @Bind(R.id.tv_add_client_location_line)
    TextView tvLocationLine;
    @Bind(R.id.tv_add_client_type)
    TextView tvType;
    @Bind(R.id.et_add_client_wx)
    EditText etWx;
    @Bind(R.id.tv_add_client_wx_line)
    TextView tvWxLine;
    @Bind(R.id.rb_tc_one)
    RadioButton rbTcOne;
    @Bind(R.id.rb_tc_two)
    RadioButton rbTcTwo;
    @Bind(R.id.rb_tc_three)
    RadioButton rbTcThree;
    @Bind(R.id.rg_add_client_tc)
    RadioGroup rgTc;
    @Bind(R.id.tv_add_client_tc_content)
    TextView tvContent;

    public static final String TITLE = "新增";

    //条件选择器
    private OptionsPickerView optionsPickerView;

    private List<String> groupList;

    private List<List<String>> childList;

    private List<TcInfo.BodyBean.TC> tcList;

    //客户类型一
    private int clientTypeOne;

    //客户类型二
    private int clientTypeTwo;

    //接收套餐选择的类型
    private int tcType;

    @Override
    public int getLayout() {
        return R.layout.activity_add_client;
    }

    @Override
    public void initData() {

        initTitle();
        initLine();
        initTc();
    }

    private void initTitle() {
        tvTitle.setText(TITLE);
    }

    private void initLine() {

        EditText[] etArray = new EditText[]{etClientName, etPhoneNum, etCompanyName, etAcreage, etLocation, etWx};
        TextView[] tvArray = new TextView[]{tvClientNameLin, tvPhoneNumLine, tvCompanyNameLine, tvAcreageLine, tvLocationLine, tvWxLine};

        lineSelector(etArray, tvArray);

    }

    private void initTc() {

        etPhoneNum.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {

                } else {
                    String phone = etPhoneNum.getText().toString().trim();
                    if(phone!=null&&phone.length()>0){
                        mPresenter.getphone(phone);
                    }
                }
            }
        });

        groupList = new ArrayList<>();

        childList = new ArrayList<>();

        tcList = new ArrayList<>();

        mPresenter.getTc();

        rgTc.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_tc_one:
                        showTcType(tcList.get(0));
                        break;
                    case R.id.rb_tc_two:
                        showTcType(tcList.get(1));
                        break;
                    case R.id.rb_tc_three:
                        showTcType(tcList.get(2));
                        break;
                }
            }
        });
    }

    private void showTcType(TcInfo.BodyBean.TC tcInfo) {
        if (tcInfo.getCommission() == 0) {
            tvContent.setText(tcInfo.getMeasureMoney() + "元信息费，无提成");
        } else if (tcInfo.getMeasureMoney() == 0) {
            tvContent.setText(tcInfo.getCommission() + "%提成，无信息费");
        } else {
            tvContent.setText(tcInfo.getMeasureMoney() + "元信息费+" + tcInfo.getCommission() + "%提成");
        }
        tcType = tcInfo.getID();
    }

    @Override
    protected AddClientPresenter onCreatePresenter() {
        return new AddClientPresenter(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("客户添加");
        MobclickAgent.onResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("客户添加");
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    String clientName, phoneNum, companyName, acreage, location, wx;

    @OnClick({R.id.iv_back, R.id.tv_add_client_type, R.id.btn_add_client})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_add_client_type:
                if (optionsPickerView != null)
                    optionsPickerView.show();
                break;
            case R.id.btn_add_client:
                clientName = etClientName.getText().toString().trim();
                phoneNum = etPhoneNum.getText().toString().trim();
                companyName = etCompanyName.getText().toString().trim();
                acreage = etAcreage.getText().toString().trim();
                location = etLocation.getText().toString().trim();
                wx = etWx.getText().toString().trim();
                if (TextUtils.isEmpty(clientName)) {
                    showToast("请输入姓名");
                    return;
                }
                if (TextUtils.isEmpty(phoneNum)) {
                    showToast("请输入手机号");
                    return;
                }
                if (!StringUtils.isMobileNO(phoneNum)) {
                    showToast("请输入正确的手机号");
                    return;
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("提示");
                builder.setMessage("姓名只可填写一次，不可修改,请核实信息");
                builder.setCancelable(false);
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mPresenter.getphone(phoneNum);
                    }
                });
                builder.setNegativeButton("取消", null);
                builder.show();
                break;
        }
    }

    @Override
    public void responseTc(List<TcInfo.BodyBean.TC> dataList) {
        tcList.clear();
        tcList.addAll(dataList);
    }

    @Override
    public void responseClientType(final List<TcInfo.BodyBean.ClientType> dataList) {
        for (TcInfo.BodyBean.ClientType typeInfo : dataList) {
            groupList.add(typeInfo.getMingCheng());
            List<String> child = new ArrayList<>();
            for (TcInfo.BodyBean.ClientType.ClientChildType childType : typeInfo.getZiji()) {
                child.add(childType.getMingCheng());
            }
            ;
            childList.add(child);
        }
        optionsPickerView = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tvType.setText(dataList.get(options1).getMingCheng() + " —  " + dataList.get(options1).getZiji().get(options2).getMingCheng());
                clientTypeOne = dataList.get(options1).getID();
                clientTypeTwo = dataList.get(options1).getZiji().get(options2).getID();
            }
        }).build();
        optionsPickerView.setPicker(groupList, childList);
    }

    @Override
    public void responseTcError(String msg) {
        showToast(msg);
    }

    @Override
    public void responseClientData(ClientListInfo.ClientInfo data) {
        showToast("上传成功");
        Intent intent = new Intent();
        intent.putExtra(Constants.BACK_TO_CLIENT_LIST, data);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void responseClientDataError(String msg) {
        showToast(msg);
    }

    @Override
    public void responsephone(String msg) {
        mPresenter.subClientInfo(clientName, phoneNum, wx, acreage, clientTypeOne + "", clientTypeTwo + "", companyName, location, App.cardNo, tcType + "");
    }

    @Override
    public void responsephoneError(String msg) {
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
