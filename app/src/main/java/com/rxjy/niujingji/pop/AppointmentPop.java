package com.rxjy.niujingji.pop;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.rxjy.niujingji.R;
import com.rxjy.niujingji.commons.utils.AutoUtils;


/**
 * Created by AAA on 2017/9/1.
 */

public class AppointmentPop extends PopupWindow {

    public interface OnAppointmentPopClickListener {

        void subAppointment(String name, String phone);

        void tipInputName();

        void tipInputPhone();

        void tipInputRightPhone();

    }

    private View mView;
    private Context context;
    private OnAppointmentPopClickListener mListener;

    private EditText etName;
    private TextView tvNameLin;

    private EditText etPhone;
    private TextView tvPhoneLin;

    private ImageView ivClose;

    private Button btnSub;

    public AppointmentPop(Context context, int layoutId, int with, int height) {
        this.context = context;
        mView = LayoutInflater.from(context).inflate(layoutId, null);

        AutoUtils.auto(mView);

        setContentView(mView);
        //设置宽度
        setWidth(with);
        //设置高度
        setHeight(height);
        //设置背景透明
        setBackgroundDrawable(new ColorDrawable(80));

        etName = (EditText) mView.findViewById(R.id.et_pop_name);
        tvNameLin = (TextView) mView.findViewById(R.id.tv_pop_name_lin);

        etPhone = (EditText) mView.findViewById(R.id.et_pop_phone);
        tvPhoneLin = (TextView) mView.findViewById(R.id.tv_pop_phone_lin);

        ivClose = (ImageView) mView.findViewById(R.id.iv_pop_close);

        btnSub = (Button) mView.findViewById(R.id.btn_cancel);

        EditText[] etArray = {etName, etPhone};
        TextView[] tvArray = {tvNameLin, tvPhoneLin};

        lineSelector(etArray, tvArray);

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString().trim();
                String phone = etPhone.getText().toString().trim();
                if (mListener != null) {
                    if (name.equals("")) {
                        mListener.tipInputName();
                        return;
                    }
                    if (phone.equals("")) {
                        mListener.tipInputPhone();
                        return;
                    }
                    if (phone.length() != 11) {
                        mListener.tipInputRightPhone();
                        return;
                    }
                    dismiss();
                    mListener.subAppointment(name, phone);
                }
            }
        });

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }

    //输入框下划线效果
    public void lineSelector(EditText[] etArray, final TextView[] tvArray) {
        for (int i = 0; i < etArray.length; i++) {
            final int position = i;
            etArray[i].setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        //此处为得到焦点时
                        tvArray[position].setEnabled(true);
                    } else {
                        //此处为失去焦点时
                        tvArray[position].setEnabled(false);
                    }
                }
            });
        }
    }

    public void setOnAppointmentPopClickListener(OnAppointmentPopClickListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public void dismiss() {
        super.dismiss();
        //设置背景变暗
        WindowManager.LayoutParams lp = ((Activity) context).getWindow().getAttributes();
        lp.alpha = 1.0f; //0.0-1.0  
        ((Activity) context).getWindow().setAttributes(lp);
    }

    @Override
    public void update() {
        super.update();
        //设置背景变暗
        WindowManager.LayoutParams lp = ((Activity) context).getWindow().getAttributes();
        lp.alpha = 0.5f; //0.0-1.0  
        ((Activity) context).getWindow().setAttributes(lp);
    }
}
