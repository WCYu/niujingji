package com.rxjy.niujingji.entity;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by AAA on 2017/9/13.
 */

public class PoiButtonInfo {

    private LinearLayout lin;
    private ImageView iv;
    private TextView tvName;
    private TextView tvCount;
    private int iconNormal;
    private int iconChecked;
    private int state;

    public PoiButtonInfo(LinearLayout lin, ImageView iv, TextView tvName, TextView tvCount, int iconNormal, int iconChecked) {
        this.lin = lin;
        this.iv = iv;
        this.tvName = tvName;
        this.tvCount = tvCount;
        this.iconNormal = iconNormal;
        this.iconChecked = iconChecked;
    }

    public LinearLayout getLin() {
        return lin;
    }

    public void setLin(LinearLayout lin) {
        this.lin = lin;
    }

    public ImageView getIv() {
        return iv;
    }

    public void setIv(ImageView iv) {
        this.iv = iv;
    }

    public TextView getTvName() {
        return tvName;
    }

    public void setTvName(TextView tvName) {
        this.tvName = tvName;
    }

    public TextView getTvCount() {
        return tvCount;
    }

    public void setTvCount(TextView tvCount) {
        this.tvCount = tvCount;
    }

    public int getIconNormal() {
        return iconNormal;
    }

    public void setIconNormal(int iconNormal) {
        this.iconNormal = iconNormal;
    }

    public int getIconChecked() {
        return iconChecked;
    }

    public void setIconChecked(int iconChecked) {
        this.iconChecked = iconChecked;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
