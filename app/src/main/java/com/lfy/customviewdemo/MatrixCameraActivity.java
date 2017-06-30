package com.lfy.customviewdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.lfy.customviewdemo.ui.MatrixCameraView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MatrixCameraActivity extends AppCompatActivity {
    @BindView(R.id.matrixCameraView)
    MatrixCameraView matrixCameraView;
    @BindView(R.id.xButton)
    RadioButton xButton;
    @BindView(R.id.yButton)
    RadioButton yButton;
    @BindView(R.id.zButton)
    RadioButton zButton;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.rotateBtn)
    Button rotateBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrixcamera);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.rotateBtn)
    public void onClick() {
        int checkedId = radioGroup.getCheckedRadioButtonId();
        switch (checkedId) {
            case R.id.xButton:
                matrixCameraView.rotate("x", 180f);
                break;
            case R.id.yButton:
                matrixCameraView.rotate("y", 180f);
                break;
            case R.id.zButton:
                matrixCameraView.rotate("z", 180f);
                break;
        }
    }
}
