package com.example.ruru.sophiesblog.design_model.create.singleton;

public class Singleton5 {

    private static class LazyHolder {
        private static final Singleton5 singleton = new Singleton5();
    }

    public static Singleton5 getInstance() {
        return LazyHolder.singleton;
    }

    public void e() {
        System.out.println("e");
    }
}
