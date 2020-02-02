package com.example.ruru.sophiesblog.data_algorithm.algorithm.other.bitoperation;

import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 判断其二进制数是否是交替位
 */
public class A693 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a693);

        int num = 7;
        boolean res = hasAlternatingBits(num);
        Log.d(getClass().getName(), "onCreate:res= " + res);
    }

    /**
     * 别人的解法：位运算。
     * 0ms  33.2MB
     * <p>
     * n&1相当于n%2
     * n>>1相当于n/2
     */
    public boolean hasAlternatingBits(int n) {
        int pre = n & 1;//第一位余数
        n >>= 1;//第一位除数
        while (n > 0) {//保证除数大于0
            int cur = n & 1;//现在余数
            if (cur == pre) return false;//如果现在余数=以前余数，则返回false。
            pre = cur;//以前余数=现在余数
            n >>= 1;//n继续除2
        }
        return true;
    }

    /**
     * 我的解法2：正确
     * 1ms  33.4MB
     */
    public boolean haslternatingBits2(int n) {
        String str = Integer.toBinaryString(n);
        int len = str.length();
        for (int i = 0; i < len - 1; i++) {
            if (str.charAt(i) - '0' == str.charAt(i + 1) - '0')
                return false;
        }
        return true;
    }

    /**
     * 我的解法1：错误，转为二进制数输入1364开始报错。
     */
    public boolean hasAlternatingBits1(int num) {

        int number = toBinaryNum1(num);//转二进制数

        Log.d(getClass().getName(), "hasAlternatingBits:二进制数= " + number);

        String str = String.valueOf(number);
        int n = str.length();

        for (int i = 1; i < n; i++) {
            if (str.charAt(i) - '0' == str.charAt(i - 1) - '0')
                return false;
        }

        return true;
    }

    private int toBinaryNum1(int num) {//num看成除数divisor
        int res = 0;//结果：二进制数
        int cnt = 0;//计数
        while (num > 0) {
            int remainder = num % 2;//余数

            //注意：此处结果为1010101001.0E10，所以导致此题输入1364开始报错。
            Log.d(getClass().getName(), "toBinaryNum:==== " + 101010100 + 1 * Math.pow(10, 10));


            res += remainder * Math.pow(10, cnt);

            num /= 2;
            cnt++;
        }
        return res;
    }
}
