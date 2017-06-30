package com.lfy.customviewdemo.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class FlingScrollView extends ScrollView {
    public FlingScrollView(Context context) {
        super(context);
    }

    public FlingScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlingScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void fling(int velocityY) {
        super.fling(velocityY / 2);
    }
}
