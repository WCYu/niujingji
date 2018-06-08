package com.rxjy.niujingji.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rxjy.niujingji.R;
import com.rxjy.niujingji.commons.base.SingleBaseAdapter;
import com.rxjy.niujingji.commons.base.SingleViewHolder;
import com.rxjy.niujingji.entity.ClientListInfo;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by AAA on 2017/7/26.
 */

public class HomeAdapter extends SingleBaseAdapter<ClientListInfo.ClientInfo, HomeAdapter.ViewHolder> {

    public HomeAdapter(Context context, List<ClientListInfo.ClientInfo> datas) {
        super(context, datas);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.list_item_home;
    }

    @Override
    public ViewHolder initViewHolder() {
        return new ViewHolder();
    }

    @Override
    public void onBindView(int position, final ClientListInfo.ClientInfo data, ViewHolder holder) {
        holder.tvName.setText(data.getXingMing());
        holder.tvTime.setText(data.getTianJiaShiJian());
        holder.tvOrderNo.setText(data.getDanHao());
        holder.tvState.setText(data.getStateName());
        holder.ivCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + data.getShouJiHaoYi()));
                context.startActivity(intent);
            }
        });
        if (data.getState() == 8 || data.getState() == 3){
            holder.tvState.setTextColor(context.getResources().getColor(R.color.colorRedLight));
        }else {
            holder.tvState.setTextColor(context.getResources().getColor(R.color.colorBlackLight));
        }
    }

    class ViewHolder implements SingleViewHolder {
        @Bind(R.id.tv_list_item_home_name)
        TextView tvName;
        @Bind(R.id.tv_list_item_home_time)
        TextView tvTime;
        @Bind(R.id.tv_list_item_home_order_no)
        TextView tvOrderNo;
        @Bind(R.id.tv_list_item_home_state)
        TextView tvState;
        @Bind(R.id.iv_list_item_home_call)
        ImageView ivCall;

        @Override
        public void onInFlate(View v) {
            ButterKnife.bind(this, v);
        }
    }
}
