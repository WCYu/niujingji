package com.rxjy.niujingji.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.rxjy.niujingji.R;
import com.rxjy.niujingji.commons.base.SingleBaseAdapter;
import com.rxjy.niujingji.commons.base.SingleViewHolder;
import com.rxjy.niujingji.entity.WalletRecordInfo;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by AAA on 2017/7/18.
 */

public class WalletRecordAdapter extends SingleBaseAdapter<WalletRecordInfo.WalletRecord, WalletRecordAdapter.ViewHolder> {

    public WalletRecordAdapter(Context context, List<WalletRecordInfo.WalletRecord> datas) {
        super(context, datas);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.list_item_wallet_new;
    }

    @Override
    public ViewHolder initViewHolder() {
        return new ViewHolder();
    }

    @Override
    public void onBindView(int position, WalletRecordInfo.WalletRecord data, ViewHolder holder) {
        holder.tvState.setText(data.getLeiXing());
        holder.tvTime.setText(data.getTianJaiShiJian());
        holder.tvContent.setText(data.getZhaiYao1());
        holder.tvPrice.setText(data.getIncomeExpenditure());
        if (Double.parseDouble(data.getIncomeExpenditure()) > 0) {
            holder.tvPrice.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        } else {
            holder.tvPrice.setTextColor(context.getResources().getColor(R.color.colorRedLight));
        }
    }

    class ViewHolder implements SingleViewHolder {
        @Bind(R.id.tv_list_item_wallet_state)
        TextView tvState;
        @Bind(R.id.tv_list_item_wallet_time)
        TextView tvTime;
        @Bind(R.id.tv_list_item_wallet_content)
        TextView tvContent;
        @Bind(R.id.tv_list_item_wallet_price)
        TextView tvPrice;

        @Override
        public void onInFlate(View v) {
            ButterKnife.bind(this, v);
        }
    }
}
