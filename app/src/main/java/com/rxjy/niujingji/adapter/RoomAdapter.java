package com.rxjy.niujingji.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rxjy.niujingji.R;
import com.rxjy.niujingji.commons.base.SingleBaseAdapter;
import com.rxjy.niujingji.commons.base.SingleViewHolder;
import com.rxjy.niujingji.entity.RoomListInfo;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by AAA on 2017/9/8.
 */

public class RoomAdapter extends SingleBaseAdapter<RoomListInfo.RoomList, RoomAdapter.ViewHolder> {

    public RoomAdapter(Context context, List<RoomListInfo.RoomList> datas) {
        super(context, datas);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.list_item_room;
    }

    @Override
    public ViewHolder initViewHolder() {
        return new ViewHolder();
    }

    @Override
    public void onBindView(int position, RoomListInfo.RoomList data, ViewHolder holder) {
        holder.tvRoomAcreage.setText(data.getHousingArea() + "㎡");
        holder.tvRoomType.setText(data.getFit());
        holder.tvRoomTime.setText(data.getDay() + "天前");
        if (data.getTypes().equals("卖")) {
            holder.ivStatus.setImageResource(R.mipmap.sell_small_icon);
            holder.tvRoomPrice.setText("¥" + data.getSalePrice());
            holder.tvUnit.setText("元/㎡");
        } else {
            holder.ivStatus.setImageResource(R.mipmap.rent_small_icon);
            holder.tvRoomPrice.setText("¥" + data.getDayRent());
            holder.tvUnit.setText("元/㎡/天起");
        }
        RequestOptions options = new RequestOptions();
        options.placeholder(R.mipmap.building_icon);
        options.error(R.mipmap.building_icon);
        Glide.with(context).load(data.getThumbnail()).apply(options).into(holder.ivRoom);
    }

    class ViewHolder implements SingleViewHolder {
        @Bind(R.id.iv_room)
        ImageView ivRoom;
        @Bind(R.id.tv_room_price)
        TextView tvRoomPrice;
        @Bind(R.id.tv_room_acreage)
        TextView tvRoomAcreage;
        @Bind(R.id.tv_room_type)
        TextView tvRoomType;
        @Bind(R.id.tv_room_time)
        TextView tvRoomTime;
        @Bind(R.id.tv_room_unit)
        TextView tvUnit;
        @Bind(R.id.iv_status)
        ImageView ivStatus;

        @Override
        public void onInFlate(View v) {
            ButterKnife.bind(this, v);
        }
    }
}
