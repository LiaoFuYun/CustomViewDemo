package com.lfy.customviewdemo.ui;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import com.lfy.customviewdemo.R;
import com.lfy.customviewdemo.utils.DimensionUtil;

public class CircleView extends View {
    private Context mContext;
    private int mColor = Color.BLUE;
    private Paint mPaint = new Paint();
    // 默认宽高单位dp
    private static final int defaultWidth = 300;
    private static final int defaultHeight = 100;

    private int mWidth;
    private int mHeight;

//    private Picture mPicture = new Picture();

    private Bitmap mBitmap;
    private int animatedValue = 0;

    public CircleView(Context context) {
        super(context);
        init(context);
    }

    public CircleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleView);
        mColor = typedArray.getColor(R.styleable.CircleView_circle_color, Color.BLUE);
        typedArray.recycle();

        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        mPaint.setColor(mColor);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(10);
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);

//        beginRecord();
//        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.checkmark);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int defaultWid = DimensionUtil.dp2Px(mContext, defaultWidth);
        int defaultHei = DimensionUtil.dp2Px(mContext, defaultHeight);

        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(defaultWid, defaultHei);
        } else if (widthMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(defaultWid, heightSize);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSize, defaultHei);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        final int paddingLeft = getPaddingLeft();
        final int paddingRight = getPaddingRight();
        final int paddingTop = getPaddingTop();
        final int paddingBottom = getPaddingBottom();

        int width = mWidth - paddingLeft - paddingRight;
        int height = mHeight - paddingTop - paddingBottom;
        int radius = Math.min(width, height) / 2;

//        canvas.drawColor(mColor);
//        canvas.drawPoint(paddingLeft + width / 2, paddingTop + height / 2, mPaint);
//        canvas.drawPoints(new float[]{paddingLeft + width / 2, paddingTop + height / 2, 200, 50, 250, 30}, mPaint);
//        canvas.drawLine(100, 30, 250, 80, mPaint);
//        canvas.drawLines(new float[]{100, 200, 200, 200, 100, 300, 200, 300}, mPaint);
//        canvas.drawRect(20, 10, 500, 500, mPaint);
//        canvas.drawRect(new RectF(20, 10, 500, 500), mPaint);
//        canvas.drawRect(new Rect(20, 10, 500, 500), mPaint);
//        canvas.drawRoundRect(new RectF(20, 10, 500, 300), 30, 50, mPaint);
//        canvas.drawRoundRect(new RectF(20, 10, 500, 300), 500, 300, mPaint);
//        canvas.drawRoundRect(new RectF(20, 10, 500, 300), 240, 145, mPaint);
//        canvas.drawOval(new RectF(20, 10, 500, 300), mPaint);
//        canvas.drawCircle(paddingLeft + width / 2, paddingTop + height / 2, radius, mPaint);
//        RectF rectF = new RectF(100, 100, 800, 800);
//        canvas.drawOval(rectF,new Paint());
//        canvas.drawArc(rectF, 0, 90, true, mPaint);

//        canvas.rotate(30);
//        canvas.translate(200, 200);
//        canvas.drawLine(0, 0, 200, 0, mPaint);
//        canvas.translate(200, 200);
//        canvas.drawPoint(60, 60, mPaint);
//        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setColor(Color.LTGRAY);
//        canvas.translate(400, 800);
//        canvas.drawLine(0, 0, 800, 0, mPaint);
//        canvas.drawLine(0, 0, -800, 0, mPaint);
//        canvas.drawLine(0, 0, 0, 800, mPaint);
//        canvas.drawLine(0, 0, 0, -800, mPaint);
//
//        mPaint.setColor(Color.GREEN);
//        canvas.drawRect(0, -400, 400, 0, mPaint);
//
//        canvas.scale(-0.5f, -0.5f);
//        mPaint.setColor(Color.CYAN);
//        canvas.drawRect(0, -400, 400, 0, mPaint);
//
//        canvas.scale(-2f, -2f);
//        mPaint.setColor(Color.BLUE);
//        canvas.drawRect(0, -400, 400, 0, mPaint);

//        canvas.translate(mWidth / 2 + paddingLeft, mHeight / 2 + paddingTop);
//        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setColor(Color.BLACK);
//        for (int i = 0; i < 100; i++) {
//            canvas.scale(0.95f, 0.95f);
//            canvas.drawRect(-400f, -400f, 400f, 400f, mPaint);
//        }

//        canvas.translate(mWidth / 2, mHeight / 2);
//        mPaint.setColor(Color.GRAY);
//        canvas.drawLine(0, 0, 800, 0, mPaint);
//        canvas.drawLine(0, 0, -800, 0, mPaint);
//        canvas.drawLine(0, 0, 0, 800, mPaint);
//        canvas.drawLine(0, 0, 0, -800, mPaint);
//
//        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setColor(Color.BLUE);
//        canvas.drawRect(20, -400, 400, -20, mPaint);
//
//        mPaint.setColor(Color.GREEN);
//        canvas.rotate(180, 210, 0);
//        canvas.drawRect(20, -400, 400, -20, mPaint);


//        canvas.translate(mWidth / 2, mHeight / 2);
//        mPaint.setColor(Color.GRAY);
//        mPaint.setStyle(Paint.Style.STROKE);
//        canvas.drawPoint(0, 0, mPaint);
//
//        canvas.drawCircle(0, 0, 400, mPaint);
//        canvas.drawCircle(0, 0, 300, mPaint);
//
//        for (int i = 0; i < 36; i++) {
//            canvas.rotate(10);
//            canvas.drawLine(300, 0, 400, 0, mPaint);
//        }

//        canvas.translate(mWidth / 2, mHeight / 2);
//        mPaint.setColor(Color.GRAY);
//        canvas.drawLine(0, 0, 800, 0, mPaint);
//        canvas.drawLine(0, 0, -800, 0, mPaint);
//        canvas.drawLine(0, 0, 0, 800, mPaint);
//        canvas.drawLine(0, 0, 0, -800, mPaint);
//        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setColor(Color.BLUE);
//        canvas.drawRect(0, -400, 400, 0, mPaint);
//        canvas.save();
//
//        canvas.skew(0, 1);
//        canvas.skew(1, 0);
//        mPaint.setColor(Color.GREEN);
//        canvas.drawRect(0, -400, 400, 0, mPaint);
//
//        canvas.restore();
//        canvas.drawRect(0, -200, 200, 0, mPaint);

//        canvas.drawPicture(mPicture, new Rect(0, 0, 500, 500));
//        PictureDrawable pictureDrawable = new PictureDrawable(mPicture);
//        pictureDrawable.setBounds(new Rect(0, 0, 500, 500));
//        pictureDrawable.draw(canvas);

//        canvas.translate(mWidth / 2, mHeight / 2);
//        canvas.drawCircle(0, 0, 240, mPaint);
//
//        int bitmapHeight = mBitmap.getHeight();
//
//        Rect bitmapRec = new Rect((animatedValue - 1) * bitmapHeight, 0, animatedValue * bitmapHeight, bitmapHeight);
//        RectF rectF = new RectF(-bitmapHeight, -bitmapHeight, bitmapHeight, bitmapHeight);
//        canvas.drawBitmap(mBitmap, bitmapRec, rectF, mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(50);
        char[] text = "我是廖福赟".toCharArray();
        canvas.drawText(text, 2, 3, 300, 500, mPaint);
//        canvas.drawPosText(text, 2, 3, new float[]{100, 100, 200, 200, 300, 300}, mPaint);
    }


//    private void beginRecord() {
//        Canvas canvas = mPicture.beginRecording(500, 500);
//
//        Paint paint = new Paint();
//        paint.setColor(Color.GREEN);
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeWidth(5);
//
//        canvas.translate(250, 250);
//        mPaint.setColor(Color.GRAY);
//        canvas.drawLine(0, 0, 250, 0, mPaint);
//        canvas.drawLine(0, 0, -250, 0, mPaint);
//        canvas.drawLine(0, 0, 0, 250, mPaint);
//        canvas.drawLine(0, 0, 0, -250, mPaint);
//
//        canvas.drawCircle(0, 0, 200, paint);
//
//        mPicture.endRecording();
//    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
//        mBitmap.recycle();
    }

    public void checkDo() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 13);
        valueAnimator.setInterpolator(new AccelerateInterpolator());
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                animatedValue = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.start();
    }

    public void unCheckDo() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(13, 0);
        valueAnimator.setInterpolator(new AccelerateInterpolator());
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                animatedValue = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.start();
    }


    public void setCircleColor(int color) {
        this.mColor = color;
        mPaint.setColor(color);
        invalidate();
    }
}
