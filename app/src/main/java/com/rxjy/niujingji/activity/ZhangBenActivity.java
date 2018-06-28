package com.rxjy.niujingji.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.rxjy.niujingji.R;
import com.rxjy.niujingji.adapter.ZhangBenAdapter;
import com.rxjy.niujingji.api.ApiEngine;
import com.rxjy.niujingji.commons.App;
import com.rxjy.niujingji.commons.base.BaseActivity;
import com.rxjy.niujingji.commons.base.BasePresenter;
import com.rxjy.niujingji.entity.ZhangBenInfo;
import com.rxjy.niujingji.utils.OkhttpUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by 解亚鑫 on 2018/6/20.
 */

public class ZhangBenActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.iv_add)
    ImageView ivAdd;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.lv_zhangben)
    ListView lvzhangben;
    private static final String TAG = "ZhangBenActivity";
    @Bind(R.id.img_zonge)
    ImageView imgZonge;
    @Bind(R.id.tv_sum)
    TextView tvSum;
    public List<ZhangBenInfo.BodyBean.TableBean> table;
    private ZhangBenInfo zhangBenInfo;

    @Override
    public int getLayout() {
        return R.layout.activity_zhangben;
    }

    @Override
    public void initData() {
        tvTitle.setText("账本");
        getData();
        initListener();
    }

    private void initListener() {
        ivBack.setOnClickListener(this);
        lvzhangben.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ZhangBenActivity.this, ZhangBenDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("table",table.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void getData() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("card_no", App.cardNo);
//        map.put("card_no", "s00004141");
        OkhttpUtils.doPost(ApiEngine.New_SW_API_HOST + "AppAgent/GetZhangBenList", map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                Log.i(TAG, "data>>>>>>>" + string);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson1 = new Gson();
                        zhangBenInfo = gson1.fromJson(string, ZhangBenInfo.class);
                        tvSum.setText(zhangBenInfo.getBody().getTotal().getARMoney()+"");
                        table = zhangBenInfo.getBody().getTable();
                        initAdapter();
                    }
                });
            }
        });
    }

    private void initAdapter() {
        ZhangBenAdapter adapter = new ZhangBenAdapter(table, ZhangBenActivity.this);
        lvzhangben.setAdapter(adapter);
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
