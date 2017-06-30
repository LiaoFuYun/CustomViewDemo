package com.lfy.customviewdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.lfy.customviewdemo.ui.ScrollViewEx;

import java.util.ArrayList;

public class TouchConflictActivity2 extends AppCompatActivity {

    private ScrollViewEx container;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touchconflict2);
        container = (ScrollViewEx) findViewById(R.id.container);
        setListView();
    }

    private void setListView() {
        ListView listView = (ListView) findViewById(R.id.listV);
        ArrayList<String> arrayList = new ArrayList<>();
        for (int j = 0; j < 50; j++) {
            arrayList.add(" 项目 " + j);
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);
        container.setListView(listView);
    }
}
