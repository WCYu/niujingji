package com.rxjy.niujingji.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rxjy.niujingji.R;
import com.rxjy.niujingji.activity.CounselorInfoActivity;
import com.rxjy.niujingji.activity.MsgListActivity;
import com.rxjy.niujingji.activity.SettingActivity;
import com.rxjy.niujingji.activity.UserInfoActivity;
import com.rxjy.niujingji.activity.WalletActivity;
import com.rxjy.niujingji.commons.App;
import com.rxjy.niujingji.commons.Constants;
import com.rxjy.niujingji.commons.base.BaseFragment;
import com.rxjy.niujingji.commons.base.BasePresenter;
import com.rxjy.niujingji.widget.RoundAngleImageView;
import com.umeng.analytics.MobclickAgent;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by AAA on 2017/7/26.
 */

public class MineFragment extends BaseFragment {
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_mine_name)
    TextView tvMineName;
    @Bind(R.id.tv_mine_card)
    TextView tvMineCard;
    @Bind(R.id.tv_msg_state)
    TextView tvState;
    @Bind(R.id.riv_mine)
    RoundAngleImageView rivMine;

    public static final String TITLE = "我";

    private static int state;
    @Bind(R.id.rl_wallet)
    RelativeLayout rlWallet;

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void FragmentInitData() {
        initTitle();
    }

    private void initTitle() {
        ivBack.setVisibility(View.INVISIBLE);
        tvTitle.setText(TITLE);
    }

    private void initUserData() {
        tvMineName.setText(App.baseInfo.getNickName() == null ? "昵称" : App.baseInfo.getNickName());
//        tvMineCard.setText("账号：" + App.cardNo);
        tvMineCard.setText(App.baseInfo.getPhone() == null ? "昵称" : App.baseInfo.getPhone());
        RequestOptions options = new RequestOptions();
        options.placeholder(R.mipmap.head_portrait_icon);
        options.error(R.mipmap.head_portrait_icon);
        Glide.with(getActivity()).load(App.baseInfo.getImage()).apply(options).into(rivMine);
    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("我");
        initUserData();
        initReadState();
    }

    private void initReadState() {
        if (state == 1) {
            tvState.setVisibility(View.VISIBLE);
        } else {
            tvState.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("我");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    //

    @OnClick({R.id.rl_user_info, R.id.rl_settings, R.id.rl_msg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_user_info:
                startActivity(new Intent(getActivity(), UserInfoActivity.class));
                break;
            case R.id.rl_settings:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
            case R.id.rl_msg:
                startActivity(new Intent(getActivity(), MsgListActivity.class));
                break;
//            case R.id.rl_counselor:
//                startActivity(new Intent(getActivity(), CounselorInfoActivity.class));
//                break;
        }
    }

    @OnClick(R.id.rl_wallet)
    public void onViewClicked() {
        startActivity(new Intent(getContext(), WalletActivity.class));
    }

    public static class PushStateReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action == Constants.ACTION_INFORMATION_MINE) {
                state = intent.getIntExtra(Constants.KEY_STATE, 0);
            }
        }
    }

}
