package com.lfy.customviewdemo.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.lfy.customviewdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MVPActivity extends AppCompatActivity implements MVPInterface.ViewI {
    @BindView(R.id.textV)
    TextView textV;
    @BindView(R.id.btn)
    Button btn;

    private MVPPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        ButterKnife.bind(this);
        presenter = new MVPPresenter(this);
    }

    @OnClick(R.id.btn)
    public void onClick() {
        presenter.getText(15500);
    }

    @Override
    public void setText(String text) {
        textV.setText(text);
    }
}
