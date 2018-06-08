package com.rxjy.niujingji.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rxjy.niujingji.R;
import com.rxjy.niujingji.commons.utils.AutoUtils;
import com.rxjy.niujingji.commons.utils.GlideCircleTransform;
import com.rxjy.niujingji.entity.DownLineBean;

import java.util.ArrayList;
/**
 * Created by Administrator on 2018/5/30.
 */

public class DownLineAdapter extends BaseAdapter{

    private Context context;
    private ArrayList<DownLineBean> DownLinelist;

    public DownLineAdapter(Context context,ArrayList<DownLineBean> DownLinelist) {
        this.context = context;
        this.DownLinelist=DownLinelist;
    }

    @Override
    public int getCount() {
        return DownLinelist.get(0).getBody().getTable().size();
    }

    @Override
    public Object getItem(int position) {
        return DownLinelist.get(0).getBody().getTable().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder viewHolder;

        if(convertView==null){

            view = LayoutInflater.from(context).inflate(R.layout.downline_list_it, parent, false);
            AutoUtils.auto(view);
            viewHolder = new ViewHolder();
            /* 调用View的findViewById()方法分别获得image和name实例 */
            viewHolder.downlineItHead = (ImageView) view.findViewById(R.id.downline_it_Head);
            viewHolder.downlineItPhone = (TextView) view.findViewById(R.id.downline_it_phone);
            viewHolder.downlineItIdentity = (TextView) view.findViewById(R.id.downline_it_identity);
            viewHolder.downlineItActivation= (TextView) view.findViewById(R.id.downline_it_activation);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.downlineItPhone.setText(DownLinelist.get(0).getBody().getTable().get(position).getPhone());
        viewHolder.downlineItIdentity.setText(DownLinelist.get(0).getBody().getTable().get(position).getName());
        if(DownLinelist.get(0).getBody().getTable().get(position).getState().equals("已激活")){
            viewHolder.downlineItActivation.setTextColor(Color.parseColor("#33FF33"));
            viewHolder.downlineItActivation.setText(DownLinelist.get(0).getBody().getTable().get(position).getState());
        }else{
            viewHolder.downlineItActivation.setTextColor(Color.parseColor("#e60012"));
            viewHolder.downlineItActivation.setText(DownLinelist.get(0).getBody().getTable().get(position).getState());
        }
        if(DownLinelist.get(0).getBody().getTable().get(position).getImage()!=null){
            RequestOptions options = new RequestOptions();
            options.centerCrop().transform(new GlideCircleTransform(context));
            Glide.with(context).load(DownLinelist.get(0).getBody().getTable().get(position).getImage()).apply(options).into(viewHolder.downlineItHead);
        }
        return view;
    }








    class ViewHolder{
        TextView downlineItPhone,downlineItActivation,downlineItIdentity;
        ImageView downlineItHead;
    }

}
