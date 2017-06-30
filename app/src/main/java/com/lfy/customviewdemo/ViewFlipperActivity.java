package com.lfy.customviewdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewFlipperActivity extends AppCompatActivity {
    @BindView(R.id.flipper)
    ViewFlipper flipper;
    private MyGestureListener gestureListener;
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewflipper);
        ButterKnife.bind(this);

        ImageView iv1 = new ImageView(this);
        iv1.setImageResource(R.mipmap.s_1);
        iv1.setBackgroundColor(Color.parseColor("#ffff00"));

        ImageView iv2 = new ImageView(this);
        iv2.setBackgroundColor(Color.parseColor("#ff00ff"));
        iv2.setImageResource(R.mipmap.s_2);

        ImageView iv3 = new ImageView(this);
        iv3.setBackgroundColor(Color.parseColor("#00ffff"));
        iv3.setImageResource(R.mipmap.s_3);

        ImageView iv4 = new ImageView(this);
        iv4.setBackgroundColor(Color.parseColor("#ff0022"));
        iv4.setImageResource(R.mipmap.s_4);

        flipper.addView(iv1);
        flipper.addView(iv2);
        flipper.addView(iv3);
        flipper.addView(iv4);

        flipper.setFlipInterval(3000);
        flipper.setInAnimation(this, R.anim.flipin);
        flipper.setOutAnimation(this, R.anim.flipout);
//        flipper.startFlipping();

        gestureListener = new MyGestureListener();
        gestureDetector = new GestureDetector(this, gestureListener);
    }

    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (e1.getX() - e2.getX() > 200) {
                flipper.setInAnimation(ViewFlipperActivity.this, R.anim.flipin);
                flipper.setOutAnimation(ViewFlipperActivity.this, R.anim.flipout);
                flipper.showNext();
            } else if (e2.getX() - e1.getX() > 200) {
                flipper.setInAnimation(ViewFlipperActivity.this, R.anim.rightin);
                flipper.setOutAnimation(ViewFlipperActivity.this, R.anim.rightout);
                flipper.showPrevious();
            }
            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }
}
