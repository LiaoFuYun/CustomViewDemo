package com.lfy.customviewdemo.databinding;

public interface IDatabinding {
    public interface View {
        String getUserName();

        String getPwd();

        void clear();

        void showWaiting();

        void dismissWaiting();

        void loginSuccess();

        void loginFail();
    }

    public interface presenter {
        void login();

        void clear();
    }

}
