package com.lfy.customviewdemo.mvp;

import android.support.annotation.NonNull;

public class MVPPresenter implements MVPInterface.Presenter {
    private MVPInterface.ViewI viewI;

    public MVPPresenter(@NonNull MVPInterface.ViewI viewI) {
        this.viewI = viewI;
    }

    @Override
    public void getText(int i) {
        viewI.setText("测试setText" + i);
    }

    @Override
    public void getText() {
        getText(1000);
    }
}
