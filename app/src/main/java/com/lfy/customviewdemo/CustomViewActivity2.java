package com.lfy.customviewdemo;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.lfy.customviewdemo.ui.StickyLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomViewActivity2 extends AppCompatActivity {
    @BindView(R.id.container)
    StickyLayout container;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customerview2);
        ButterKnife.bind(this);

        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);

        for (int i = 0; i < 3; i++) {
            ViewGroup viewGroup = (ViewGroup) getLayoutInflater().inflate(R.layout.touchconflic_item2, container, false);
            viewGroup.getLayoutParams().width = size.x;
            TextView titleTv = (TextView) viewGroup.findViewById(R.id.titleTv);
            titleTv.setText("Page" + i);
            viewGroup.setBackgroundColor(Color.rgb(255 / (i + 1), 255 / (i + 2), 125 / (i + 3)));
            setListView(viewGroup, i);
            container.addView(viewGroup);
        }
    }

    private void setListView(ViewGroup viewGroup, int i) {
        ListView listView = (ListView) viewGroup.findViewById(R.id.listV);
        ArrayList<String> arrayList = new ArrayList<>();
        for (int j = 0; j < 20; j++) {
            arrayList.add(" 页面 " + i + " 项目 " + j);
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);
    }
}
