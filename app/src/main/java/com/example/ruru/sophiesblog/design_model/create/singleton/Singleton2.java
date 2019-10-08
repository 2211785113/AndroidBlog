package com.example.ruru.sophiesblog.design_model.create.singleton;

/**
 * 懒汉
 */
public class Singleton2 {

    private static Singleton2 singleton;

    public static Singleton2 getInstance() {
        if (singleton == null) {
            singleton = new Singleton2();
        }
        return singleton;
    }

    public void b() {
        System.out.println("b");
    }
}
