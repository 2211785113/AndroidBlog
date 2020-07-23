package com.example.ruru.sophiesblog.design_model.create.factory_method;

import android.os.Bundle;

import com.example.ruru.sophiesblog.R;
import com.example.ruru.sophiesblog.design_model.create.single_factory.example2.AsusComputer;
import com.example.ruru.sophiesblog.design_model.create.single_factory.example2.Computer;
import com.example.ruru.sophiesblog.design_model.create.single_factory.example2.HpComputer;
import com.example.ruru.sophiesblog.design_model.create.single_factory.example2.LenovaComputer;

import androidx.appcompat.app.AppCompatActivity;

public class FactoryMethod extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);

        Factory factory = new ComputerFactory();

        try {
            HpComputer hp = factory.createComputer(HpComputer.class);
            hp.start();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        try {
            LenovaComputer lenova = factory.createComputer(LenovaComputer.class);
            lenova.start();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        try {
            Computer asus = factory.createComputer(AsusComputer.class);
            asus.start();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
