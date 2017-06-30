package com.lfy.customviewdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lfy.customviewdemo.bean.PieBean;
import com.lfy.customviewdemo.ui.PieView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PieActivity extends AppCompatActivity {
    @BindView(R.id.pieView)
    PieView pieView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pieview);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        ArrayList<PieBean> arrayList = new ArrayList<>();
        arrayList.add(new PieBean("", 0.25f, Color.BLUE));
        arrayList.add(new PieBean("", 0.15f, Color.GREEN));
        arrayList.add(new PieBean("", 0.1f, Color.CYAN));
        arrayList.add(new PieBean("", 0.15f, Color.GRAY));
        arrayList.add(new PieBean("", 0.25f, Color.RED));
        arrayList.add(new PieBean("", 0.10f, Color.YELLOW));

        pieView.setPieList(arrayList);
    }
}
