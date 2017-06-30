package com.lfy.customviewdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ListViewActivity extends AppCompatActivity {
    @BindView(R.id.listV)
    ListView listV;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);

        ArrayList<String> data = new ArrayList<>();
        data.add("item 1");
        data.add("item 2");
        data.add("item 3");
        data.add("item 4");
        data.add("item 5");
        data.add("item 6");
        data.add("item 7");

        MyAdapter adapter = new MyAdapter(data, this);
        listV.setAdapter(adapter);
    }


    private class MyAdapter extends CommonAdapter<String> {

        public MyAdapter(List<String> mData, Context mContext) {
            super(mData, mContext, R.layout.list_item);
        }

        @Override
        public void convert(CommonViewHolder holder, String s) {
            holder.setText(R.id.textV, s);
        }
    }
}
