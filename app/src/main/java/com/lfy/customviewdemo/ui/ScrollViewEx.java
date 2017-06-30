package com.lfy.customviewdemo.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.ScrollView;

public class ScrollViewEx extends ScrollView {

    private ListView mListView;

    public ScrollViewEx(Context context) {
        super(context);
    }

    public ScrollViewEx(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollViewEx(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private boolean isScrollTop() {
        boolean is = false;
        if (mListView.getFirstVisiblePosition() == 0) {
            View view = mListView.getChildAt(0);
            if (view != null && view.getTop() >= 0) {
                is = true;
            }
        }
        return is;
    }


    private boolean isScrollBottom() {
        boolean is = false;
        int count = mListView.getCount();
        System.out.println("count:" + count);
        if (mListView.getLastVisiblePosition() == count - 1) {
            View view = mListView.getAdapter().getView(count - 1, null, null);
            System.out.println("view:" + view);
            if (view != null && view.getBottom() == 0) {
                System.out.println(view.getBottom() + "|" + mListView.getHeight());
                is = true;
            }
        }
        return is;
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        boolean intercept = false;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                intercept = false;
                break;
            case MotionEvent.ACTION_MOVE:
                if (isScrollBottom()) {
                    intercept = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                intercept = false;
                break;
        }
        System.out.println("intercept:" + intercept);
        return intercept;
    }

    public void setListView(ListView mListView) {
        this.mListView = mListView;
    }
}
