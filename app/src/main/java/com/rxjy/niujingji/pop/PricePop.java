package com.rxjy.niujingji.pop;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.rxjy.niujingji.R;
import com.rxjy.niujingji.commons.base.SingleBaseAdapter;
import com.rxjy.niujingji.commons.base.SingleViewHolder;
import com.rxjy.niujingji.commons.utils.AutoUtils;
import com.rxjy.niujingji.entity.PriceInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by AAA on 2017/9/1.
 */

public class PricePop extends PopupWindow {

    public interface OnPricePopClickListener {

        void customPriceData(PriceInfo data);

        void selectorPriceData(PriceInfo data);

    }

    private View mView;
    private Context context;
    private OnPricePopClickListener mListener;

    private List<PriceInfo> acreageList;

    private final AcreageAdapter mAdapter;

    private final ListView lvAcreage;

    private EditText etLeft;

    private EditText etRight;

    private Button btnSub;

    public PricePop(Context context, int layoutId, int with, int height) {
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

        lvAcreage = (ListView) mView.findViewById(R.id.lv_pop_price);

        etLeft = (EditText) mView.findViewById(R.id.et_pop_price_left);

        etRight = (EditText) mView.findViewById(R.id.et_pop_price_right);

        btnSub = (Button) mView.findViewById(R.id.btn_pop_price);

        acreageList = new ArrayList<>();

        mAdapter = new AcreageAdapter(context, acreageList);

        lvAcreage.setAdapter(mAdapter);

        lvAcreage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dismiss();
                etLeft.setText("");
                etRight.setText("");
                if (mListener != null)
                    mListener.selectorPriceData(acreageList.get(position));
            }
        });

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                String min = etLeft.getText().toString().trim();
                String max = etRight.getText().toString().trim();
                etLeft.setText("");
                etRight.setText("");
                PriceInfo info = new PriceInfo();
                info.setMin(min.equals("") ? null : Integer.parseInt(min));
                info.setMax(max.equals("") ? null : Integer.parseInt(max));
                if (min.equals("") && !max.equals("")) {
                    info.setValue("¥" + max + "以下");
                }
                if (!min.equals("") && max.equals("")) {
                    info.setValue("¥" + min + "以上");
                }
                if (!min.equals("") && !max.equals("")) {
                    info.setValue("¥" + min + "-" + max);
                }
                if (mListener != null)
                    mListener.customPriceData(info);
            }
        });

        etLeft.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String min = etLeft.getText().toString().trim();
                String max = etRight.getText().toString().trim();
                if (!min.equals("") || !max.equals("")) {
                    btnSub.setVisibility(View.VISIBLE);
                } else {
                    btnSub.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etRight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String min = etLeft.getText().toString().trim();
                String max = etRight.getText().toString().trim();
                if (!min.equals("") || !max.equals("")) {
                    btnSub.setVisibility(View.VISIBLE);
                } else {
                    btnSub.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public void setOnPricePopClickListener(OnPricePopClickListener mListener) {
        this.mListener = mListener;
    }

    public void setAcreageList(List<PriceInfo> acreageList) {
        this.acreageList.clear();
        this.acreageList.addAll(acreageList);
        mAdapter.notifyDataSetChanged();
    }

    class AcreageAdapter extends SingleBaseAdapter<PriceInfo, AcreageAdapter.ViewHolder> {

        public AcreageAdapter(Context context, List<PriceInfo> datas) {
            super(context, datas);
        }

        @Override
        public int getLayoutRes() {
            return R.layout.list_item_pop_price;
        }

        @Override
        public ViewHolder initViewHolder() {
            return new ViewHolder();
        }

        @Override
        public void onBindView(int position, PriceInfo data, ViewHolder holder) {
            holder.tvPrice.setText(data.getValue());
        }

        class ViewHolder implements SingleViewHolder {
            @Bind(R.id.tv_pop_price)
            TextView tvPrice;

            @Override
            public void onInFlate(View v) {
                ButterKnife.bind(this, v);
            }
        }
    }


}
