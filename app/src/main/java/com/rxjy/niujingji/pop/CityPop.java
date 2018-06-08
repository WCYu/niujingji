package com.rxjy.niujingji.pop;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.rxjy.niujingji.R;
import com.rxjy.niujingji.commons.base.SingleBaseAdapter;
import com.rxjy.niujingji.commons.base.SingleViewHolder;
import com.rxjy.niujingji.commons.utils.AutoUtils;
import com.rxjy.niujingji.entity.CityListInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by AAA on 2017/9/1.
 */

public class CityPop extends PopupWindow {

    public interface OnCityPopClickListener {

        void selectorCityData(CityListInfo.CityList data);

    }

    private View mView;
    private Context context;
    private OnCityPopClickListener mListener;

    private List<CityListInfo.CityList> cityList;

    private final CityAdapter mAdapter;

    private final ListView lvCity;

    public CityPop(Context context, int layoutId, int with, int height) {
        this.context = context;
        mView = LayoutInflater.from(context).inflate(layoutId, null);

        AutoUtils.auto(mView);

        setContentView(mView);
        //设置宽度
        setWidth(with);
        //设置高度
        setHeight(height);
        //设置背景透明
        setBackgroundDrawable(new ColorDrawable(80));

        lvCity = (ListView) mView.findViewById(R.id.lv_pop_city);

        cityList = new ArrayList<>();

        mAdapter = new CityAdapter(context, cityList);

        lvCity.setAdapter(mAdapter);

        lvCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dismiss();
                if (mListener != null)
                    mListener.selectorCityData(cityList.get(position));
            }
        });

    }

    public void setOnCityPopClickListener(OnCityPopClickListener mListener) {
        this.mListener = mListener;
    }

    public void setCityList(List<CityListInfo.CityList> cityList) {
        this.cityList.clear();
        this.cityList.addAll(cityList);
        mAdapter.notifyDataSetChanged();
    }

    class CityAdapter extends SingleBaseAdapter<CityListInfo.CityList, CityAdapter.ViewHolder> {

        public CityAdapter(Context context, List<CityListInfo.CityList> datas) {
            super(context, datas);
        }

        @Override
        public int getLayoutRes() {
            return R.layout.list_item_pop_city;
        }

        @Override
        public ViewHolder initViewHolder() {
            return new ViewHolder();
        }

        @Override
        public void onBindView(int position, CityListInfo.CityList data, ViewHolder holder) {
            holder.tvCity.setText(data.getName());
        }

        class ViewHolder implements SingleViewHolder {
            @Bind(R.id.tv_pop_city)
            TextView tvCity;

            @Override
            public void onInFlate(View v) {
                ButterKnife.bind(this, v);
            }
        }
    }


}
