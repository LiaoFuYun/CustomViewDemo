package com.lfy.customviewdemo;

import android.graphics.Matrix;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.lfy.customviewdemo.ui.MatrixImageView;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MatrixActivity extends AppCompatActivity {

    @BindView(R.id.matrixImageView)
    MatrixImageView matrixImageView;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.radioButton0)
    RadioButton radioButton0;
    @BindView(R.id.radioButton1)
    RadioButton radioButton1;
    @BindView(R.id.radioButton2)
    RadioButton radioButton2;
    @BindView(R.id.radioButton3)
    RadioButton radioButton3;
    @BindView(R.id.radioButton4)
    RadioButton radioButton4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix);
        ButterKnife.bind(this);

        float[] pts = new float[]{0, 0, 80, 100, 200, 100, 100, 150};
        float[] dst = new float[8];
        Matrix matrix = new Matrix();
        matrix.setScale(0.5f, 2f);
        matrix.mapPoints(dst, 2, pts, 1, 3);
        Log.i("MatrixActivity", "pts:" + Arrays.toString(pts));
        Log.d("MatrixActivity", "dst:" + Arrays.toString(dst));

        Matrix matrix1 = new Matrix();
        matrix1.setScale(2f, 0.5f);
        float radius = 100f;
        float mapRadius = matrix1.mapRadius(radius);
        Log.d("MatrixActivity", "mapRadius:" + mapRadius);

        Matrix matrix2 = new Matrix();
        matrix2.setScale(0.5f, 2f);
        matrix2.postSkew(1, 0);
        RectF rectF = new RectF(-200, -200, 200, 200);
        RectF rectF1 = new RectF();
        boolean b = matrix2.mapRect(rectF1, rectF);
        Log.d("MatrixActivity", "rectF:" + rectF + "\nrectF1" + rectF1 + "|" + b);


        float[] src = {100, 800};
        float[] d = new float[2];
        Matrix matrix3 = new Matrix();
        matrix3.postTranslate(100, 200);
        matrix3.mapVectors(d, src);
        Log.d("MatrixActivity", "d:" + Arrays.toString(d));
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton0:
                        matrixImageView.setPoint(0);
                        break;
                    case R.id.radioButton1:
                        matrixImageView.setPoint(1);
                        break;
                    case R.id.radioButton2:
                        matrixImageView.setPoint(2);
                        break;
                    case R.id.radioButton3:
                        matrixImageView.setPoint(3);
                        break;
                    case R.id.radioButton4:
                        matrixImageView.setPoint(4);
                        break;
                }
            }
        });
    }
}
