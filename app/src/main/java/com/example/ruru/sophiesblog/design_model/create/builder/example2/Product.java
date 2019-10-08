package com.example.ruru.sophiesblog.design_model.create.builder.example2;

public class Product {
    public static final int ANDROID = 0;
    public static final int IOS = 1;

    private String appName;
    private String appFuction;
    private int appSystem;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppFuction() {
        return appFuction;
    }

    public void setAppFuction(String appFuction) {
        this.appFuction = appFuction;
    }

    public int getAppSystem() {
        return appSystem;
    }

    public void setAppSystem(int appSystem) {
        this.appSystem = appSystem;
    }

    @Override
    public String toString() {
        return "{" +
                "\"appName\":\'" + appName + "\'" +
                ", \"appFuction\":\'" + appFuction + "\'" +
                ", \"appSystem\":" + appSystem +
                '}';
    }
}
