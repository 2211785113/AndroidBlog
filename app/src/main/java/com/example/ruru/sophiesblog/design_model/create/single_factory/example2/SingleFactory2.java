package com.example.ruru.sophiesblog.design_model.create.single_factory.example2;

import android.os.Bundle;

import com.example.ruru.sophiesblog.R;

import androidx.appcompat.app.AppCompatActivity;

public class SingleFactory2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);

        Factory2 factory = new Factory2();

        Computer hp = factory.createComputer("hp");
        hp.start();

        Computer lenova = factory.createComputer("lenova");
        lenova.start();

        Computer asus = factory.createComputer("asus");
        asus.start();
    }
}
