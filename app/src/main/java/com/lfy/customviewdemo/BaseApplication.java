package com.lfy.customviewdemo;

import android.app.Application;
import android.graphics.Color;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ThemeConfig;

public class BaseApplication extends Application {

    public ImageLoaderConfiguration configuration;


    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoader();
    }

    private void initImageLoader() {
        // 异步加载图片ImageLoader初始化
        String cachePath = getExternalCacheDir() + "/image/cache";
        File file = new File(cachePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        configuration = new ImageLoaderConfiguration.Builder(this).diskCache(
                new UnlimitedDiskCache(file)).build();
        ImageLoader.getInstance().init(configuration);

        initGalleryFinal();
    }

    private void initGalleryFinal() {
        //=====gallery final 配置
        //设置主题
        ThemeConfig theme = new ThemeConfig.Builder()
                .setTitleBarBgColor(Color.WHITE)
                .setTitleBarTextColor(Color.BLACK)
                .setTitleBarIconColor(Color.BLACK)
                .setCropControlColor(Color.WHITE)
                .setFabNornalColor(Color.parseColor("#FFA726"))
                .setFabPressedColor(Color.LTGRAY)
                .setCheckNornalColor(Color.WHITE)
                .setCheckSelectedColor(Color.BLACK)
//                .setIconCamera(R.drawable.ic_action_camera)
                .build();
        //配置功能
        FunctionConfig functionConfig = new FunctionConfig.Builder()
                .setEnableCamera(true)
                .setEnableEdit(true)
                .setEnableCrop(true)
                .setForceCrop(true)
                .setEnableRotate(false)
                .setCropSquare(false)
                .setCropSquare(true)
                .setCropWidth(400)
                .setCropHeight(600)
                .setEnablePreview(false)
                .build();
        CoreConfig coreConfig = new CoreConfig.Builder(this, new UILImageLoader(), ThemeConfig.DEFAULT)
                .setFunctionConfig(functionConfig)
                .setNoAnimcation(true)
                .setPauseOnScrollListener(new UILPauseOnScrollListener(false, true))
                .build();
        GalleryFinal.init(coreConfig);
    }
}
