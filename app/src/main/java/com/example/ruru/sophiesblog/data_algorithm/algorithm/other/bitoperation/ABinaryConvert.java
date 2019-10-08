package com.example.ruru.sophiesblog.data_algorithm.algorithm.other.bitoperation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;

public class ABinaryConvert extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abinary_convert);

        int decimalNum = 7;//1364
        int res1 = decimalToBinary(decimalNum);
        Log.d(getClass().getName(), "onCreate: res1=" + res1);//二进制为111

        int binaryNum = 111;
        int res2 = binaryToDecimal1(binaryNum);
        Log.d(getClass().getName(), "onCreate: res2=" + res2);//十进制为7
    }

    /**
     * 十进制to二进制：错误。1364。
     */
    private int decimalToBinary(int decimal) {
        int sum = 0;
        int cnt = 0;

        while (true) {
            if (decimal == 0) {
                break;
            } else {
                int val = decimal % 2;
                sum += val * Math.pow(10, cnt);
                decimal = decimal / 2;
                cnt++;
            }
        }
        return sum;
    }

    /**
     * 十进制转二进制：A693。错误。
     */
    private int decimalToBinary1(int num) {//num看成除数divisor
        int res = 0;//结果：二进制数
        int cnt = 0;//计数
        while (num > 0) {
            int remainder = num & 1;//余数

            //注意：此处结果为1010101001.0E10，所以导致此题输入1364开始报错。
            Log.d(getClass().getName(), "toBinaryNum:==== " + 101010100 + 1 * Math.pow(10, 10));

            res += remainder * Math.pow(10, cnt);

            num >>= 1;
            cnt++;
        }
        return res;
    }

    /**
     * 二进制to十进制
     */
    private int binaryToDecimal(int binary) {
        int cnt = 0;
        int val = 0;
        int sum = 0;
        while (true) {
            if (binary == 0) {
                break;
            } else {
                val = binary % 10;
                sum += val * Math.pow(2, cnt);
                binary = binary / 10;
                cnt++;
            }
        }
        return sum;
    }

    /**
     * 二进制转十进制：错误。
     */
    private int binaryToDecimal1(int num) {
        int res = 0;
        String str = String.valueOf(num);
        int n = str.length();

        for (int i = 0; i < n; i++) {
            res += (str.charAt(i) - '0') * (int) Math.pow(2, n - 1 - i);
        }

        return res;
    }
}
