package com.rxjy.niujingji.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.rxjy.niujingji.R;
import com.rxjy.niujingji.api.ApiEngine;
import com.rxjy.niujingji.commons.Constants;
import com.rxjy.niujingji.commons.base.BaseActivity;
import com.rxjy.niujingji.commons.utils.JSONUtils;
import com.rxjy.niujingji.commons.utils.StringUtils;
import com.rxjy.niujingji.entity.AgeBean;
import com.rxjy.niujingji.entity.AttributesBean;
import com.rxjy.niujingji.entity.ClientInfo;
import com.rxjy.niujingji.entity.CustomerBean;
import com.rxjy.niujingji.entity.TcInfo;
import com.rxjy.niujingji.mvp.contract.UpdClientContract;
import com.rxjy.niujingji.mvp.presenter.UpdClientPresenter;
import com.rxjy.niujingji.utils.OkhttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

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
    TextView tvAddMake;
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
    TextView tvAddRenovation;
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
    TextView tvAddSetup;
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
    @Bind(R.id.tv_add_Telephone)
    EditText tvAddTelephone;
    @Bind(R.id.tv_add_fuwu_text)
    TextView tvAddFuwuText;
    @Bind(R.id.tv_add_Telephone_text)
    TextView tvAddTelephoneText;
    @Bind(R.id.linearLayout_one)
    LinearLayout linearLayoutOne;
    @Bind(R.id.LinearLayout_two)
    LinearLayout LinearLayoutTwo;
    @Bind(R.id.tv_add_Famous_two)
    TextView tvAddFamousTwo;
    @Bind(R.id.tv_xuqiu)
    TextView tvXuqiu;
    @Bind(R.id.btn_submit)
    Button btnSubmit;
    @Bind(R.id.qita_xiuqu)
    RelativeLayout qitaXiuqu;
    @Bind(R.id.btn_upd_client_group)
    LinearLayout btnUpdClientGroup;
    @Bind(R.id.coustactivity)
    RelativeLayout coustactivity;
    @Bind(R.id.jiaofangone)
    RelativeLayout jiaofangone;
    @Bind(R.id.tv_add_demand_one)
    TextView tvAddDemandOne;
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
    private int matEnddowment;
    private int zhaoBiao;
    private int serviceGroups;
    private int xiangMuShuXing;
    private int shenFenID;
    private int qiYeXingZhiID;
    private int qiYeGuiMoID;
    private int qiYeRuZhu;
    private int zhiMingQiYe;
    private String url;

    private List<String> mfu;
    private List<List<String>> mzi;
    private List<AttributesBean.BodyBean.ListBean> list;
    private OptionsPickerView xuqiuoptionsPickerView;
    private List<AgeBean.BodyBean> bodyone;
    private int mianZuQi;
    private String secondleave;
    private String aid;


    @Override
    public int getLayout() {
        return R.layout.activity_customer;
    }

    private void initIntent() {
        boolean isChanged = getIntent().getBooleanExtra(Constants.ACTION_TO_UPD_CLIENT_IS_CAN_CHANGED, false);
        clientID = getIntent().getIntExtra(Constants.ACTION_TO_UPD_CLIENT_CLIENT_ID, 0);

        if (!isChanged) {
            disableSubControls(coustactivity);
            btnUpdClientGroup.setVisibility(View.INVISIBLE);
        } else {
            btnUpdClientGroup.setVisibility(View.VISIBLE);
        }

    }

    public static void disableSubControls(ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View v = viewGroup.getChildAt(i);
            if (v instanceof ViewGroup) {
                if (v instanceof Spinner) {
                    Spinner spinner = (Spinner) v;
                    spinner.setClickable(false);
                    spinner.setEnabled(false);
                } else if (v instanceof ListView) {
                    ((ListView) v).setClickable(false);
                    ((ListView) v).setEnabled(false);

                } else {
                    disableSubControls((ViewGroup) v);
                }
            } else if (v instanceof EditText) {
                ((EditText) v).setFocusable(false);
                //  Log.i("tag", "A EditText is unabled");
            } else if (v instanceof TextView) {
                ((TextView) v).setEnabled(false);
            }
        }
    }

    @Override
    public void initData() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initIntent();
        tvTitle.setText("详情");
        mfu = new ArrayList<>();
        mzi = new ArrayList<>();
        loadDta();
        loadAttributes();
        initClientType();
        initArray();
        initPicker();

    }

    private void loadAttributes() {
        String url = ApiEngine.SW_API_HOST + "AppAgent/GetOneTowLevelList";
        OkhttpUtils.doGet(url, null, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("tag", e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        AttributesBean attributesBean = JSONUtils.toObject(string, AttributesBean.class);
                        if (attributesBean.getStatusCode() == 0) {
                            AttributesBean.BodyBean body = attributesBean.getBody();
                            list = body.getList();
                            for (int i = 0; i < list.size(); i++) {
                                mfu.add(list.get(i).getName());
                                List<String> child = new ArrayList<>();
                                AttributesBean.BodyBean.ListBean listBean = list.get(i);
                                List<AttributesBean.BodyBean.ListBean.DetailBean> detail = listBean.getDetail();
                                for (AttributesBean.BodyBean.ListBean.DetailBean abc : detail) {
                                    child.add(abc.getBname());
                                }
                                mzi.add(child);
                            }
                            xuqiuoptionsPickerView = new OptionsPickerView.Builder(CustomerActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
                                @Override
                                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                    String s = list.get(options1).getName() + " —  " + list.get(options1).getDetail().get(options2).getBname();
                                    tvXuqiu.setText(s);
                                    fu = list.get(options1).getId();
                                    Log.e("tag+------------", fu + "=========");
                                    secondleave = list.get(options1).getDetail().get(options2).getBname();
                                    aid = list.get(options1).getDetail().get(options2).getAid();
                                    zi = Integer.parseInt(list.get(options1).getDetail().get(options2).getBid());

                                }
                            }).build();
                            xuqiuoptionsPickerView.setPicker(mfu, mzi);
                        } else {
                            showToast(attributesBean.getStatusMsg());
                        }
                    }
                });
            }
        });

    }

    private int identity, housefrom, house, saletype, need, liansuonex, fu, zi;

    private void initPicker() {
        attributepicker = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tvProjectAttributees.setText(attribute.get(options1));
                switch (options1) {
                    case 0:
                        xiangMuShuXing = options1 + 1;
                        break;
                    case 1:
                        xiangMuShuXing = options1 + 1;
                        break;
                    case 2:
                        xiangMuShuXing = options1 + 2;
                        break;
                    case 3:
                        xiangMuShuXing = options1 + 2;
                        break;
                    case 4:
                        xiangMuShuXing = options1 + 2;
                        break;
                    default:
                        break;

                }
                if (xiangMuShuXing == 6) {
                    LinearLayoutTwo.setVisibility(View.GONE);
                    linearLayoutOne.setVisibility(View.GONE);
                    qitaXiuqu.setVisibility(View.VISIBLE);
                } else {
                    LinearLayoutTwo.setVisibility(View.VISIBLE);
                    linearLayoutOne.setVisibility(View.VISIBLE);
                    qitaXiuqu.setVisibility(View.GONE);
                }
            }
        }).build();
        attributepicker.setPicker(attribute);

        //tvAddResources
        fangyuanPicker = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tvAddResources.setText(fangyuan.get(options1));
                housefrom = options1 + 1;

            }
        }).build();
        fangyuanPicker.setPicker(fangyuan);

        //tvAddHouse
        fangwuPicker = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tvAddHouse.setText(fangwu.get(options1));
                if (options1 + 1 >= 4) {
                    house = options1 + 2;
                } else {
                    house = options1 + 1;
                }
            }
        }).build();
        fangwuPicker.setPicker(fangwu);

        chengjiaoPicker = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tvAddChenjiao.setText(chengjiao.get(options1));
                saletype = options1 + 1;
                if (saletype == 1) {
                    mianzu.setVisibility(View.VISIBLE);
                    jiaofangone.setVisibility(View.GONE);
                } else {
                    mianzu.setVisibility(View.GONE);
                    jiaofangone.setVisibility(View.VISIBLE);
                }
            }
        }).build();
        chengjiaoPicker.setPicker(chengjiao);

        xiuqiuPicker = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tvAddDemandText.setText(xuqiu.get(options1));
                need = options1 + 1;
            }
        }).build();
        xiuqiuPicker.setPicker(xuqiu);
        mianzuPicker = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tvRentFree.setText(mianzua.get(options1));
                mianZuQi = options1 + 1;
            }
        }).build();
        mianzuPicker.setPicker(mianzua);
        //tvAddMoney
        fuwuPickerView = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //tv_add_fuwu
                tvAddFuwu.setText(fuwua.get(options1));
                serviceGroups = options1 + 1;
            }
        }).build();
        fuwuPickerView.setPicker(fuwua);
        xingzhiPickerView = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //tv_add_fuwu
                tvAddNature.setText(xingzhi.get(options1));

                switch (options1) {
                    case 0:
                        qiYeXingZhiID = 109;
                        break;
                    case 1:
                        qiYeXingZhiID = 110;
                        break;
                    case 2:
                        qiYeXingZhiID = 111;
                        break;
                    case 3:
                        qiYeXingZhiID = 112;
                        break;
                    case 4:
                        qiYeXingZhiID = 113;
                        break;
                    case 5:
                        qiYeXingZhiID = 117;
                        break;
                    default:
                        break;

                }

            }
        }).build();
        xingzhiPickerView.setPicker(xingzhi);

        //tv_add_fuwu
        guimoPickerView = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //tv_add_fuwu
                tvAddScale.setText(guimo.get(options1));
                qiYeGuiMoID = options1 + 115;

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
        fangwu = new ArrayList<>();
        fangwu.add("毛坯房");
        fangwu.add("清水房");
        fangwu.add("旧房改造");
        fangwu.add("翻新");
        fangwu.add("精装房");

        chengjiao = new ArrayList<>();
        chengjiao.add("租");
        chengjiao.add("买");
        chengjiao.add("自建房");

        xuqiu = new ArrayList<>();
        xuqiu.add("基础装修");
        xuqiu.add("二次改造");
        xuqiu.add("精装修");
        xuqiu.add("局部装修");
        xuqiu.add("无法确定");
        xingzhi = new ArrayList<>();
        xingzhi.add("国企");
        xingzhi.add("外企");
        xingzhi.add("私企");
        xingzhi.add("央企");
        xingzhi.add("合资");
        xingzhi.add("政府");
        guimo = new ArrayList<>();
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
        Log.e("tag", clientID + "");

        groupList = new ArrayList<>();

        childList = new ArrayList<>();

        mPresenter.getTc();

    }

    private void initClientData() {

        //获取客户信息
        //   mPresenter.getClientInfo(clientID);

    }

    private void loadDta() {

        url = ApiEngine.SW_API_HOST + "HuiYuan/XunJiaShenFenSelect";

        OkhttpUtils.doGet(url, null, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("tag", e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        AgeBean data = JSONUtils.toObject(string, AgeBean.class);
                        identitylist = new ArrayList<>();
                        identityidlist = new ArrayList<>();
                        bodyone = data.getBody();

                        for (int i = 0; i < data.getBody().size(); i++) {
                            identitylist.add(data.getBody().get(i).getMingCheng());
                            identityidlist.add(data.getBody().get(i).getID());
                        }
                    }
                });
            }
        });
    }

    @Override
    public void responseClientType(final List<TcInfo.BodyBean.ClientType> dataList) {

        for (TcInfo.BodyBean.ClientType typeInfo : dataList) {
            groupList.add(typeInfo.getMingCheng());
            List<String> child = new ArrayList<>();
            for (TcInfo.BodyBean.ClientType.ClientChildType childType : typeInfo.getZiji()) {
                child.add(childType.getMingCheng());
            }
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
        showLoadDta(dataList);
    }

    /**
     * 身份
     */
    ArrayList<String> identitylist;
    ArrayList<Integer> identityidlist;

    private void showLoadDta(final List<TcInfo.BodyBean.ClientType> dataList) {

        Map<String, Object> map = new HashMap<>();
        map.put("id", clientID);
        //获取客户详情
        String url = ApiEngine.SW_API_HOST + "AppAgent/GetKeHuInfo";
        OkhttpUtils.doGet(url, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(final Call call, Response response) throws IOException {
                final String string = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        CustomerBean customerBean = JSONUtils.toObject(string, CustomerBean.class);
                        if (customerBean.getStatusCode() == 0) {

                            List<CustomerBean.BodyBean> body = customerBean.getBody();
                            CustomerBean.BodyBean bodyBean = body.get(0);

                            identity = bodyBean.getShenFenID();
                            Log.e("identity", identity + "");
                            if (identitylist != null && identity != 0) {
                                for (int i = 0; i < bodyone.size(); i++) {
                                    AgeBean.BodyBean bodyBean1 = bodyone.get(i);
                                    int id = bodyBean1.getID();
                                    Log.e("aaaaaaaa", id + "");
                                    if (id == identity) {
                                        tvAddIdentity.setText(bodyBean1.getMingCheng());
                                    }
                                }

                            }
                            xiangMuShuXing = bodyBean.getXiangMuShuXing();
                            if (attribute != null && xiangMuShuXing != 0) {
                                switch (xiangMuShuXing) {
                                    case 1:
                                        tvProjectAttributees.setText(attribute.get(xiangMuShuXing - 1));
                                        break;
                                    case 2:
                                        tvProjectAttributees.setText(attribute.get(xiangMuShuXing - 1));
                                        break;
                                    case 4:
                                        tvProjectAttributees.setText(attribute.get(xiangMuShuXing - 2));
                                        break;
                                    case 5:
                                        tvProjectAttributees.setText(attribute.get(xiangMuShuXing - 2));
                                        break;
                                    case 6:
                                        tvProjectAttributees.setText(attribute.get(xiangMuShuXing - 2));
                                        break;
                                    default:
                                        break;
                                }

                            }
                            int isJoin = bodyBean.getIsJoin();
                            if (isJoin != 0) {
                                linearLayoutOne.setVisibility(View.GONE);
                                LinearLayoutTwo.setVisibility(View.GONE);
                                qitaXiuqu.setVisibility(View.VISIBLE);
                            } else {
                                linearLayoutOne.setVisibility(View.VISIBLE);
                                LinearLayoutTwo.setVisibility(View.VISIBLE);
                                qitaXiuqu.setVisibility(View.GONE);
                            }
                            house = bodyBean.getYuJiZhuangXiu();

                            if (fangwu != null && house != 0) {
                                if (house < 5) {
                                    tvAddHouse.setText(fangwu.get(house - 1));
                                } else {
                                    tvAddHouse.setText(fangwu.get(house - 2));
                                }
                            }
                            tvAddShop.setText(bodyBean.getGongSiMingCheng());
                            StringBuffer stringBuffer = new StringBuffer();
                            for (TcInfo.BodyBean.ClientType mlsit : dataList) {
                                int id = mlsit.getID();
                                if (id == bodyBean.getLeiXingYi()) {
                                    stringBuffer.append(mlsit.getMingCheng() + "-");
                                    typeOne = bodyBean.getLeiXingYi();
                                }
                                List<TcInfo.BodyBean.ClientType.ClientChildType> ziji = mlsit.getZiji();
                                int leiXingEr = bodyBean.getLeiXingEr();
                                for (TcInfo.BodyBean.ClientType.ClientChildType kry : ziji) {
                                    if (leiXingEr == kry.getID()) {
                                        stringBuffer.append(kry.getMingCheng());
                                        typeTwo = leiXingEr;
                                    }
                                }

                            }
                            tvAddProject.setText(stringBuffer);
                            StringBuffer strin = new StringBuffer();
                            String xuQiuLeiXingone = new String();
                            String xuQiuLeiXingtwo = new String();
                            int xuQiuLeiXing1 = bodyBean.getXuQiuLeiXing();
                            if (list != null && list.size() > 0 && xuQiuLeiXing1 != 0) {
                                for (AttributesBean.BodyBean.ListBean mlsit : list) {
                                    int id = mlsit.getId();
                                    Log.e("bug", id + "");
                                    if (id == xuQiuLeiXing1) {
                                        Log.e("bug", mlsit.getName());
                                        xuQiuLeiXingone = mlsit.getName();
                                        strin.append(mlsit.getName() + "-");
                                        fu = bodyBean.getLeiXingYi();
                                    }
                                    List<AttributesBean.BodyBean.ListBean.DetailBean> ziji = mlsit.getDetail();
                                    int leiXingEr = bodyBean.getLabelId();
                                    Log.e("tag", leiXingEr + "");
                                    for (AttributesBean.BodyBean.ListBean.DetailBean kry : ziji) {
                                        if (leiXingEr == Integer.parseInt(kry.getBid())) {
                                            strin.append(kry.getBname());
                                            xuQiuLeiXingtwo = kry.getBname();
                                            zi = leiXingEr;
                                            break;
                                        }
                                    }
                                }
                            }
                            Log.e("aaaa", strin.toString());
                            tvXuqiu.setText(xuQiuLeiXingone + "-" + xuQiuLeiXingtwo);
                            tvAddProject.setText(stringBuffer);
                            //XingMing
                            String xingMing = bodyBean.getXingMing();
                            tvAddCustomer.setText(xingMing);
                            String shouJiHaoYi = bodyBean.getShouJiHaoYi();
                            tvAddTelephone.setText(shouJiHaoYi);

                            //   bodyBean.getShenFenName()
                            double mianJi = bodyBean.getMianJi();
                            tvAddMeasure.setText(mianJi + "");
                            //ZhuangXiuYuSuan 
                            double zhuangXiuYuSuan = bodyBean.getZhuangXiuYuSuan();
                            tvAddBudget.setText(zhuangXiuYuSuan + "");
                            housefrom = bodyBean.getFangYuan();
                            if (housefrom == 1) {
                                tvAddResources.setText("已定");
                            } else if (housefrom == 2) {
                                tvAddResources.setText("未定");
                            }
                            mianZuQi = bodyBean.getMianZuQi();
                            if (mianzua != null && mianZuQi != 0) {
                                //tv_Rent_free
                                tvRentFree.setText(mianzua.get(mianZuQi - 1));
                            }
                            saletype = bodyBean.getChengJiaoLeiXing();

                            if (chengjiao != null && saletype != 0) {
                                tvAddChenjiao.setText(chengjiao.get(saletype - 1));
                            }
                            if (!StringUtils.isEmpty(bodyBean.getJiaoFangShiJian())) {//交房时间
                                tvAddMake.setText(bodyBean.getJiaoFangShiJian());
                                //  tvSubmithousetime.setTextColor(this.getResources().getColor(R.color.textblack));

                            }
                            need = bodyBean.getZhuangXiuXuQiu();
                            if (xuqiu != null && need != 0) {
                                tvAddDemandText.setText(xuqiu.get(need - 1));
                            }
                            String yuJiLiangFang = bodyBean.getYuJiLiangFang();
                            if (!StringUtils.isEmpty(yuJiLiangFang)) {
                                tvAddDemand.setText(yuJiLiangFang);
                            }
                            // MatEnddowment
                            matEnddowment = bodyBean.getMatEnddowment();

                            if (matEnddowment == 1) {
                                tvAddMoney.setText("是");
                            } else if (matEnddowment == 2) {
                                tvAddMoney.setText("否");
                            }
                            //ZuJinDanJia
                            //ZuJinDanJia
                            int zuJinDanJia = bodyBean.getZuJinDanJia();

                            tvAddPrice.setText(zuJinDanJia + "");
                            zhaoBiao = bodyBean.getZhaoBiao();
                            //tv_add_Price

                            if (zhaoBiao == 1) {
                                tvAddTendering.setText("是");
                            } else if (zhaoBiao == 2) {
                                tvAddTendering.setText("否");
                            }
                            //tvAddChain
                            liansuonex = bodyBean.getIsChain();
                            if (liansuonex == 1) {
                                tvAddChain.setText("是");

                            } else if (liansuonex == 2) {
                                tvAddChain.setText("否");
                            }
                            //PartCompany 
                            String partCompany = bodyBean.getPartCompany();
                            if (!StringUtils.isEmpty(partCompany)) {
                                tvAddGinseng.setText(partCompany);
                            }
                            //ServiceGroups
                            serviceGroups = bodyBean.getServiceGroups();
                            if (fuwua != null && serviceGroups != 0) {
                                tvAddFuwu.setText(fuwua.get(serviceGroups - 1));
                            }

                            //tv_add_Style
                            //DecorateStyle
                            String decorateStyle = bodyBean.getDecorateStyle();
                            if (!StringUtils.isEmpty(decorateStyle)) {
                                tvAddStyle.setText(decorateStyle);
                            }
                            //YuJiZhuangXiuShiJian
                            String yuJiZhuangXiuShiJian = bodyBean.getYuJiZhuangXiuShiJian();
                            if (!StringUtils.isEmpty(yuJiZhuangXiuShiJian)) {
                                tvAddRenovation.setText(yuJiZhuangXiuShiJian);
                            }
                            // BrandName
                            String brandName = bodyBean.getBrandName();
                            if (!StringUtils.isEmpty(brandName)) {
                                tvAddPingpai.setText(brandName);
                            }
                            //tv_add_Company
                            //GongSiMingCheng
                            String gongSiMingCheng = bodyBean.getGongSiMingCheng();
                            if (!StringUtils.isEmpty(gongSiMingCheng)) {
                                tvAddCompany.setText(gongSiMingCheng);
                            }
                            //
                            String zhuCeDiZhi = bodyBean.getZhuCeDiZhi();
                            if (!StringUtils.isEmpty(zhuCeDiZhi)) {
                                tvAddWorkan.setText(zhuCeDiZhi);
                            }
                            //tv_add_Address
                            //LiangFangDiZhi
                            String liangFangDiZhi = bodyBean.getLiangFangDiZhi();
                            if (!StringUtils.isEmpty(liangFangDiZhi)) {
                                tvAddAddress.setText(liangFangDiZhi);
                            }
                            //QiYeXingZhiID
                            qiYeXingZhiID = bodyBean.getQiYeXingZhiID();
                            switch (qiYeXingZhiID) {
                                case 0:
                                    break;
                                case 109:
                                    tvAddNature.setText(xingzhi.get(0));
                                    break;
                                case 110:
                                    tvAddNature.setText(xingzhi.get(1));
                                    break;
                                case 111:
                                    tvAddNature.setText(xingzhi.get(2));
                                    break;
                                case 112:
                                    tvAddNature.setText(xingzhi.get(3));
                                    break;
                                case 113:
                                    tvAddNature.setText(xingzhi.get(4));
                                    break;
                                case 117:
                                    tvAddNature.setText(xingzhi.get(5));
                                    break;
                                default:
                                    break;
                            }


                            qiYeGuiMoID = bodyBean.getQiYeGuiMoID();

                            if (guimo != null && qiYeGuiMoID != 0) {
                                tvAddScale.setText(guimo.get(qiYeGuiMoID - 115));
                            }
                            //GongSiChengLiDate
                            String gongSiChengLiDate = bodyBean.getGongSiChengLiDate();

                            if (!StringUtils.isEmpty(gongSiChengLiDate)) {
                                tvAddSetup.setText(gongSiChengLiDate);
                            }
                            qiYeRuZhu = bodyBean.getQiYeRuZhu();
                            if (ruzhu != null && qiYeRuZhu != 0) {
                                tvAddText.setText(ruzhu.get(qiYeRuZhu - 1));
                            }
                            //tvAddText

                            String zhuCeZiJin = bodyBean.getZhuCeZiJin();
                            if (!StringUtils.isEmpty(zhuCeZiJin)) {
                                tvAddFunds.setText(zhuCeZiJin);
                            }
                            //hiMingQiYe
                            zhiMingQiYe = bodyBean.getZhiMingQiYe();

                            if (ruzhu != null && qiYeRuZhu != 0) {
                                tvAddFamousTwo.setText(ruzhu.get(zhiMingQiYe - 1));
                            }
                            //   .set
                        } else {
                            showToast(customerBean.getStatusMsg());
                        }

                    }
                });
            }
        });

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
            R.id.tv_add_Funds_text, R.id.tv_add_Funds, R.id.tv_add_Famous_text,
            R.id.tv_add_workan_text, R.id.tv_add_workan, R.id.tv_add_Remarks_text, R.id.tv_add_Remarks,
            R.id.btn_upd_client, R.id.tv_add_money, R.id.tv_add_Price, R.id.tv_add_Tendering, R.id.tv_add_chain,
            R.id.tv_add_chenjiao, R.id.tv_add_pingpai, R.id.tv_add_fuwu, R.id.tv_add_House, R.id.tv_xuqiu,
            R.id.btn_submit, R.id.tv_add_Famous_two})

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
                if (identitylist != null && identitylist.size() > 0) {
                    shenfengPicker = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
                        @Override
                        public void onOptionsSelect(int options1, int options2, int options3, View v) {
                            tvAddIdentity.setText(identitylist.get(options1));
                            identity = identityidlist.get(options1);
                        }
                    }).build();
                    shenfengPicker.setPicker(identitylist);
                }
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
                        qiYeRuZhu = options1 + 1;

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
            case R.id.tv_add_workan_text:
                break;
            case R.id.tv_add_workan:
                break;
            case R.id.tv_add_Remarks_text:
                break;
            case R.id.tv_add_Remarks:
                break;
            case R.id.btn_upd_client:
                if (xiangMuShuXing == 6) {
                    qitasave(17);
                } else {
                    submit(typeOne);
                }

                break;
            case R.id.tv_add_money:
                dianziPicker = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        tvAddMoney.setText(ruzhu.get(options1));
                        matEnddowment = options1 + 1;
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
                        zhaoBiao = options1 + 1;

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
                        liansuonex = options1 + 1;
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
            case R.id.tv_add_Famous_two:
                dianziPicker = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        tvAddFamousTwo.setText(ruzhu.get(options1));
                        zhiMingQiYe = options1 + 1;
                    }
                }).build();
                dianziPicker.setPicker(ruzhu);
                dianziPicker.show();
                break;
            case R.id.tv_xuqiu:
                xuqiuoptionsPickerView.show();
                break;
            case R.id.btn_submit:
                if (xiangMuShuXing == 6) {
                    qitasave(0);
                } else {
                    baocun();
                }
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

    private void submit(int id) {
        String kehu = tvAddCustomer.getText().toString();
        String dianhua = tvAddTelephone.getText().toString();
        String mianji = tvAddMeasure.getText().toString();
        String yusuan = tvAddBudget.getText().toString();
        String jiaofang = tvAddMake.getText().toString();
        String lianfang = tvAddDemand.getText().toString();
        String ginseng = tvAddGinseng.getText().toString();
        String fengge = tvAddStyle.getText().toString();
        String zhuangxiu = tvAddRenovation.getText().toString();
        String bangong = tvAddWorkan.getText().toString();
        String dizhi = tvAddAddress.getText().toString();
        String gongsi = tvAddCompany.getText().toString();
        String dianpu = tvAddShop.getText().toString();
        String danjia = tvAddPrice.getText().toString();
        String pingpai = tvAddPingpai.getText().toString();
        String chengli = tvAddSetup.getText().toString();
        String zijian = tvAddFunds.getText().toString();
        Log.e("tag", id + "");
        String url = ApiEngine.SW_API_HOST + "AppAgent/UpdateInfo";
        switch (id) {
            case 1:
                if (typeOne != 0 && Verification(kehu) && Verification(dianhua) && xiangMuShuXing != 0 && identity != 0 && Verification(mianji) && Verification(yusuan) && housefrom != 0 && house != 0 && saletype != 0 && need != 0 && Verification(lianfang) && Verification(ginseng) && Verification(fengge) && Verification(zhuangxiu) && Verification(gongsi) && Verification(dizhi)) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("KeHuBaseID", clientID);
                    map.put("XingMing", kehu);
                    map.put("ShouJiHaoYi", dianhua);
                    map.put("LeiXingYi", typeOne);
                    map.put("LeiXingEr", typeTwo);
                    map.put("ShenFenID", identity);
                    map.put("MianJi", mianji);
                    map.put("ZhuangXiuYuSuan", yusuan);
                    map.put("FangYuan", housefrom);
                    map.put("YuJiZhuangXiu", house);
                    map.put("ChengJiaoLeiXing", saletype);
                    map.put("ZhuangXiuXuQiu", need);
                    map.put("YuJiLiangFang", lianfang);
                    map.put("PartCompany", ginseng);
                    map.put("DecorateStyle", fengge);
                    map.put("YuJiZhuangXiuShiJian", zhuangxiu);
                    map.put("GongSiMingCheng", gongsi);
                    map.put("LiangFangDiZhi", dizhi);
                    map.put("QiYeXingZhiID", qiYeXingZhiID);
                    map.put("QiYeGuiMoID", qiYeGuiMoID);
                    map.put("GongSiChengLiDate", chengli);
                    //QiYeRuZhu
                    map.put("QiYeRuZhu", qiYeRuZhu);
                    map.put("ZhuangTai", 17);
                    //zijian ZhuCeZiJin
                    map.put("ZhuCeZiJin", zijian);
                    map.put("XiangMuShuXing", xiangMuShuXing);
                    //  zhiMingQiYe
                    map.put("ZhiMingQiYe", zhiMingQiYe);
                    //ZhuCeDiZhi
                    map.put("ZhuCeDiZhi", bangong);
                    if (saletype == 1) {
                        map.put("MianZuQi", mianZuQi);

                    } else {
                        map.put("JiaoFangShiJian", jiaofang);
                    }
                    OkhttpUtils.doPost(url, map, new Callback() {
                        @Override
                        public void onFailure(Call call, final IOException e) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    showToast(e.getMessage());
                                }
                            });
                            Log.e("tag", e.getMessage());
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            final String string = response.body().string();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        JSONObject jsonObject = new JSONObject(string);
                                        int statusCode = jsonObject.getInt("StatusCode");
                                        if (statusCode == 0) {
                                            finish();
                                        } else {
                                            String statusMsg = jsonObject.getString("StatusMsg");
                                            showToast(statusMsg);
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    });
                } else if (typeOne == 0) {
                    showToast("请选择项目类型");
                } else if (!Verification(kehu)) {
                    showToast("请输入名字");
                } else if (!Verification(dianhua)) {
                    showToast("请输入电话");
                } else if (xiangMuShuXing == 0) {
                    showToast("请选择项目属性");
                } else if (!Verification(mianji)) {
                    showToast("请输入面积");
                } else if (!Verification(yusuan)) {
                    showToast("请输入预算");
                } else if (identity == 0) {
                    showToast("请选择身份");
                } else if (housefrom == 0) {
                    showToast("请选择房源");
                } else if (house == 0) {
                    showToast("请选择房屋");
                } else if (saletype == 0) {
                    showToast("请选择成交类型！");
                } else if (need == 0) {
                    showToast("请选择装修需求！");
                } else if (!Verification(lianfang)) {
                    showToast("请输入量房时间！");
                } else if (saletype != 0) {
                    if (saletype == 1) {
                        if (mianZuQi == 0) {
                            showToast("请选择免租日期！");
                        } else {

                            if (!Verification(ginseng)) {
                                showToast("请输入参与公司！");
                            } else if (!Verification(fengge)) {
                                showToast("请输入风格！");
                            } else if (!Verification(zhuangxiu)) {
                                showToast("请输入装修时间！");
                            } else if (!Verification(gongsi)) {
                                showToast("请输入公司！");
                            } else if (!Verification(dizhi)) {
                                showToast("请输入地址！");
                            }
                        }
                    } else {
                        if (!Verification(jiaofang)) {
                            showToast("请输入交房时间！");
                        } else {

                            if (!Verification(ginseng)) {
                                showToast("请输入参与公司！");
                            } else if (!Verification(fengge)) {
                                showToast("请输入风格！");
                            } else if (!Verification(zhuangxiu)) {
                                showToast("请输入装修时间！");
                            } else if (!Verification(gongsi)) {
                                showToast("请输入公司！");
                            } else if (!Verification(dizhi)) {
                                showToast("请输入地址！");
                            }
                        }
                    }
                }
                break;
            case 2:
                if (typeOne != 0 && Verification(kehu) && Verification(dianhua) && xiangMuShuXing != 0 && identity != 0 && Verification(mianji) && Verification(yusuan) && housefrom != 0 && house != 0 && saletype != 0 && Verification(lianfang) && Verification(ginseng) && Verification(fengge) && Verification(zhuangxiu) && Verification(dizhi) && liansuonex != 0 && Verification(dianpu)) {

                    Map<String, Object> map = new HashMap<>();
                    map.put("KeHuBaseID", clientID);
                    map.put("XingMing", kehu);
                    map.put("ShouJiHaoYi", dianhua);
                    map.put("ShenFenID", identity);
                    map.put("MianJi", mianji);
                    map.put("LeiXingYi", typeOne);
                    map.put("LeiXingEr", typeTwo);
                    map.put("ZhuangTai", 17);
                    map.put("ZhuangXiuYuSuan", yusuan);
                    map.put("FangYuan", housefrom);
                    map.put("YuJiZhuangXiu", house);
                    map.put("ChengJiaoLeiXing", saletype);
                    map.put("IsChain", liansuonex);
                    map.put("YuJiLiangFang", lianfang);
                    map.put("PartCompany", ginseng);
                    map.put("DecorateStyle", fengge);
                    map.put("YuJiZhuangXiuShiJian", zhuangxiu);

                    map.put("GongSiMingCheng", gongsi);
                    map.put("LiangFangDiZhi", dizhi);
                    map.put("QiYeXingZhiID", qiYeXingZhiID);
                    map.put("QiYeGuiMoID", qiYeGuiMoID);
                    map.put("GongSiChengLiDate", chengli);
                    //QiYeRuZhu
                    map.put("QiYeRuZhu", qiYeRuZhu);

                    map.put("XiangMuShuXing", xiangMuShuXing);
                    //zijian ZhuCeZiJin
                    map.put("ZhuCeZiJin", zijian);
                    //  zhiMingQiYe
                    map.put("ZhiMingQiYe", zhiMingQiYe);
                    //ZhuCeDiZhi
                    map.put("ZhuCeDiZhi", bangong);
                    if (saletype == 1) {
                        map.put("MianZuQi", mianZuQi);
                    } else {
                        map.put("JiaoFangShiJian", jiaofang);
                    }
                    OkhttpUtils.doPost(url, map, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.e("tag", e.getMessage());
                        }
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            final String string = response.body().string();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        JSONObject jsonObject = new JSONObject(string);
                                        int statusCode = jsonObject.getInt("StatusCode");
                                        if (statusCode == 0) {
                                            finish();
                                        } else {
                                            String statusMsg = jsonObject.getString("StatusMsg");
                                            showToast(statusMsg);
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    });

                } else if (typeOne == 0) {
                    showToast("请选择项目类型");
                } else if (!Verification(kehu)) {
                    showToast("请输入名字");
                } else if (!Verification(dianhua)) {
                    showToast("请输入电话");
                } else if (xiangMuShuXing == 0) {
                    showToast("请选择项目属性");
                } else if (!Verification(mianji)) {
                    showToast("请输入面积");
                } else if (!Verification(yusuan)) {
                    showToast("请输入预算");
                } else if (identity == 0) {
                    showToast("请选择身份");
                } else if (housefrom == 0) {
                    showToast("请选择房源");
                } else if (house == 0) {
                    showToast("请选择房屋");
                } else if (saletype == 0) {
                    showToast("请选择成交类型！");
                } else if (!Verification(lianfang)) {
                    showToast("请输入量房时间！");
                } else if (saletype != 0) {
                    if (saletype == 1) {
                        if (mianZuQi == 0) {
                            showToast("请选择免租日期！");
                        } else {
                            if (!Verification(ginseng)) {
                                showToast("请输入参与公司！");
                            } else if (!Verification(fengge)) {
                                showToast("请输入风格！");
                            } else if (!Verification(zhuangxiu)) {
                                showToast("请输入装修时间！");
                            } else if (!Verification(dianpu)) {
                                showToast("请输入店铺！");
                            } else if (!Verification(dizhi)) {
                                showToast("请输入地址！");
                            } else if (liansuonex == 0) {
                                showToast("请输入连锁！");
                            }
                        }
                    } else {
                        if (!Verification(jiaofang)) {
                            showToast("请输入交房时间！");
                        } else {
                            if (!Verification(ginseng)) {
                                showToast("请输入参与公司！");
                            } else if (!Verification(fengge)) {
                                showToast("请输入风格！");
                            } else if (!Verification(zhuangxiu)) {
                                showToast("请输入装修时间！");
                            } else if (!Verification(dianpu)) {
                                showToast("请输入店铺！");
                            } else if (!Verification(dizhi)) {
                                showToast("请输入地址！");
                            } else if (liansuonex == 0) {
                                showToast("请输入连锁！");
                            }
                        }
                    }
                }
                break;
            case 3:
                if (typeOne != 0 && Verification(kehu) && Verification(dianhua) && xiangMuShuXing != 0 && identity != 0 && Verification(mianji) && Verification(yusuan) && housefrom != 0 && house != 0 && saletype != 0 && Verification(lianfang) && Verification(ginseng) && Verification(fengge) && Verification(zhuangxiu) && Verification(dizhi) && liansuonex != 0 && Verification(dianpu)) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("KeHuBaseID", clientID);
                    map.put("XingMing", kehu);
                    map.put("ShouJiHaoYi", dianhua);
                    map.put("ShenFenID", identity);
                    map.put("MianJi", mianji);
                    map.put("LeiXingYi", typeOne);
                    map.put("LeiXingEr", typeTwo);
                    map.put("ZhuangXiuYuSuan", yusuan);
                    map.put("FangYuan", housefrom);
                    map.put("YuJiZhuangXiu", house);
                    map.put("ChengJiaoLeiXing", saletype);

                    map.put("IsChain", liansuonex);
                    map.put("YuJiLiangFang", lianfang);
                    map.put("PartCompany", ginseng);
                    map.put("ZhuangTai", 17);
                    map.put("DecorateStyle", fengge);
                    map.put("YuJiZhuangXiuShiJian", zhuangxiu);
                    map.put("GongSiMingCheng", gongsi);
                    //店铺
                    // map.put("GongSiMingCheng", gongsi);
                    map.put("LiangFangDiZhi", dizhi);
                    map.put("XiangMuShuXing", xiangMuShuXing);
                    map.put("QiYeXingZhiID", qiYeXingZhiID);
                    map.put("QiYeGuiMoID", qiYeGuiMoID);
                    map.put("GongSiChengLiDate", chengli);
                    //QiYeRuZhu
                    map.put("QiYeRuZhu", qiYeRuZhu);
                    //zijian ZhuCeZiJin
                    map.put("ZhuCeZiJin", zijian);
                    //  zhiMingQiYe
                    map.put("ZhiMingQiYe", zhiMingQiYe);
                    //ZhuCeDiZhi
                    map.put("ZhuCeDiZhi", bangong);
                    if (saletype == 1) {
                        map.put("MianZuQi", mianZuQi);
                    } else {
                        map.put("JiaoFangShiJian", jiaofang);
                    }
                    OkhttpUtils.doPost(url, map, new Callback() {
                        @Override
                        public void onFailure(Call call, final IOException e) {
                            Log.e("tag", e.getMessage());
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    showToast(e.getMessage());
                                }
                            });
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            final String string = response.body().string();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        JSONObject jsonObject = new JSONObject(string);
                                        int statusCode = jsonObject.getInt("StatusCode");
                                        if (statusCode == 0) {
                                            finish();
                                        } else {
                                            String statusMsg = jsonObject.getString("StatusMsg");
                                            showToast(statusMsg);
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    });
                } else if (typeOne == 0) {
                    showToast("请选择项目类型");
                } else if (!Verification(kehu)) {
                    showToast("请输入名字");
                } else if (xiangMuShuXing == 0) {
                    showToast("请选择项目属性");
                } else if (!Verification(dianhua)) {
                    showToast("请输入电话");
                } else if (!Verification(mianji)) {
                    showToast("请输入面积");
                } else if (!Verification(yusuan)) {
                    showToast("请输入预算");
                } else if (identity == 0) {
                    showToast("请选择身份");
                } else if (housefrom == 0) {
                    showToast("请选择房源");
                } else if (house == 0) {
                    showToast("请选择房屋");
                } else if (saletype == 0) {
                    showToast("请选择成交类型！");
                } else if (saletype != 0) {
                    if (saletype == 1) {
                        if (mianZuQi == 0) {
                            showToast("请选择免租日期！");
                        } else {
                            if (!Verification(lianfang)) {
                                showToast("请输入量房时间！");
                            } else if (!Verification(ginseng)) {
                                showToast("请输入参司！");
                            } else if (!Verification(fengge)) {
                                showToast("请输入风格！");
                            } else if (!Verification(zhuangxiu)) {
                                showToast("请输入装修时间！");
                            } else if (!Verification(dianpu)) {
                                showToast("请输入店铺！");
                            } else if (!Verification(dizhi)) {
                                showToast("请输入地址！");
                            } else if (liansuonex == 0) {
                                showToast("请输入连锁！");
                            }
                        }
                    } else {
                        if (!Verification(jiaofang)) {
                            showToast("请输入交房时间！");
                        } else {
                            if (!Verification(lianfang)) {
                                showToast("请输入量房时间！");
                            } else if (!Verification(ginseng)) {
                                showToast("请输入参司！");
                            } else if (!Verification(fengge)) {
                                showToast("请输入风格！");
                            } else if (!Verification(zhuangxiu)) {
                                showToast("请输入装修时间！");
                            } else if (!Verification(dianpu)) {
                                showToast("请输入店铺！");
                            } else if (!Verification(dizhi)) {
                                showToast("请输入地址！");
                            } else if (liansuonex == 0) {
                                showToast("请输入连锁！");
                            }
                        }
                    }
                }
                break;
            case 4:

                if (typeOne != 0 && Verification(kehu) && Verification(dianhua) && xiangMuShuXing != 0 && identity != 0 && Verification(mianji) && Verification(yusuan) && housefrom != 0 && house != 0 && saletype != 0 && need != 0 && Verification(lianfang) && Verification(ginseng) && Verification(zhuangxiu) && Verification(dizhi) && matEnddowment != 0 && Verification(danjia) && zhaoBiao != 0 && Verification(pingpai)) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("KeHuBaseID", clientID);
                    map.put("XingMing", kehu);
                    map.put("ShouJiHaoYi", dianhua);
                    map.put("ShenFenID", identity);
                    map.put("MianJi", mianji);
                    map.put("LeiXingYi", typeOne);
                    map.put("LeiXingEr", typeTwo);
                    map.put("ZhuangXiuYuSuan", yusuan);
                    map.put("FangYuan", housefrom);
                    map.put("YuJiZhuangXiu", house);
                    map.put("ZhuangTai", 17);
                    map.put("ChengJiaoLeiXing", saletype);
                    map.put("IsChain", liansuonex);
                    map.put("YuJiLiangFang", lianfang);
                    map.put("PartCompany", ginseng);
                    map.put("YuJiZhuangXiuShiJian", zhuangxiu);
                    map.put("GongSiMingCheng", gongsi);
                    map.put("LiangFangDiZhi", dizhi);
                    map.put("QiYeXingZhiID", qiYeXingZhiID);
                    map.put("QiYeGuiMoID", qiYeGuiMoID);
                    map.put("GongSiChengLiDate", chengli);
                    //QiYeRuZhu
                    map.put("QiYeRuZhu", qiYeRuZhu);
                    //zijian ZhuCeZiJin
                    map.put("ZhuCeZiJin", zijian);
                    //  zhiMingQiYe
                    map.put("ZhiMingQiYe", zhiMingQiYe);
                    //ZhuCeDiZhi
                    map.put("ZhuCeDiZhi", bangong);
                    map.put("XiangMuShuXing", xiangMuShuXing);
                    map.put("MatEnddowment", matEnddowment);
                    map.put("ZuJinDanJia", danjia);
                    map.put("ZhaoBiao", zhaoBiao);
                    map.put("BrandName", pingpai);
                    if (saletype == 1) {
                        map.put("MianZuQi", mianZuQi);
                    } else {
                        map.put("JiaoFangShiJian", jiaofang);
                    }
                    OkhttpUtils.doPost(url, map, new Callback() {
                        @Override
                        public void onFailure(Call call, final IOException e) {
                            Log.e("tag", e.getMessage());
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    showToast(e.getMessage());
                                }
                            });
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            final String string = response.body().string();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        JSONObject jsonObject = new JSONObject(string);
                                        int statusCode = jsonObject.getInt("StatusCode");
                                        if (statusCode == 0) {
                                            finish();
                                        } else {
                                            String statusMsg = jsonObject.getString("StatusMsg");
                                            showToast(statusMsg);
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    });
                } else if (typeOne == 0) {
                    showToast("请选择项目类型");
                } else if (!Verification(kehu)) {
                    showToast("请输入名字");
                } else if (xiangMuShuXing == 0) {
                    showToast("请选择项目属性");
                } else if (!Verification(dianhua)) {
                    showToast("请输入电话");
                } else if (!Verification(mianji)) {
                    showToast("请输入面积");
                } else if (!Verification(yusuan)) {
                    showToast("请输入预算");
                } else if (identity == 0) {
                    showToast("请选择身份");
                } else if (housefrom == 0) {
                    showToast("请选择房源");
                } else if (house == 0) {
                    showToast("请选择房屋");
                } else if (saletype == 0) {
                    showToast("请选择成交类型！");
                } else if (need == 0) {
                    showToast("请选择装修需求！");
                } else if (saletype != 0) {
                    if (saletype == 1) {
                        if (mianZuQi == 0) {
                            showToast("请选择免租日期！");
                        } else {
                            if (!Verification(lianfang)) {
                                showToast("请输入量房时间！");
                            } else if (!Verification(ginseng)) {
                                showToast("请输入参与公司！");
                            } else if (!Verification(zhuangxiu)) {
                                showToast("请输入装修时间！");
                            } else if (!Verification(dizhi)) {
                                showToast("请输入地址！");
                            } else if (matEnddowment == 0) {
                                showToast("请输入垫资！");
                            } else if (!Verification(danjia)) {
                                showToast("请输入单价！");
                            } else if (zhaoBiao == 0) {
                                showToast("请选择招标！");
                            } else if (!Verification(pingpai)) {
                                showToast("请输入品牌！");
                            }
                        }
                    } else {
                        if (!Verification(jiaofang)) {
                            showToast("请输入交房时间！");
                        } else {
                            if (!Verification(lianfang)) {
                                showToast("请输入量房时间！");
                            } else if (!Verification(ginseng)) {
                                showToast("请输入参与公司！");
                            } else if (!Verification(zhuangxiu)) {
                                showToast("请输入装修时间！");
                            } else if (!Verification(dizhi)) {
                                showToast("请输入地址！");
                            } else if (matEnddowment == 0) {
                                showToast("请输入垫资！");
                            } else if (!Verification(danjia)) {
                                showToast("请输入单价！");
                            } else if (zhaoBiao == 0) {
                                showToast("请选择招标！");
                            } else if (!Verification(pingpai)) {
                                showToast("请输入品牌！");
                            }

                        }
                    }

                }
                break;
            case 6:
                if (typeOne != 0 && Verification(kehu) && Verification(dianhua) && identity != 0 && xiangMuShuXing != 0 && Verification(mianji) && Verification(yusuan) && housefrom != 0 && house != 0 && saletype != 0 && Verification(lianfang) && Verification(ginseng) && Verification(zhuangxiu) && Verification(dizhi) && liansuonex != 0 && Verification(pingpai) && serviceGroups != 0) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("KeHuBaseID", clientID);
                    map.put("XingMing", kehu);
                    map.put("ShouJiHaoYi", dianhua);
                    map.put("ShenFenID", identity);
                    map.put("MianJi", mianji);
                    map.put("LeiXingYi", typeOne);
                    map.put("LeiXingEr", typeTwo);
                    map.put("ZhuangXiuYuSuan", yusuan);
                    map.put("FangYuan", housefrom);
                    map.put("YuJiZhuangXiu", house);
                    map.put("ChengJiaoLeiXing", saletype);
                    map.put("IsChain", liansuonex);
                    map.put("YuJiLiangFang", lianfang);
                    map.put("PartCompany", ginseng);
                    map.put("ZhuangTai", 17);
                    map.put("DecorateStyle", fengge);
                    map.put("YuJiZhuangXiuShiJian", zhuangxiu);
                    map.put("ServiceGroups", serviceGroups);
                    map.put("LiangFangDiZhi", dizhi);
                    map.put("QiYeXingZhiID", qiYeXingZhiID);
                    map.put("QiYeGuiMoID", qiYeGuiMoID);
                    map.put("GongSiChengLiDate", chengli);
                    //QiYeRuZhu
                    map.put("QiYeRuZhu", qiYeRuZhu);
                    //zijian ZhuCeZiJin
                    map.put("ZhuCeZiJin", zijian);
                    //  zhiMingQiYe
                    map.put("ZhiMingQiYe", zhiMingQiYe);
                    //ZhuCeDiZhi
                    map.put("ZhuCeDiZhi", bangong);
                    map.put("XiangMuShuXing", xiangMuShuXing);
                    map.put("BrandName", pingpai);
                    if (saletype == 1) {
                        map.put("MianZuQi", mianZuQi);
                    } else {
                        map.put("JiaoFangShiJian", jiaofang);
                    }
                    OkhttpUtils.doPost(url, map, new Callback() {
                        @Override
                        public void onFailure(Call call, final IOException e) {
                            Log.e("tag", e.getMessage());
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    showToast(e.getMessage());
                                }
                            });
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            final String string = response.body().string();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        JSONObject jsonObject = new JSONObject(string);
                                        int statusCode = jsonObject.getInt("StatusCode");
                                        if (statusCode == 0) {
                                            finish();
                                        } else {
                                            String statusMsg = jsonObject.getString("StatusMsg");
                                            showToast(statusMsg);
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    });
                } else if (typeOne == 0) {
                    showToast("请选择项目类型");
                } else if (xiangMuShuXing == 0) {
                    showToast("请选择项目属性");
                } else if (!Verification(kehu)) {
                    showToast("请输入名字");
                } else if (!Verification(dianhua)) {
                    showToast("请输入电话");
                } else if (!Verification(mianji)) {
                    showToast("请输入面积");
                } else if (!Verification(yusuan)) {
                    showToast("请输入预算");
                } else if (identity == 0) {
                    showToast("请选择身份");
                } else if (housefrom == 0) {
                    showToast("请选择房源");
                } else if (house == 0) {
                    showToast("请选择房屋");
                } else if (saletype == 0) {
                    showToast("请选择成交类型！");
                } else if (saletype != 0) {
                    if (saletype == 1) {
                        if (mianZuQi == 0) {
                            showToast("请选择免租日期！");
                        } else {
                            if (!Verification(lianfang)) {
                                showToast("请输入量房时间！");
                            } else if (!Verification(ginseng)) {
                                showToast("请输入参与公司！");
                            } else if (!Verification(zhuangxiu)) {
                                showToast("请输入装修时间！");
                            } else if (!Verification(pingpai)) {
                                showToast("请输入品牌！");
                            } else if (!Verification(dizhi)) {
                                showToast("请输入地址！");
                            } else if (serviceGroups == 0) {
                                showToast("请选择服务！");
                            } else if (liansuonex == 0) {
                                showToast("请输入连锁！");
                            }
                        }
                    } else {
                        if (!Verification(jiaofang)) {
                            showToast("请输入交房时间！");
                        } else {
                            if (!Verification(lianfang)) {
                                showToast("请输入量房时间！");
                            } else if (!Verification(ginseng)) {
                                showToast("请输入参与公司！");
                            } else if (!Verification(zhuangxiu)) {
                                showToast("请输入装修时间！");
                            } else if (!Verification(pingpai)) {
                                showToast("请输入品牌！");
                            } else if (!Verification(dizhi)) {
                                showToast("请输入地址！");
                            } else if (serviceGroups == 0) {
                                showToast("请选择服务！");
                            } else if (liansuonex == 0) {
                                showToast("请输入连锁！");
                            }
                        }
                    }
                }
                break;
            case 7:
                if (typeOne != 0 && Verification(kehu) && Verification(dianhua) && xiangMuShuXing != 0 && identity != 0 && Verification(mianji) && Verification(yusuan) && housefrom != 0 && house != 0 && saletype != 0 && need != 0 && Verification(lianfang) && Verification(ginseng) && Verification(zhuangxiu) && Verification(dizhi)) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("KeHuBaseID", clientID);
                    map.put("XingMing", kehu);
                    map.put("ShouJiHaoYi", dianhua);
                    map.put("ShenFenID", identity);
                    map.put("MianJi", mianji);
                    map.put("LeiXingYi", typeOne);
                    map.put("LeiXingEr", typeTwo);
                    map.put("ZhuangXiuYuSuan", yusuan);
                    map.put("FangYuan", housefrom);
                    map.put("YuJiZhuangXiu", house);
                    map.put("ChengJiaoLeiXing", saletype);
                    map.put("ZhuangXiuXuQiu", need);
                    map.put("YuJiLiangFang", lianfang);
                    map.put("ZhuangTai", 17);
                    map.put("XiangMuShuXing", xiangMuShuXing);
                    map.put("PartCompany", ginseng);
                    map.put("LiangFangDiZhi", dizhi);
                    map.put("QiYeXingZhiID", qiYeXingZhiID);
                    map.put("QiYeGuiMoID", qiYeGuiMoID);
                    map.put("GongSiChengLiDate", chengli);
                    //QiYeRuZhu
                    map.put("QiYeRuZhu", qiYeRuZhu);
                    //zijian ZhuCeZiJin
                    map.put("ZhuCeZiJin", zijian);
                    //  zhiMingQiYe
                    map.put("ZhiMingQiYe", zhiMingQiYe);
                    //ZhuCeDiZhi
                    map.put("ZhuCeDiZhi", bangong);
                    if (saletype == 1) {
                        map.put("MianZuQi", mianZuQi);
                    } else {
                        map.put("JiaoFangShiJian", jiaofang);
                    }
                    OkhttpUtils.doPost(url, map, new Callback() {
                        @Override
                        public void onFailure(Call call, final IOException e) {
                            Log.e("tag", e.getMessage());
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    showToast(e.getMessage());
                                }
                            });
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            final String string = response.body().string();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        JSONObject jsonObject = new JSONObject(string);
                                        int statusCode = jsonObject.getInt("StatusCode");
                                        if (statusCode == 0) {
                                            finish();
                                        } else {
                                            String statusMsg = jsonObject.getString("StatusMsg");
                                            showToast(statusMsg);
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    });
                } else if (typeOne == 0) {
                    showToast("请选择项目类型");
                } else if (xiangMuShuXing == 0) {
                    showToast("请选择项目属性");
                } else if (!Verification(kehu)) {
                    showToast("请输入名字");
                } else if (!Verification(dianhua)) {
                    showToast("请输入电话");
                } else if (!Verification(mianji)) {
                    showToast("请输入面积");
                } else if (!Verification(yusuan)) {
                    showToast("请输入预算");
                } else if (identity == 0) {
                    showToast("请选择身份");
                } else if (housefrom == 0) {
                    showToast("请选择房源");
                } else if (house == 0) {
                    showToast("请选择房屋");
                } else if (saletype == 0) {
                    showToast("请选择成交类型！");

                } else if (saletype != 0) {
                    if (saletype == 1) {
                        if (mianZuQi == 0) {
                            showToast("请选择免租日期！");
                        } else {
                            if (need == 0) {
                                showToast("请选择装修需求！");
                            } else if (!Verification(lianfang)) {
                                showToast("请输入量房时间！");
                            } else if (!Verification(ginseng)) {
                                showToast("请输入参与公司！");
                            } else if (!Verification(zhuangxiu)) {
                                showToast("请输入装修时间！");
                            } else if (!Verification(dizhi)) {
                                showToast("请输入地址！");
                            }
                        }
                    } else {
                        if (!Verification(jiaofang)) {
                            showToast("请输入交房时间！");
                        } else {
                            if (need == 0) {
                                showToast("请选择装修需求！");
                            } else if (!Verification(lianfang)) {
                                showToast("请输入量房时间！");
                            } else if (!Verification(ginseng)) {
                                showToast("请输入参与公司！");
                            } else if (!Verification(zhuangxiu)) {
                                showToast("请输入装修时间！");
                            } else if (!Verification(dizhi)) {
                                showToast("请输入地址！");
                            }
                        }
                    }
                }
                break;
            default:
                if (typeOne == 0) {
                    showToast("请输入项目类型");
                }

                break;
        }
//serviceGroups
    }

    private void qitasave(int stats) {
        String kehu = tvAddCustomer.getText().toString();
        String dianhua = tvAddTelephone.getText().toString();
        String mianji = tvAddMeasure.getText().toString();
        String yusuan = tvAddBudget.getText().toString();
        String jiaofang = tvAddMake.getText().toString();
        String lianfang = tvAddDemand.getText().toString();
        String ginseng = tvAddGinseng.getText().toString();
        String fengge = tvAddStyle.getText().toString();
        String zhuangxiu = tvAddRenovation.getText().toString();
        String bangong = tvAddWorkan.getText().toString();
        String dizhi = tvAddAddress.getText().toString();
        String gongsi = tvAddCompany.getText().toString();
        String dianpu = tvAddShop.getText().toString();
        String danjia = tvAddPrice.getText().toString();
        String pingpai = tvAddPingpai.getText().toString();
        String chengli = tvAddSetup.getText().toString();
        String zijian = tvAddFunds.getText().toString();
        if (stats == 0) {
            Map<String, Object> map = new HashMap<>();
            map.put("LeiXingYi", typeOne);
            map.put("LeiXingEr", typeTwo);
            map.put("KeHuBaseID", clientID);
            map.put("XingMing", kehu);
            map.put("ShouJiHaoYi", dianhua);
            Log.e("tag", xiangMuShuXing + "");
            map.put("XiangMuShuXing", xiangMuShuXing);
            map.put("ShenFenID", identity);
            map.put("MianJi", mianji);
            map.put("ServiceGroups", serviceGroups);
            map.put("ZhuangXiuYuSuan", yusuan);
            map.put("FangYuan", housefrom);
            map.put("YuJiZhuangXiu", house);
            map.put("ChengJiaoLeiXing", saletype);
            map.put("JiaoFangShiJian", jiaofang);
            map.put("IsChain", liansuonex);
            map.put("YuJiLiangFang", lianfang);
            map.put("PartCompany", ginseng);
            map.put("PartCompany", ginseng);
            map.put("ZhuangTai", 0);
            map.put("ZhuangXiuXuQiu", need);
            map.put("YuJiLiangFang", lianfang);
            map.put("GongSiMingCheng", gongsi);
            map.put("LiangFangDiZhi", dizhi);
            map.put("QiYeXingZhiID", qiYeXingZhiID);
            map.put("QiYeGuiMoID", qiYeGuiMoID);
            map.put("GongSiChengLiDate", chengli);
            //zijian ZhuCeZiJin
            map.put("ZhuCeZiJin", zijian);
            map.put("DecorateStyle", fengge);
            map.put("YuJiZhuangXiuShiJian", zhuangxiu);
            map.put("MatEnddowment", matEnddowment);
            map.put("ZuJinDanJia", danjia);
            map.put("ZhaoBiao", zhaoBiao);
            map.put("ZhuCeDiZhi", bangong);
            map.put("BrandName", pingpai);
            map.put("ZhiMingQiYe", zhiMingQiYe);
            map.put("QiYeRuZhu", qiYeRuZhu);
            map.put("XuQiuLeiXing", fu);
            Log.e("tag---", fu + "===");
            map.put("BuMenShuXing", secondleave);
            map.put("LabelId", zi);
            map.put("isjoin", Integer.parseInt(aid));
            tijiao(map);
        } else if (stats == 17) {
            if (Verification(kehu) && Verification(dianhua) && identity != 0 && Verification(gongsi) && Verification(dizhi)) {
                Map<String, Object> map = new HashMap<>();
                map.put("LeiXingYi", typeOne);
                map.put("LeiXingEr", typeTwo);
                map.put("KeHuBaseID", clientID);
                map.put("XingMing", kehu);
                map.put("ShouJiHaoYi", dianhua);
                Log.e("tag", xiangMuShuXing + "");
                map.put("XiangMuShuXing", xiangMuShuXing);
                map.put("ShenFenID", identity);
                map.put("MianJi", mianji);
                map.put("ServiceGroups", serviceGroups);
                map.put("ZhuangXiuYuSuan", yusuan);
                map.put("FangYuan", housefrom);
                map.put("YuJiZhuangXiu", house);
                map.put("ChengJiaoLeiXing", saletype);
                map.put("JiaoFangShiJian", jiaofang);
                map.put("IsChain", liansuonex);
                map.put("YuJiLiangFang", lianfang);
                map.put("PartCompany", ginseng);
                map.put("PartCompany", ginseng);
                map.put("ZhuangTai", 17);
                map.put("ZhuangXiuXuQiu", need);
                map.put("YuJiLiangFang", lianfang);
                map.put("GongSiMingCheng", gongsi);
                map.put("LiangFangDiZhi", dizhi);
                map.put("QiYeXingZhiID", qiYeXingZhiID);
                map.put("QiYeGuiMoID", qiYeGuiMoID);
                map.put("GongSiChengLiDate", chengli);
                //zijian ZhuCeZiJin
                map.put("ZhuCeZiJin", zijian);
                map.put("DecorateStyle", fengge);
                map.put("YuJiZhuangXiuShiJian", zhuangxiu);
                map.put("MatEnddowment", matEnddowment);
                map.put("ZuJinDanJia", danjia);
                map.put("ZhaoBiao", zhaoBiao);
                map.put("ZhuCeDiZhi", bangong);
                map.put("BrandName", pingpai);
                map.put("ZhiMingQiYe", zhiMingQiYe);
                map.put("QiYeRuZhu", qiYeRuZhu);
                map.put("XuQiuLeiXing", fu);
                map.put("LabelId", zi);
                tijiao(map);
            } else if (!Verification(kehu)) {
                showToast("请输入名字");
            } else if (!Verification(dianhua)) {
                showToast("请输入电话");
            } else if (identity == 0) {
                showToast("请选择身份");
            } else if (!Verification(gongsi)) {
                showToast("请输入公司名称");
            } else if (!Verification(dizhi)) {
                showToast("请输入公司地址");
            }
        }
    }

    private void tijiao(Map<String, Object> map) {
        String url = ApiEngine.SW_API_HOST + "AppAgent/UpdateInfo";
        OkhttpUtils.doPost(url, map, new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                Log.e("tag", e.getMessage());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showToast(e.getMessage());
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject jsonObject = new JSONObject(string);
                            int statusCode = jsonObject.getInt("StatusCode");
                            if (statusCode == 0) {
                                finish();
                            } else {
                                String statusMsg = jsonObject.getString("StatusMsg");
                                showToast(statusMsg);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        });
    }

    private boolean Verification(String msg) {
        if (msg.equals("")) {
            return false;

        } else {
            return true;
        }
    }

    private void baocun() {
        String kehu = tvAddCustomer.getText().toString();
        String dianhua = tvAddTelephone.getText().toString();
        String mianji = tvAddMeasure.getText().toString();
        String yusuan = tvAddBudget.getText().toString();
        String jiaofang = tvAddMake.getText().toString();
        String lianfang = tvAddDemand.getText().toString();
        String ginseng = tvAddGinseng.getText().toString();
        String fengge = tvAddStyle.getText().toString();
        String zhuangxiu = tvAddRenovation.getText().toString();
        String bangong = tvAddWorkan.getText().toString();
        String dizhi = tvAddAddress.getText().toString();
        String gongsi = tvAddCompany.getText().toString();
        String dianpu = tvAddShop.getText().toString();
        String danjia = tvAddPrice.getText().toString();
        String pingpai = tvAddPingpai.getText().toString();
        String chengli = tvAddSetup.getText().toString();
        String zijian = tvAddFunds.getText().toString();
        Map<String, Object> map = new HashMap<>();
        map.put("LeiXingYi", typeOne);
        map.put("LeiXingEr", typeTwo);
        map.put("KeHuBaseID", clientID);
        map.put("XingMing", kehu);
        map.put("ShouJiHaoYi", dianhua);
        Log.e("tag", xiangMuShuXing + "");
        map.put("XiangMuShuXing", xiangMuShuXing);
        map.put("ShenFenID", identity);
        map.put("MianJi", mianji);
        map.put("ServiceGroups", serviceGroups);
        map.put("ZhuangXiuYuSuan", yusuan);
        map.put("FangYuan", housefrom);
        map.put("YuJiZhuangXiu", house);
        map.put("ChengJiaoLeiXing", saletype);
        map.put("JiaoFangShiJian", jiaofang);
        map.put("IsChain", liansuonex);
        map.put("YuJiLiangFang", lianfang);
        map.put("PartCompany", ginseng);
        map.put("PartCompany", ginseng);
        map.put("ZhuangTai", 0);
        map.put("ZhuangXiuXuQiu", need);
        map.put("YuJiLiangFang", lianfang);
        map.put("GongSiMingCheng", gongsi);
        map.put("LiangFangDiZhi", dizhi);
        map.put("QiYeXingZhiID", qiYeXingZhiID);
        map.put("QiYeGuiMoID", qiYeGuiMoID);
        map.put("GongSiChengLiDate", chengli);
        //zijian ZhuCeZiJin
        map.put("MianZuQi", mianZuQi);
        map.put("ZhuCeZiJin", zijian);
        map.put("DecorateStyle", fengge);
        map.put("YuJiZhuangXiuShiJian", zhuangxiu);
        map.put("MatEnddowment", matEnddowment);
        map.put("ZuJinDanJia", danjia);
        map.put("ZhaoBiao", zhaoBiao);
        map.put("ZhuCeDiZhi", bangong);
        map.put("BrandName", pingpai);
        map.put("ZhiMingQiYe", zhiMingQiYe);
        map.put("QiYeRuZhu", qiYeRuZhu);
        String url = ApiEngine.SW_API_HOST + "AppAgent/UpdateInfo";
        OkhttpUtils.doPost(url, map, new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                Log.e("tag", e.getMessage());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showToast(e.getMessage());
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String string = response.body().string();


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject jsonObject = new JSONObject(string);
                            int statusCode = jsonObject.getInt("StatusCode");
                            if (statusCode == 0) {
                                finish();
                            } else {
                                String statusMsg = jsonObject.getString("StatusMsg");
                                showToast(statusMsg);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
