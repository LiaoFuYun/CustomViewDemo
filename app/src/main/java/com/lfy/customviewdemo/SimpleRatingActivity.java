package com.lfy.customviewdemo;

import android.graphics.Path;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lfy.customviewdemo.ui.SimpleRatingBar;

/**
 * Created by lfy on 2017/9/4.
 */

public class SimpleRatingActivity extends AppCompatActivity implements View.OnClickListener {

    private SimpleRatingBar simpleRatingBar;
    private EditText editText;
    private Button calcBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simplerating);
        simpleRatingBar = (SimpleRatingBar) findViewById(R.id.simpleRatingBar);
        editText = (EditText) findViewById(R.id.editText);
        calcBtn = (Button) findViewById(R.id.calcBtn);
        calcBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String str = editText.getText().toString();
        if (TextUtils.isEmpty(str)) {
            Toast.makeText(SimpleRatingActivity.this, "分数不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            float score = Float.parseFloat(str);
            simpleRatingBar.setScore(score);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
