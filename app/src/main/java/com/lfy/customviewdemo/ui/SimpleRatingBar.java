package com.lfy.customviewdemo.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.lfy.customviewdemo.R;

/**
 * Created by lfy on 2017/9/4.
 */
public class SimpleRatingBar extends View {
    private static final String TAG = "SimpleRatingBar";
    // 评分星星数量、宽度、高度、间隔、图标、默认分数(10分制)、背景颜色
    private int mStarNum;
    private int mStarWidth;
    private int mStarHeight;
    private int mStarSpacing;
    private Drawable mRatingOnDrawable;
    private Drawable mRatingOffDrawable;
    private float mScore;
    private int mBackground = Color.WHITE;
    // 当前评分(5分制)
    private float mTempRating = 1;

    private int mWidth;
    private int mHeight;

    // 开始画星星的坐标(为了达到星星永远居中效果)
    private int mStartX = 0;
    private int mStartY = 0;

    // 是否作为指示器使用
    private boolean isIndicator;

    private Paint mPaint;

    private OnScoreChangeListener onScoreChangeListener = null;

    public SimpleRatingBar(Context context) {
        this(context, null);
    }

    public SimpleRatingBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleRatingBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SimpleRatingBar);
        mStarNum = a.getInteger(R.styleable.SimpleRatingBar_mStarNum, 5);
        mStarWidth = a.getDimensionPixelOffset(R.styleable.SimpleRatingBar_mStarWidth, 60);
        mStarHeight = a.getDimensionPixelOffset(R.styleable.SimpleRatingBar_mStarHeight, 60);
        mStarSpacing = a.getDimensionPixelOffset(R.styleable.SimpleRatingBar_mStarSpacing, 10);
        mRatingOnDrawable = a.getDrawable(R.styleable.SimpleRatingBar_mStarResId_on);
        mRatingOffDrawable = a.getDrawable(R.styleable.SimpleRatingBar_mStarResId_off);
        mScore = a.getFloat(R.styleable.SimpleRatingBar_mScore, 2f);
        mBackground = a.getResourceId(R.styleable.SimpleRatingBar_mBackground, Color.WHITE);
        isIndicator = a.getBoolean(R.styleable.SimpleRatingBar_mIsIndicator, true);
        a.recycle();
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        if (mScore > 10) {
            Log.e(TAG, "分数不能超过10分");
            mScore = 10;
        }
        mTempRating = score2Rating(mScore);
        setBackgroundColor(mBackground);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int defaultWidth = mStarWidth * mStarNum + mStarSpacing * (mStarNum + 1);
        int defaultHeight = mStarHeight + 2 * mStarSpacing;

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
        mWidth = w - getPaddingLeft() - getPaddingRight();
        mHeight = h - getPaddingTop() - getPaddingBottom();
        mStartX = (int) ((mWidth - mStarWidth * mStarNum - mStarSpacing * (mStarNum + 1)) / 2f);
        mStartY = (int) ((mHeight - mStarHeight) / 2f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawEmpty(canvas);
        drawChoose(canvas);
    }

    // 画出选中的星星
    private void drawChoose(Canvas canvas) {
        if (mTempRating > 1) {
            int intPart = (int) mTempRating;
            drawInteger(canvas, intPart);
            if (mTempRating > intPart) {//分数不为整数有0.5的分数需要画半星部分
                Bitmap bitmap = drawable2Bitmap(mRatingOnDrawable);

                mPaint.setShader(new BitmapShader(bitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
                int left = mStartX + mStarWidth * intPart + (intPart + 1) * mStarSpacing;
                int top = mStartY;
                canvas.save();
                canvas.translate(left, top);
                canvas.drawRect(0, 0, mStarWidth / 2, mStarHeight, mPaint);
                canvas.restore();
            }
        } else {// 小于一星自动补全为一星
            int left = mStartX + mStarSpacing;
            int top = mStartY;
            mRatingOnDrawable.setBounds(left, top, left + mStarWidth, top + mStarHeight);
            mRatingOnDrawable.draw(canvas);
        }
    }

    // 画出整数部分满星
    private void drawInteger(Canvas canvas, int starNum) {
        for (int i = 0; i < starNum; i++) {
            int left = mStartX + mStarWidth * i + (i + 1) * mStarSpacing;
            int top = mStartY;
            mRatingOnDrawable.setBounds(left, top, left + mStarWidth, top + mStarHeight);
            mRatingOnDrawable.draw(canvas);
        }
    }


    // 画出未选中的星星
    private void drawEmpty(Canvas canvas) {
        for (int i = 0; i < mStarNum; i++) {
            int left = mStartX + mStarWidth * i + (i + 1) * mStarSpacing;
            int top = mStartY;
            mRatingOffDrawable.setBounds(left, top, left + mStarWidth, top + mStarHeight);
            mRatingOffDrawable.draw(canvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isIndicator) {// 如果只当做评分指示器用不提供触摸事件
            return super.onTouchEvent(event);
        }
        float eventX = event.getX();
        float eventY = event.getY();
        if (eventX < mStartX || eventX > mWidth - mStartX
                || eventY < mStartY || eventY > mHeight - mStartY) {
            return false;
        }
        int ratingBarWidth = mStarWidth * mStarNum + mStarSpacing * (mStarNum + 1);

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                mTempRating = (eventX * 1.0f - mStartX) / (ratingBarWidth * 1.0f / mStarNum);
                if (mTempRating - (int) mTempRating >= 0.5) {
                    mTempRating = (int) mTempRating + 1;
                } else {
                    mTempRating = (int) mTempRating + 0.5f;
                }
                if (mTempRating >= 1) {
                    mScore = mTempRating * 2f;
                    if (onScoreChangeListener != null) {
                        onScoreChangeListener.onScoreChange(mScore);
                    }
                    invalidate();
                }
                break;
        }
        return true;
    }

    private Bitmap drawable2Bitmap(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        Bitmap bitmap = Bitmap.createBitmap(mStarWidth, mStarHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, mStarWidth, mStarHeight);
        drawable.draw(canvas);
        return bitmap;
    }

    // 10分制转5颗星制(参考豆瓣)
    private float score2Rating(float score) {
        float rating;
        if (score >= 9.5f) {
            rating = 5f;
        } else if (score >= 8.6f) {
            rating = 4.5f;
        } else if (score >= 7.6f) {
            rating = 4f;
        } else if (score >= 6.6f) {
            rating = 3.5f;
        } else if (score >= 5.6f) {
            rating = 3f;
        } else if (score >= 4.6f) {
            rating = 2.5f;
        } else if (score >= 3.5f) {
            rating = 2f;
        } else if (score >= 2.6f) {
            rating = 1.5f;
        } else {
            rating = 1f;
        }
        return rating;
    }

    // 设置是否只当做评分指示器
    public void setIsIndicator(boolean isIndicator) {
        this.isIndicator = isIndicator;
    }

    public void setStarNum(int starNum) {
        this.mStarNum = starNum;
    }

    public void setStarSize(int starSize) {
        this.mStarWidth = starSize;
        this.mStarHeight = starSize;
    }

    public void setStarSpacing(int starSpacing) {
        this.mStarSpacing = starSpacing;
    }

    public void setRatingOnDrawable(Drawable ratingOnDrawable) {
        this.mRatingOnDrawable = ratingOnDrawable;
    }

    public void setRatingOffDrawable(Drawable ratingOffDrawable) {
        this.mRatingOffDrawable = ratingOffDrawable;
    }

    public void setBackground(int backgroundResId) {
        this.mBackground = backgroundResId;
    }

    // 设置评分（0-10分）
    public void setScore(float score) throws Exception {
        if (score < 0 || score > 10) {
            throw new Exception("分数不正确,正确范围0-10");
        }
        this.mScore = score;
        this.mTempRating = score2Rating(score);
        invalidate();
    }

    // 获取评分
    public float getScore() {
        return mScore;
    }

    // 设置分数改变通知
    public void setOnScoreChangeListener(@Nullable OnScoreChangeListener listener) {
        this.onScoreChangeListener = listener;
    }

    // 分数改变监听
    public interface OnScoreChangeListener {
        void onScoreChange(float score);
    }
}
