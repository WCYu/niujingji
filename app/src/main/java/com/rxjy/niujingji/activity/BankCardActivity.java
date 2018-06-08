package com.rxjy.niujingji.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.rxjy.niujingji.R;
import com.rxjy.niujingji.adapter.BankCardAdapter;
import com.rxjy.niujingji.commons.App;
import com.rxjy.niujingji.commons.base.BaseActivity;
import com.rxjy.niujingji.commons.utils.AutoUtils;
import com.rxjy.niujingji.entity.BankCardInfo;
import com.rxjy.niujingji.entity.UserInfo;
import com.rxjy.niujingji.mvp.contract.BankCardContract;
import com.rxjy.niujingji.mvp.presenter.BankCardPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BankCardActivity extends BaseActivity<BankCardPresenter> implements BankCardContract.View, AdapterView.OnItemClickListener {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.lv_bank_card)
    ListView lvBankCard;

    public static final String TITLE = "银行卡";

    private List<BankCardInfo> cardList;

    private BankCardAdapter mAdapter;

    @Override
    public int getLayout() {
        return R.layout.activity_bank_card;
    }

    @Override
    public void initData() {
        initTitle();
        initCardData();
    }

    private void initTitle() {
        tvTitle.setText(TITLE);
    }

    private void initCardData() {

        cardList = new ArrayList<>();

        mAdapter = new BankCardAdapter(this, cardList);

        lvBankCard.setAdapter(mAdapter);

        View footerView = View.inflate(this, R.layout.list_item_add_bank, null);

        AutoUtils.auto(footerView);

        lvBankCard.addFooterView(footerView);

        lvBankCard.setOnItemClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getLoginUserInfo(App.cardNo, App.token);
    }

    @Override
    protected BankCardPresenter onCreatePresenter() {
        return new BankCardPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (cardList.size() > 0) {
            if (position == cardList.size()) {
                showToast("只允许添加一张银行卡");
            } else {
                Intent intent = new Intent(this, UpdBankCardActivity.class);
                startActivity(intent);
            }
        } else {
            startActivity(new Intent(this, AddBankCardActivity.class));
        }
    }

    @Override
    public void responseLogin(UserInfo.User data) {
        if (mAdapter != null) {
            cardList.clear();
            if (App.baseInfo.getBankCard() != null) {
                App.baseInfo.setBankBgImage(data.getBaseinfo().getBankBgImage());
                cardList.add(new BankCardInfo(App.baseInfo.getBankUserName(), App.baseInfo.getBankName(), App.baseInfo.getBankCard()));
                mAdapter.notifyDataSetChanged();
            }
        }
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
