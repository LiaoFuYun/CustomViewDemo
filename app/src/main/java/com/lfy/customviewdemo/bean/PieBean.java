package com.lfy.customviewdemo.bean;

public class PieBean {
    private String pieName;
    private float piePercent;
    private int pieColor;

    public PieBean(String pieName, float piePercent, int pieColor) {
        this.pieName = pieName;
        this.piePercent = piePercent;
        this.pieColor = pieColor;
    }

    public String getPieName() {
        return pieName;
    }

    public void setPieName(String pieName) {
        this.pieName = pieName;
    }

    public float getPiePercent() {
        return piePercent;
    }

    public void setPiePercent(float piePercent) {
        this.piePercent = piePercent;
    }

    public int getPieColor() {
        return pieColor;
    }

    public void setPieColor(int pieColor) {
        this.pieColor = pieColor;
    }
}
