package com.rxjy.niujingji.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.rxjy.niujingji.R;
import com.rxjy.niujingji.commons.App;
import com.rxjy.niujingji.commons.Constants;
import com.rxjy.niujingji.commons.base.BaseActivity;
import com.rxjy.niujingji.entity.ClientInfo;
import com.rxjy.niujingji.entity.TcInfo;
import com.rxjy.niujingji.mvp.contract.UpdClientContract;
import com.rxjy.niujingji.mvp.presenter.UpdClientPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdClientActivity extends BaseActivity<UpdClientPresenter> implements UpdClientContract.View {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.et_upd_client_client_name)
    EditText etClientName;
    @Bind(R.id.tv_upd_client_client_name_lin)
    TextView tvClientNameLin;
    @Bind(R.id.et_upd_client_phone_num)
    EditText etPhoneNum;
    @Bind(R.id.tv_upd_client_phone_num_line)
    TextView tvPhoneNumLine;
    @Bind(R.id.et_upd_client_company_name)
    EditText etCompanyName;
    @Bind(R.id.tv_upd_client_company_name_line)
    TextView tvCompanyNameLine;
    @Bind(R.id.et_upd_client_acreage)
    EditText etAcreage;
    @Bind(R.id.tv_upd_client_acreage_line)
    TextView tvAcreageLine;
    @Bind(R.id.et_upd_client_location)
    EditText etLocation;
    @Bind(R.id.tv_upd_client_location_line)
    TextView tvLocationLine;
    @Bind(R.id.tv_upd_client_type)
    TextView tvType;
    @Bind(R.id.et_upd_client_wx)
    EditText etWx;
    @Bind(R.id.tv_upd_client_wx_line)
    TextView tvWxLine;
    @Bind(R.id.btn_upd_client)
    Button btnUpdClient;

    public static final String TITLE = "修改客户信息";

    private String clientName;

    private String companyName;

    private double acreage;

    private String location;

    private int typeOne;

    private int typeTwo;

    private String WX;

    private String type;

    //条件选择器
    private OptionsPickerView optionsPickerView;

    private List<String> groupList;

    private List<List<String>> childList;

    private int clientID;

    private boolean isChanged;

    @Override
    public int getLayout() {
        return R.layout.activity_upd_client;
    }

    @Override
    public void initData() {
        initIntent();
        initTitle();
        initLine();
        initClientType();
        initClientData();
    }

    private void initIntent() {
        isChanged = getIntent().getBooleanExtra(Constants.ACTION_TO_UPD_CLIENT_IS_CAN_CHANGED, false);
        clientID = getIntent().getIntExtra(Constants.ACTION_TO_UPD_CLIENT_CLIENT_ID, 0);

        if (!isChanged) {
            etClientName.setFocusable(false);
            etCompanyName.setFocusable(false);
            etPhoneNum.setFocusable(false);
            etAcreage.setFocusable(false);
            etLocation.setFocusable(false);
            etWx.setFocusable(false);
            tvType.setEnabled(false);
            btnUpdClient.setVisibility(View.INVISIBLE);
        }

    }

    private void initTitle() {
        tvTitle.setText(TITLE);
    }

    private void initLine() {

        EditText[] etArray = new EditText[]{etClientName, etCompanyName, etAcreage, etLocation, etWx};
        TextView[] tvArray = new TextView[]{tvClientNameLin, tvCompanyNameLine, tvAcreageLine, tvLocationLine, tvWxLine};

        lineSelector(etArray, tvArray);

    }

    private void initClientType() {

        groupList = new ArrayList<>();

        childList = new ArrayList<>();

        mPresenter.getTc();

    }

    private void initClientData() {

        //获取客户信息
        mPresenter.getClientInfo(clientID);

    }

    @Override
    protected UpdClientPresenter onCreatePresenter() {
        return new UpdClientPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.tv_upd_client_type, R.id.btn_upd_client})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_upd_client_type:
                optionsPickerView.show();
                break;
            case R.id.btn_upd_client:
                clientName = etClientName.getText().toString().trim();
                companyName = etCompanyName.getText().toString().trim();
                acreage = Double.parseDouble(etAcreage.getText().toString().trim().equals("") ? "0" : etAcreage.getText().toString().trim());
                location = etLocation.getText().toString().trim();
                WX = etWx.getText().toString().trim();
                if (TextUtils.isEmpty(clientName)) {
                    showToast("客户名称不能为空");
                    return;
                }
                mPresenter.updClientInfo(clientName, WX, acreage + "", typeOne + "", typeTwo + "", companyName, location, App.cardNo, type, "0", clientID);
//                AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                builder.setTitle("提示");
//                builder.setMessage("确认修改客户信息");
//                builder.setCancelable(false);
//                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        mPresenter.updClientInfo(clientName, WX, acreage + "", typeOne + "", typeTwo + "", companyName, location, App.cardNo, type, "0", clientID);
//                    }
//                });
//                builder.setNegativeButton("取消", null);
//                builder.show();
                break;
        }
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
                typeOne = dataList.get(options1).getID();
                typeTwo = dataList.get(options1).getZiji().get(options2).getID();
            }
        }).build();
        optionsPickerView.setPicker(groupList, childList);
    }

    @Override
    public void responseTcError(String msg) {
        showToast(msg);
    }

    @Override
    public void responseUpdClientData() {
        showToast("修改成功");
        finish();
    }

    @Override
    public void responseUpdClientDataError(String msg) {
        showToast(msg);
    }

    @Override
    public void responseClientData(ClientInfo.Client data) {

        clientName = data.getXingMing();

        companyName = data.getGongSiMingCheng();

        acreage = data.getMianJi();

        location = data.getLiangFangDiZhi();

        typeOne = data.getLeiXingYi();

        typeTwo = data.getLeiXingEr();

        WX = data.getWeiXin();

        type = data.getZhuangTai() + "";

        String typeOneName = data.getLeiXingYiName() == null ? "" : data.getLeiXingYiName();

        String typeTwoName = data.getLeiXingErName() == null ? "" : data.getLeiXingErName();

        etClientName.setText(clientName);
        etPhoneNum.setText(data.getShouJiHaoYi());
        etCompanyName.setText(companyName);
        etAcreage.setText(acreage + "");
        etLocation.setText(location);
        if (data.getLeiXingYiName() == null && data.getLeiXingErName() == null) {
            tvType.setText("请选择");
        } else {
            tvType.setText(typeOneName + " — " + typeTwoName);
        }
        etWx.setText(WX);
        //手机号不能修改
        etPhoneNum.setFocusable(false);
    }

    @Override
    public void responseClientDataError(String msg) {
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
