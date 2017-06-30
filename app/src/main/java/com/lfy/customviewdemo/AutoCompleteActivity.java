package com.lfy.customviewdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AutoCompleteActivity extends AppCompatActivity {
    @BindView(R.id.autoTv)
    AutoCompleteTextView autoTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autocomplete);
        ButterKnife.bind(this);
        String[] data = {"abcjflksjf", "ewtfsdfsaf", "wetfsfsadf", "sdsfdsfsda", "gwerwqr", "4erfsfsdf", "afdsfsadfsafsdf"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, data);
        autoTv.setAdapter(arrayAdapter);
    }
}
