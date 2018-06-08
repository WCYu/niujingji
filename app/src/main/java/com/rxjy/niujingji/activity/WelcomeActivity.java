package com.rxjy.niujingji.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.rxjy.niujingji.R;
import com.rxjy.niujingji.adapter.WelcomeAdapter;
import com.rxjy.niujingji.commons.Constants;
import com.rxjy.niujingji.commons.base.BaseActivity;
import com.rxjy.niujingji.commons.base.BasePresenter;
import com.rxjy.niujingji.commons.utils.PrefUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WelcomeActivity extends BaseActivity {

    @Bind(R.id.vp_welcome)
    ViewPager vpWelcome;
    @Bind(R.id.btn_enter)
    Button btnEnter;

    private WelcomeAdapter mAdapter;
    private List<View> viewList;

    //引导页图片资源
    private static final int[] pics = {
            R.mipmap.welcome_one,
            R.mipmap.welcome_two,
            R.mipmap.welcome_three,
            R.mipmap.welcome_four,
            R.mipmap.welcome_five
    };

    @Override
    public int getLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    public void initData() {
        initViewData();
    }

    private void initViewData() {

        viewList = new ArrayList<>();

        LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        //初始化引导页视图列表
        for (int i = 0; i < pics.length; i++) {
            ImageView img = new ImageView(this);
            img.setLayoutParams(mParams);
            img.setImageResource(pics[i]);
            img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            viewList.add(img);
        }
        //初始化adapter
        mAdapter = new WelcomeAdapter(viewList);
        vpWelcome.setAdapter(mAdapter);
        vpWelcome.setOnPageChangeListener(new PageChangeListener());
    }

    private void isShowBtn(int position) {
        if (position == viewList.size() - 1) {
            btnEnter.setVisibility(View.VISIBLE);
        } else {
            btnEnter.setVisibility(View.GONE);
        }
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

    @OnClick(R.id.btn_enter)
    public void onViewClicked() {
        PrefUtils.putBooleanValue(this, Constants.IS_FIRST_LOGIN, true);
        startActivity(new Intent(this, LogoActivity.class));
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        PrefUtils.putBooleanValue(this, Constants.IS_FIRST_LOGIN, true);
    }

    private class PageChangeListener implements ViewPager.OnPageChangeListener {

        // 当滑动状态改变时调用
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            // arg0 ==1的时辰默示正在滑动，arg0==2的时辰默示滑动完毕了，arg0==0的时辰默示什么都没做。
            isShowBtn(position);
        }

        // 当前页面被滑动时调用
        @Override
        public void onPageSelected(int position) {
            // arg0 :当前页面，及你点击滑动的页面
            // arg1:当前页面偏移的百分比
            // arg2:当前页面偏移的像素位置
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            // 当新的页面被选中时调用
        }
    }
}
