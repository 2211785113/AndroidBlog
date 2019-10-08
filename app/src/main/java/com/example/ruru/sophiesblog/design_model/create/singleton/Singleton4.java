package com.example.ruru.sophiesblog.design_model.create.singleton;

public class Singleton4 {

    private volatile static Singleton4 singleton;

    public static Singleton4 getInstance() {
        if (singleton == null) {
            synchronized (Singleton4.class) {
                if (singleton == null) {
                    singleton = new Singleton4();
                }
            }
        }
        return singleton;
    }

    public void d() {
        System.out.println("d");
    }
}
