package com.lfy.customviewdemo.ui;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.lfy.customviewdemo.R;
import com.lfy.customviewdemo.utils.DimensionUtil;

public class MatrixCameraView extends View {
    private Context mContext;
    private Paint mPaint;
    private Matrix mMatrix;
    private Camera mCamera;
    private int mWidth, mHeight;
    private Bitmap mBitmap;

    private ValueAnimator animator;
    private String rotateType = "x";
    private float degree = 0f;

    public MatrixCameraView(Context context) {
        super(context);
        init(context);
    }

    public MatrixCameraView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MatrixCameraView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        mPaint = new Paint();
        mMatrix = new Matrix();
        mCamera = new Camera();
        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.s_1);
        animator = ValueAnimator.ofFloat(0, 1);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                Matrix temp = new Matrix();
                mCamera.getMatrix(temp);
                switch (rotateType) {
                    case "x":
                        temp.preTranslate(-mBitmap.getWidth() / 2, -mBitmap.getHeight() / 2);
                        mCamera.rotateX(degree * animatedValue);
                        temp.postTranslate(mBitmap.getWidth() / 2, mBitmap.getHeight() / 2);
                        invalidate();
                        break;
                    case "y":
                        temp.preTranslate(-mBitmap.getWidth() / 2, -mBitmap.getHeight() / 2);
                        mCamera.rotateY(degree * animatedValue);
                        temp.postTranslate(mBitmap.getWidth() / 2, mBitmap.getHeight() / 2);
                        invalidate();
                        break;
                    case "z":
                        temp.preTranslate(-mBitmap.getWidth() / 2, -mBitmap.getHeight() / 2);
                        mCamera.rotateZ(degree * animatedValue);
                        temp.postTranslate(mBitmap.getWidth() / 2, mBitmap.getHeight() / 2);
                        invalidate();
                        break;
                }

            }
        });
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
        canvas.translate(mWidth / 2, mHeight / 2);
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawPoint(0, 0, mPaint);
        mCamera.applyToCanvas(canvas);
        canvas.drawBitmap(mBitmap, -mBitmap.getWidth() / 2, -mBitmap.getHeight() / 2, mPaint);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mBitmap.recycle();
    }

    public void rotate(String type, float degree) {
        this.rotateType = type;
        this.degree = degree;
        animator.start();
    }
}
