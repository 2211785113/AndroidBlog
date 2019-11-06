package com.example.ruru.sophiesblog.java.controlFlow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ruru.sophiesblog.R;

public class PrintTwoPower extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_two_power);

        int a = 2147483647;//测试int最高位




        printPower();
    }

    private void printPower() {
        for (int i = 1; i <= 100; i++) {
            System.out.println(Math.pow(2, i));
        }
    }

    private void printPower1() {
        int x = 2;
        for (int i = 1; i <= 100; i++) {
            System.out.println(x);
            x <<= 1;
        }
    }
}
