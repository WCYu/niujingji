package com.rxjy.niujingji.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rxjy.niujingji.R;
import com.rxjy.niujingji.adapter.HomeAdapter;
import com.rxjy.niujingji.commons.App;
import com.rxjy.niujingji.commons.Constants;
import com.rxjy.niujingji.commons.base.BaseActivity;
import com.rxjy.niujingji.entity.ClientListInfo;
import com.rxjy.niujingji.entity.GiftGetBean;
import com.rxjy.niujingji.mvp.contract.HomeContract;
import com.rxjy.niujingji.mvp.presenter.HomePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity<HomePresenter> implements HomeContract.View, AdapterView.OnItemClickListener {

    @Bind(R.id.et_search)
    EditText etSearch;
    @Bind(R.id.tv_search_line)
    TextView tvSearchLine;
    @Bind(R.id.lv_search)
    ListView lvSearch;
    @Bind(R.id.tv_search_data_tip)
    TextView tvSearchDataTip;
    @Bind(R.id.rl_search_data_tip)
    RelativeLayout rlSearchDataTip;

    private List<ClientListInfo.ClientInfo> clientList;

    private HomeAdapter mAdapter;
    private String searchContent = "";

    @Override
    public int getLayout() {
        return R.layout.activity_search;
    }

    @Override
    public void initData() {
        initLine();
        initSearchData();
    }

    private void initLine() {

        EditText[] etArray = new EditText[]{etSearch};
        TextView[] tvArray = new TextView[]{tvSearchLine};

        lineSelector(etArray, tvArray);
    }

    private void initSearchData() {

        clientList = new ArrayList<>();

        mAdapter = new HomeAdapter(this, clientList);

        lvSearch.setAdapter(mAdapter);

        lvSearch.setOnItemClickListener(this);

    }

    private void isShowTip() {
        if (clientList.size() != 0) {
            rlSearchDataTip.setVisibility(View.GONE);
        } else {
            rlSearchDataTip.setVisibility(View.VISIBLE);
            tvSearchDataTip.setText("没有搜索到相关内容！");
        }
    }

    @Override
    protected HomePresenter onCreatePresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.iv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_search:
                searchContent = etSearch.getText().toString().trim();
                if (TextUtils.isEmpty(searchContent)) {
                    showToast("请输入搜索内容");
                    return;
                }
                //收起软键盘
                ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(SearchActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                mPresenter.getClientList(App.cardNo, searchContent);
                break;
        }
    }

    @Override
    public void responseClientListData(List<ClientListInfo.ClientInfo> dataList) {
        clientList.clear();
        clientList.addAll(dataList);
        mAdapter.notifyDataSetChanged();
        isShowTip();
    }

    @Override
    public void responseClientListDataError(String msg) {
        showToast(msg);
    }

    @Override
    public void responseGiftData(GiftGetBean giftGetBean) {

    }

    @Override
    public void responseGiftError(String msg) {

    }

    @Override
    public void showDialog() {
        showLoading();
    }

    @Override
    public void hideDialog() {
        dismissLoading();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ClientListInfo.ClientInfo info = clientList.get(position);
        int state = info.getState();
//        单子状态为0,2,3,18,21,22可以修改信息
        if (state == 0 || state == 3) {
            Intent intent = new Intent(this, UpdClientActivity.class);
            intent.putExtra(Constants.ACTION_TO_UPD_CLIENT_IS_CAN_CHANGED, true);
            intent.putExtra(Constants.ACTION_TO_UPD_CLIENT_CLIENT_ID, info.getKeHuBaseID());
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(this, UpdClientActivity.class);
            intent.putExtra(Constants.ACTION_TO_UPD_CLIENT_IS_CAN_CHANGED, false);
            intent.putExtra(Constants.ACTION_TO_UPD_CLIENT_CLIENT_ID, info.getKeHuBaseID());
            startActivity(intent);
            finish();
        }
    }
}
