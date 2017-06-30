package com.lfy.customviewdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lfy.customviewdemo.ui.RegionClickView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegionClickActivity extends AppCompatActivity {

    @BindView(R.id.regionClickView)
    RegionClickView regionClickView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regionclick);
        ButterKnife.bind(this);
    }
}
