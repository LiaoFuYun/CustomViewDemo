package com.lfy.customviewdemo.ui;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

public class MagicCircle extends View {
    private Context mContext;
    private Paint mPaint;
    private Path mPath;

    private int mColor = Color.RED;

    private int mWidth, mHeight;
    private float circleC = 0.551915024494f;

    private Data[] mData = new Data[4];

    public MagicCircle(Context context) {
        super(context);
        init(context);
    }

    public MagicCircle(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MagicCircle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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

        int widthPixels = mContext.getResources().getDisplayMetrics().widthPixels;
        int heightPixels = mContext.getResources().getDisplayMetrics().heightPixels;

        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthPixels, heightPixels);
        } else if (widthMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthPixels, heightSize);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSize, heightPixels);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;

        int width = mWidth - getPaddingLeft() - getPaddingRight();
        int height = mHeight - getPaddingTop() - getPaddingBottom();
        int radius = Math.min(width, height) / 4;

        float ctrlValue = radius * circleC;
        setValue(radius, ctrlValue);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = mWidth - getPaddingLeft() - getPaddingRight();
        int height = mHeight - getPaddingTop() - getPaddingBottom();
        canvas.translate(width / 2 + getPaddingLeft(), height / 2 + getPaddingTop());
        mPaint.setColor(Color.GRAY);
        canvas.drawLines(new float[]{-2000, 0, 2000, 0, 0, 2000, 0, -2000}, mPaint);
        mPath.reset();
        mPath.moveTo(mData[0].start.x, mData[0].start.y);
        for (int i = 0; i < mData.length; i++) {
            Data data = mData[i];
            mPaint.setColor(Color.BLACK);
            canvas.drawLine(data.start.x, data.start.y, data.ctrl1.x, data.ctrl1.y, mPaint);
            canvas.drawLine(data.end.x, data.end.y, data.ctrl2.x, data.ctrl2.y, mPaint);
            mPaint.setColor(mColor);
            mPath.cubicTo(data.ctrl1.x, data.ctrl1.y, data.ctrl2.x, data.ctrl2.y, data.end.x, data.end.y);
        }
        canvas.drawPath(mPath, mPaint);
        Paint eyePaint = new Paint();
        eyePaint.setColor(Color.BLACK);
        eyePaint.setStyle(Paint.Style.FILL);
        eyePaint.setStrokeWidth(20);
        canvas.drawPoint(mData[1].end.x / 2f, mData[1].end.y - 20f, eyePaint);
        canvas.drawPoint(mData[0].start.x / 2f, mData[0].start.y - 20f, eyePaint);
        canvas.drawOval(new RectF(mData[0].end.x - 50, mData[0].end.y - 120, mData[0].end.x + 50, mData[0].end.y - 80), eyePaint);
    }

    private void setValue(int radius, float ctrlValue) {
        Data rightBottom = new Data(new PointF(radius, 0), new PointF(0, radius), new PointF(radius, ctrlValue), new PointF(ctrlValue, radius));

        Data leftBottom = new Data(new PointF(0, radius), new PointF(-radius, 0), new PointF(-ctrlValue, radius), new PointF(-radius, ctrlValue));

        Data leftTop = new Data(new PointF(-radius, 0), new PointF(0, -radius), new PointF(-radius, -ctrlValue), new PointF(-ctrlValue, -radius));

        Data rightTop = new Data(new PointF(0, -radius), new PointF(radius, 0), new PointF(ctrlValue, -radius), new PointF(radius, -ctrlValue));

        mData[0] = rightBottom;
        mData[1] = leftBottom;
        mData[2] = leftTop;
        mData[3] = rightTop;
    }

    public void startAnim() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 0.4f);
        valueAnimator.setInterpolator(new AccelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();

//                mData[2].end.y -= mData[2].end.y * animatedValue;
//                mData[3].start.y -= mData[3].start.y * animatedValue;

                mData[0].end.y += mData[0].end.y * animatedValue;
                mData[0].ctrl1.y += mData[0].ctrl1.y * animatedValue;
                mData[0].ctrl2.y += mData[0].ctrl2.y * animatedValue;


                mData[1].start.y += mData[1].start.y * animatedValue;
                mData[1].ctrl1.y += mData[1].ctrl1.y * animatedValue;
                mData[1].ctrl2.y += mData[1].ctrl2.y * animatedValue;
                invalidate();
            }
        });
        valueAnimator.start();
    }


    // 包装四三阶贝塞尔曲线的起点、终点和控制点坐标
    private class Data {
        private PointF start;
        private PointF end;
        private PointF ctrl1;
        private PointF ctrl2;

        public Data(PointF start, PointF end, PointF ctrl1, PointF ctrl2) {
            this.start = start;
            this.end = end;
            this.ctrl1 = ctrl1;
            this.ctrl2 = ctrl2;
        }

        public PointF getStart() {
            return start;
        }

        public void setStart(PointF start) {
            this.start = start;
        }

        public PointF getEnd() {
            return end;
        }

        public void setEnd(PointF end) {
            this.end = end;
        }

        public PointF getCtrl1() {
            return ctrl1;
        }

        public void setCtrl1(PointF ctrl1) {
            this.ctrl1 = ctrl1;
        }

        public PointF getCtrl2() {
            return ctrl2;
        }

        public void setCtrl2(PointF ctrl2) {
            this.ctrl2 = ctrl2;
        }
    }
}
