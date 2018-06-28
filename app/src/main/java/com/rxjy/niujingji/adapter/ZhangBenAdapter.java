package com.rxjy.niujingji.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rxjy.niujingji.R;
import com.rxjy.niujingji.entity.ZhangBenInfo;

import java.util.List;

/**
 * Created by 解亚鑫 on 2018/6/20.
 */

public class ZhangBenAdapter extends BaseAdapter {
    List<ZhangBenInfo.BodyBean.TableBean> table;
    Context context;

    public ZhangBenAdapter(List<ZhangBenInfo.BodyBean.TableBean> table, Context context) {
        this.table = table;
        this.context = context;
    }

    @Override
    public int getCount() {
        return table.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.zhangben_item_layout,null);
        TextView tv_laiyuan = (TextView) convertView.findViewById(R.id.tv_laiyuan);
        TextView tv_balance = (TextView) convertView.findViewById(R.id.tv_balance);
        ImageView img_zhangben = (ImageView) convertView.findViewById(R.id.img_zhangben);
        switch (table.get(position).getLaiYuan()){
            case "个人":
                tv_laiyuan.setText(table.get(position).getLaiYuan());
                tv_balance.setText(table.get(position).getSumMoney()+"");
                img_zhangben.setImageResource(R.mipmap.zhuce);
                break;
            case "会员":
                tv_laiyuan.setText(table.get(position).getLaiYuan());
                tv_balance.setText(table.get(position).getSumMoney()+"");
                img_zhangben.setImageResource(R.mipmap.shoudan);
                break;
            case "提现记录":
                tv_laiyuan.setText(table.get(position).getLaiYuan());
                tv_balance.setText(table.get(position).getSumMoney()+"");
                img_zhangben.setImageResource(R.mipmap.tixian);
                break;
            case "预收":
                tv_laiyuan.setText(table.get(position).getLaiYuan());
                tv_balance.setText(table.get(position).getSumMoney()+"");
                img_zhangben.setImageResource(R.mipmap.yushoukuan);
                break;
                default:
        }
        return convertView;
    }
}
