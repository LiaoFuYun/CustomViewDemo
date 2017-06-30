package com.lfy.customviewdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lfy.customviewdemo.ui.SearchView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchViewActivity extends AppCompatActivity {
    @BindView(R.id.searchView)
    SearchView searchView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchview);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.searchView)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.searchView:
                searchView.startSearch();
                break;
        }
    }
}
