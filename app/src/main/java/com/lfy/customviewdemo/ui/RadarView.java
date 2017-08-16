package com.lfy.customviewdemo.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.lfy.customviewdemo.R;

import java.util.ArrayList;

public class RadarView extends View {
    private static final String TAG = "RadarView.class";
    private Context mContext;
    private Paint mMainPaint;
    private Paint mSecondPaint;

    private Path mMainPath;
    private Path mSecondPath;

    private int mainColor = Color.GREEN;
    private int secondColor = Color.LTGRAY;

    // View的真实宽高
    private int mWidth;
    private int mHeight;
    // 蛛网最大半径
    private int maxRadius = 0;
    // 多少个项目
    private int dataNum = 6;
    // 项目数据
    private ArrayList<Float> data = new ArrayList<>();

    public RadarView(Context context) {
        this(context, null);
    }

    public RadarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RadarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RadarView);
        mainColor = typedArray.getColor(R.styleable.RadarView_mainColor, Color.BLUE);
        secondColor = typedArray.getColor(R.styleable.RadarView_secondColor, Color.LTGRAY);
        typedArray.recycle();
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;

        mMainPaint = new Paint();
        mMainPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mMainPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mMainPaint.setColor(mainColor);
        mMainPaint.setStrokeWidth(1);
        mMainPaint.setAlpha(127);

        mSecondPaint = new Paint();
        mSecondPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mSecondPaint.setStyle(Paint.Style.STROKE);
        mSecondPaint.setColor(secondColor);
        mSecondPaint.setStrokeWidth(2);

        mMainPath = new Path();
        mSecondPath = new Path();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int defaultWidth = mContext.getResources().getDisplayMetrics().widthPixels;
        int defaultHeight = mContext.getResources().getDisplayMetrics().heightPixels;

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
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        maxRadius = Math.min(w, h) / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);
        mMainPath.reset();
        mSecondPath.reset();
        drawSecond(canvas);
        drawMain(canvas);
    }

    private void drawMain(Canvas canvas) {
        if (data.size() != dataNum) {
            new Exception("数据不正确").printStackTrace();
            return;
        }
        double angle = Math.PI * 2 / dataNum;
        for (int i = 0; i < data.size(); i++) {
            float radius = (data.get(i) / 100f) * maxRadius;
            if (i == 0) {
                mMainPath.moveTo(radius, 0);
            } else {
                float x = (float) (radius * Math.cos(angle * i));
                float y = (float) (radius * Math.sin(angle * i));
                mMainPath.lineTo(x, y);
            }
        }
        mMainPath.close();
        canvas.drawPath(mMainPath, mMainPaint);
    }

    private void drawSecond(Canvas canvas) {
        drawPolygon();
        drawLines();
        canvas.drawPath(mSecondPath, mSecondPaint);
    }

    private void drawLines() {
        // mei角度
        double angle = Math.PI * 2 / dataNum;
        mSecondPath.moveTo(0, 0);
        for (int j = 0; j < dataNum; j++) {
            float x = (float) (maxRadius * Math.cos(angle * j));
            float y = (float) (maxRadius * Math.sin(angle * j));
            mSecondPath.lineTo(x, y);
            mSecondPath.moveTo(0, 0);
        }
    }

    private void drawPolygon() {
        //每个多边形的间距
        float perRadius = maxRadius / 5f;
        // mei角度
        double angle = Math.PI * 2 / dataNum;

        for (int i = 1; i <= 5; i++) {
            float tempRadius = perRadius * i;
            for (int j = 0; j < dataNum; j++) {
                if (j == 0) {
                    mSecondPath.moveTo(tempRadius, 0);
                } else {
                    float x = (float) (tempRadius * Math.cos(angle * j));
                    float y = (float) (tempRadius * Math.sin(angle * j));
                    mSecondPath.lineTo(x, y);
                }
            }
            mSecondPath.close();
        }
    }

    public void setData(ArrayList<Float> data) {
        if (data.size() <= 4) {
            new Exception("数据不正确，最小需要4个").printStackTrace();
            return;
        }
        this.data = data;
        this.dataNum = data.size();
        invalidate();
    }
}
