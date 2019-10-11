package com.example.ruru.sophiesblog.java.sourceCode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;

public class AStringClass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_string);

        String a = "123456";
        a.substring(0, 3);
        Log.d(getClass().getName(), "onCreate:res= " + a);//答案为123456

        String aa = "123456";
        String res = a.substring(0, 3);
        Log.d(getClass().getName(), "onCreate: res= " + res);//123

        /* ==比较的是对象的堆内存地址 */
        //JVM处理String的特性：如果字符串缓冲池中已经存在这个对象，就将这个对象返回出来。所以结果位true。
        String a1 = "a" + "b" + 1;
        String b1 = "ab1";
        Log.d(getClass().getName(), "onCreate:res= " + (a1 == b1));//true

        //JVM处理String的特性：new String是要开辟一个新的内存空间，所以返回false。
        String a2 = new String("ab1");
        String b2 = "ab1";
        Log.d(getClass().getName(), "onCreate:res= " + (a2 == b2));//false

    }
}
