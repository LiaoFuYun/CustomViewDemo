package com.lfy.customviewdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lfy.customviewdemo.ui.PathTanView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PathTanViewActivity extends AppCompatActivity {
    @BindView(R.id.pathTanView)
    PathTanView pathTanView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pathtanview);
        ButterKnife.bind(this);
    }
}
