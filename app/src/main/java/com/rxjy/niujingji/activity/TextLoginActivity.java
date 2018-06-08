package com.rxjy.niujingji.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rxjy.niujingji.R;
import com.rxjy.niujingji.adapter.AccountListAdapter;
import com.rxjy.niujingji.commons.App;
import com.rxjy.niujingji.commons.Constants;
import com.rxjy.niujingji.commons.base.BaseActivity;
import com.rxjy.niujingji.commons.base.BasePresenter;
import com.rxjy.niujingji.commons.utils.JSONUtils;
import com.rxjy.niujingji.commons.utils.PrefUtils;
import com.rxjy.niujingji.commons.utils.ShowUtils;
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
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/5/28.
 */

public class TextLoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {

    private String TAG = "LoginActivity";

    @Bind(R.id.ed_phone)
    EditText ed_phone;
    @Bind(R.id.ed_vitifycode)
    EditText ed_vitifycode;
    @Bind(R.id.ed_pwd)
    EditText ed_pwd;

    @Bind(R.id.tv_vitifycodeget)
    TextView tv_vitifycodeget;
    @Bind(R.id.tv_forgetpwd)
    TextView tv_forgetpwd;

    @Bind(R.id.btn_next)
    Button btn_next;

    @Bind(R.id.rl_veritycode)
    RelativeLayout rl_veritycode;
    @Bind(R.id.rl_phone)
    RelativeLayout rl_phone;
    @Bind(R.id.rl_pwd)
    RelativeLayout rl_pwd;
    @Bind(R.id.ll_toforget)
    LinearLayout ll_toforget;

    @Bind(R.id.iv_list)
    ImageView ivList;
    @Bind(R.id.lv_accountlist)
    ListView lvAccountlist;

    @Bind(R.id.ll_accountview)
    LinearLayout llAccountview;
    String accountliststr;
    ArrayList<String> accountlist;
    PwdSaveBean pwdSaveBean;

    String phonenum, veritycode, pwdnum;
    int logintype;//0：密码登录  -2：验证码登录

    @Override
    public int getLayout() {
        return R.layout.activity_loginfirst;
    }

    @Override
    public void initData() {
        rl_veritycode.setVisibility(View.GONE);
        btn_next.setVisibility(View.GONE);
        rl_pwd.setVisibility(View.GONE);
        tv_forgetpwd.setVisibility(View.GONE);
        ll_toforget.setVisibility(View.GONE);
        ed_phone.addTextChangedListener(new MyEditListener(ed_phone));


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

    @Override
    protected LoginPresenter onCreatePresenter() {
        return new LoginPresenter(this);
    }

    @Override
    public void responseToken(TokenInfo.Token data) {

    }

    @Override
    public void responseTokenError(String msg) {
    showToast(msg);
    }
    int issame;
    @Override
    public void responseLogin(UserInfo.User data) {
        for (int i = 0; i < accountlist.size(); i++) {
            if(ed_phone.getText().toString().equals(accountlist.get(i))){
                issame=1;
                break;
            }
        }
        if(issame!=1){
            SharedPreferences mspaccount = getSharedPreferences("njj_account", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editmspaccount = mspaccount.edit();
            accountlist.add(ed_phone.getText().toString());
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

    @Override
    public void responseLoginError(String msg) {
      showToast(msg);
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void hideDialog() {

    }

    @Override
    public void responseIsCheck(CheckLoginBean subInfo) {
        switch (subInfo.getStatusCode()) {
            case 0://用户存在
                btn_next.setText("登录");
                logintype = 0;
                btn_next.setVisibility(View.VISIBLE);
                rl_pwd.setVisibility(View.VISIBLE);
                tv_forgetpwd.setVisibility(View.VISIBLE);
                ll_toforget.setVisibility(View.VISIBLE);
                rl_veritycode.setVisibility(View.GONE);
                break;
            case 1://用户不存在,获取验证码注册
                logintype = 1;
                rl_veritycode.setVisibility(View.VISIBLE);
                btn_next.setVisibility(View.VISIBLE);
                rl_pwd.setVisibility(View.VISIBLE);
                tv_forgetpwd.setVisibility(View.GONE);
                ll_toforget.setVisibility(View.GONE);
                break;
            default:
                showToast(subInfo.getStatusMsg());
                break;
        }

    }

    @Override
    public void responseVerityCode(SubInfo data) {
        int statusCode = data.getStatusCode();
        Log.e("tag",statusCode+"");
        switch (data.getStatusCode()) {
            case 0://发送成功，，，倒计时。。。
                updateTime();
                Toast.makeText(this, data.getStatusMsg(), Toast.LENGTH_SHORT).show();
                break;
            case -2://发送失败
                Toast.makeText(this, data.getStatusMsg(), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void responseLogin(LoginInfo.BodyBean loginInfo) {
        PrefUtils.putValue(this,Constants.PHONE,phonenum);
        PrefUtils.putValue(this,Constants.PASSWORD,pwdnum);
        PrefUtils.putValue(this,Constants.CARD_NO,loginInfo.getCardNo());
        PrefUtils.putValue(this,Constants.TOKEN,loginInfo.getToken());
        PrefUtils.putBooleanValue(this, Constants.IS_LOGIN, true);
        mPresenter.getLoginUserInfo(loginInfo.getCardNo(),loginInfo.getToken());
    }
    int isshow;
    @OnClick({R.id.btn_next, R.id.tv_vitifycodeget, R.id.tv_forgetpwd, R.id.ll_toforget,R.id.iv_list})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_next:
//                startActivity(new Intent(this, SupervisionMainActivity.class));
                veritycode = ed_vitifycode.getText().toString();
                pwdnum = ed_pwd.getText().toString();
                switch (logintype) {
                    case 0:
                        if (!phonenum.isEmpty() && !pwdnum.isEmpty()) {
                            //请求登录
                            mPresenter.AppLogin(phonenum, pwdnum, "", "2");
                        } else if (phonenum.isEmpty()) {
                            ShowUtils.Toastshort(this, "请输入手机号！");
                        } else if (pwdnum.isEmpty()) {
                            ShowUtils.Toastshort(this, "请输入密码！");
                        }
                        break;
                    case 1:
                        if (!phonenum.isEmpty() && !veritycode.isEmpty() && !pwdnum.isEmpty()) {
                            //请求登录
                            mPresenter.AppLogin(phonenum, pwdnum, veritycode, "2");
                        } else if (phonenum.isEmpty()) {
                            ShowUtils.Toastshort(this, "请输入手机号！");
                        } else if (veritycode.isEmpty()) {
                            ShowUtils.Toastshort(this, "请输入验证码！");
                        } else if (pwdnum.isEmpty()) {
                            ShowUtils.Toastshort(this, "请输入密码！");
                        }
                        break;
                }

                break;
            case R.id.tv_vitifycodeget:

                mPresenter.getInsideVcodeLanding(phonenum, "2");
                break;
            case R.id.tv_forgetpwd:
              // startActivity(new Intent(this,ForgetPwdActivity.class).putExtra("phone",getphone));
                break;
            case R.id.ll_toforget:
                startActivity(new Intent(this, ForgetPwdActivity.class));
                break;
            case R.id.iv_list:
            switch (isshow){
                case 0:
                    isshow=1;
                    llAccountview.setVisibility(View.VISIBLE);
                    btn_next.setVisibility(View.GONE);
                    break;
                case 1:
                    isshow=0;
                    llAccountview.setVisibility(View.GONE);
                    btn_next.setVisibility(View.VISIBLE);
                    break;
            }
            break;

        }
    }

    private class MyEditListener implements TextWatcher {

        private EditText edittext;

        public MyEditListener(EditText edittext) {
            super();
            this.edittext = edittext;
        }

        @Override
        public void afterTextChanged(Editable arg0) {
            int lengths = arg0.length();
            switch (edittext.getId()) {
                case R.id.ed_phone:
                    phonenum = ed_phone.getText().toString();
                    if (lengths == 11) {//11手机号位请求判断

                        if (StringUtils.isMobileNO(phonenum)) {
                            mPresenter.getCheckUserInfo(phonenum, "2");
                        } else {
                            Toast.makeText(TextLoginActivity.this, "请输入正确的手机号！", Toast.LENGTH_SHORT).show();
                        }

                    }
                    break;
            }
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                  int arg3) {
            // TODO Auto-generated method stub
        }

    }

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            int time = msg.arg1;
            if (tv_vitifycodeget != null) {
                tv_vitifycodeget.setText(time + "s后重发");
                tv_vitifycodeget.setEnabled(false);
                tv_vitifycodeget.setBackgroundColor(getResources().getColor(R.color.colorline));
                if (time == 0) {
                    tv_vitifycodeget.setText("获取验证码");
                    tv_vitifycodeget.setEnabled(true);
                    tv_vitifycodeget.setBackgroundColor(getResources().getColor(R.color.text_red));
                }
            }
        }
    };

    //倒计时
    private void updateTime() {
        new Thread() {
            public void run() {
                for (int i = 60; i >= 0; --i) {
                    Message msg = handler.obtainMessage();
                    msg.arg1 = i;
                    handler.sendMessage(msg);
                    SystemClock.sleep(1000);     //  1s
                }
            }
        }.start();
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
                ed_phone.setText(pwdSaveBean.getPwdlist().get(position));
                isshow=0;
                llAccountview.setVisibility(View.GONE);
                btn_next.setVisibility(View.VISIBLE);
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
                    btn_next.setVisibility(View.VISIBLE);
                }else{
                    editmspaccount.putString("njj_accountliststr", accountliststr);
                    editmspaccount.commit();
                }
            }
        });
    }
}
