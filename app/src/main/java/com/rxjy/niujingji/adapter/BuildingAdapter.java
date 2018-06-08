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
import com.rxjy.niujingji.entity.BuildingListInfo;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by AAA on 2017/9/6.
 */

public class BuildingAdapter extends SingleBaseAdapter<BuildingListInfo.BuildingList, BuildingAdapter.ViewHolder> {

    public BuildingAdapter(Context context, List<BuildingListInfo.BuildingList> datas) {
        super(context, datas);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.list_item_houses;
    }

    @Override
    public ViewHolder initViewHolder() {
        return new ViewHolder();
    }

    @Override
    public void onBindView(int position, BuildingListInfo.BuildingList data, ViewHolder holder) {

        RequestOptions options = new RequestOptions();
        options.placeholder(R.mipmap.building_list_icon);
        options.error(R.mipmap.building_list_icon);
        Glide.with(context).load(data.getMainImage()).apply(options).into(holder.ivHouses);

        holder.tvName.setText(data.getName());
        holder.tvLocation.setText(data.getCountyName() + "-" + data.getBussiness());
        holder.tvType.setText(data.getBuildType());

        if (!data.getAvgDailyRent().equals("面议")) {
            holder.tvPrice.setText("¥" + data.getAvgDailyRent());
            holder.tvUnit.setVisibility(View.VISIBLE);
        } else {
            holder.tvPrice.setText(data.getAvgDailyRent());
            holder.tvUnit.setVisibility(View.INVISIBLE);
        }

    }

    class ViewHolder implements SingleViewHolder {
        @Bind(R.id.iv_houses)
        ImageView ivHouses;
        @Bind(R.id.tv_houses_name)
        TextView tvName;
        @Bind(R.id.tv_houses_price)
        TextView tvPrice;
        @Bind(R.id.tv_houses_location)
        TextView tvLocation;
        @Bind(R.id.tv_houses_type)
        TextView tvType;
        @Bind(R.id.tv_houses_unit)
        TextView tvUnit;

        @Override
        public void onInFlate(View v) {
            ButterKnife.bind(this, v);
        }
    }
}
