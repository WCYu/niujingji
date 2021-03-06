package com.rxjy.niujingji.activity.more;

import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.rxjy.niujingji.R;
import com.rxjy.niujingji.commons.App;
import com.rxjy.niujingji.commons.base.BaseActivity;
import com.rxjy.niujingji.commons.base.BasePresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DianPuActivity extends BaseActivity {

    @Bind(R.id.web_dianpu)
    WebView webDianpu;
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.iv_add)
    ImageView ivAdd;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    public static String url = "http://www.niujingji.cn/static/shop/index.html?CardNo="+ App.cardNo;
    @Override
    public int getLayout() {
        return R.layout.activity_dian_pu;
    }

    @Override
    public void initData() {
        ButterKnife.bind(this);

        webDianpu.getSettings().setJavaScriptEnabled(true);
        // 为图片添加放大缩小功能
        webDianpu.getSettings().setUseWideViewPort(true);
        webDianpu.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, request);
            }
        });
        webDianpu.loadUrl(url);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
//        webDianpu.destroy();
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
