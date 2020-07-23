package com.example.ruru.sophiesblog.data_algorithm.mathmatics;

import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 时间复杂度：T(N)=O(N)
 */
public class A728 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a728);

        int left = 1;
        int right = 22;
        List<Integer> list = selfDividingNumbers(left, right);
        Log.d(getClass().getName(), "onCreate:res= " + list.toString());//[1,2,3,4,5,6,7,8,9,11,12,15,22]
    }

    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = left; i <= right; i++) {
            if (isSelfDivisor(i))
                list.add(i);
        }
        return list;
    }

    /**
     * 13ms
     */
    private boolean isSelfDivisor(int num) {
        String str = String.valueOf(num);
        int n = str.length();
        int j = 0;
        while (j < n) {
            int c = (int) (num % Math.pow(10, j + 1) / Math.pow(10, j));
            if (c == 0 || num % c != 0) {
                return false;
            }
            j++;//没有j++就会一直while循环，超出时间限制。
        }
        return true;
    }

    /**
     * 7ms
     */
    private boolean isSelfDivisor1(int num) {
        String str = String.valueOf(num);
        int n = str.length();
        int count = 0;
        for (int j = 0; j < n; j++) {
            int c = str.charAt(j) - '0';
            if (c != 0 && num % c == 0)
                count++;
        }
        return count == n ? true : false;
    }

    /**
     * 4ms
     */
    private boolean isSelfDivisor2(int num) {
        String str = String.valueOf(num);
        int n = str.length();
        for (int j = 0; j < n; j++) {
            int c = str.charAt(j) - '0';
            if (c == 0 || num % c != 0)
                return false;
        }
        return true;
    }
}
