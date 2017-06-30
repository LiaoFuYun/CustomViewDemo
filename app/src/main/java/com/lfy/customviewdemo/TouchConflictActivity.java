package com.lfy.customviewdemo;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lfy.customviewdemo.ui.HorizontalScrollViewEX;
import com.lfy.customviewdemo.ui.ListViewEx;

import java.util.ArrayList;

public class TouchConflictActivity extends AppCompatActivity {

    private HorizontalScrollViewEX container;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touchconflict);
        container = (HorizontalScrollViewEX) findViewById(R.id.container);

        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);

        for (int i = 0; i < 3; i++) {
            ViewGroup viewGroup = (ViewGroup) getLayoutInflater().inflate(R.layout.touchconflic_item, container, false);
            viewGroup.getLayoutParams().width = size.x;
            TextView titleTv = (TextView) viewGroup.findViewById(R.id.titleTv);
            titleTv.setText("Page" + i);

            viewGroup.setBackgroundColor(Color.rgb(255 / (i + 1), 255 / (i + 2), 125 / (i + 3)));

            setListView(viewGroup, i);

            container.addView(viewGroup);
        }
    }

    private void setListView(ViewGroup viewGroup, int i) {
        ListViewEx listView = (ListViewEx) viewGroup.findViewById(R.id.listV);
        listView.setmScrollEx(container);
        ArrayList<String> arrayList = new ArrayList<>();
        for (int j = 0; j < 20; j++) {
            arrayList.add(" 页面 " + i + " 项目 " + j);
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);
    }
}
