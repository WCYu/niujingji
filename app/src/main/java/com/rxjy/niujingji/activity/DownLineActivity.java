package com.rxjy.niujingji.activity;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.rxjy.niujingji.R;
import com.rxjy.niujingji.adapter.DownLineAdapter;
import com.rxjy.niujingji.commons.App;
import com.rxjy.niujingji.commons.base.BaseActivity;
import com.rxjy.niujingji.entity.DownLineBean;
import com.rxjy.niujingji.mvp.contract.DownLineContract;
import com.rxjy.niujingji.mvp.presenter.DownLinePresenter;
import com.rxjy.niujingji.widget.xlistview.XListView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/5/30.
 */

public class DownLineActivity extends BaseActivity<DownLinePresenter> implements DownLineContract.View{

    private ListView downLineList;
    private ArrayList<DownLineBean> downLineDateList=new ArrayList<>();
    private TextView tvTitleName;
    private ImageView ivBack;




    @Override
    public int getLayout() {
        return R.layout.activity_downlin;
    }

    @Override
    public void initData() {
        initID();
        tvTitleName.setText("下线");
        Log.d("DownLineActivity", "initData:=== "+App.cardNo);
        mPresenter.getDownLineList(App.cardNo);

    }

    private void initID() {
        downLineList= (ListView) findViewById(R.id.my_downline_list);
        tvTitleName= (TextView) findViewById(R.id.tv_title);
        ivBack= (ImageView) findViewById(R.id.iv_back);
    }


    @Override
    protected DownLinePresenter onCreatePresenter() {
        return new DownLinePresenter(this);
    }


    @Override
    public void lodeDate(DownLineBean downLineBean) {
//        Toast.makeText(DownLineActivity.this,"成功",Toast.LENGTH_SHORT).show();
        downLineDateList.add(downLineBean);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if(downLineDateList.get(0).getBody().getTable().size()>0){
            DownLineAdapter downLineAdapter=new DownLineAdapter(DownLineActivity.this,downLineDateList);
            downLineList.setAdapter(downLineAdapter);
        }
    }

    @Override
    public void lodeDateErr(String s) {
        Toast.makeText(DownLineActivity.this,""+s,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void hideDialog() {

    }

}
