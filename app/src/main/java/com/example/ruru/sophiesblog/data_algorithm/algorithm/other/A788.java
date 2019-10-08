package com.example.ruru.sophiesblog.data_algorithm.algorithm.other;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;

import java.util.HashSet;
import java.util.Set;

/**
 * 类型：String/动态规划
 */

/**
 * 分析题目：
 * 条件一：有效：不能有3,4,7
 * 条件二：旋转后和X不同：不能都是0,1,8--->必须包含2569
 * 总条件：必须包含2569，不能包含347
 * 求解：
 * 从1到N中有多少个数X是好数。
 * N的取值范围：[1,10000]
 * 例：
 * [1,10]中有四个好数，2,5,6,9（4）
 * [10,20]中有五个好数，12,15,16,19,20（9）
 * [20,30]中有五个好数，21,22,25,26,28,29（15）
 */

/*
总结：
优点：外层for循环，内层while循环。
内化
用一个for循环即可，里边用while循环。
用双层for循环效率低。
 */

public class A788 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a788);

        int res = rotatedDigits(30);
        Log.d("aaa", "onCreate:res= " + res);
    }

    /**
     * 别人解法--最优解
     * 优点：1个for循环
     */
    public int rotatedDigits(int N) {
        int count = 0;
        for (int i = 1; i <= N; i++) {
            int n = i, flag = 0;
            while (n != 0) {
                int x = n % 10;
                if (x == 3 || x == 4 || x == 7) break;
                if (flag != 0 || x == 2 || x == 5 || x == 6 || x == 9) flag = 1;
                n /= 10;
            }
            if (n == 0 && flag != 0) count++;
        }
        return count;
    }

    private boolean isGoodNum(int number) {
        char[] rs = {'0', '1', '5', ' ', ' ', '2', '9', ' ', '8', '6'};
        String num = String.valueOf(number);
        int len = num.length();
        int same = 0;
        for (int i = 0; i < len; i++) {
            char c = num.charAt(i);

            if (rs[c - '0'] == ' ')
                return false;

            if (rs[c - '0'] == c)
                same++;
        }
        return same == len ? false : true;
    }


    /**
     * 我的解法1
     * ![8fffec1bd0fa3f8befecaf512e886ce0.png](evernotecid://92A5B156-9259-4F1C-8EE5-D7D49B7E673F/appyinxiangcom/19433878/ENResource/p4731)
     * 缺点：双层for循环
     * 时间复杂度：
     * O(n^2)
     */
    public int rotatedDigits1(int N) {
        int count = 0;
        for (int i = 1; i <= N; i++) {
            if (isGoodNum1(i)) {
                count++;
            }
        }
        return count;
    }

    private boolean isGoodNum1(int num) {
        int len = String.valueOf(num).length();
        int count = 0;

        for (int i = len; i > 0; i--) {
            int nu = (int) ((num % Math.pow(10, i)) / Math.pow(10, (i - 1)));

            if (nu == 3 || nu == 4 || nu == 7) {
                return false;
            }

            if (nu == 0 || nu == 1 || nu == 8) {
                count++;
            }
        }

        if (len == count) {
            return false;
        }

        return true;
    }

    /**
     * 我的解法2-不对
     * 缺点：双层for循环
     */
    public int rotatedDigits2(int N) {
        int count = 0;
        for (int i = 1; i <= N; i++) {
            if (isGoodNum2(i)) {
                count++;
            }
        }
        return count;
    }

    private boolean isGoodNum2(int num) {
        Set<Character> set = new HashSet<Character>();
        for (char a : "2569".toCharArray()) {
            set.add(a);
        }

        Set<Character> noSet = new HashSet<Character>();
        for (char b : "347".toCharArray()) {
            noSet.add(b);
        }

        for (char c : String.valueOf(num).toCharArray()) {
            //这里错误
            if (set.contains(c) && (!noSet.contains(c))) {
                return true;
            }
        }
        return false;
    }
}
