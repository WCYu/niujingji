package com.rxjy.niujingji.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.rxjy.niujingji.R;
import com.rxjy.niujingji.commons.base.SingleBaseAdapter;
import com.rxjy.niujingji.commons.base.SingleViewHolder;
import com.rxjy.niujingji.entity.HotWordListInfo;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by AAA on 2017/9/7.
 */

public class HotWordAdapter extends SingleBaseAdapter<HotWordListInfo.HotWordList, HotWordAdapter.ViewHolder> {

    public HotWordAdapter(Context context, List<HotWordListInfo.HotWordList> datas) {
        super(context, datas);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.list_item_hot_word;
    }

    @Override
    public ViewHolder initViewHolder() {
        return new ViewHolder();
    }

    @Override
    public void onBindView(int position, HotWordListInfo.HotWordList data, ViewHolder holder) {
        holder.tvHotWord.setText(data.getBusinessName());
    }

    class ViewHolder implements SingleViewHolder {
        @Bind(R.id.tv_hot_word)
        TextView tvHotWord;

        @Override
        public void onInFlate(View v) {
            ButterKnife.bind(this, v);
        }
    }
}
