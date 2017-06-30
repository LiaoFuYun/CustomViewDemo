package com.lfy.customviewdemo.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.lfy.customviewdemo.utils.DimensionUtil;

public class RegionClickView extends View {
    private static final String TAG = "RegionClickView";
    private static final int defaultWidth = 300;
    private static final int defaultHeight = 300;
    private Context mContext;
    private int mWidth, mHeight;
    private Path pCenter, pBottom, pLeft, pTop, pRight;
    private Region rCenter, rBottom, rLeft, rTop, rRight;
    private Paint mPaint;
    private int mColor1 = Color.DKGRAY;
    private int mColor2 = Color.GREEN;

    private TouchArea tempArea = TouchArea.NONE;

    private enum TouchArea {
        NONE,
        CENTER,
        BOTTOM,
        LEFT,
        TOP,
        RIGHT
    }

    private int downX = -1;
    private int downY = -1;

    public RegionClickView(Context context) {
        super(context);
        init(context);
    }

    public RegionClickView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RegionClickView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        pCenter = new Path();
        pBottom = new Path();
        pLeft = new Path();
        pTop = new Path();
        pRight = new Path();
        rCenter = new Region();
        rBottom = new Region();
        rLeft = new Region();
        rTop = new Region();
        rRight = new Region();
        mPaint = new Paint();
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(mColor1);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(5);
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
        canvas.translate(mWidth / 2, mHeight / 2);
//        mPaint.setColor(Color.GRAY);
//        canvas.drawLines(new float[]{-2000, 0, 2000, 0, 0, 2000, 0, -2000}, mPaint);

        int min = Math.min(mWidth, mHeight);
        float br = min / 2f;
        float sr = min / 4f;

        RectF rectF = new RectF(-br, -br, br, br);
        RectF rectF1 = new RectF(-sr, -sr, sr, sr);
        float sweepAngleB = 84;
        float sweepAngleS = -80;

        pCenter.addCircle(0, 0, min / 5f, Path.Direction.CW);
        pBottom.addArc(rectF, 48, sweepAngleB);
        pBottom.arcTo(rectF1, 130, sweepAngleS);
        pBottom.close();

        pLeft.addArc(rectF, 138, sweepAngleB);
        pLeft.arcTo(rectF1, 220, sweepAngleS);
        pLeft.close();

        pTop.addArc(rectF, 228, sweepAngleB);
        pTop.arcTo(rectF1, 310, sweepAngleS);
        pTop.close();

        pRight.addArc(rectF, 318, sweepAngleB);
        pRight.arcTo(rectF1, 40, sweepAngleS);
        pRight.close();

        Region globalRegion = new Region(-mWidth, -mHeight, mWidth, mHeight);

        rCenter.setPath(pCenter, globalRegion);
        rBottom.setPath(pBottom, globalRegion);
        rLeft.setPath(pLeft, globalRegion);
        rTop.setPath(pTop, globalRegion);
        rRight.setPath(pRight, globalRegion);

        rCenter.translate(mWidth / 2, mHeight / 2);
        rBottom.translate(mWidth / 2, mHeight / 2);
        rLeft.translate(mWidth / 2, mHeight / 2);
        rTop.translate(mWidth / 2, mHeight / 2);
        rRight.translate(mWidth / 2, mHeight / 2);


        canvas.drawPath(pCenter, mPaint);
        canvas.drawPath(pBottom, mPaint);
        canvas.drawPath(pLeft, mPaint);
        canvas.drawPath(pTop, mPaint);
        canvas.drawPath(pRight, mPaint);

        if (tempArea != TouchArea.NONE) {
            mPaint.setColor(mColor2);
            switch (tempArea) {
                case CENTER:
                    canvas.drawPath(pCenter, mPaint);
                    break;
                case BOTTOM:
                    canvas.drawPath(pBottom, mPaint);
                    break;
                case LEFT:
                    canvas.drawPath(pLeft, mPaint);
                    break;
                case TOP:
                    canvas.drawPath(pTop, mPaint);
                    break;
                case RIGHT:
                    canvas.drawPath(pRight, mPaint);
                    break;
            }
        }


//        if (downX == -1 && downY == -1) {
//            return;
//        }
//        Matrix inverMatrix = new Matrix();
//        canvas.getMatrix().invert(inverMatrix);
//        float[] floats = {downX, downY};
//        inverMatrix.mapPoints(floats);
//
//        canvas.drawCircle(floats[0], floats[1], 20, mPaint);

    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        float x = event.getRawX();
//        float y = event.getRawY();
//        switch (event.getActionMasked()) {
//            case MotionEvent.ACTION_DOWN:
//            case MotionEvent.ACTION_MOVE:
//                downX = (int) x;
//                downY = (int) y;
//                invalidate();
//                break;
//
//            case MotionEvent.ACTION_UP:
//            case MotionEvent.ACTION_CANCEL:
//                downX = downY = -1;
//                invalidate();
//                break;
//        }
//
//        return true;
//    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                if (rCenter.contains(x, y)) {
//                    Toast.makeText(mContext, "中央", Toast.LENGTH_SHORT).show();
                    tempArea = TouchArea.CENTER;
                }
                if (rBottom.contains(x, y)) {
//                    Toast.makeText(mContext, "下方", Toast.LENGTH_SHORT).show();
                    tempArea = TouchArea.BOTTOM;
                }
                if (rLeft.contains(x, y)) {
//                    Toast.makeText(mContext, "左边", Toast.LENGTH_SHORT).show();
                    tempArea = TouchArea.LEFT;
                }
                if (rTop.contains(x, y)) {
//                    Toast.makeText(mContext, "上方", Toast.LENGTH_SHORT).show();
                    tempArea = TouchArea.TOP;
                }
                if (rRight.contains(x, y)) {
//                    Toast.makeText(mContext, "右边", Toast.LENGTH_SHORT).show();
                    tempArea = TouchArea.RIGHT;
                }
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                tempArea = TouchArea.NONE;
                mPaint.setColor(mColor1);
                invalidate();
                break;
        }
        return true;
    }
}
