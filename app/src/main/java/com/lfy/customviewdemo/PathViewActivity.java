package com.lfy.customviewdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lfy.customviewdemo.ui.PathView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PathViewActivity extends AppCompatActivity {
    @BindView(R.id.pathView)
    PathView pathView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pathview);
        ButterKnife.bind(this);
    }
}
