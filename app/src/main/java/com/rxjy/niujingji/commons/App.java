package com.rxjy.niujingji.commons;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.multidex.MultiDex;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.igexin.sdk.PushManager;
import com.rxjy.niujingji.commons.utils.ImageLoaderUtil;
import com.rxjy.niujingji.entity.UserInfo;
import com.rxjy.niujingji.service.IntentService;
import com.rxjy.niujingji.service.PushService;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/11.
 */
public class App extends Application {

    private static App app;

    private List<Activity> activities;


    public static int daojishi=0;
    public static int codelogindaojishi=0;
    public static String token;
    public static String cardNo;

    public static UserInfo.User.BaseInfo baseInfo;
    public static UserInfo.User.PersonnelInfo personnelInfo;

    public static String city;
    public static String district;
    public static String street;

    public static double latitude;
    public static double longitude;

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化异常处理
        app = this;
        ImageLoaderUtil.init(this);
        activities = new ArrayList<>();
        // PushService 为第三方自定义推送服务,初始化推送服务
        PushManager.getInstance().initialize(this.getApplicationContext(), PushService.class);
        // IntentService 为第三方自定义的推送服务事件接收类
        PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), IntentService.class);
        //设置手动统计
        MobclickAgent.openActivityDurationTrack(false);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static Application getApplication() {
        return app;
    }

    public static App getApp() {
        return app;
    }

    public static Context getContext() {
        return getApp().getApplicationContext();
    }

    //获取版本号
    public static String getVersionCode() {
        try {
            PackageManager manager = getContext().getPackageManager();
            PackageInfo info = manager.getPackageInfo(getContext().getPackageName(), 0);
            return String.valueOf(info.versionCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    //获取版本名称
    public static String getVersionName() {
        try {
            PackageManager manager = getContext().getPackageManager();
            PackageInfo info = manager.getPackageInfo(getContext().getPackageName(), 0);
            return info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public App addActivity(Activity activity) {
        activities.add(activity);
        return app;
    }

    public void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    private void killActivity(Activity ac) {
        if (ac != null) {
            ac.finish();
        }
    }

    public void exitApp() {
        int size = activities.size();
        for (int i = 0; i < size; i++) {
            killActivity(activities.get(i));
        }
    }
    public static void disableSubControls(ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View v = viewGroup.getChildAt(i);
            if (v instanceof ViewGroup) {
                if (v instanceof Spinner) {
                    Spinner spinner = (Spinner) v;
                    spinner.setClickable(false);
                    spinner.setEnabled(false);

                    //Log.i(TAG, "A Spinner is unabled");
                } else if (v instanceof ListView) {
                    ((ListView) v).setClickable(false);
                    ((ListView) v).setEnabled(false);

                    //  Log.i(TAG, "A ListView is unabled");
                } else {
                    disableSubControls((ViewGroup) v);
                }
            } else if (v instanceof EditText) {
                ((EditText)v) .setFocusable(false);
                //  Log.i("tag", "A EditText is unabled");
            } else if (v instanceof TextView) {
                ((TextView)v).setEnabled(false);
                // Log.i("tag", "A TextView is unabled");
            }
        }
    }
}
