package com.lfy.customviewdemo;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CommonViewHolder {
    private int mPosition;
    private View mConvertView;
    private SparseArray<View> mViews;


    public CommonViewHolder(Context mContext, int layoutId, int position, ViewGroup parent) {
        mViews = new SparseArray<>();
        mPosition = position;
        mConvertView = LayoutInflater.from(mContext).inflate(layoutId, parent, false);
        mConvertView.setTag(this);
    }

    public static CommonViewHolder getHolder(Context mContext, View convertView, int layoutId, int position, ViewGroup parent) {
        CommonViewHolder holder;
        if (convertView == null) {
            holder = new CommonViewHolder(mContext, layoutId, position, parent);
        } else {
            holder = (CommonViewHolder) convertView.getTag();
        }
        holder.mPosition = position;
        return holder;
    }

    public <T extends View> T getView(int id) {
        View view = mViews.get(id);
        if (view == null) {
            view = mConvertView.findViewById(id);
            mViews.put(id, view);
        }
        return (T) view;
    }

    public CommonViewHolder setText(int id, String text) {
        TextView textView = getView(id);
        textView.setText(text);
        return this;
    }

    public View getConvertView() {
        return mConvertView;
    }

    public int getPosition() {
        return mPosition;
    }
}
