package com.lfy.customviewdemo;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.lfy.customviewdemo.ui.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OtherActivity extends AppCompatActivity {
    @BindView(R.id.chronometer)
    Chronometer chronometer;
    @BindView(R.id.startBtn)
    Button startBtn;
    @BindView(R.id.endBtn)
    Button endBtn;
    @BindView(R.id.resetBtn)
    Button resetBtn;
    @BindView(R.id.formatBtn)
    Button formatBtn;
    private Context mContext = this;

    @BindView(R.id.bottomBtn)
    Button bottomBtn;
    @BindView(R.id.topBtn)
    Button topBtn;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    @BindView(R.id.textV)
    TextView textV;
    @BindView(R.id.editV)
    EditText editV;
    @BindView(R.id.delEditV)
    DelEditText delEditV;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.circleImageV)
    CircleImageView circleImageV;
    @BindView(R.id.radioButton1)
    RadioButton radioButton1;
    @BindView(R.id.radioButton2)
    RadioButton radioButton2;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.checkbox1)
    CheckBox checkbox1;
    @BindView(R.id.checkbox2)
    CheckBox checkbox2;
    @BindView(R.id.checkbox3)
    CheckBox checkbox3;
    @BindView(R.id.showBtn)
    Button showBtn;
    @BindView(R.id.closeBtn)
    Button closeBtn;
    @BindView(R.id.animImageV)
    ImageView animImageV;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        ButterKnife.bind(this);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append("好友").append(i).append(",");
        }

        circleImageV.setmBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.girl));

        textV.setText(partClickableText(sb.substring(0, sb.lastIndexOf(","))), TextView.BufferType.SPANNABLE);
    }

    private SpannableStringBuilder partClickableText(String str) {
        SpannableStringBuilder ssb = new SpannableStringBuilder(str);
        String[] split = str.split(",");
        for (final String s : split) {
            ssb.setSpan(new ClickableSpan() {
                @Override
                public void onClick(View widget) {
                    Toast.makeText(OtherActivity.this, s, Toast.LENGTH_SHORT).show();
                }
            }, str.indexOf(s), str.indexOf(s) + s.length(), 0);
        }

        return ssb.append("等" + split.length + "人觉得很赞");
    }

    @OnClick({R.id.circleImageV, R.id.showBtn, R.id.closeBtn, R.id.bottomBtn, R.id.topBtn,
            R.id.startBtn, R.id.endBtn, R.id.resetBtn, R.id.formatBtn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.circleImageV:
                //                Toast.makeText(OtherActivity.this, radioGroup.getCheckedRadioButtonId() == R.id.radioButton1 ? "男" : "女", Toast.LENGTH_SHORT).show();
                Toast.makeText(OtherActivity.this, checkbox1.isChecked() + "" + checkbox2.isChecked() + checkbox3.isChecked(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.showBtn:
                animImageV.setVisibility(View.VISIBLE);
                break;
            case R.id.closeBtn:
                animImageV.setVisibility(View.GONE);
                break;
            case R.id.bottomBtn:
                scrollView.fullScroll(View.FOCUS_DOWN);
                topBtn.setVisibility(View.VISIBLE);
                bottomBtn.setVisibility(View.GONE);
                break;
            case R.id.topBtn:
                scrollView.fullScroll(View.FOCUS_UP);
                topBtn.setVisibility(View.GONE);
                bottomBtn.setVisibility(View.VISIBLE);
                break;

            case R.id.startBtn:
                chronometer.start();
                break;
            case R.id.endBtn:
                chronometer.stop();
                break;
            case R.id.resetBtn:
                chronometer.setBase(SystemClock.elapsedRealtime());
                break;
            case R.id.formatBtn:
                chronometer.setFormat("Time %s");
                break;
        }
    }
}
