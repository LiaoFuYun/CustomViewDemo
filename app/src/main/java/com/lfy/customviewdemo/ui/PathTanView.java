package com.lfy.customviewdemo.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

import com.lfy.customviewdemo.R;

public class PathTanView extends View {
    private Context mContext;
    private Paint mPaint;
    private Path mPath;
    private PathMeasure measure;
    private Matrix mMatrix;
    private int mWidth, mHeight;

    private float currentDistance = 0;
    private float[] pos = new float[2];
    private float[] tan = new float[2];

    private Bitmap mBitmap;

    public PathTanView(Context context) {
        super(context);
        init(context);
    }

    public PathTanView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathTanView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        mPaint = new Paint();
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mPath = new Path();
        measure = new PathMeasure();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.arrow, options);
        mMatrix = new Matrix();
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
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);
        mPath.reset();
        mPath.addCircle(0, 0, 300, Path.Direction.CW);
        measure.setPath(mPath, false);
        currentDistance += 0.005;
        if (currentDistance >= 1) {
            currentDistance = 0;
        }
//        boolean posTan = measure.getPosTan(measure.getLength() * currentDistance, pos, tan);
        boolean matrix = measure.getMatrix(measure.getLength() * currentDistance, mMatrix, PathMeasure.POSITION_MATRIX_FLAG | PathMeasure.TANGENT_MATRIX_FLAG);
        if (matrix) {
            mMatrix.preTranslate(-mBitmap.getWidth() / 2, -mBitmap.getHeight() / 2);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(5);
            mPaint.setColor(Color.BLACK);
            canvas.drawBitmap(mBitmap, mMatrix, mPaint);
            canvas.drawPath(mPath, mPaint);
            invalidate();
        }


//        if (posTan) {
//            mMatrix.reset();
//            float degree = (float) (Math.atan2(tan[1], tan[0]) * 180f / Math.PI);
//            mMatrix.postRotate(degree, mBitmap.getWidth() / 2, mBitmap.getHeight() / 2);
//            mMatrix.postTranslate(pos[0] - mBitmap.getWidth() / 2, pos[1] - mBitmap.getHeight() / 2);
//            mPaint.setStyle(Paint.Style.STROKE);
//            mPaint.setStrokeWidth(5);
//            mPaint.setColor(Color.BLACK);
//            canvas.drawBitmap(mBitmap, mMatrix, mPaint);
//            canvas.drawPath(mPath, mPaint);
//            invalidate();
//        }
    }
}
