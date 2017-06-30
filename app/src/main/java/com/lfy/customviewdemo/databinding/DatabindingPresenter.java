package com.lfy.customviewdemo.databinding;

public class DatabindingPresenter implements IDatabinding.presenter {
    private IDatabinding.View iView;

    public DatabindingPresenter(IDatabinding.View iView) {
        this.iView = iView;
    }

    @Override
    public void login() {
        iView.showWaiting();
        String userName = iView.getUserName();
        String pwd = iView.getPwd();

        iView.dismissWaiting();
        if (userName.equals("lfy") && pwd.equals("123")) {
            iView.loginSuccess();
        } else {
            iView.loginFail();
        }
    }

    @Override
    public void clear() {
        iView.clear();
    }
}
