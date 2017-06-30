package com.lfy.customviewdemo.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.lfy.customviewdemo.bean.PieBean;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PieView extends View {
    private static final String TAG = "PieView.class";
    private Context mContext;
    private Paint mPaint;
    private int defaultWidth;
    private int defaultHeight;

    private int mWidth;
    private int mHeight;

    private ArrayList<PieBean> pieList;

    public PieView(Context context) {
        super(context);
        init(context);
    }

    public PieView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PieView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);

        defaultWidth = context.getResources().getDisplayMetrics().widthPixels;
        defaultHeight = context.getResources().getDisplayMetrics().heightPixels;
    }

    public void setPieList(@NotNull ArrayList<PieBean> pieList) {
        this.pieList = pieList;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(defaultWidth, defaultHeight);
        } else if (widthMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(defaultWidth, heightSize);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSize, defaultHeight);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mWidth = w;
        mHeight = h;
    }

//    @Override
//    protected void onDraw(Canvas canvas) {
//        int paddingLeft = getPaddingLeft();
//        int paddingRight = getPaddingRight();
//        int paddingTop = getPaddingTop();
//        int paddingBottom = getPaddingBottom();
//
//        int widthCanUse = mWidth - paddingLeft - paddingRight;
//        int heightCanUse = mHeight - paddingTop - paddingBottom;
//        int radius = Math.min(widthCanUse, heightCanUse) / 2;
//        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setColor(Color.GRAY);
//        canvas.drawCircle(widthCanUse / 2 + paddingLeft, heightCanUse / 2 + paddingTop, radius, mPaint);
//
//        if (pieList == null || pieList.size() <= 0) {
//            return;
//        }
//
//        int left = widthCanUse / 2 + paddingLeft - radius;
//        int top = heightCanUse / 2 + paddingTop - radius;
//        int right = widthCanUse / 2 + paddingLeft + radius;
//        int bottom = heightCanUse / 2 + paddingTop + radius;
//
//        RectF rectF = new RectF(left, top, right, bottom);
//
//        float tempAngle = 0;
//
//        for (int i = 0; i < pieList.size(); i++) {
//            PieBean pieBean = pieList.get(i);
//            int pieColor = pieBean.getPieColor();
//            float piePercent = pieBean.getPiePercent();
//
//            mPaint.setStyle(Paint.Style.FILL);
//            mPaint.setColor(pieColor);
//            float sweepAngle = 360 * piePercent;
//            canvas.drawArc(rectF, tempAngle, 360 * piePercent, true, mPaint);
//            if (i != pieList.size() - 1) {
//                tempAngle = tempAngle + sweepAngle;
//            }
//        }
//    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (pieList == null || pieList.size() <= 0) {
            return;
        }

        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();

        int widthCanUse = mWidth - paddingLeft - paddingRight;
        int heightCanUse = mHeight - paddingTop - paddingBottom;
        int radius = Math.min(widthCanUse, heightCanUse) / 2;

        canvas.translate(widthCanUse / 2 + paddingLeft, heightCanUse / 2 + paddingTop);

        RectF rectF = new RectF(-radius, -radius, radius, radius);

        float tempAngle = 0;

        for (int i = 0; i < pieList.size(); i++) {
            PieBean pieBean = pieList.get(i);
            int pieColor = pieBean.getPieColor();
            float piePercent = pieBean.getPiePercent();

            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setColor(pieColor);
            float sweepAngle = 360 * piePercent;
            canvas.drawArc(rectF, tempAngle, 360 * piePercent, true, mPaint);
            if (i != pieList.size() - 1) {
                tempAngle = tempAngle + sweepAngle;
            }
        }
    }
}
