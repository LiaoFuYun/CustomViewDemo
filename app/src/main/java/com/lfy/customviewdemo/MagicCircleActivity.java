package com.lfy.customviewdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.lfy.customviewdemo.ui.MagicCircle;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MagicCircleActivity extends AppCompatActivity {
    @BindView(R.id.magicCircleView)
    MagicCircle magicCircleView;
    @BindView(R.id.startBtn)
    Button startBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magiccircle);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.startBtn)
    public void onClick() {
        magicCircleView.startAnim();
    }
}
