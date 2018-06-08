package com.rxjy.niujingji.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.rxjy.niujingji.R;
import com.rxjy.niujingji.commons.base.SingleBaseAdapter;
import com.rxjy.niujingji.commons.base.SingleViewHolder;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hjh on 2017/12/29.
 */

public class AccountListAdapter extends SingleBaseAdapter<String,AccountListAdapter.ViewHolder> {



    public AccountListAdapter(Context context, List<String> datas) {
        super(context, datas);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_accountview;
    }

    @Override
    public ViewHolder initViewHolder() {
        return new ViewHolder();
    }

    @Override
    public void onBindView(final int position, String data, ViewHolder holder) {
        holder.tvPhonenum.setText(data);
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete.clickdel(position);
            }
        });
    }


    class ViewHolder implements SingleViewHolder {
        @Bind(R.id.tv_phonenum)
        TextView tvPhonenum;
        @Bind(R.id.iv_delete)
        ImageView ivDelete;

        @Override
        public void onInFlate(View v) {
            ButterKnife.bind(this, v);
        }
    }

    private Delete delete;

    public interface Delete{
        public void clickdel(int position);
    }

    public void clickdel(Delete del){
        delete=del;
    }

}
