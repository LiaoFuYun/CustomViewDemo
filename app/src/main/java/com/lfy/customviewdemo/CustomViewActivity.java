package com.lfy.customviewdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.lfy.customviewdemo.ui.CircleView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomViewActivity extends AppCompatActivity {
    @BindView(R.id.changeBtn)
    Button changeBtn;

    @BindView(R.id.circleView)
    CircleView circleView;
    @BindView(R.id.unCheckBtn)
    Button unCheckBtn;
    @BindView(R.id.checkBtn)
    Button checkBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customview);
        ButterKnife.bind(this);

//        circleView = new CircleView(this);
////        circleView.setBackgroundColor(Color.DKGRAY);
//        int px = DimensionUtil.dp2Px(this, 10);
//        circleView.setPadding(px, px, px, px);
//        ViewGroup contentView = (ViewGroup) getWindow().getDecorView().findViewById(android.R.id.content);
//        ((ViewGroup) contentView.getChildAt(0)).addView(circleView);
    }

    @OnClick({R.id.unCheckBtn, R.id.checkBtn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.changeBtn:
                circleView.setCircleColor(Color.GREEN);
                break;
            case R.id.unCheckBtn:
                circleView.unCheckDo();
                break;
            case R.id.checkBtn:
                circleView.checkDo();
                break;
        }
    }
}
