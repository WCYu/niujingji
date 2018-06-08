package com.rxjy.niujingji.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.rxjy.niujingji.R;
import com.rxjy.niujingji.commons.Constants;
import com.rxjy.niujingji.commons.base.BaseActivity;
import com.rxjy.niujingji.commons.base.BasePresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WebActivity extends BaseActivity {

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.wv_web)
    WebView wvWeb;

    private String title;

    private String url;

    @Override
    public int getLayout() {
        return R.layout.activity_web;
    }

    @Override
    public void initData() {
        initIntent();
        initTitle();
        initWv();
    }

    private void initIntent() {
        title = getIntent().getStringExtra(Constants.ACTION_TO_WEB_TITLE);
        url = getIntent().getStringExtra(Constants.ACTION_TO_WEB_URL);
    }

    private void initTitle() {
        tvTitle.setText(title);
    }

    private void initWv() {

        wvWeb.loadUrl(url);

        wvWeb.getSettings().setJavaScriptEnabled(true);
        // 为图片添加放大缩小功能
        wvWeb.getSettings().setUseWideViewPort(true);

        wvWeb.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && wvWeb.canGoBack()) {
            wvWeb.goBack();// 返回前一个页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }



    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
