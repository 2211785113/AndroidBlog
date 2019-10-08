package com.example.ruru.sophiesblog.design_model.create.builder.example2;

public class ProductManager {
    public static Product create(int system) {
        return new Progremer().setAppSystem(system).setAppName("探探").setAppFuction("划一划，找妹子。").build();
    }
}