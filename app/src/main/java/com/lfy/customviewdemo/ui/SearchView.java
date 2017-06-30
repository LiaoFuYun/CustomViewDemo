package com.lfy.customviewdemo.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.lfy.customviewdemo.utils.DimensionUtil;

public class SearchView extends View {
    private static final String TAG = "SearchView";
    private Context mContext;
    private int mWidth, mHeight;
    private Paint mPaint;
    private Path pathCircle;
    private PathMeasure measureCircle;

    private Path pathMagnifier;
    private PathMeasure measureMagnifier;

    private static final int defaultWidth = 300;
    private static final int defaultHeight = 300;

    private enum state {
        INIT_STATE,
        READY_STATE,
        SEARCHING_STATE,
        END_STATE
    }

    private state tempState = state.INIT_STATE;


    private ValueAnimator readyAnimator;
    private ValueAnimator searchingAnimator;
    private ValueAnimator endAnimator;
    private float animatedValue;

    private boolean isStarting = false;

    public SearchView(Context context) {
        super(context);
        init(context);
    }

    public SearchView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        mPaint = new Paint();
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        int mColor = Color.WHITE;
        mPaint.setColor(mColor);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        initPath();
        initAnimator();
    }

    private void initPath() {
        pathCircle = new Path();
        pathMagnifier = new Path();
        RectF rectCircle = new RectF(-100, -100, 100, 100);
        RectF rectMagnifier = new RectF(-50, -50, 50, 50);

        pathMagnifier.addArc(rectMagnifier, 45, 359.9f);
        pathCircle.addArc(rectCircle, 45, -359.9f);
        measureCircle = new PathMeasure(pathCircle, false);
        float[] pos = new float[2];
        boolean posTan = measureCircle.getPosTan(0, pos, null);
        if (posTan) {
            pathMagnifier.lineTo(pos[0], pos[1]);
        }
        measureMagnifier = new PathMeasure();
    }

    private void initAnimator() {
        int animatorDuration = 1500;
        readyAnimator = ValueAnimator.ofFloat(0, 1).setDuration(animatorDuration);
        searchingAnimator = ValueAnimator.ofFloat(0, 1).setDuration(animatorDuration);
        endAnimator = ValueAnimator.ofFloat(1, 0).setDuration(animatorDuration);
        ValueAnimator.AnimatorUpdateListener updateListener = new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                animatedValue = (float) animation.getAnimatedValue();
                invalidate();
            }
        };

        AnimatorListenerAdapter animatorListener = new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                switch (tempState) {
                    case READY_STATE:
                        tempState = state.SEARCHING_STATE;
                        searchingAnimator.setRepeatMode(ValueAnimator.RESTART);
                        searchingAnimator.setRepeatCount(ValueAnimator.INFINITE);
                        searchingAnimator.start();
                        break;
                    case SEARCHING_STATE:
                        tempState = state.END_STATE;
                        endAnimator.start();
                        break;
                    case END_STATE:
                        isStarting = false;
                        break;
                    default:
                        break;
                }

            }
        };

        readyAnimator.addUpdateListener(updateListener);
        searchingAnimator.addUpdateListener(updateListener);
        endAnimator.addUpdateListener(updateListener);

        readyAnimator.addListener(animatorListener);
        searchingAnimator.addListener(animatorListener);
        endAnimator.addListener(animatorListener);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int defWidth = DimensionUtil.dp2Px(mContext, defaultWidth);
        int defHeight = DimensionUtil.dp2Px(mContext, defaultHeight);

        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(defWidth, defHeight);
        } else if (widthMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(defWidth, heightSize);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSize, defHeight);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2 + getPaddingLeft(), mHeight / 2 + getPaddingTop());
        drawSearch(canvas);
    }

    private void drawSearch(Canvas canvas) {
        switch (tempState) {
            case INIT_STATE:
                canvas.drawPath(pathMagnifier, mPaint);
                break;
            case READY_STATE:
                measureMagnifier.setPath(pathMagnifier, false);
                Path dst = new Path();
                float v = animatedValue * measureMagnifier.getLength();
                boolean posTan = measureMagnifier.getSegment(v, measureMagnifier.getLength(), dst, true);
                if (posTan) {
                    canvas.drawPath(dst, mPaint);
                }
                break;
            case SEARCHING_STATE:
                measureCircle.setPath(pathCircle, false);
                float stop = animatedValue * measureCircle.getLength();
                float start = (float) (stop - ((0.5 - Math.abs(animatedValue - 0.5f)) * 800f));
                Path dst1 = new Path();
                boolean segment = measureCircle.getSegment(start, stop, dst1, true);
                if (segment) {
                    canvas.drawPath(dst1, mPaint);
                }
                break;
            case END_STATE:
                measureMagnifier.setPath(pathMagnifier, false);
                Path dst2 = new Path();
                float v2 = animatedValue * measureMagnifier.getLength();
                boolean segment1 = measureMagnifier.getSegment(v2, measureMagnifier.getLength(), dst2, true);
                if (segment1) {
                    canvas.drawPath(dst2, mPaint);
                }
                break;
        }
    }

    public void startSearch() {
        if (!isStarting) {
            tempState = state.READY_STATE;
            readyAnimator.start();
            isStarting = true;
        } else if (tempState == state.SEARCHING_STATE) {
            searchingAnimator.setRepeatCount(0);
        }
    }
}
