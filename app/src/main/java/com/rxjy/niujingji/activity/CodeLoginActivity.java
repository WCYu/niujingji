package com.rxjy.niujingji.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.rxjy.niujingji.R;
import com.rxjy.niujingji.commons.App;
import com.rxjy.niujingji.commons.Constants;
import com.rxjy.niujingji.commons.base.BaseActivity;
import com.rxjy.niujingji.commons.utils.PrefUtils;
import com.rxjy.niujingji.commons.utils.StringUtils;
import com.rxjy.niujingji.entity.TokenInfo;
import com.rxjy.niujingji.entity.UserInfo;
import com.rxjy.niujingji.mvp.contract.CodeLoginContract;
import com.rxjy.niujingji.mvp.presenter.CodeLoginPresenter;
import com.rxjy.niujingji.widget.DownTimerButton;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CodeLoginActivity extends BaseActivity<CodeLoginPresenter> implements CodeLoginContract.View {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.et_phone_num)
    EditText etPhoneNum;
    @Bind(R.id.tv_old_password_line)
    TextView tvPhoneLine;
    @Bind(R.id.btn_verification_code)
    Button btnVerificationCode;
    @Bind(R.id.et_verification_code)
    EditText etVerificationCode;
    @Bind(R.id.tv_verification_code_line)
    TextView tvVerificationCodeLine;

    public static final String TITLE = "验证码登录";

    @Override
    public int getLayout() {
        return R.layout.activity_code_login;
    }

    @Override
    public void initData() {
        String phone=PrefUtils.getValue(this,Constants.CODELOGINPHONE);
        etPhoneNum.setText(phone);
        App.codelogindaojishi=PrefUtils.getIntValue(this,Constants.CODE_LOGIN_DAOJISHI);
        if (App.codelogindaojishi!=0){
            startTimer2();
        }

        initTitle();
        initLine();
        //设置倒计时为60秒
        //btnVerificationCode.setDuration(60000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    private void initTitle() {
        tvTitle.setText(TITLE);
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
        etVerificationCode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    //此处为得到焦点时
                    tvVerificationCodeLine.setEnabled(true);
                } else {
                    //此处为失去焦点时
                    tvVerificationCodeLine.setEnabled(false);
                }
            }
        });
    }

    @Override
    protected CodeLoginPresenter onCreatePresenter() {
        return new CodeLoginPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.btn_verification_code, R.id.btn_code_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_verification_code:
                String phone = etPhoneNum.getText().toString().trim();
                if (TextUtils.isEmpty(phone)) {
                    showToast("请输入手机号");
                    return;
                }
//                if (!StringUtils.isMobileNO(phone)) {
//                    showToast("请输入正确的手机号");
//                    return;
//                }
                mPresenter.getVCode(phone);
                break;
            case R.id.btn_code_login:
                String phoneNum = etPhoneNum.getText().toString().trim();
                String vCode = etVerificationCode.getText().toString().trim();
                if (TextUtils.isEmpty(phoneNum)) {
                    showToast("请输入手机号");
                    return;
                }
//                if (!StringUtils.isMobileNO(phoneNum)) {
//                    showToast("请输入正确的手机号");
//                    return;
//                }
                if (TextUtils.isEmpty(vCode)) {
                    showToast("请输入密码");
                    return;
                }
                mPresenter.getTokenByCode(phoneNum, vCode);
                break;
        }
    }

    private Timer mTimer; //调度器
    private TimerTask mTask;
    private long duration = 60000; //倒计时时长，默认为10秒
    private long temp_duration;
    private String clickBefore = "验证码"; //点击前
    private String clickAfter = "秒"; //点击后


    //计时开始
    private void startTimer() {
        temp_duration = duration;
        //设置按钮不可点击
        btnVerificationCode.setEnabled(false);
        mTimer = new Timer();
        mTask = new TimerTask() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(0x01);
            }
        };
        mTimer.schedule(mTask, 0, 1000);  //调度分配，延时0秒，时间间隔1秒
    }

    private void startTimer2() {
        temp_duration = App.codelogindaojishi*1000;
        //设置按钮不可点击
        btnVerificationCode.setEnabled(false);
        mTimer = new Timer();
        mTask = new TimerTask() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(0x01);
            }
        };
        mTimer.schedule(mTask, 0, 1000);  //调度分配，延时0秒，时间间隔1秒
    }

    //计时结束
    private void stopTimer() {
        if (mTask != null) {
            mTask.cancel();
            mTask = null;
        }
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            PrefUtils.putIntValue(CodeLoginActivity.this,Constants.CODE_LOGIN_DAOJISHI, (int) (temp_duration / 1000));
            if (btnVerificationCode!=null) {
                btnVerificationCode.setText(temp_duration / 1000 + clickAfter);
            }
            temp_duration -= 1000;

            //倒计时结束
            if (temp_duration < 0) {
                //设置可以点击

                if (btnVerificationCode!=null) {
                    btnVerificationCode.setEnabled(true);
                    btnVerificationCode.setText(clickBefore);
                }
                stopTimer();
            }
        }
    };







    @Override
    public void responseTokenByCode(TokenInfo.Token data) {


        PrefUtils.putValue(this, Constants.CARD_NO, data.getCardNo());
        PrefUtils.putValue(this, Constants.TOKEN, data.getToken());
        //存储已经登录的状态
        PrefUtils.putBooleanValue(this, Constants.IS_LOGIN, true);

        App.cardNo = data.getCardNo();
        App.token = data.getToken();

        mPresenter.getLoginUserInfoByCode(data.getCardNo(), data.getToken());

    }

    @Override
    public void responseTokenByCodeError(String msg) {
        showToast(msg);
    }

    @Override
    public void responseLoginByCode(UserInfo.User data) {

        App.baseInfo = data.getBaseinfo();
        App.personnelInfo = data.getPersonnelInfo();

        startActivity(new Intent(this, NjjActivity.class));
    }

    @Override
    public void responseLoginByCodeError(String msg) {
        showToast(msg);
    }

    @Override
    public void responseVCcode() {
        PrefUtils.putValue(this,Constants.CODELOGINPHONE,etPhoneNum.getText().toString());
        startTimer();
    }

    @Override
    public void responseVCodeError(String msg) {
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
}
