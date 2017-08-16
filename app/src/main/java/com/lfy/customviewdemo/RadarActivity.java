package com.lfy.customviewdemo;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import com.lfy.customviewdemo.ui.RadarView;

import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RadarActivity extends AppCompatActivity {
    @BindView(R.id.radarView)
    RadarView radarView;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radar);
        ButterKnife.bind(this);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                ArrayList<Float> arrayList = new ArrayList<>();
                int n = 6;
                switch (i) {
                    case R.id.radioButton6:
                        n = 6;
                        break;
                    case R.id.radioButton7:
                        n = 7;
                        break;
                    case R.id.radioButton8:
                        n = 8;
                        break;
                    case R.id.radioButton9:
                        n = 9;
                        break;
                    case R.id.radioButton10:
                        n = 10;
                        break;
                }
                for (int j = 0; j < n; j++) {
                    arrayList.add((float) (new Random().nextInt(100) % (100 - 10 + 1) + 10));
                }
                radarView.setData(arrayList);
            }
        });
    }
}
