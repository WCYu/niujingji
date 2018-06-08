package com.rxjy.niujingji.entity;

import java.util.ArrayList;

/**
 * Created by hjh on 2017/12/29.
 */

public class PwdSaveBean {

    private ArrayList<String> pwdlist;

    public PwdSaveBean() {super();
    }

    @Override
    public String toString() {
        return "PwdSaveBean{" +
                "pwdlist=" + pwdlist +
                '}';
    }

    public ArrayList<String> getPwdlist() {
        return pwdlist;
    }

    public void setPwdlist(ArrayList<String> pwdlist) {
        this.pwdlist = pwdlist;
    }
}
