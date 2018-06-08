package com.rxjy.niujingji.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.rxjy.niujingji.R;
import com.rxjy.niujingji.commons.Constants;
import com.rxjy.niujingji.commons.base.BaseActivity;
import com.rxjy.niujingji.entity.ClientInfo;
import com.rxjy.niujingji.entity.TcInfo;
import com.rxjy.niujingji.mvp.contract.UpdClientContract;
import com.rxjy.niujingji.mvp.presenter.UpdClientPresenter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by asus on 2018/6/7.
 */

public class CustomerActivity extends BaseActivity<UpdClientPresenter> implements UpdClientContract.View {
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_add_Project_typetext)
    TextView tvAddProjectTypetext;
    @Bind(R.id.tv_add_Project)
    TextView tvAddProject;
    @Bind(R.id.tv_project_attributestext)
    TextView tvProjectAttributestext;
    @Bind(R.id.tv_project_attributees)
    TextView tvProjectAttributees;
    @Bind(R.id.tv_add_client_wx_line)
    TextView tvAddClientWxLine;
    @Bind(R.id.tv_add_customer_text)
    TextView tvAddCustomerText;
    @Bind(R.id.tv_add_customer)
    EditText tvAddCustomer;
    @Bind(R.id.tv_add_identity_text)
    TextView tvAddIdentityText;
    @Bind(R.id.tv_add_identity)
    TextView tvAddIdentity;
    @Bind(R.id.tv_add_measure_text)
    TextView tvAddMeasureText;
    @Bind(R.id.tv_add_measure)
    EditText tvAddMeasure;
    @Bind(R.id.tv_add_budget_text)
    TextView tvAddBudgetText;
    @Bind(R.id.tv_add_budget)
    EditText tvAddBudget;
    @Bind(R.id.tv_add_resources_text)
    TextView tvAddResourcesText;
    @Bind(R.id.tv_add_resources)
    TextView tvAddResources;
    @Bind(R.id.tv_add_House_text)
    TextView tvAddHouseText;
    @Bind(R.id.tv_add_House)
    TextView tvAddHouse;
    @Bind(R.id.tv_add_deal_text)
    TextView tvAddDealText;
    @Bind(R.id.tv_add_deal)
    TextView tvAddDeal;
    @Bind(R.id.tv_add_make_text)
    TextView tvAddMakeText;
    @Bind(R.id.tv_add_make)
    EditText tvAddMake;
    @Bind(R.id.tv_add_client_wx_text)
    TextView tvAddClientWxText;
    @Bind(R.id.tv_add_demand_text)
    TextView tvAddDemandText;
    @Bind(R.id.tv_add_demand)
    TextView tvAddDemand;
    @Bind(R.id.tv_add_Ginseng_text)
    TextView tvAddGinsengText;
    @Bind(R.id.tv_add_Ginseng)
    EditText tvAddGinseng;
    @Bind(R.id.tv_add_Style_text)
    TextView tvAddStyleText;
    @Bind(R.id.tv_add_Style)
    EditText tvAddStyle;
    @Bind(R.id.tv_add_Renovation_text)
    TextView tvAddRenovationText;
    @Bind(R.id.tv_add_Renovation)
    EditText tvAddRenovation;
    @Bind(R.id.tv_add_Company_text)
    TextView tvAddCompanyText;
    @Bind(R.id.tv_add_Company)
    EditText tvAddCompany;
    @Bind(R.id.tv_add_Address_text)
    TextView tvAddAddressText;
    @Bind(R.id.tv_add_Address)
    EditText tvAddAddress;
    @Bind(R.id.tv_add_Nature_text)
    TextView tvAddNatureText;
    @Bind(R.id.tv_add_Nature)
    TextView tvAddNature;
    @Bind(R.id.tv_add_Scale_text)
    TextView tvAddScaleText;
    @Bind(R.id.tv_add_Scale)
    TextView tvAddScale;
    @Bind(R.id.tv_add_Setup_text)
    TextView tvAddSetupText;
    @Bind(R.id.tv_add_Setup)
    EditText tvAddSetup;
    @Bind(R.id.tv_add_Check_in_text)
    TextView tvAddCheckInText;
    @Bind(R.id.tv_add_text)
    TextView tvAddText;
    @Bind(R.id.tv_add_Funds_text)
    TextView tvAddFundsText;
    @Bind(R.id.tv_add_Funds)
    EditText tvAddFunds;
    @Bind(R.id.tv_add_Famous_text)
    TextView tvAddFamousText;
    @Bind(R.id.tv_add_Famous)
    TextView tvAddFamous;
    @Bind(R.id.tv_add_workan_text)
    TextView tvAddWorkanText;
    @Bind(R.id.tv_add_workan)
    EditText tvAddWorkan;
    @Bind(R.id.tv_add_Remarks_text)
    TextView tvAddRemarksText;
    @Bind(R.id.tv_add_Remarks)
    EditText tvAddRemarks;
    @Bind(R.id.btn_upd_client)
    Button btnUpdClient;
    @Bind(R.id.tv_Rent_free)
    TextView tvRentFree;
    @Bind(R.id.tv_add_money_text)
    TextView tvAddMoneyText;
    @Bind(R.id.tv_add_money)
    TextView tvAddMoney;
    @Bind(R.id.tv_add_Price_text)
    TextView tvAddPriceText;
    @Bind(R.id.tv_add_Price)
    EditText tvAddPrice;
    @Bind(R.id.tv_add_Tendering_text)
    TextView tvAddTenderingText;
    @Bind(R.id.tv_add_Tendering)
    TextView tvAddTendering;
    @Bind(R.id.tv_add_chain_text)
    TextView tvAddChainText;
    @Bind(R.id.tv_add_chain)
    TextView tvAddChain;
    @Bind(R.id.tv_add_Shop_text)
    TextView tvAddShopText;
    @Bind(R.id.tv_add_Shop)
    EditText tvAddShop;
    @Bind(R.id.gongsi)
    RelativeLayout gongsi;
    @Bind(R.id.mianzu)
    RelativeLayout mianzu;
    @Bind(R.id.dianzi)
    RelativeLayout dianzi;
    @Bind(R.id.dianjia)
    RelativeLayout dianjia;
    @Bind(R.id.zhaobiao)
    RelativeLayout zhaobiao;
    @Bind(R.id.liansuo)
    RelativeLayout liansuoa;
    @Bind(R.id.fengge)
    RelativeLayout fengge;
    @Bind(R.id.tv_add_chenjiao)
    TextView tvAddChenjiao;
    @Bind(R.id.dianpu)
    RelativeLayout dianpu;
    @Bind(R.id.tv_add_pingpai)
    EditText tvAddPingpai;
    @Bind(R.id.pingpai)
    RelativeLayout pingpai;
    @Bind(R.id.zhuan_xiu)
    RelativeLayout zhuanXiu;
    @Bind(R.id.xiu_qiu)
    RelativeLayout xiuQiu;
    @Bind(R.id.tv_add_pingpai_text)
    TextView tvAddPingpaiText;
    @Bind(R.id.tv_Rent_free_text)
    TextView tvRentFreeText;
    @Bind(R.id.tv_add_fuwu)
    TextView tvAddFuwu;
    @Bind(R.id.fuwu)
    RelativeLayout fuwu;
    //条件选择器
    private OptionsPickerView optionsPickerView;
    private List<String> groupList;
    private List<List<String>> childList;
    private int typeOne;

    private int typeTwo;

    private List<String> attribute;
    private List<String> shenfeng;
    private List<String> fangyuan;
    private List<String> fangwu;
    private List<String> chengjiao;
    private List<String> xuqiu;
    private List<String> xingzhi;
    private List<String> guimo;
    private List<String> ruzhu;
    private List<String> zhiming;

    private TimePickerView timePickerView;
    private OptionsPickerView attributepicker;
    private OptionsPickerView shenfengPicker;
    private OptionsPickerView fangyuanPicker;
    private OptionsPickerView fangwuPicker;
    private OptionsPickerView chengjiaoPicker;
    private List<String> liansuo;
    private OptionsPickerView xiuqiuPicker;
    private OptionsPickerView dianziPicker;
    private int clientID;
    private List<String> mianzua;
    private OptionsPickerView mianzuPicker;
    private List<String> fuwua;
    private OptionsPickerView fuwuPickerView;
    private List<String> xingzha;
    private OptionsPickerView xingzhiPickerView;
    private OptionsPickerView guimoPickerView;


    @Override
    public int getLayout() {
        return R.layout.activity_customer;
    }

    @Override
    public void initData() {
        initClientType();
        initArray();
        initPicker();
    }

    private void initPicker() {
        attributepicker = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tvProjectAttributees.setText(attribute.get(options1));
            }
        }).build();
        attributepicker.setPicker(attribute);
        shenfengPicker = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tvAddIdentity.setText(shenfeng.get(options1));
            }
        }).build();
        shenfengPicker.setPicker(shenfeng);
        //tvAddResources
        fangyuanPicker = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tvAddBudget.setText(fangyuan.get(options1));
            }
        }).build();
        fangyuanPicker.setPicker(fangyuan);

        //tvAddHouse
        fangwuPicker = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tvAddHouse.setText(fangwu.get(options1));
            }
        }).build();
        fangwuPicker.setPicker(fangwu);

        chengjiaoPicker = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tvAddChenjiao.setText(chengjiao.get(options1));
            }
        }).build();
        chengjiaoPicker.setPicker(chengjiao);

        xiuqiuPicker = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tvAddDemandText.setText(xuqiu.get(options1));
            }
        }).build();
        xiuqiuPicker.setPicker(xuqiu);
        mianzuPicker = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tvRentFree.setText(mianzua.get(options1));
            }
        }).build();
        mianzuPicker.setPicker(mianzua);
        //tvAddMoney
        fuwuPickerView= new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //tv_add_fuwu
                tvAddFuwu.setText(fuwua.get(options1));
            }
        }).build();
        fuwuPickerView.setPicker(fuwua);
         xingzhiPickerView= new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //tv_add_fuwu
                tvAddFuwu.setText(xingzhi.get(options1));
            }
        }).build();
        xingzhiPickerView.setPicker(xingzhi);

        //tv_add_fuwu
        guimoPickerView = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //tv_add_fuwu
                tvAddScale.setText(guimo.get(options1));
            }
        }).build();
        guimoPickerView.setPicker(guimo);
    }

    private void initArray() {
        attribute = new ArrayList<>();
        attribute.add("正常");
        attribute.add("平面报价");
        attribute.add("全面报价");
        attribute.add("清单报价");
        attribute.add("其他");
        shenfeng = new ArrayList<>();
        shenfeng.add("请选择");
        shenfeng.add("行政");
        shenfeng.add("副总");
        shenfeng.add("合同负责人");
        shenfeng.add("财务");
        shenfeng.add("合伙人");
        shenfeng.add("老板");
        shenfeng.add("老板助理");
        fangyuan = new ArrayList<>();
        fangyuan.add("已定");
        fangyuan.add("未定");
        fangyuan.add("暂定");
        fangwu = new ArrayList<>();
        fangwu.add("请选择");
        fangwu.add("毛坯房");
        fangwu.add("清水房");
        fangwu.add("旧房改造");
        fangwu.add("翻新");
        fangwu.add("精装房");

        chengjiao = new ArrayList<>();
        chengjiao.add("请选择");
        chengjiao.add("租");
        chengjiao.add("买");
        chengjiao.add("自建房");

        xuqiu = new ArrayList<>();
        xuqiu.add("请选择");
        xuqiu.add("基础装修");
        xuqiu.add("二次改造");
        xuqiu.add("精装修");
        xuqiu.add("局部装修");
        xuqiu.add("无法确定");
        xingzhi = new ArrayList<>();
        xingzhi.add("请选择");
        xingzhi.add("国企");
        xingzhi.add("外企");
        xingzhi.add("私企");
        xingzhi.add("央企");
        xingzhi.add("合资");
        xingzhi.add("政府");
        guimo = new ArrayList<>();
        guimo.add("请选择");
        guimo.add("30人已下");
        guimo.add("30-50人");
        guimo.add("50-100人");
        guimo.add("100人以上");
        ruzhu = new ArrayList<>();
        ruzhu.add("是");
        ruzhu.add("否");
        zhiming = new ArrayList<>();
        zhiming.add("是");
        zhiming.add("否");

        mianzua = new ArrayList<>();
        mianzua.add("无");
        mianzua.add("1个月");
        mianzua.add("1-2个月");
        mianzua.add("2个月以上");

        fuwua = new ArrayList<>();
        fuwua.add("学前");
        fuwua.add("小学");
        fuwua.add("中学");
        fuwua.add("高中");
        fuwua.add("成人");
    }

    @Override
    protected UpdClientPresenter onCreatePresenter() {
        return new UpdClientPresenter(this);
    }

    private void initClientType() {
        initClientData();

        clientID = getIntent().getIntExtra(Constants.ACTION_TO_UPD_CLIENT_CLIENT_ID, 0);
        Log.e("tag",clientID+"");
        groupList = new ArrayList<>();

        childList = new ArrayList<>();

        mPresenter.getTc();

    }

    private void initClientData() {

        //获取客户信息
        mPresenter.getClientInfo(clientID);

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
                tvAddProject.setText(dataList.get(options1).getMingCheng() + " —  " + dataList.get(options1).getZiji().get(options2).getMingCheng());
                typeOne = dataList.get(options1).getID();
                typeTwo = dataList.get(options1).getZiji().get(options2).getID();
                Log.e("tag", typeOne + "");
            }
        }).build();
        optionsPickerView.setPicker(groupList, childList);
    }

    @Override
    public void responseTcError(String msg) {

    }

    @Override
    public void responseUpdClientData() {

    }

    @Override
    public void responseUpdClientDataError(String msg) {
        showToast(msg);
    }

    @Override
    public void responseClientData(ClientInfo.Client data) {
        String xingMing = data.getXingMing();

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


    @OnClick({R.id.iv_back, R.id.tv_title, R.id.tv_add_Project_typetext, R.id.tv_add_Project,
            R.id.tv_project_attributestext, R.id.tv_project_attributees, R.id.tv_add_client_wx_line,
            R.id.tv_add_customer_text, R.id.tv_add_customer, R.id.tv_add_identity_text, R.id.tv_add_identity,
            R.id.tv_add_measure_text, R.id.tv_add_measure, R.id.tv_add_budget_text, R.id.tv_add_budget,
            R.id.tv_add_resources_text, R.id.tv_add_resources, R.id.tv_add_House_text, R.id.tv_add_deal_text,
            R.id.tv_add_deal, R.id.tv_add_make_text, R.id.tv_add_make, R.id.tv_add_client_wx_text,
            R.id.tv_add_demand_text, R.id.tv_add_demand, R.id.tv_add_Ginseng_text, R.id.tv_add_Ginseng,
            R.id.tv_add_Style_text, R.id.tv_add_Style, R.id.tv_add_Renovation_text, R.id.tv_add_Renovation,
            R.id.tv_add_Company_text, R.id.tv_add_Company, R.id.tv_add_Address_text, R.id.tv_add_Address,
            R.id.tv_add_Nature_text, R.id.tv_add_Nature, R.id.tv_add_Scale_text, R.id.tv_add_Scale,
            R.id.tv_add_Setup_text, R.id.tv_add_Setup, R.id.tv_add_Check_in_text, R.id.tv_add_text,
            R.id.tv_add_Funds_text, R.id.tv_add_Funds, R.id.tv_add_Famous_text, R.id.tv_add_Famous,
            R.id.tv_add_workan_text, R.id.tv_add_workan, R.id.tv_add_Remarks_text, R.id.tv_add_Remarks,
            R.id.btn_upd_client, R.id.tv_add_money, R.id.tv_add_Price, R.id.tv_add_Tendering, R.id.tv_add_chain,
            R.id.tv_add_chenjiao, R.id.tv_add_pingpai, R.id.tv_add_fuwu,R.id.tv_add_House})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                break;
            case R.id.tv_title:
                break;
            case R.id.tv_add_Project_typetext:
                break;
            case R.id.tv_add_Project:
                optionsPickerView.show();

                break;
            case R.id.tv_project_attributestext:
                break;
            case R.id.tv_project_attributees:
                attributepicker.show();
                break;
            case R.id.tv_add_client_wx_line:
                break;
            case R.id.tv_add_customer_text:
                break;
            case R.id.tv_add_customer:
                break;
            case R.id.tv_add_identity_text:
                break;
            case R.id.tv_add_identity:
                shenfengPicker.show();
                break;
            case R.id.tv_add_measure_text:
                break;
            case R.id.tv_add_measure:
                break;
            case R.id.tv_add_budget_text:
                break;
            case R.id.tv_add_budget:

                break;
            case R.id.tv_add_resources_text:

                break;
            case R.id.tv_add_resources:
                fangyuanPicker.show();
                break;
            case R.id.tv_add_House_text:
                break;
            case R.id.tv_add_House:
                fangwuPicker.show();
                break;
            case R.id.tv_add_deal_text:
                break;
            case R.id.tv_add_deal:
                chengjiaoPicker.show();
                break;
            case R.id.tv_add_make_text:
                break;
            case R.id.tv_add_make:

                timePickerView = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        String time = getDateToString(date, "yyyy/MM/dd");
                        tvAddMake.setText(time);
                    }
                }).setType(new boolean[]{true, true, true, false, false, false})
                        .setCancelText("取消")//取消按钮文字
                        .setSubmitText("确定")//确认按钮文字
                        .setContentSize(16)//滚轮文字大小
                        .setTitleSize(20)//标题文字大小
                        .setTitleText("请选择时间")//标题文字
                        .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                        .setRange(1900, Calendar.YEAR)
                        .isCyclic(true)//是否循环滚动
                        .setLabel("年", "月", "日", "时", "分", "秒")
                        .isCenterLabel(true) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                        .build();
                timePickerView.show();
                break;
            case R.id.tv_add_client_wx_text:
                break;
            case R.id.tv_add_demand_text:
                xiuqiuPicker.show();
                break;
            case R.id.tv_add_demand:
                timePickerView = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        String time = getDateToString(date, "yyyy/MM/dd");
                        tvAddDemand.setText(time);
                    }
                }).setType(new boolean[]{true, true, true, false, false, false})
                        .setCancelText("取消")//取消按钮文字
                        .setSubmitText("确定")//确认按钮文字
                        .setContentSize(16)//滚轮文字大小
                        .setTitleSize(20)//标题文字大小
                        .setTitleText("请选择时间")//标题文字
                        .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                        .setRange(1900, Calendar.YEAR)
                        .isCyclic(true)//是否循环滚动
                        .setLabel("年", "月", "日", "时", "分", "秒")
                        .isCenterLabel(true) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                        .build();
                timePickerView.show();
                break;
            case R.id.tv_add_Ginseng_text:
                break;
            case R.id.tv_add_Ginseng:
                break;
            case R.id.tv_add_Style_text:
                break;
            case R.id.tv_add_Style:
                break;
            case R.id.tv_add_Renovation_text:
                break;
            case R.id.tv_add_Renovation:

                timePickerView = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        String time = getDateToString(date, "yyyy/MM/dd");
                        tvAddRenovation.setText(time);
                    }
                }).setType(new boolean[]{true, true, true, false, false, false})
                        .setCancelText("取消")//取消按钮文字
                        .setSubmitText("确定")//确认按钮文字
                        .setContentSize(16)//滚轮文字大小
                        .setTitleSize(20)//标题文字大小
                        .setTitleText("请选择时间")//标题文字
                        .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                        .setRange(1900, Calendar.YEAR)
                        .isCyclic(true)//是否循环滚动
                        .setLabel("年", "月", "日", "时", "分", "秒")
                        .isCenterLabel(true) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                        .build();
                timePickerView.show();
                break;
            case R.id.tv_add_Company_text:
                break;
            case R.id.tv_add_Company:
                break;
            case R.id.tv_add_Address_text:
                break;
            case R.id.tv_add_Address:
                break;
            case R.id.tv_add_Nature_text:
                break;
            case R.id.tv_add_Nature:
                xingzhiPickerView.show();
                break;
            case R.id.tv_add_Scale_text:
                break;
            case R.id.tv_add_Scale:
                guimoPickerView.show();
                break;
            case R.id.tv_add_Setup_text:
                break;
            case R.id.tv_add_Setup:
                timePickerView = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        String time = getDateToString(date, "yyyy/MM/dd");
                        tvAddSetup.setText(time);
                    }
                }).setType(new boolean[]{true, true, true, false, false, false})
                        .setCancelText("取消")//取消按钮文字
                        .setSubmitText("确定")//确认按钮文字
                        .setContentSize(16)//滚轮文字大小
                        .setTitleSize(20)//标题文字大小
                        .setTitleText("请选择时间")//标题文字
                        .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                        .setRange(1900, Calendar.YEAR)
                        .isCyclic(true)//是否循环滚动
                        .setLabel("年", "月", "日", "时", "分", "秒")
                        .isCenterLabel(true) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                        .build();
                timePickerView.show();
                break;
            case R.id.tv_add_Check_in_text:
                break;
            case R.id.tv_add_text:
                dianziPicker = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        tvAddText.setText(ruzhu.get(options1));
                    }
                }).build();
                dianziPicker.setPicker(ruzhu);
                dianziPicker.show();
                break;
            case R.id.tv_add_Funds_text:
                break;
            case R.id.tv_add_Funds:
                break;
            case R.id.tv_add_Famous_text:
                break;
            case R.id.tv_add_Famous:
                dianziPicker = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        tvAddFamous.setText(ruzhu.get(options1));
                    }
                }).build();
                dianziPicker.setPicker(ruzhu);
                dianziPicker.show();
                break;
            case R.id.tv_add_workan_text:
                break;
            case R.id.tv_add_workan:
                break;
            case R.id.tv_add_Remarks_text:
                break;
            case R.id.tv_add_Remarks:
                break;
            case R.id.btn_upd_client:
                break;
            case R.id.tv_add_money:
                dianziPicker = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        tvAddMoney.setText(ruzhu.get(options1));
                    }
                }).build();
                dianziPicker.setPicker(ruzhu);
                dianziPicker.show();
                break;
            case R.id.tv_add_Price:
                break;
            case R.id.tv_add_Tendering:
                dianziPicker = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        tvAddTendering.setText(ruzhu.get(options1));
                    }
                }).build();
                dianziPicker.setPicker(ruzhu);
                dianziPicker.show();
                break;
            case R.id.tv_add_chain:
                dianziPicker = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        tvAddChain.setText(ruzhu.get(options1));
                    }
                }).build();
                dianziPicker.setPicker(ruzhu);
                dianziPicker.show();
                break;
            case R.id.tv_add_chenjiao:
                chengjiaoPicker.show();
                break;
            case R.id.tv_add_pingpai:
                break;
            case R.id.tv_add_fuwu:
                fuwuPickerView.show();
                break;
            default:
                break;
        }
    }

    private void closeKeyboard() {
        View view = getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static String getDateToString(Date date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    @OnClick(R.id.tv_Rent_free)
    public void onViewClicked() {

        mianzuPicker.show();
    }




}
