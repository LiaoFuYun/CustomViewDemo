package com.lfy.customviewdemo.mvp;

public interface MVPInterface {

    public interface ViewI {

        void setText(String text);
    }

    public interface Presenter {

        void getText(int i);

        void getText();
    }

}
