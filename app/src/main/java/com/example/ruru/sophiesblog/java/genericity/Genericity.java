package com.example.ruru.sophiesblog.java.genericity;

import android.os.Bundle;

import com.example.ruru.sophiesblog.R;

import androidx.appcompat.app.AppCompatActivity;

public class Genericity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int[] array = new int[]{5, 3, 9, 1};
        int min = getMin(array);
        int max = getMax(array);
        System.out.println("min=" + min + " max=" + max);

        ArrayAlg<Integer> limit = getLimit(array);
        int max1 = limit.getMax();
        int min1 = limit.getMin();
        System.out.println("min1=" + min1 + " max1=" + max1);
    }

    public ArrayAlg<Integer> getLimit(int[] attr) {
        int len = attr.length;
        int min = attr[0];
        int max = attr[0];
        for (int i = 0; i < len; i++) {
            if (attr[i] < min) {
                min = attr[i];
            }
            if (attr[i] > max) {
                max = attr[i];
            }
        }
        ArrayAlg arrayAlg = new ArrayAlg(max, min);
        return arrayAlg;
    }

    public int getMin(int[] attr) {
        int min = attr[0];
        int len = attr.length;
        for (int i = 1; i < len; i++) {
            if (attr[i] < min) {
                min = attr[i];
            }
        }
        return min;
    }

    public int getMax(int[] attr) {
        int max = attr[0];
        int len = attr.length;
        for (int i = 1; i < len; i++) {
            if (attr[i] > max) {
                max = attr[i];
            }
        }
        return max;
    }
}
