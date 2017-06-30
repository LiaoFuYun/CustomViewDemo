package com.lfy.customviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.lfy.customviewdemo.ui.MeiziView;

public class MainActivity extends AppCompatActivity {

    private MeiziView meiziView;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
        meiziView = new MeiziView(this);
        meiziView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                meiziView.setBitmapXY(event.getX() - 100, event.getY() - 100);
                meiziView.invalidate();
                return true;
            }
        });
        frameLayout.addView(meiziView);
    }

}
