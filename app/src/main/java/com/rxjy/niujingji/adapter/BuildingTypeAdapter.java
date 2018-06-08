package com.rxjy.niujingji.adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rxjy.niujingji.R;
import com.rxjy.niujingji.commons.base.SingleBaseAdapter;
import com.rxjy.niujingji.commons.base.SingleViewHolder;
import com.rxjy.niujingji.entity.BuildingTypeInfo;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by AAA on 2017/9/8.
 */

public class BuildingTypeAdapter extends SingleBaseAdapter<BuildingTypeInfo, BuildingTypeAdapter.ViewHolder> {

    public BuildingTypeAdapter(Context context, List<BuildingTypeInfo> datas) {
        super(context, datas);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.list_item_building_type;
    }

    @Override
    public ViewHolder initViewHolder() {
        return new ViewHolder();
    }

    @Override
    public void onBindView(int position, BuildingTypeInfo data, ViewHolder holder) {

        holder.tvType.setText(data.getType());
        holder.tvCount.setText(data.getCount() + "套");

        if (data.getIsSelector() == 0) {
            holder.tvType.setTextColor(context.getResources().getColor(R.color.colorGrayDark));
            holder.tvCount.setTextColor(context.getResources().getColor(R.color.colorGrayDark));
            holder.linType.setBackgroundResource(R.drawable.shape_radio_button_normal);
        } else {
            holder.tvType.setTextColor(context.getResources().getColor(R.color.colorWhite));
            holder.tvCount.setTextColor(context.getResources().getColor(R.color.colorWhite));
            holder.linType.setBackgroundResource(R.drawable.shape_radio_button_checked);
        }

    }

    class ViewHolder implements SingleViewHolder {
        @Bind(R.id.tv_list_item_building_type)
        TextView tvType;
        @Bind(R.id.tv_list_item_building_count)
        TextView tvCount;
        @Bind(R.id.lin_list_item_building_type)
        LinearLayout linType;

        @Override
        public void onInFlate(View v) {
            ButterKnife.bind(this, v);
        }
    }
}
