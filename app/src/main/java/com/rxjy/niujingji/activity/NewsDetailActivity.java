package com.rxjy.niujingji.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.rxjy.niujingji.R;
import com.rxjy.niujingji.commons.App;
import com.rxjy.niujingji.commons.Constants;
import com.rxjy.niujingji.commons.base.BaseActivity;
import com.rxjy.niujingji.commons.base.BasePresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewsDetailActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.wv_news_detail)
    WebView wvNewsDetail;

    public static final String TITLE = "新闻详情";

    private int ID;

    @Override
    public int getLayout() {
        return R.layout.activity_news_detail;
    }

    @Override
    public void initData() {
        initIntentData();
        initTitle();
        initNewsDetail();
    }

    private void initIntentData() {
        ID = getIntent().getIntExtra(Constants.ACTION_TO_NEWS_DETAIL_NEWS_ID, 0);
    }

    private void initTitle() {
        tvTitle.setText(TITLE);
    }

    private void initNewsDetail() {

        String url= "http://news.rx/front/app_details.html?id="+ID+"&cardNo="+ App.cardNo;

        wvNewsDetail.loadUrl(url);

        wvNewsDetail.getSettings().setJavaScriptEnabled(true);
        // 为图片添加放大缩小功能
        wvNewsDetail.getSettings().setUseWideViewPort(true);

        wvNewsDetail.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && wvNewsDetail.canGoBack()) {
            wvNewsDetail.goBack();// 返回前一个页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
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
}
