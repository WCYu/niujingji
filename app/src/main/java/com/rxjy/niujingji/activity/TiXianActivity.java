package com.rxjy.niujingji.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rxjy.niujingji.R;
import com.rxjy.niujingji.api.ApiEngine;
import com.rxjy.niujingji.commons.App;
import com.rxjy.niujingji.commons.base.BaseActivity;
import com.rxjy.niujingji.commons.base.BasePresenter;
import com.rxjy.niujingji.utils.OkhttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by 解亚鑫 on 2018/6/23.
 */

public class TiXianActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.iv_add)
    ImageView ivAdd;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.et_jine)
    EditText etJine;
    @Bind(R.id.btn_end)
    Button btnEnd;

    @Override
    public int getLayout() {
        return R.layout.activity_tixian_item;
    }

    @Override
    public void initData() {
        tvTitle.setText("提现");
        initListener();
    }

    private void initListener() {
        ivBack.setOnClickListener(this);
        btnEnd.setOnClickListener(this);
    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_end:
                String trim = etJine.getText().toString().trim();
                HashMap<String, Object> map = new HashMap<>();
                map.put("card_no", App.cardNo);
                Log.i("tag", App.cardNo);
                Log.i("tag","提现金额>>>>>>>>>>>>>"+trim);
                map.put("Money", trim);
                OkhttpUtils.doPost(ApiEngine.SW_API_HOST + "AppAgent/HuiYuanTiXian", map, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String string = response.body().string();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                JSONObject jsonObject = null;
                                try {
                                    jsonObject = new JSONObject(string);
                                    int statusCode = jsonObject.getInt("StatusCode");
                                    if (statusCode==0){
                                        Toast.makeText(TiXianActivity.this, jsonObject.getString("StatusMsg"), Toast.LENGTH_SHORT).show();
                                        finish();
                                    }else if (statusCode==1){
                                        Toast.makeText(TiXianActivity.this, jsonObject.getString("StatusMsg"), Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                });
                break;
        }
    }
}
