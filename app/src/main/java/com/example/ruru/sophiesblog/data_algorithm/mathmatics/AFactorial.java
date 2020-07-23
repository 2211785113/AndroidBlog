package com.example.ruru.sophiesblog.data_algorithm.mathmatics;

import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 求n的阶乘
 */
public class AFactorial extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factorial);

        int res = f(10);
        Log.d(getClass().getName(), "onCreate: res=" + res);//3628800
    }

    private int f(int n) {
        if (n == 1) {
            return 1;
        }
        return n * f(n - 1);
    }
}
