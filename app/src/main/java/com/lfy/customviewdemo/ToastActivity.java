package com.lfy.customviewdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ToastActivity extends AppCompatActivity {
    @BindView(R.id.showToast)
    Button showToast;
    @BindView(R.id.showToast2)
    Button showToast2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.showToast, R.id.showToast2})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.showToast:
                Toast toast = Toast.makeText(this, "这是一个吐司提示", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                LinearLayout view = (LinearLayout) toast.getView();
                view.setBackgroundColor(Color.WHITE);
                ImageView imageView = new ImageView(this);
                imageView.setImageResource(R.mipmap.ic_launcher);
                view.addView(imageView, 0);
                TextView textV = (TextView) view.findViewById(android.R.id.message);
                textV.setTextColor(Color.BLUE);
                textV.setTextSize(20);
                textV.setText("改变后的提示");
                toast.show();
                break;
            case R.id.showToast2:
                showToast2();
                break;
        }

    }

    private void showToast2() {
        Toast toast = Toast.makeText(this, "这是一个吐司提示", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
        View convertView = LayoutInflater.from(this).inflate(R.layout.toast_item, null);
        toast.setView(convertView);
        toast.show();
    }
}
