package com.lfy.customviewdemo.ui;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;

import com.lfy.customviewdemo.R;
import com.lfy.customviewdemo.utils.DimensionUtil;

public class WaveView extends View {
    private static final String TAG = "WaveView.class";
    private Context mContext;
    private Paint mPaint;
    private int mColor = Color.CYAN;
    private Path mPath;
    private PointF ctrlTop1, ctrlTop2;
    private PointF ctrlBottom1, ctrlBottom2;

    private PointF startPoint;

    private PointF leftPoint1, leftPoint2;
    private PointF centerPoint1, centerPoint2;
    private PointF rightPoint1, rightPoint2;

    private float waveTop = 300f;
    private float waveBottom = 300f;
    private float waveHeight = 600f;

    private float mWidth, mHeight;

    private ValueAnimator valueAnimator;

    public WaveView(Context context) {
        super(context);
        init(context);
    }

    public WaveView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.WaveView);
        mColor = a.getColor(R.styleable.WaveView_wave_color, Color.BLUE);
        a.recycle();

        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(mColor);
        mPath = new Path();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int defaultSize = DimensionUtil.dp2Px(mContext, 200);
        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(defaultSize, defaultSize);
        } else if (widthMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(defaultSize, heightSize);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSize, defaultSize);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mWidth = getWidth();
        mHeight = getHeight();
        setPoint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPath.reset();
        mPath.moveTo(leftPoint1.x, leftPoint1.y);
        mPath.quadTo(ctrlBottom1.x, ctrlBottom1.y, centerPoint1.x, centerPoint1.y);
        mPath.quadTo(ctrlTop1.x, ctrlTop1.y, rightPoint1.x, rightPoint1.y);
        mPath.quadTo(ctrlBottom2.x, ctrlBottom2.y, centerPoint2.x, centerPoint2.y);
        mPath.quadTo(ctrlTop2.x, ctrlTop2.y, rightPoint2.x, rightPoint2.y);

        mPath.lineTo(rightPoint2.x, mHeight);
        mPath.lineTo(leftPoint1.x, mHeight);
        mPath.lineTo(leftPoint1.x, leftPoint1.y);
        canvas.drawPath(mPath, mPaint);
    }

    private void setPoint() {
        startPoint = new PointF(-mWidth, mHeight - waveHeight);

        leftPoint1 = new PointF(-mWidth, mHeight - waveHeight);
        centerPoint1 = new PointF(-mWidth / 2f, mHeight - waveHeight);
        rightPoint1 = new PointF(0, mHeight - waveHeight);

        ctrlBottom1 = new PointF(-0.75f * mWidth, mHeight - waveHeight - waveBottom);
        ctrlTop1 = new PointF(-0.25f * mWidth, mHeight - waveHeight + waveTop);

        leftPoint2 = new PointF(0, mHeight - waveHeight);
        centerPoint2 = new PointF(mWidth / 2f, mHeight - waveHeight);
        rightPoint2 = new PointF(mWidth, mHeight - waveHeight);

        ctrlBottom2 = new PointF(0.25f * mWidth, mHeight - waveHeight - waveBottom);
        ctrlTop2 = new PointF(0.75f * mWidth, mHeight - waveHeight + waveTop);
    }

    private void startAnim() {
        valueAnimator = ValueAnimator.ofFloat(startPoint.x, 0);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setDuration(2000);
        valueAnimator.setRepeatCount(Animation.INFINITE);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                leftPoint1.x = (float) animation.getAnimatedValue();
                centerPoint1.x = leftPoint1.x + mWidth / 2f;
                rightPoint1.x = leftPoint1.x + mWidth;

                ctrlBottom1.x = leftPoint1.x + mWidth / 4f;
                ctrlTop1.x = leftPoint1.x + 3 * mWidth / 4f;

                leftPoint2.x = leftPoint1.x + mWidth;
                centerPoint2.x = leftPoint1.x + 3 * mWidth / 2f;
                rightPoint2.x = leftPoint1.x + 2 * mWidth;

                ctrlBottom2.x = leftPoint1.x + 5 * mWidth / 4f;
                ctrlTop2.x = leftPoint1.x + 7 * mWidth / 4f;
                invalidate();
            }
        });
        valueAnimator.start();
    }

    public void setRunning() {
        if (valueAnimator != null) {
            if (valueAnimator.isPaused()) {
                valueAnimator.resume();
            }
        } else {
            startAnim();
        }
    }

    public void stopRun() {
        if (valueAnimator == null) {
            return;
        }
        if (valueAnimator.isPaused()) {
            valueAnimator.end();
            valueAnimator = null;
        } else {
            valueAnimator.pause();
        }
    }
}
