package com.lfy.customviewdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.baoyz.actionsheet.ActionSheet;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;


public class ImageSelectActivity extends AppCompatActivity {
    @BindView(R.id.selectBtn)
    Button selectBtn;

    private static final int REQUEST_CODE_CAMERA = 1000;
    private static final int REQUEST_CODE_GALLERY = 1001;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageselect);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.selectBtn)
    public void onClick() {
        imageSelect(this, getSupportFragmentManager());
    }

    public void imageSelect(Context mContext, FragmentManager fragmentManager) {
        ActionSheet.createBuilder(mContext, fragmentManager)
                .setCancelButtonTitle("取消")
                .setOtherButtonTitles("拍照", "从相册选择")
                .setCancelableOnTouchOutside(true)
                .setListener(new ActionSheet.ActionSheetListener() {
                    @Override
                    public void onDismiss(ActionSheet actionSheet, boolean isCancel) {
                    }

                    @Override
                    public void onOtherButtonClick(ActionSheet actionSheet, int index) {
                        switch (index) {
                            case 0:
                                GalleryFinal.openCamera(REQUEST_CODE_CAMERA, callback);
                                break;
                            case 1:
                                GalleryFinal.openGallerySingle(REQUEST_CODE_GALLERY, callback);
                                break;
                            default:
                                break;
                        }
                    }
                })
                .show();
    }

    private final GalleryFinal.OnHanlderResultCallback callback = new GalleryFinal.OnHanlderResultCallback() {
        @Override
        public void onHanlderSuccess(int requestCode, List<PhotoInfo> resultList) {
            if (resultList != null && resultList.size() > 0) {
                Log.d("ImageSelectActivity", "resultList:" + resultList);
            }
        }

        @Override
        public void onHanlderFailure(int requestCode, String errorMsg) {

        }
    };
}
