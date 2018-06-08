package com.rxjy.niujingji.widget;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.rxjy.niujingji.R;
import com.rxjy.niujingji.commons.utils.AutoUtils;

/**
 * Created by AAA on 2017/8/14.
 */

public class TopPopWindow extends PopupWindow {

    private View mView;
    private LinearLayout linAddClient, linService;

    public TopPopWindow(Activity paramActivity, View.OnClickListener paramOnClickListener,
                        int paramInt1, int paramInt2) {
        mView = LayoutInflater.from(paramActivity).inflate(R.layout.popwindow_top_right, null);
        linAddClient = (LinearLayout) mView.findViewById(R.id.lin_add_client);
        linService = (LinearLayout) mView.findViewById(R.id.lin_service);
        if (paramOnClickListener != null) {
            //设置点击监听
            linAddClient.setOnClickListener(paramOnClickListener);
            linService.setOnClickListener(paramOnClickListener);
            AutoUtils.auto(mView);
            setContentView(mView);
            //设置宽度
            setWidth(paramInt1);
            //设置高度
            setHeight(paramInt2);
            //设置显示隐藏动画
//            setAnimationStyle(R.style.AnimTools);
            //设置背景透明
            setBackgroundDrawable(new ColorDrawable(0));
        }
    }

}
