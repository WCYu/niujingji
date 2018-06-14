package com.rxjy.niujingji.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.text.ClipboardManager;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rxjy.niujingji.R;
import com.rxjy.niujingji.activity.AddClientActivity;
import com.rxjy.niujingji.activity.CustomerActivity;
import com.rxjy.niujingji.activity.GiftPickUpActivity;
import com.rxjy.niujingji.activity.SearchActivity;
import com.rxjy.niujingji.activity.UpdClientActivity;
import com.rxjy.niujingji.adapter.HomeAdapter;
import com.rxjy.niujingji.commons.App;
import com.rxjy.niujingji.commons.Constants;
import com.rxjy.niujingji.commons.base.BaseFragment;
import com.rxjy.niujingji.commons.utils.AutoUtils;
import com.rxjy.niujingji.entity.ClientListInfo;
import com.rxjy.niujingji.entity.GiftGetBean;
import com.rxjy.niujingji.mvp.contract.HomeContract;
import com.rxjy.niujingji.mvp.presenter.HomePresenter;
import com.rxjy.niujingji.utils.OkhttpUtils;
import com.rxjy.niujingji.widget.TopPopWindow;
import com.umeng.analytics.MobclickAgent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static android.app.Activity.RESULT_OK;

/**
 * Created by AAA on 2017/7/26.
 */
public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.View, AdapterView.OnItemClickListener, View.OnClickListener {

    @Bind(R.id.iv_add)
    ImageView ivAdd;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_title_count)
    TextView tvClientCount;
    @Bind(R.id.lv_home)
    ListView lvHome;
    @Bind(R.id.rl_home)
    RelativeLayout rlHome;

    public static final String TITLE = "客户";

    private List<ClientListInfo.ClientInfo> clientList;

    private HomeAdapter mAdapter;

    private AlertDialog.Builder builder;

    private AlertDialog dialog;

    private TopPopWindow topPopWindow;

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void FragmentInitData() {
        initTitle();
        initClientData();
        initDialog();
    }

    private void initTitle() {
        ivAdd.setVisibility(View.VISIBLE);
        tvTitle.setText(TITLE);
    }

    private void initClientData() {

        clientList = new ArrayList<>();

        mAdapter = new HomeAdapter(getActivity(), clientList);

        lvHome.setAdapter(mAdapter);

        lvHome.setOnItemClickListener(this);

    }

    private void isWithoutData() {
        if (clientList.size() == 0) {
            rlHome.setVisibility(View.VISIBLE);
        } else {
            rlHome.setVisibility(View.GONE);
        }
    }

    private void initDialog() {
        final View dialogView = View.inflate(getActivity(), R.layout.dialog_custom, null);
        AutoUtils.auto(dialogView);
        builder = new AlertDialog.Builder(getActivity());
        builder.setView(dialogView);
        dialog = builder.create();
        dialogView.findViewById(R.id.lin_wx).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 得到剪贴板管理器
                ClipboardManager cmb = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                cmb.setText("牛经纪");
                showToast("复制成功");
                if (dialog != null)
                    dialog.dismiss();
            }
        });
        dialogView.findViewById(R.id.lin_qq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //打开QQ并跳转到该联系人的临时会话
                String url = "mqqwpa://im/chat?chat_type=wpa&uin=2515341286";
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                if (dialog != null)
                    dialog.dismiss();
            }
        });
        dialogView.findViewById(R.id.lin_phone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //调用拨号盘
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + "4006169688"));
                startActivity(intent);
                if (dialog != null)
                    dialog.dismiss();
            }
        });
    }

    /**
     * 显示右上角popup菜单
     */
    private void showTopRightPopMenu() {
        if (topPopWindow == null) {
            int displayWidthValue = AutoUtils.getDisplayWidthValue(320);
            int displayHeightValue = AutoUtils.getDisplayHeightValue(240);
            topPopWindow = new TopPopWindow(getActivity(), this, displayWidthValue, displayHeightValue);
            //监听窗口的焦点事件，点击窗口外面则取消显示
            topPopWindow.getContentView().setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        topPopWindow.dismiss();
                    }
                }
            });
        }
        //设置默认获取焦点
        topPopWindow.setFocusable(true);
        //以某个控件的x和y的偏移量位置开始显示窗口
        topPopWindow.showAsDropDown(ivAdd, -AutoUtils.getDisplayWidthValue(300), 0);
        //如果窗口存在，则更新
        topPopWindow.update();
        //点击事件
    }

    @Override
    protected HomePresenter onCreatePresenter() {
        return new HomePresenter(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("客户列表");
        //客户列表接口
        mPresenter.getClientList(App.cardNo, "");
        mPresenter.getGift(App.cardNo);
//        ShowGift();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            mPresenter.getGift(App.cardNo);
//            ShowGift();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("客户列表");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == Constants.REQUEST_CODE_CLIENT_INFO) {
                ClientListInfo.ClientInfo info = (ClientListInfo.ClientInfo) data.getSerializableExtra(Constants.BACK_TO_CLIENT_LIST);
                clientList.add(0, info);
                mAdapter.notifyDataSetChanged();
                tvClientCount.setText("(" + clientList.size() + ")");
                isWithoutData();
            }
            if (requestCode == Constants.REQUEST_CODE_UPD_CLIENT_INFO) {

            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.iv_add, R.id.iv_search, R.id.btn_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_add:
                showTopRightPopMenu();
                break;
            case R.id.iv_search:
                startActivity(new Intent(getActivity(), SearchActivity.class));
                break;
            case R.id.btn_add:
                startActivityForResult(new Intent(getActivity(), AddClientActivity.class), Constants.REQUEST_CODE_CLIENT_INFO);
                break;
        }
    }

    @Override
    public void responseClientListData(List<ClientListInfo.ClientInfo> dataList) {
        clientList.clear();
        clientList.addAll(dataList);
        mAdapter.notifyDataSetChanged();
        tvClientCount.setText("(" + clientList.size() + ")");
        isWithoutData();
    }

    @Override
    public void responseClientListDataError(String msg) {
        showToast(msg);
    }

    @Override
    public void responseGiftData(GiftGetBean giftGetBean) {
        if (giftGetBean.getBody().getGiftStatus() == 1) {//有礼物
            ShowGift();
        }
    }

    @Override
    public void responseGiftError(String msg) {
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
        ClientListInfo.ClientInfo info = clientList.get(position);
        int state = info.getState();
//        单子状态为0,2,3,18,21,22可以修改信息
//        if (state == 0 || state == 3) {
//            Intent intent = new Intent(getActivity(), UpdClientActivity.class);
//            intent.putExtra(Constants.ACTION_TO_UPD_CLIENT_IS_CAN_CHANGED, true);
//            intent.putExtra(Constants.ACTION_TO_UPD_CLIENT_CLIENT_ID, info.getKeHuBaseID());
//            startActivityForResult(intent, Constants.REQUEST_CODE_UPD_CLIENT_INFO);
//        } else {
//            Intent intent = new Intent(getActivity(), UpdClientActivity.class);
//            intent.putExtra(Constants.ACTION_TO_UPD_CLIENT_IS_CAN_CHANGED, false);
//            intent.putExtra(Constants.ACTION_TO_UPD_CLIENT_CLIENT_ID, info.getKeHuBaseID());
//            startActivityForResult(intent, Constants.REQUEST_CODE_UPD_CLIENT_INFO);
//        }
        Intent intent = new Intent(getActivity(), CustomerActivity.class);
        intent.putExtra(Constants.ACTION_TO_UPD_CLIENT_IS_CAN_CHANGED, false);
        intent.putExtra(Constants.ACTION_TO_UPD_CLIENT_CLIENT_ID, info.getKeHuBaseID());
        startActivityForResult(intent, Constants.REQUEST_CODE_UPD_CLIENT_INFO);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lin_add_client:
                topPopWindow.dismiss();
                startActivityForResult(new Intent(getActivity(), AddClientActivity.class), Constants.REQUEST_CODE_CLIENT_INFO);
                break;
            case R.id.lin_service:
                topPopWindow.dismiss();
                dialog.show();
                break;
        }
    }


    /**
     * 领取礼物弹窗
     */
    Dialog gift_dialog;
    private View view;
    private ImageView ic_cancle, iv_getgift;

    private void ShowGift() {
        if (gift_dialog != null && gift_dialog.isShowing()) {
        } else {
            gift_dialog = new Dialog(getActivity(), R.style.ActionSheetDialogStyleone);
            view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_gift, null);
            gift_dialog.setContentView(view);
            Window window = gift_dialog.getWindow();
            window.setGravity(Gravity.CENTER);
            WindowManager windowManager = getActivity().getWindowManager();
            Display display = windowManager.getDefaultDisplay();
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width = display.getWidth();
            lp.height = display.getHeight();
            window.setAttributes(lp);
            gift_dialog.setCancelable(false);
            gift_dialog.setCanceledOnTouchOutside(false);
            gift_dialog.show();
            iv_getgift = (ImageView) view.findViewById(R.id.iv_getgift);
            iv_getgift.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gift_dialog.dismiss();
                    startActivity(new Intent(getActivity(), GiftPickUpActivity.class));
                }
            });
            ic_cancle = (ImageView) view.findViewById(R.id.ic_cancle);
            ic_cancle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gift_dialog.dismiss();
                }
            });
        }


    }

}
