package com.lfy.customviewdemo.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.lfy.customviewdemo.R;

import java.util.Timer;
import java.util.TimerTask;

public class MeiziView extends FrameLayout {
    private ImageView imageView;

    public MeiziView(Context context) {
        this(context, null);
    }

    public MeiziView(Context context, AttributeSet attrs) {
        this(context, null, 0);
    }

    public MeiziView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        imageView = new ImageView(context);
        imageView.setBackgroundResource(R.mipmap.s_1);
        setBitmapXY(20, 100);

        addView(imageView, new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(1);
            }
        }, 0, 170);

    }

    Handler handler = new Handler() {
        int i = 1;

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                move(i);
                i++;
                if (i > 8) {
                    i = 1;
                }
                imageView.invalidate();
            }
        }
    };

    private void move(int i) {
        switch (i) {
            case 1:
                imageView.setBackgroundResource(R.mipmap.s_1);
                break;
            case 2:
                imageView.setBackgroundResource(R.mipmap.s_2);
                break;
            case 3:
                imageView.setBackgroundResource(R.mipmap.s_3);
                break;
            case 4:
                imageView.setBackgroundResource(R.mipmap.s_4);
                break;
            case 5:
                imageView.setBackgroundResource(R.mipmap.s_5);
                break;
            case 6:
                imageView.setBackgroundResource(R.mipmap.s_6);
                break;
            case 7:
                imageView.setBackgroundResource(R.mipmap.s_7);
                break;
            case 8:
                imageView.setBackgroundResource(R.mipmap.s_8);
                break;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        Paint p = new Paint();
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
//        canvas.drawBitmap(bitmap, bitmapX, bitmapY, p);
//        if (!bitmap.isRecycled()) {
//            bitmap.recycle();
//        }

    }

    public void setBitmapXY(float bitmapX, float bitmapY) {
        imageView.setX(bitmapX);
        imageView.setY(bitmapY);
        imageView.invalidate();
    }
}
