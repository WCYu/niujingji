package com.rxjy.niujingji.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rxjy.niujingji.R;
import com.rxjy.niujingji.activity.CustomerActivity;
import com.rxjy.niujingji.activity.DownLineActivity;
import com.rxjy.niujingji.activity.RecommendingActivity;
import com.rxjy.niujingji.activity.more.DianPuActivity;
import com.rxjy.niujingji.commons.App;
import com.rxjy.niujingji.commons.base.BaseFragment;
import com.rxjy.niujingji.entity.IsReadStateInfo;
import com.rxjy.niujingji.mvp.contract.WalletFragmentContract;
import com.rxjy.niujingji.mvp.presenter.WalletFragmentPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/5/23.
 */

public class WalletFragmentNew extends BaseFragment<WalletFragmentPresenter> implements WalletFragmentContract.View {

    @Bind(R.id.tv_tab_wallet_state)
    TextView tvTabWalletState;
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.courtesy)
    LinearLayout courtesy;
    @Bind(R.id.imageView27)
    ImageView imageView27;
    LinearLayout kehu;
    @Bind(R.id.iv_add)
    ImageView ivAdd;
    @Bind(R.id.red_image)
    ImageView redImage;
    @Bind(R.id.ly_dianpu)
    LinearLayout lyDianpu;
    @Bind(R.id.ly_huiyuan)
    LinearLayout lyHuiyuan;
    @Bind(R.id.ly_kehu)
    LinearLayout lyKehu;
    @Bind(R.id.ly_zhangben)
    LinearLayout lyZhangben;
    @Bind(R.id.ly_huishou)
    LinearLayout lyHuishou;
    @Bind(R.id.ly_keshou)
    LinearLayout lyKeshou;
    @Bind(R.id.ly_keyuan)
    LinearLayout lyKeyuan;
    @Bind(R.id.ly_fangyuan)
    LinearLayout lyFangyuan;
    @Bind(R.id.ly_loupan)
    LinearLayout lyLoupan;

    @Override
    protected int getFragmentLayout() {
        return R.layout.waller_fagment;
    }

    @Override
    protected void FragmentInitData() {
        tvTitle.setText("更多");
        ivBack.setVisibility(View.INVISIBLE);
//        floorLine.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getActivity(), HousesActivity.class));
//            }
//        });
        courtesy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), RecommendingActivity.class));
            }
        });
//        kehu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getActivity(), CustomerActivity.class));
//            }
//        });
    }

    @Override
    protected WalletFragmentPresenter onCreatePresenter() {
        return new WalletFragmentPresenter(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getState(App.cardNo);
    }


    @Override
    public void responseStateData(IsReadStateInfo.BodyBean data) {

        if (data.getCount() <= 0) {
            tvTabWalletState.setVisibility(View.INVISIBLE);

        } else {
            tvTabWalletState.setVisibility(View.VISIBLE);
            tvTabWalletState.setText(data.getCount());

        }
    }

    @Override
    public void responseVersionDataError(String msg) {
        showToast(msg);
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

    @OnClick({R.id.ly_dianpu, R.id.ly_huiyuan, R.id.ly_kehu, R.id.ly_zhangben, R.id.ly_huishou, R.id.ly_keshou, R.id.ly_keyuan, R.id.ly_fangyuan, R.id.ly_loupan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ly_dianpu://店铺
//                startActivity(new Intent(getActivity(), DownLineActivity.class));
                startActivity(new Intent(getActivity(), DianPuActivity.class));
                break;
            case R.id.ly_huiyuan://会员
                startActivity(new Intent(getActivity(), DownLineActivity.class));
                break;
            case R.id.ly_kehu://客户
                startActivity(new Intent(getActivity(), CustomerActivity.class));
                break;
            case R.id.ly_zhangben://账本

                break;
            case R.id.ly_huishou://会收

                break;
            case R.id.ly_keshou://客收

                break;
            case R.id.ly_keyuan://客源

                break;
            case R.id.ly_fangyuan://房源

                break;
            case R.id.ly_loupan://楼盘
                startActivity(new Intent(getActivity(), HousesActivity.class));
                break;
        }
    }
}
