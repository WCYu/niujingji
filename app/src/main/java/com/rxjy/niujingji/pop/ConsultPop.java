package com.rxjy.niujingji.pop;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.rxjy.niujingji.R;
import com.rxjy.niujingji.commons.utils.AutoUtils;


/**
 * Created by AAA on 2017/9/1.
 */

public class ConsultPop extends PopupWindow {

    public interface OnConsultPopClickListener {

        void consultQQ();

        void consultCall();

    }

    private View mView;
    private Context context;
    private OnConsultPopClickListener mListener;

    private LinearLayout linQQ;

    private LinearLayout linCall;

    private Button btnCancel;

    public ConsultPop(Context context, int layoutId, int with, int height) {
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

        linQQ = (LinearLayout) mView.findViewById(R.id.lin_qq);

        linCall = (LinearLayout) mView.findViewById(R.id.lin_service);

        btnCancel = (Button) mView.findViewById(R.id.btn_cancel);

        linQQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (mListener != null)
                    mListener.consultQQ();
            }
        });

        linCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (mListener != null)
                    mListener.consultCall();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }

    public void setOnConsultPopClickListener(OnConsultPopClickListener mListener) {
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
