package com.lfy.customviewdemo.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.lfy.customviewdemo.BuildConfig;

public class PathView extends View {
    private static final String TAG = "PathView.class";
    private Context mContext;
    private Paint mPaint;
    private int mColor = Color.GREEN;

    private Path mPath;

    private int mWidth;
    private int mHeight;

    public PathView(Context context) {
        super(context);
        init(context);
    }

    public PathView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(mColor);
        mPaint.setTextSize(40);

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
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);
        mPaint.setColor(Color.GRAY);
        canvas.drawLine(-2000, 0, 2000, 0, mPaint);
        canvas.drawLine(0, -2000, 0, 2000, mPaint);

        mPaint.setColor(Color.BLUE);
//        mPath.lineTo(500, 500);
//        mPath.setLastPoint(500, 200);
//        mPath.lineTo(500, 0);
//        mPath.close();

//        mPath.addRect(-200, -200, 200, 200, Path.Direction.CCW);
//        mPath.setLastPoint(-300, 300);
//        mPath.lineTo(100, 100);
//        mPath.addArc(new RectF(0, 0, 300, 300), 0, 270);
//
//
//        mPath.arcTo(new RectF(0, 0, 300, 300), 0, 270);
//
//        canvas.drawPath(mPath, mPaint);
        // 填充模式
//        mPath.addRect(-600, -600, 600, 600, Path.Direction.CW);
//        mPath.addRect(-400, -400, 400, 400, Path.Direction.CW);
//        mPath.addRect(-200, -200, 200, 200, Path.Direction.CCW);
//        mPath.setFillType(Path.FillType.WINDING);
//        canvas.drawPaPath, mPaint);

//        Path path1 = new Path();
//        Path path2 = new Path();
//        Path path3 = new Path();
//        Path path4 = new Path();
//
//        path1.addCircle(0, 0, 200, Path.Direction.CW);
//
//        path2.addCircle(0, -100, 100, Path.Direction.CW);
//
//        path3.addRect(0, -200, 200, 200, Path.Direction.CW);
//
//        path4.addCircle(0, 100, 100, Path.Direction.CW);
//        //月牙
////        path1.op(path3, Path.Op.DIFFERENCE);
////        path1.op(path2, Path.Op.UNION);
////        path1.op(path4, Path.Op.DIFFERENCE);
//        path1.op(path3, Path.Op.DIFFERENCE);
//        path1.op(path2, Path.Op.DIFFERENCE);
//        path1.op(path4, Path.Op.DIFFERENCE);
//        Path path1 = new Path();
//        Path path2 = new Path();
//        Path result = new Path();
//        canvas.translate(300, 200);
//        int x = 50;
//        int r = 80;
//        path1.addCircle(-x, 0, r, Path.Direction.CW);
//        path2.addCircle(x, 0, r, Path.Direction.CW);
//
//        result.op(path1, path2, Path.Op.DIFFERENCE);
//        canvas.drawPath(result, mPaint);
//        canvas.drawText("DIFFERENCE", 200, 0, mPaint);
//
//        canvas.translate(0, 250);
//        result.op(path1, path2, Path.Op.REVERSE_DIFFERENCE);
//        canvas.drawPath(result, mPaint);
//        canvas.drawText("REVERSE_DIFFERENCE", 200, 0, mPaint);
//
//        canvas.translate(0, 250);
//        result.op(path1, path2, Path.Op.INTERSECT);
//        canvas.drawPath(result, mPaint);
//        canvas.drawText("INTERSECT", 200, 0, mPaint);
//
//        canvas.translate(0, 250);
//        result.op(path1, path2, Path.Op.UNION);
//        canvas.drawPath(result, mPaint);
//        canvas.drawText("UNION", 200, 0, mPaint);
//
//        canvas.translate(0, 250);
//        result.op(path1, path2, Path.Op.XOR);
//        canvas.drawPath(result, mPaint);
//        canvas.drawText("XOR", 200, 0, mPaint);
//
//        RectF bounds = new RectF();
//        result.computeBounds(bounds, true);
//        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setColor(Color.GRAY);
//        canvas.drawRect(bounds, mPaint);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPath.addRect(-200, -200, 200, 200, Path.Direction.CW);

        Path dst = new Path();
        dst.lineTo(500, 500);
        PathMeasure measure = new PathMeasure(mPath, false);
        boolean segment = measure.getSegment(200, 600, dst, true);
        if (segment) {
            PathMeasure measureDst = new PathMeasure(dst, false);
            if (BuildConfig.DEBUG) Log.d(TAG, "measureDst.getLength():" + measureDst.getLength());
            boolean b = measureDst.nextContour();
            if (b) {
                if (BuildConfig.DEBUG)
                    Log.d(TAG, "measureDst.getLength()2:" + measureDst.getLength());
            }
            canvas.drawPath(dst, mPaint);
        }
    }
}
