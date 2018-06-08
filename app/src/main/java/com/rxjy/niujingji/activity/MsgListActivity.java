package com.rxjy.niujingji.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.rxjy.niujingji.R;
import com.rxjy.niujingji.adapter.MsgAdapter;
import com.rxjy.niujingji.commons.App;
import com.rxjy.niujingji.commons.Constants;
import com.rxjy.niujingji.commons.base.BaseActivity;
import com.rxjy.niujingji.entity.MsgInfo;
import com.rxjy.niujingji.mvp.contract.MsgListContract;
import com.rxjy.niujingji.mvp.presenter.MsgListPresenter;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MsgListActivity extends BaseActivity<MsgListPresenter> implements MsgListContract.View, AdapterView.OnItemClickListener {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.lv_msg_list)
    ListView lvMsgList;

    public static final String TITLE = "消息";

    private MsgAdapter mAdapter;

    private List<MsgInfo.Msg> msgList;

    @Override
    public int getLayout() {
        return R.layout.activity_msg_list;
    }

    @Override
    public void initData() {
        initTitle();
        initMsgData();
    }

    private void initTitle() {
        tvTitle.setText(TITLE);
    }

    private void initMsgData() {

        msgList = new ArrayList<>();

        mAdapter = new MsgAdapter(this, msgList);

        lvMsgList.setAdapter(mAdapter);

        lvMsgList.setOnItemClickListener(this);

        mPresenter.getMsgList(App.cardNo);

    }

    @Override
    protected MsgListPresenter onCreatePresenter() {
        return new MsgListPresenter(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("消息");
        MobclickAgent.onResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("消息");
        MobclickAgent.onPause(this);
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
    public void responseMsgListData(List<MsgInfo.Msg> dataList) {
        msgList.clear();
        msgList.addAll(dataList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void responseMsgListDataError(String msg) {
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        MsgInfo.Msg info = msgList.get(position);
        startActivity(new Intent(this,MsgDetailsActivity.class).putExtra("titles",info.getName()).putExtra("content",info.getContent()).putExtra("time",info.getCreateTime()));
//        Intent intent = new Intent(this, WebActivity.class);
//        intent.putExtra(Constants.ACTION_TO_WEB_TITLE, info.getName());
//        intent.putExtra(Constants.ACTION_TO_WEB_URL, info.getNewsUrl());
//        startActivity(intent);
    }
}
