package com.example.ruru.sophiesblog.design_model.create.singleton;

public class Singleton3 {

    private static Singleton3 singleton;

    public static synchronized Singleton3 getInstance() {
        if (singleton == null) {
            singleton = new Singleton3();
        }
        return singleton;
    }

    public void c() {
        System.out.println("c");
    }
}
