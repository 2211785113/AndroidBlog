package com.example.ruru.sophiesblog.data_algorithm.algorithm.other.string;

import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 题目：A和B是给定的两个小写字母即a和b
 * A交换两个字母就能等于B，则返回true。
 */
public class A859 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a859);

        String A = "aaa";
        String B = "aaa";
        boolean res = buddyStrings(A, B);
        Log.d(getClass().getName(), "onCreate:res= " + res);
    }

    /**
     * 2ms  37.6MB
     * 时间复杂度：O(N),N为A和B的长度
     * 空间复杂度：O(1)
     * <p>
     * A和B相等：aaa,aaa为true，abc,abc为false
     * A和B不等：abc,bac为true，aaaaaaabc,aaaaaaacb为true
     */
    public boolean buddyStrings(String A, String B) {

        if (A.length() != B.length())
            return false;

        if (A.equals(B)) {
            int[] count = new int[26];
            for (int i = 0; i < A.length(); ++i) {
                count[A.charAt(i) - 'a']++;
            }

            //aaa结果count[0]=3,满足,返回true。
            //abc结果count[0]=1,count[1]=1,count[2]=1,不满足,返回false。
            for (int c : count)
                if (c > 1) return true;

            return false;
        } else {
            //aaaaaaabc和aaaaaaacb，true
            //aaaaaaabc和aaaaaaaac，false
            int first = -1, second = -1;
            for (int i = 0; i < A.length(); ++i) {
                if (A.charAt(i) != B.charAt(i)) {
                    if (first == -1)
                        first = i;
                    else if (second == -1)
                        second = i;
                    else
                        return false;
                }
            }

            return (second != -1 && A.charAt(first) == B.charAt(second) && A.charAt(second) == B.charAt(first));
        }
    }
}
