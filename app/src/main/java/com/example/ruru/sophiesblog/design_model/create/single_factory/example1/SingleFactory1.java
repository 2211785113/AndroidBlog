package com.example.ruru.sophiesblog.design_model.create.single_factory.example1;

import android.os.Bundle;

import com.example.ruru.sophiesblog.R;

import androidx.appcompat.app.AppCompatActivity;

public class SingleFactory1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factory_method);

        //不用简单工厂模式
        Man man = new Man();
        man.say();

        Woman woman = new Woman();
        woman.say();

        //用简单工厂模式
        //第一步：用new构造器实例化工厂类。
        //第二步：调用工厂类中的方法
        Factory1 fm = new Factory1();
        Human man1 = fm.createHuman("man");
        Human woman1 = fm.createHuman("woman");
        man1.say();
        woman1.say();
    }
}
