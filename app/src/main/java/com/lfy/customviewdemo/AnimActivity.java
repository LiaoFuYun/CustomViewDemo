package com.lfy.customviewdemo;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnimActivity extends AppCompatActivity {
    @BindView(R.id.scrollerView)
    View scrollerView;
    @BindView(R.id.scrollerBtn)
    Button scrollerBtn;
    private int count = 0;
    private int translationX = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.scrollerBtn)
    public void onClick() {
//        animScroll();
        handlerScroll();
    }

    private void handlerScroll() {
        count = 0;
        handler.sendEmptyMessage(1);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            count++;
            if (count <= 30) {
                translationX += 10;
                scrollerView.setTranslationX(translationX);
                handler.sendEmptyMessage(1);
            }
        }
    };


    private void animScroll() {
        final int start = 0;
        final int deltax = 100;
        ValueAnimator animator = ValueAnimator.ofInt(0, 1).setDuration(2000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                int x = start + (int) (deltax * fraction);
                System.out.println("x:" + x);
                scrollerView.setTranslationX(x);
            }
        });
        animator.start();
    }
}
