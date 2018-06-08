package com.rxjy.niujingji.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.rxjy.niujingji.R;
import com.rxjy.niujingji.commons.App;
import com.rxjy.niujingji.commons.base.BaseActivity;
import com.rxjy.niujingji.commons.utils.StringUtils;
import com.rxjy.niujingji.entity.GiftInfoBean;
import com.rxjy.niujingji.mvp.contract.GiftPickUpContract;
import com.rxjy.niujingji.mvp.presenter.GiftPickUpPresenter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hjh on 2017/11/27.
 */

public class GiftPickUpActivity extends BaseActivity<GiftPickUpPresenter> implements GiftPickUpContract.View {

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_gift)
    TextView tvGift;
    @Bind(R.id.rl_gift)
    RelativeLayout rlGift;
    @Bind(R.id.et_colorlike)
    EditText etColorlike;
    @Bind(R.id.et_person)
    EditText etPerson;
    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.et_address)
    EditText etAddress;
    @Bind(R.id.tv_getgift)
    TextView tvGetgift;

    private ArrayList<String> giftlist;
    private OptionsPickerView pickerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_gift;
    }

    @Override
    public void initData() {
        tvTitle.setText("领取礼品");
        mPresenter.getGiftInfo(App.cardNo);

    }

    @Override
    protected GiftPickUpPresenter onCreatePresenter() {
        return new GiftPickUpPresenter(this);
    }

    /**
     * 显示获取的数据
     * @param giftinfobean
     */
    String id;
    @Override
    public void responseGiftInfo(GiftInfoBean giftinfobean) {
        id = giftinfobean.getBody().getID() + "";
        giftlist = new ArrayList<>();
        for (int i = 0; i < giftinfobean.getBody().getGiftName().size(); i++) {
            giftlist.add(giftinfobean.getBody().getGiftName().get(i));
        }
        etPerson.setText(giftinfobean.getBody().getName());
        etPhone.setText(giftinfobean.getBody().getPhone());
        etAddress.setText(giftinfobean.getBody().getAddress());
    }

    @OnClick({R.id.rl_gift, R.id.iv_back, R.id.tv_getgift})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_gift:
                if (giftlist != null && giftlist.size() > 0) {
                    pickerView = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
                        @Override
                        public void onOptionsSelect(int options1, int options2, int options3, View v) {
                            tvGift.setText(giftlist.get(options1));
                        }
                    }).build();
                    pickerView.setPicker(giftlist);
                    pickerView.show();
                }
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_getgift://提交
//                ShowGift();
                String gift = tvGift.getText().toString();
                String color = etColorlike.getText().toString();
                String person = etPerson.getText().toString();
                String phone = etPhone.getText().toString();
                String address = etAddress.getText().toString();
                if (!StringUtils.isEmpty(id) && !StringUtils.isEmpty(gift) && !StringUtils.isEmpty(color) && !StringUtils.isEmpty(person) && !StringUtils.isEmpty(phone) && !StringUtils.isEmpty(address)) {
                    mPresenter.getGiftPickUp(id, gift, color, person, phone, address);
                } else if (StringUtils.isEmpty(gift)) {
                    showToast("请选择礼品！");
                } else if (StringUtils.isEmpty(color)) {
                    showToast("请选择倾向颜色！");
                } else if (StringUtils.isEmpty(person)) {
                    showToast("请选择联系人！");
                } else if (StringUtils.isEmpty(phone)) {
                    showToast("请选择电话！");
                } else if (StringUtils.isEmpty(address)) {
                    showToast("请选择收货地址！");
                } else {
                    showToast("用户id不存在！");
                }
                break;
        }
    }


    @Override
    public void responseGiftInfoError(String msg) {
        showToast(msg);
    }

    @Override
    public void responseGiftPickUp() {
        //领取成功后弹窗，
        ShowGift();
    }

    @Override
    public void responseGiftPickUpError(String msg) {

    }

    @Override
    public void showDialog() {
        showLoading();
    }

    @Override
    public void hideDialog() {
        dismissLoading();
    }


    /**
     * 领取礼物成功弹窗
     */
    Dialog gift_dialog;
    private View view;
    private ImageView ic_close;
    private void ShowGift() {
        gift_dialog = new Dialog(this, R.style.ActionSheetDialogStyleone);
        view = LayoutInflater.from(this).inflate(R.layout.dialog_giftsuccess, null);
        gift_dialog.setContentView(view);
        Window window = gift_dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        WindowManager windowManager = this.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = display.getWidth();
        lp.height = display.getHeight();
        window.setAttributes(lp);
        gift_dialog.setCancelable(false);
        gift_dialog.setCanceledOnTouchOutside(false);
        gift_dialog.show();
        ic_close = (ImageView) view.findViewById(R.id.ic_close);
        ic_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gift_dialog.dismiss();
                finish();
            }
        });

       //若未手动关闭 3秒消失且finish
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(gift_dialog!=null){
                    gift_dialog.dismiss();
                    finish();
                }
            }
        }, 3000);//3秒后执行Runnable中的run方法


    }


}
