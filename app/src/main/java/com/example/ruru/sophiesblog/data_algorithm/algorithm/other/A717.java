package com.example.ruru.sophiesblog.data_algorithm.algorithm.other;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;

/**
 * 思路
 判断从倒数第二位到第一位之间有多少个连续的1，奇数返回false，偶数返回true。
 注意遇到0便中断循环。
 */
public class A717 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a717);

        int[] bits = new int[]{1, 1, 1, 0};
        boolean res = isOneBitCharacter(bits);
        boolean res1 = isOneBitCharacter1(bits);
        Log.d(getClass().getName(), "onCreate: res=" + res1);
    }

    /**
     * 二分查找：不成功。
     *
     * @param bits
     * @return
     */
    public boolean isOneBitCharacter(int[] bits) {

        if (bits.length == 0) {
            return false;
        }
        if (bits[bits.length - 1] != 0) {
            return false;
        }

        if (bits.length == 1) {
            return true;
        }

        int twoLast = bits[bits.length - 2];
        if (twoLast == 0) {
            return true;
        } else if (twoLast == 1) {
            if (bits.length - 1 >> 1 == 0) {
                return true;
            } else {
                return false;
            }
        }

        return false;
    }

    public boolean isOneBitCharacter1(int[] bits) {

        int c = 0;
        for (int i = bits.length - 2; i >= 0; i--) {
            if (bits[i] == 1) ++c;
            else break;
        }

        return c % 2 == 0;
    }
}
