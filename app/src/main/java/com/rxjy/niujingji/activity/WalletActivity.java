package com.rxjy.niujingji.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.rxjy.niujingji.R;
import com.rxjy.niujingji.commons.base.BaseActivity;
import com.rxjy.niujingji.commons.base.BasePresenter;
import com.rxjy.niujingji.fragment.WalletFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/5/23.
 */

public class WalletActivity extends BaseActivity {
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.fl_main)
    FrameLayout flMain;
    private WalletFragment walletFragment;
    public static final String TITLE = "钱包";
    @Override
    public int getLayout() {
        return R.layout.activity_waller;
    }

    @Override
    public void initData() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(TITLE);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             finish();
            }
        });
        walletFragment=new WalletFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction action = manager.beginTransaction();
        if (!walletFragment.isAdded()) {
            action.add(R.id.fl_main, walletFragment);
        }

        action.show(walletFragment);
        action.commit();
    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }


}
