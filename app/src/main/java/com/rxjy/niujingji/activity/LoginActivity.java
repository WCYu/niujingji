package com.rxjy.niujingji.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.rxjy.niujingji.R;
import com.rxjy.niujingji.adapter.AccountListAdapter;
import com.rxjy.niujingji.commons.App;
import com.rxjy.niujingji.commons.Constants;
import com.rxjy.niujingji.commons.base.BaseActivity;
import com.rxjy.niujingji.commons.utils.JSONUtils;
import com.rxjy.niujingji.commons.utils.PrefUtils;
import com.rxjy.niujingji.commons.utils.StringUtils;
import com.rxjy.niujingji.entity.CheckLoginBean;
import com.rxjy.niujingji.entity.LoginInfo;
import com.rxjy.niujingji.entity.PwdSaveBean;
import com.rxjy.niujingji.entity.SubInfo;
import com.rxjy.niujingji.entity.TokenInfo;
import com.rxjy.niujingji.entity.UserInfo;
import com.rxjy.niujingji.mvp.contract.LoginContract;
import com.rxjy.niujingji.mvp.presenter.LoginPresenter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {

    @Bind(R.id.iv_logo)
    ImageView ivLogo;
    @Bind(R.id.et_phone_num)
    EditText etPhoneNum;
    @Bind(R.id.tv_old_password_line)
    TextView tvPhoneLine;
    @Bind(R.id.et_password)
    EditText etPassword;
    @Bind(R.id.tv_password_line)
    TextView tvPasswordLine;
    @Bind(R.id.btn_login)
    Button btnLogin;
    @Bind(R.id.ll_accountview)
    LinearLayout llAccountview;
    @Bind(R.id.iv_list)
    ImageView ivList;
    @Bind(R.id.lv_accountlist)
    ListView lvAccountlist;

    private String phoneNum;
    private String password;

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }



    String accountliststr;
    ArrayList<String> accountlist;
    PwdSaveBean pwdSaveBean;
    @Override
    public void initData() {
        initIsLogin();
        initLine();

        pwdSaveBean=new PwdSaveBean();
        accountlist=new ArrayList<>();
        SharedPreferences sp = getSharedPreferences("njj_account", Activity.MODE_PRIVATE);
        accountliststr = sp.getString("njj_accountliststr", null);
        if(accountliststr!=null&&accountliststr.length()>0){
            pwdSaveBean= JSONUtils.toObject(accountliststr,PwdSaveBean.class);
            for (int i = 0; i < pwdSaveBean.getPwdlist().size(); i++) {
                accountlist.add(pwdSaveBean.getPwdlist().get(i));
            }
            ivList.setVisibility(View.VISIBLE);
            Log.e("accountliststr",accountliststr);
            ShowAccount();
        }else{
            ivList.setVisibility(View.GONE);
        }

    }

    private void initIsLogin() {
//        // 免登陆
//        if (PrefUtils.getBooleanValue(this, Constants.IS_LOGIN)) {
//            String cardNo = PrefUtils.getValue(this, Constants.CARD_NO);
//            String token = PrefUtils.getValue(this, Constants.TOKEN);
//            mPresenter.getLoginUserInfo(cardNo, token);
//        }
    }

    private void initLine() {
        etPhoneNum.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    //此处为得到焦点时
                    tvPhoneLine.setEnabled(true);
                } else {
                    //此处为失去焦点时
                    tvPhoneLine.setEnabled(false);
                }
            }
        });
        etPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    //此处为得到焦点时
                    tvPasswordLine.setEnabled(true);
                } else {
                    //此处为失去焦点时
                    tvPasswordLine.setEnabled(false);
                }
            }
        });
    }

    @Override
    protected LoginPresenter onCreatePresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    int isshow;
    @OnClick({R.id.tv_verification_code_login, R.id.tv_forget_password, R.id.tv_register, R.id.btn_login,R.id.iv_list})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_verification_code_login:
                startActivity(new Intent(this, CodeLoginActivity.class));
                break;
            case R.id.tv_forget_password:
                startActivity(new Intent(this, ForgetPwdActivity.class));
                break;
            case R.id.tv_register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.btn_login:
                phoneNum = etPhoneNum.getText().toString().trim();
                password = etPassword.getText().toString().trim();
                if (TextUtils.isEmpty(phoneNum)) {
                    showToast("请输入手机号");
                    return;
                }
//                if (!StringUtils.isMobileNO(phoneNum)) {
//                    showToast("请输入正确的手机号");
//                    return;
//                }
                if (TextUtils.isEmpty(password)) {
                    showToast("请输入密码");
                    return;
                }
                mPresenter.getToken(phoneNum, password);
                break;
            case R.id.iv_list:
                switch (isshow){
                    case 0:
                        isshow=1;
                        llAccountview.setVisibility(View.VISIBLE);
                        btnLogin.setVisibility(View.GONE);
                        break;
                    case 1:
                        isshow=0;
                        llAccountview.setVisibility(View.GONE);
                        btnLogin.setVisibility(View.VISIBLE);
                        break;
                }
                break;
        }
    }

    @Override
    public void responseToken(TokenInfo.Token data) {

        PrefUtils.putValue(this, Constants.CARD_NO, data.getCardNo());
        PrefUtils.putValue(this, Constants.TOKEN, data.getToken());
        //存储已经登录的状态
        PrefUtils.putBooleanValue(this, Constants.IS_LOGIN, true);

        mPresenter.getLoginUserInfo(data.getCardNo(), data.getToken());
    }

    @Override
    public void responseTokenError(String msg) {
        showToast(msg);
    }

    int issame;
    @Override
    public void responseLogin(UserInfo.User data) {
        for (int i = 0; i < accountlist.size(); i++) {
            if(etPhoneNum.getText().toString().equals(accountlist.get(i))){
                issame=1;
                break;
            }
        }
        if(issame!=1){
            SharedPreferences mspaccount = getSharedPreferences("njj_account", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editmspaccount = mspaccount.edit();
            accountlist.add(etPhoneNum.getText().toString());
            pwdSaveBean.setPwdlist(accountlist);
            accountliststr= JSONUtils.toString(pwdSaveBean);
            editmspaccount.putString("njj_accountliststr", accountliststr);
            editmspaccount.commit();
        }


        App.cardNo = PrefUtils.getValue(this, Constants.CARD_NO);
        App.token = PrefUtils.getValue(this, Constants.TOKEN);

        App.baseInfo = data.getBaseinfo();
        App.personnelInfo = data.getPersonnelInfo();

        startActivity(new Intent(this, NjjActivity.class));
        finish();

    }


    /**
     * 展示账号列表
     */
    private void ShowAccount(){
        final AccountListAdapter accountListAdapter=new AccountListAdapter(this,pwdSaveBean.getPwdlist());
        lvAccountlist.setAdapter(accountListAdapter);
        lvAccountlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {//选中显示，收起列表
                etPhoneNum.setText(pwdSaveBean.getPwdlist().get(position));
                isshow=0;
                llAccountview.setVisibility(View.GONE);
                btnLogin.setVisibility(View.VISIBLE);
            }
        });
        accountListAdapter.clickdel(new AccountListAdapter.Delete() {
            @Override
            public void clickdel(int position) {//删除此条，更新缓存，刷新页面
                pwdSaveBean.getPwdlist().remove(position);
                accountlist.remove(position);
                SharedPreferences mspaccount = getSharedPreferences("njj_account", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editmspaccount = mspaccount.edit();
                accountliststr= JSONUtils.toString(pwdSaveBean);
                Log.e("删除pwdSaveBean",accountliststr);
                accountListAdapter.notifyDataSetChanged();
                if(pwdSaveBean.getPwdlist().size()<=0){
                    editmspaccount.putString("njj_accountliststr", null);
                    editmspaccount.commit();
                    ivList.setVisibility(View.GONE);
                    isshow=0;
                    llAccountview.setVisibility(View.GONE);
                    btnLogin.setVisibility(View.VISIBLE);
                }else{
                    editmspaccount.putString("njj_accountliststr", accountliststr);
                    editmspaccount.commit();
                }
            }
        });
    }


    @Override
    public void responseLoginError(String msg) {
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
    public void responseIsCheck(CheckLoginBean subInfo) {

    }

    @Override
    public void responseVerityCode(SubInfo data) {

    }

    @Override
    public void responseLogin(LoginInfo.BodyBean loginInfo) {

    }
}
