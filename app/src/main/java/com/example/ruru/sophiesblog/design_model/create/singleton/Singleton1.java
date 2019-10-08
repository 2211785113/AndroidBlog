package com.example.ruru.sophiesblog.design_model.create.singleton;

import android.util.Log;

import java.io.ObjectStreamException;

/**
 * 饿汉
 */
public class Singleton1 {

    private static Singleton1 instance = new Singleton1();

    public static Singleton1 getInstance() {
        return instance;
    }

    private Object readResolve() throws ObjectStreamException {
        return instance;
    }

    public void a() {
        System.out.println("a");
    }
}
