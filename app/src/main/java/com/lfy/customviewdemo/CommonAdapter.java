package com.lfy.customviewdemo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class CommonAdapter<T> extends BaseAdapter {
    private Context mContext;
    private List<T> mData;
    private int layoutId;

    public CommonAdapter(List<T> mData, Context mContext, int layoutId) {
        this.mData = mData;
        this.mContext = mContext;
        this.layoutId = layoutId;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        T t = mData.get(position);
        CommonViewHolder holder = CommonViewHolder.getHolder(mContext, convertView, layoutId, position, parent);
        convert(holder, t);
        return holder.getConvertView();
    }

    public abstract void convert(CommonViewHolder holder, T t);
}
