package com.example.ruru.sophiesblog.java.string;

import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 判断String对阵的字符串是否相同。
 */
public class AStringSame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_astring_same);

        String str = "helloh";
        boolean res = isSame(str);
        Log.d(getClass().getName(), "onCreate:res= " + res);//false
    }

    private boolean isSame(String str) {
        boolean flag = true;
        char[] chars = str.toCharArray(); //将string字符串转化成字符数组
        for (int start = 0, end = chars.length - 1; start <= end; start++, end--) {
            //只要有一个不等就flag=false
            if (chars[start] != chars[end]) {
                flag = false;
            }
        }
        return flag;
    }
}
