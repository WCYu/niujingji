package com.rxjy.niujingji.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rxjy.niujingji.R;
import com.rxjy.niujingji.commons.base.SingleBaseAdapter;
import com.rxjy.niujingji.commons.base.SingleViewHolder;
import com.rxjy.niujingji.entity.MsgInfo;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by AAA on 2017/8/14.
 */

public class MsgAdapter extends SingleBaseAdapter<MsgInfo.Msg, MsgAdapter.ViewHolder> {

    public MsgAdapter(Context context, List<MsgInfo.Msg> datas) {
        super(context, datas);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.list_item_msg_list;
    }

    @Override
    public ViewHolder initViewHolder() {
        return new ViewHolder();
    }

    @Override
    public void onBindView(int position, MsgInfo.Msg data, ViewHolder holder) {
        holder.tvTime.setText(data.getCreateTime());
        holder.tvTitle.setText(data.getName());
        holder.tvDepartment.setText(data.getCreatedByName());
//        Glide.with(context).load(data.getImgUrl()).into(holder.ivPhoto);
    }

    class ViewHolder implements SingleViewHolder {
        @Bind(R.id.tv_list_item_msg_list_time)
        TextView tvTime;
        @Bind(R.id.tv_list_item_msg_list_title)
        TextView tvTitle;
        @Bind(R.id.tv_list_item_msg_list_department)
        TextView tvDepartment;
        @Bind(R.id.iv_list_item_msg_list_photo)
        ImageView ivPhoto;

        @Override
        public void onInFlate(View v) {
            ButterKnife.bind(this, v);
        }
    }
}
