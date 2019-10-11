package com.example.ruru.sophiesblog.java.extends_.one;

import android.util.Log;

public class A {

    public String name = "A";//实例域，创建A对象的时候才会调用(错)，调用实例域name的时候才会调用。

    public A() {//构造器，创建A对象的时候才会调用
        call();
    }

    public void call() {//调用A方法的时候才会调用该call方法
        Log.d("ClientExtends", "call:A " + name);
    }

    public static class B extends A {
        public String name = "B";//实例域，创建B对象的时候才会调用(错)，调用实例域name的时候才会调用。

        //子类默认调用父类的构造器，创建B对象的时候才会调用

        public void call() {//调用父类构造器的时候才会调用该call方法
            Log.d("ClientExtends", "call:B " + name);
        }
    }
}
