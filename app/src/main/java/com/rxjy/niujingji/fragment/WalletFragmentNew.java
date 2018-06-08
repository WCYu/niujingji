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

    @Bind(R.id.guwen)
    LinearLayout guwen;
    @Bind(R.id.zhuanzheng_line)
    LinearLayout floorLine;
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
    @Bind(R.id.kehu)
    LinearLayout kehu;

    @Override
    protected int getFragmentLayout() {
        return R.layout.waller_fagment;
    }

    @Override
    protected void FragmentInitData() {
        tvTitle.setText("更多");
        ivBack.setVisibility(View.INVISIBLE);
        floorLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), HousesActivity.class));
            }
        });
        courtesy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), RecommendingActivity.class));
            }
        });
        kehu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CustomerActivity.class));
            }
        });
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

    @OnClick(R.id.guwen)
    public void onViewClicked() {

        startActivity(new Intent(getActivity(), DownLineActivity.class));
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

}
