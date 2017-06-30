package com.lfy.customviewdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.lfy.customviewdemo.ui.WaveView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WaveActivity extends AppCompatActivity {
    @BindView(R.id.waveView)
    WaveView waveView;
    @BindView(R.id.startBtn)
    Button startBtn;
    @BindView(R.id.stopBtn)
    Button stopBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waveview);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.startBtn, R.id.stopBtn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.startBtn:
                waveView.setRunning();
                break;
            case R.id.stopBtn:
                waveView.stopRun();
                break;
        }
    }
}
