package com.example.ruru.sophiesblog.design_model.create.singleton;

import android.os.Bundle;

import com.example.ruru.sophiesblog.R;

import androidx.appcompat.app.AppCompatActivity;

public class Singleton extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_ton);

        //不用单例模式：用new新创建对象，且可以创建n个对象。
        //单例模式：整个应用程序只有一个静态全局对象，只可以创建一个对象。

        Singleton1 instance1 = Singleton1.getInstance();
        instance1.a();

        Singleton2 instance2 = Singleton2.getInstance();
        instance2.b();

        Singleton3 instance3 = Singleton3.getInstance();
        instance3.c();

        Singleton4 instance4 = Singleton4.getInstance();
        instance4.d();

        Singleton5 instance5 = Singleton5.getInstance();
        instance5.e();

        Singleton6 instance6 = Singleton6.INSTANCE;
        instance6.f();

        //打印结果：a b c d e f
    }
}
