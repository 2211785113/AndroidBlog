package com.example.ruru.sophiesblog.data_algorithm.algorithm.other;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * ### 总结
 * <p>
 * 我的解法：target字母插入数组(set)集合，然后循环数组找target后的那个即为结果。
 * 别人的解法：有序数组直接比大小，大于则返回结果，否则返回第一个。
 * <p>
 * ### 关键:
 * char类型可以比大小，比的是字符的ASCII码。
 * 看清题目：有序
 */
public class A744 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afind_char);

        char[] letters = new char[]{'c', 'f', 'j'};
        char target = 'a';
        char res = nextGreatestLetter(letters, target);

        Log.d(getClass().getName(), "onCreate:res= " + res);
    }

    /**
     * 别人的解法
     */
    private char nextGreatestLetter(char[] letters, char target) {
        for (char c : letters) {
            if (c > target) {
                return c;
            }
        }
        return letters[0];//如果都没有比target大的话，直接返回第一个字母
    }

    /**
     * 我的解法
     * <p>
     * 出现问题1：
     * java.lang.ClassCastException: java.lang.Object[] cannot be cast to java.lang.String[]
     * 出现问题2：
     * 字母顺序循环造成指针越界异常，例子后两个不满足，加个判断条件
     */
    private char nextGreatestLetterOne(char[] letters, char target) {
        Set<String> set = new HashSet<String>();
        for (char c : letters) {
            set.add(c + "");
        }
        set.add(target + "");
        String[] newLetters = set.toArray(new String[set.size()]);
        Arrays.sort(newLetters);
        int len = newLetters.length;
        for (int i = 0; i < len; i++) {
            if (newLetters[i].equals(target + "")) {
                return i == len - 1 ? newLetters[0].charAt(0) : newLetters[i + 1].charAt(0);
            }
        }
        return ' ';
    }
}
