package com.lfy.customviewdemo.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

public class StickyLayout extends ViewGroup {

    private Context mContext;
    private Scroller mScroller;
    private VelocityTracker mVelocityTracker;

    private int childIndex = 0;
    private int childWidth = 0;

    private int lastInterceptX = 0;
    private int lastInterceptY = 0;
    private int lastX = 0;
    private int lastY = 0;

    public StickyLayout(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public StickyLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    public StickyLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    private void init() {
        mScroller = new Scroller(mContext);
        mVelocityTracker = VelocityTracker.obtain();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercept = false;
        int eventX = (int) ev.getX();
        int eventY = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                intercept = false;
                break;

            case MotionEvent.ACTION_MOVE:
                int diffX = eventX - lastInterceptX;
                int diffY = eventY - lastInterceptY;
                intercept = Math.abs(diffX) > Math.abs(diffY);
                break;

            case MotionEvent.ACTION_UP:
                intercept = false;
                break;
        }
        lastInterceptX = eventX;
        lastInterceptY = eventY;
        lastX = eventX;
        lastY = eventY;
        return intercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mVelocityTracker.addMovement(event);
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                scrollBy(lastX - x, 0);
                break;
            case MotionEvent.ACTION_UP:
                int scrollX = getScrollX();
                mVelocityTracker.computeCurrentVelocity(1000);
                float xVelocity = mVelocityTracker.getXVelocity();
                System.out.println("scrollX:" + scrollX + "xVelocity:" + xVelocity);
                if (Math.abs(xVelocity) > 500) {
                    childIndex = xVelocity > 0 ? childIndex - 1 : childIndex + 1;
                    System.out.println("fast:" + childIndex);
                } else {
                    childIndex = ((scrollX + childWidth / 2) / childWidth);
                    System.out.println("slow:" + childIndex);
                }
                childIndex = Math.max(0, Math.min(childIndex, getChildCount() - 1));

                int destX = childIndex * childWidth;
                smoothScrollTo(destX, 0);
                mVelocityTracker.clear();
                break;
        }
        lastX = x;
        lastY = y;
        return true;
    }

    private void smoothScrollTo(int destX, int destY) {
        int scrollX = getScrollX();
        int scrollY = getScrollY();

        mScroller.startScroll(scrollX, 0, destX - scrollX, destY - scrollY);
        invalidate();
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (!mScroller.computeScrollOffset()) {
            return;
        }
        scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
        postInvalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int measureWidth = 0;
        int measureHeight = 0;

        final int childCount = getChildCount();
        if (childCount > 0) {
            measureChildren(widthMeasureSpec, heightMeasureSpec);
        }

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        if (childCount == 0) {
            setMeasuredDimension(widthSpecSize, heightSpecSize);
        } else if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            for (int i = 0; i < childCount; i++) {
                View child = getChildAt(i);
                int childWidth = child.getMeasuredWidth();
                int childHeight = child.getMeasuredHeight();
                // 取所有子view的宽度总和
                measureWidth += childWidth;
                // 取所有子view中的最高高度
                if (childHeight > measureHeight) {
                    measureHeight = childHeight;
                }
            }
            setMeasuredDimension(measureWidth, measureHeight);
        } else if (widthMode == MeasureSpec.AT_MOST) {
            for (int i = 0; i < childCount; i++) {
                View child = getChildAt(i);
                int childWidth = child.getMeasuredWidth();
                // 取所有子view的宽度总和
                measureWidth += childWidth;
            }
            setMeasuredDimension(measureWidth, heightSpecSize);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            for (int i = 0; i < childCount; i++) {
                View child = getChildAt(i);
                int childHeight = child.getMeasuredHeight();
                // 取所有子view中的最高高度
                if (childHeight > measureHeight) {
                    measureHeight = childHeight;
                }
            }
            setMeasuredDimension(widthSpecSize, measureHeight);
        }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childLeft = 0;
        final int childCount = getChildCount();

        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child != null && child.getVisibility() != View.GONE) {
                final int childMeasuredWidth = child.getMeasuredWidth();
                childWidth = childMeasuredWidth;
                child.layout(childLeft, 0, childLeft + childMeasuredWidth, child.getMeasuredHeight());
                childLeft += childMeasuredWidth;
            }
        }

    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mVelocityTracker.recycle();
        mScroller.abortAnimation();
    }
}
