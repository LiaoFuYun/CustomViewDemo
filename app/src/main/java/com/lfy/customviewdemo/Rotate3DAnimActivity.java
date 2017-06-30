package com.lfy.customviewdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.lfy.customviewdemo.ui.Rotate3dAnimation;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Rotate3DAnimActivity extends AppCompatActivity {
    @BindView(R.id.imageView)
    ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotate3danim);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.imageView)
    public void onClick(View v) {
        Rotate3dAnimation animation = new Rotate3dAnimation(Rotate3DAnimActivity.this, 0, 180, v.getWidth() / 2, v.getHeight() / 2, 0f, true);
        animation.setDuration(3000);
        animation.setFillAfter(true);
        animation.setInterpolator(new LinearInterpolator());
        v.startAnimation(animation);
    }
}
