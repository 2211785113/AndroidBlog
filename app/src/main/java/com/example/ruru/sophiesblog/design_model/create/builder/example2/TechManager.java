package com.example.ruru.sophiesblog.design_model.create.builder.example2;


public abstract class TechManager {
    public abstract TechManager setAppName(String appName);
    public abstract TechManager setAppFuction(String appFuction);
    public abstract TechManager setAppSystem(int appSystem);
    public abstract Product build();
}
