package com.rxjy.niujingji.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rxjy.niujingji.R;
import com.rxjy.niujingji.commons.App;
import com.rxjy.niujingji.commons.Constants;
import com.rxjy.niujingji.commons.base.BaseActivity;
import com.rxjy.niujingji.commons.utils.AutoUtils;
import com.rxjy.niujingji.commons.utils.CheckPermissionsUitl;
import com.rxjy.niujingji.commons.utils.DownLoadApk;
import com.rxjy.niujingji.entity.IconInfo;
import com.rxjy.niujingji.entity.IsReadStateInfo;
import com.rxjy.niujingji.entity.VersionInfo;
import com.rxjy.niujingji.fragment.FindFragment;
import com.rxjy.niujingji.fragment.MineFragment;
import com.rxjy.niujingji.fragment.NewHomeFragment;
import com.rxjy.niujingji.fragment.WalletFragmentNew;
import com.rxjy.niujingji.mvp.contract.NjjContract;
import com.rxjy.niujingji.mvp.presenter.NjjPresenter;
import com.rxjy.niujingji.service.CoreService;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NjjActivity extends BaseActivity<NjjPresenter> implements NjjContract.View {

    @Bind(R.id.fl_main)
    FrameLayout flMain;
    @Bind(R.id.iv_tab_home)
    ImageView ivTabHome;
    @Bind(R.id.tv_tab_home)
    TextView tvTabHome;
    @Bind(R.id.rl_tab_home)
    RelativeLayout rlTabHome;
    @Bind(R.id.iv_tab_wallet)
    ImageView ivTabWallet;
    @Bind(R.id.tv_tab_wallet)
    TextView tvTabWallet;
    @Bind(R.id.rl_tab_wallet)
    RelativeLayout rlTabWallet;
    @Bind(R.id.iv_tab_find)
    ImageView ivTabFind;
    @Bind(R.id.tv_tab_find)
    TextView tvTabFind;
    @Bind(R.id.rl_tab_find)
    RelativeLayout rlTabFind;
    @Bind(R.id.iv_tab_mine)
    ImageView ivTabMine;
    @Bind(R.id.tv_tab_mine)
    TextView tvTabMine;
    @Bind(R.id.rl_tab_mine)
    RelativeLayout rlTabMine;
    @Bind(R.id.tv_tab_home_state)
    TextView tvHomeState;
    @Bind(R.id.tv_tab_wallet_state)
    TextView tvWalletState;
    @Bind(R.id.tv_tab_find_state)
    TextView tvFindState;
    @Bind(R.id.tv_tab_mine_state)
    TextView tvMineState;

    private Fragment currentFragment;

    private int[] iconNormal = new int[]{
            R.mipmap.home_normal,
            R.mipmap.ic_tab_more,
            R.mipmap.ic_tab_find,
            R.mipmap.mine_normal
    };

    private int[] iconPressed = new int[]{
            R.mipmap.home_pressed,
            R.mipmap.ic_tab_moreorange,
            R.mipmap.ic_tab_findorange,
            R.mipmap.mine_pressed
    };

    //指定Fragment的坐标
    private final int HOME_FRAGMENT = 0;
    private final int WALLET_FRAGMENT = 1;
    private final int FIND_FRAGMENT = 2;
    private final int MINE_FRAGMENT = 3;

    //Tab图标的集合
    private List<IconInfo> iconList;

    //碎片的集合
    private List<Fragment> fragmentList;

    private NewHomeFragment homeFragment;
    private WalletFragmentNew walletFragment;
    private FindFragment findFragment;
    //private HousesActivity housesFragment;
    private MineFragment mineFragment;

    //广播
    public MineFragment.PushStateReceiver pushReceiver;

    //检测权限列表
    private String[] requestPermissions = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.CALL_PHONE
    };

    @Override
    public int getLayout() {
        return R.layout.activity_njj;
    }

    @Override
    public void initData() {
        initIcon();
        initFragment();
        //初始化服务
        initStartService();
        //注册广播
        registerPushReceiver();
        //加载默认显示碎片
        showFragment(fragmentList.get(HOME_FRAGMENT), HOME_FRAGMENT);
        //权限检测
        CheckPermissionsUitl.checkPermissions(this, requestPermissions);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //获取版本信息
        mPresenter.getVersionInfo(Integer.parseInt(App.getVersionCode()));
        //获取未读状态
        mPresenter.getState(App.cardNo);
        //统计时长
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //统计时长
        MobclickAgent.onPause(this);
    }

    private void initStartService() {
        //启动数据获取服务
        Intent serviceIntent = new Intent(this, CoreService.class);
        startService(serviceIntent);
    }

    private void registerPushReceiver() {
        pushReceiver = new MineFragment.PushStateReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constants.ACTION_INFORMATION_MINE);
        registerReceiver(pushReceiver, filter);
    }

    private void initFragment() {
        //初始化碎片
        if (homeFragment == null)
            homeFragment = new NewHomeFragment();
        if (walletFragment == null)
            walletFragment = new WalletFragmentNew();
        if (findFragment == null)
            findFragment = new FindFragment();
//        if (housesFragment == null)
//            housesFragment = new HousesActivity();
        if (mineFragment == null)
            mineFragment = new MineFragment();
        //初始化fragmentList数据集合
        fragmentList = new ArrayList<>();
        //将碎片添加到集合中
        fragmentList.add(homeFragment);
        fragmentList.add(walletFragment);
        fragmentList.add(findFragment);
        //   fragmentList.add(housesFragment);
        fragmentList.add(mineFragment);
    }

    private void initIcon() {
        //初始化iconList数据集合
        iconList = new ArrayList<>();
        //将图标添加到集合中
        iconList.add(new IconInfo(ivTabHome, tvTabHome));
        iconList.add(new IconInfo(ivTabWallet, tvTabWallet));
        iconList.add(new IconInfo(ivTabFind, tvTabFind));
        iconList.add(new IconInfo(ivTabMine, tvTabMine));
    }

    /**
     * 显示指定Fragment界面的方法
     *
     * @param fragment
     * @param position
     */
    private void showFragment(Fragment fragment, int position) {
        JumpFragment(fragment);
        setIcon(position);
    }

    /**
     * 加载指定Fragment的方法
     *
     * @param fragment
     */
    private void JumpFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction action = manager.beginTransaction();
        if (!fragment.isAdded()) {
            action.add(R.id.fl_main, fragment);
        }
        if (currentFragment != null) {
            action.hide(currentFragment);
        }
        action.show(fragment);
        action.commit();
        currentFragment = fragment;
    }

    /**
     * 设置图标点击效果
     *
     * @param position
     */
    private void setIcon(int position) {
        for (int i = 0; i < iconList.size(); i++) {
            iconList.get(i).getImageView().setImageResource(iconNormal[i]);
            iconList.get(i).getTextView().setTextColor(this.getResources().getColor(R.color.colorGrayDark));
        }
        iconList.get(position).getImageView().setImageResource(iconPressed[position]);
        iconList.get(position).getTextView().setTextColor(this.getResources().getColor(R.color.colorPrimary));
    }


    @Override
    protected NjjPresenter onCreatePresenter() {
        return new NjjPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rl_tab_home, R.id.rl_tab_wallet, R.id.rl_tab_find, R.id.rl_tab_mine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_tab_home:
                showFragment(fragmentList.get(HOME_FRAGMENT), HOME_FRAGMENT);
                break;
            case R.id.rl_tab_wallet:
                showFragment(fragmentList.get(WALLET_FRAGMENT), WALLET_FRAGMENT);
                break;
            case R.id.rl_tab_find:
                showFragment(fragmentList.get(FIND_FRAGMENT), FIND_FRAGMENT);
                break;
            case R.id.rl_tab_mine:
                showFragment(fragmentList.get(MINE_FRAGMENT), MINE_FRAGMENT);
                break;
        }
    }

    @Override
    public void responseStateData(IsReadStateInfo.BodyBean data) {
        //  发送广播
//        Intent intent = new Intent(Constants.ACTION_INFORMATION_MINE);
//        intent.putExtra(Constants.KEY_STATE, data.getCount());
//        sendBroadcast(intent);
        if (data.getCount() <= 0) {
            tvWalletState.setVisibility(View.INVISIBLE);
        } else {
            tvWalletState.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void responseVersionData(final VersionInfo.Version data) {
        if (data.getVersionNo() > Integer.parseInt(App.getVersionCode())) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.newPassword);

            builder.setCancelable(false);// 设置点击屏幕Dialog不消失
            View inflate = getLayoutInflater().inflate(R.layout.upgrade_layout, null);
            AutoUtils.setSize(this, false, 720, 1280);
            AutoUtils.auto(inflate);
            TextView confirmupgrade = (TextView) inflate.findViewById(R.id.confirm_upgrade);
            TextView updatecontent = (TextView) inflate.findViewById(R.id.update_content);
            TextView colse = (TextView) inflate.findViewById(R.id.close);
            if (data.getForce() == 1) {
                builder.setCancelable(false);// 设置点击屏幕Dialog不消失
                colse.setVisibility(View.GONE);

            } else {
                builder.setCancelable(true);// 设置点击屏幕Dialog不消失
            }
            String content = data.getContent();
            if (!TextUtils.isEmpty(content)) {
                updatecontent.setText(content);
            }
            confirmupgrade.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DownLoadApk downLoadApk = new DownLoadApk(NjjActivity.this);
                    downLoadApk.downLoadApk(data);
                }
            });
            builder.setView(inflate);
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //反注册广播
        unregisterReceiver(pushReceiver);
    }

    @Override
    public void responseVersionDataError(String msg) {
        showToast(msg);
    }
}
