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
import com.rxjy.niujingji.entity.AreaListInfo;
import com.rxjy.niujingji.entity.TradingListInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by AAA on 2017/9/1.
 */

public class AreaPop extends PopupWindow {

    public interface OnAreaPopClickListener {

        void selectorAreaData(AreaListInfo.AreaList data);

        void selectorAreaUnlimitedData();

        void selectorTradingData(TradingListInfo.TradingList data);

        void selectorTradingUnlimitedData();

    }

    private View mView;
    private Context context;
    private OnAreaPopClickListener mListener;

    private List<AreaListInfo.AreaList> areaList;

    private List<TradingListInfo.TradingList> tradingList;

    private final AreaAdapter areaAdapter;

    private final TradingAdapter traAdapter;

    private final ListView lvArea;

    private final ListView lvTrading;

    public AreaPop(Context context, int layoutId, int with, int height) {
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

        lvArea = (ListView) mView.findViewById(R.id.lv_pop_area);

        lvTrading = (ListView) mView.findViewById(R.id.lv_pop_trading);

        areaList = new ArrayList<>();

        tradingList = new ArrayList<>();

        areaAdapter = new AreaAdapter(context, areaList);

        traAdapter = new TradingAdapter(context, tradingList);

        lvArea.setAdapter(areaAdapter);

        lvTrading.setAdapter(traAdapter);

        lvArea.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for (AreaListInfo.AreaList areInfo : areaList) {
                    areInfo.setIsSelector(0);
                }
                AreaListInfo.AreaList info = AreaPop.this.areaList.get(position);
                info.setIsSelector(1);
                areaAdapter.notifyDataSetChanged();
                if (position == 0) {
                    dismiss();
                    tradingList.clear();
                    traAdapter.notifyDataSetChanged();
                    if (mListener != null)
                        mListener.selectorAreaUnlimitedData();
                } else {
                    if (mListener != null)
                        mListener.selectorAreaData(info);
                }
            }
        });

        lvTrading.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TradingListInfo.TradingList info = AreaPop.this.tradingList.get(position);
                dismiss();
                if (position == 0) {
                    if (mListener != null)
                        mListener.selectorTradingUnlimitedData();
                } else {
                    if (mListener != null)
                        mListener.selectorTradingData(info);
                }
            }
        });

    }

    public void setOnAreaPopClickListener(OnAreaPopClickListener mListener) {
        this.mListener = mListener;
    }

    public void setAreaList(List<AreaListInfo.AreaList> cityList) {
        this.areaList.clear();
        this.areaList.addAll(cityList);
        this.areaList.add(0, new AreaListInfo.AreaList("不限", 1));
        areaAdapter.notifyDataSetChanged();
    }

    public void clearTradingList(){
        tradingList.clear();
        traAdapter.notifyDataSetChanged();
    }

    public void setTradingList(List<TradingListInfo.TradingList> tradingList) {
        this.tradingList.clear();
        this.tradingList.addAll(tradingList);
        this.tradingList.add(0, new TradingListInfo.TradingList("不限"));
        traAdapter.notifyDataSetChanged();
    }

    class AreaAdapter extends SingleBaseAdapter<AreaListInfo.AreaList, AreaAdapter.ViewHolder> {

        public AreaAdapter(Context context, List<AreaListInfo.AreaList> datas) {
            super(context, datas);
        }

        @Override
        public int getLayoutRes() {
            return R.layout.list_item_pop_area;
        }

        @Override
        public ViewHolder initViewHolder() {
            return new ViewHolder();
        }

        @Override
        public void onBindView(int position, AreaListInfo.AreaList data, ViewHolder holder) {
            if (data.getIsSelector() == 0) {
                holder.tvArea.setTextColor(context.getResources().getColor(R.color.colorGrayDark));
                holder.tvArea.setBackgroundResource(R.color.colorWhite);
            } else {
                holder.tvArea.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                holder.tvArea.setBackgroundResource(R.color.colorBG);
            }
            holder.tvArea.setText(data.getName());
        }

        class ViewHolder implements SingleViewHolder {
            @Bind(R.id.tv_pop_area)
            TextView tvArea;

            @Override
            public void onInFlate(View v) {
                ButterKnife.bind(this, v);
            }
        }
    }

    class TradingAdapter extends SingleBaseAdapter<TradingListInfo.TradingList, TradingAdapter.ViewHolder> {

        public TradingAdapter(Context context, List<TradingListInfo.TradingList> datas) {
            super(context, datas);
        }

        @Override
        public int getLayoutRes() {
            return R.layout.list_item_pop_trading;
        }

        @Override
        public ViewHolder initViewHolder() {
            return new ViewHolder();
        }

        @Override
        public void onBindView(int position, TradingListInfo.TradingList data, ViewHolder holder) {
            holder.tvTrading.setText(data.getName());
        }

        class ViewHolder implements SingleViewHolder {
            @Bind(R.id.tv_pop_trading)
            TextView tvTrading;

            @Override
            public void onInFlate(View v) {
                ButterKnife.bind(this, v);
            }
        }
    }

}
