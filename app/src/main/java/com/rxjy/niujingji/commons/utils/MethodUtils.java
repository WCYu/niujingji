package com.rxjy.niujingji.commons.utils;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.rxjy.niujingji.R;

/**
 * Created by hjh on 2017/12/19.
 */

public class MethodUtils {

    /**
     * 关闭软键盘
     */
    public static void closeKeyboard(View view,Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void Dialog_pwd(Context context,WindowManager windowManager){
        Dialog dialog = new Dialog(context, R.style.ActionSheetDialogStyleone);
        View v = LayoutInflater.from(context).inflate(R.layout.dialog_pwd, null);
        dialog.setContentView(v);
        Window dialogwindowye = dialog.getWindow();
        dialogwindowye.setGravity(Gravity.BOTTOM);
        Display displayye = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lpye = dialogwindowye.getAttributes();
        lpye.height = (int) (displayye.getHeight());
        lpye.width = (int) (displayye.getWidth()*0.8);
        dialogwindowye.setAttributes(lpye);
        dialog.show();
    }

}
