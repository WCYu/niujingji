package com.rxjy.niujingji.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.rxjy.niujingji.R;
import com.rxjy.niujingji.entity.ZhangBenDetailsInfo;

import java.util.List;

/**
 * Created by 解亚鑫 on 2018/6/21.
 */

public class ZhangBenDetailsAdapter extends BaseAdapter {
    List<ZhangBenDetailsInfo.BodyBean.TableBean> table1;
    Context context;

    public ZhangBenDetailsAdapter(List<ZhangBenDetailsInfo.BodyBean.TableBean> table1, Context context) {
        this.table1 = table1;
        this.context = context;
    }

    @Override
    public int getCount() {
        return table1.size();
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
        convertView = LayoutInflater.from(context).inflate(R.layout.zhangben_details_item, null);
        ViewHolder viewHolder = new ViewHolder(convertView);
        viewHolder.tv_danhao.setText(table1.get(position).getDanHao() + "");
        viewHolder.tv_ly.setText(table1.get(position).getLaiYuan() + "");
        viewHolder.tv_money.setText("+ " + table1.get(position).getMoney());
        viewHolder.tv_time.setText(table1.get(position).getTJShiJian() + "");
        int shouZhiType = table1.get(position).getShouZhiType();
        switch (shouZhiType) {
            case 1:
                viewHolder.tv_money.setText("+ " + table1.get(position).getMoney());
                break;
            case 2:
                viewHolder.tv_money.setText("- " + table1.get(position).getMoney());
                break;
            default:
                viewHolder.tv_money.setText("- " + table1.get(position).getMoney());
                break;
        }
        return convertView;
    }

    class ViewHolder {
        public View rootView;
        public TextView tv_ly;
        public TextView tv_danhao;
        public TextView tv_time;
        public TextView tv_money;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_ly = (TextView) rootView.findViewById(R.id.tv_ly);
            this.tv_danhao = (TextView) rootView.findViewById(R.id.tv_danhao);
            this.tv_time = (TextView) rootView.findViewById(R.id.tv_time);
            this.tv_money = (TextView) rootView.findViewById(R.id.tv_money);
        }

    }
}
