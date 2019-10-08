package com.example.ruru.sophiesblog.android.adapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ruru.sophiesblog.R;

/**
 * >>>：表示不带符号向右移动二进制数，移动后前面统统补0；
 * >>：表示带符号移动
 * <p>
 * 规律：a>>>1：相当于a值除以2。
 */
public class SparseArray extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sparse_array);

        int mid = getMid();
        System.out.println("mid=" + mid);//4

        int aaa = getInt();
        System.out.println("int=" + aaa);//3
    }

    /**
     * @return mid=4
     */
    private int getMid() {
        int start = 0;
        int end = 8;

        int mid = (start + end) >>> 1;//8的二进制是1000，无符号右移>>>1位=0100，即为4
        return mid;
    }

    private int getInt() {
        int i = 15 >>> 2;//15的二进制是1111，无符号右移>>>2位=0011，即为3
        return i;
    }
}
