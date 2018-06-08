package com.rxjy.niujingji.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2017/7/7.
 */
public class WelcomeAdapter extends PagerAdapter
{

    private List<View> dataList;

    public WelcomeAdapter(List<View> dataList)
    {
        this.dataList = dataList;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position)
    {
        container.addView(dataList.get(position));
        return dataList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object)
    {
        container.removeView(dataList.get(position));
    }

    @Override
    public int getCount()
    {
        return dataList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object)
    {
        return (view == object);
    }
}
