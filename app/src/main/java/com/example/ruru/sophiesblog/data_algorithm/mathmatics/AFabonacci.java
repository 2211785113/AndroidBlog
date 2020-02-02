package com.example.ruru.sophiesblog.data_algorithm.mathmatics;

import android.os.Bundle;

import com.example.ruru.sophiesblog.R;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 1、1、2、3、5、8、13、21、34、55
 */
public class AFabonacci extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afabonacci);

        fabonacci(10);
    }

    private int fabonacci(int n) {
        if (n <= 2) {
            return 1;
        }
        return fabonacci(n - 1) + fabonacci(n - 2);
    }
}
