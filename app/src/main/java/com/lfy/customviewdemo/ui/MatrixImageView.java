package com.lfy.customviewdemo.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.lfy.customviewdemo.R;
import com.lfy.customviewdemo.utils.DimensionUtil;

public class MatrixImageView extends View {
    private static final String TAG = "MatrixImageView.class";
    private Context mContext;
    private int mWidth, mHeight;
    private Bitmap mBitmap;
    private Matrix mMatrix;
    private float[] src;
    private float[] dst;
    private int mPoint = 4;

    private Paint mPaint;

    public MatrixImageView(Context context) {
        super(context);
        init(context);
    }

    public MatrixImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MatrixImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.girl);
        mMatrix = new Matrix();
        src = new float[]{
                0, 0,
                mBitmap.getWidth(), 0,
                mBitmap.getWidth(), mBitmap.getHeight(),
                0, mBitmap.getHeight()
        };

        dst = new float[]{
                0, 0,
                mBitmap.getWidth(), 500,
                mBitmap.getWidth(), mBitmap.getHeight() - 400,
                0, mBitmap.getWidth()
        };

        mMatrix.setPolyToPoly(src, 0, dst, 0, src.length >> 1);
        mMatrix.postTranslate(0, 200);
//        mMatrix.postScale(0.5f, 0.5f);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int defWidth = DimensionUtil.dp2Px(mContext, 300);
        int defHeight = DimensionUtil.dp2Px(mContext, 300);

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
        canvas.drawBitmap(mBitmap, mMatrix, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                for (int i = 0; i < dst.length; i += 2) {
                    if (Math.abs(dst[i] - x) <= 100 && Math.abs(dst[i + 1] - y) <= 100) {
                        dst[i] = x;
                        dst[i + 1] = y;
                        break;
                    }
                }
                resetPoly();
                invalidate();
                break;
        }

        return true;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mBitmap.recycle();
    }

    private void resetPoly() {
        mMatrix.reset();
        mMatrix.setPolyToPoly(src, 0, dst, 0, mPoint);
    }

    public void setPoint(int point) {
        this.mPoint = point < 0 || point > 4 ? 4 : point;
        dst = src.clone();
        resetPoly();
        invalidate();
    }
}
