package com.example.ruru.sophiesblog.java.collection;

import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

import androidx.appcompat.app.AppCompatActivity;

public class ClientCollection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client6);


        Collection collection;


        /* list */
        ArrayList arrayList;
        LinkedList linkedList;


        /* set */
        HashSet set;
        TreeSet treeSet;


        /* map */
        HashMap hashMap;
        Hashtable hashtable;
        LinkedHashMap linkedHashMap;
        ConcurrentHashMap concurrentHashMap;


        /* test */
        int[] arr = new int[]{1, 2, 3, 4};
        int len = arr.length;
        int i = 0;
        for (; i < len; i++) {

        }
        Log.d(getClass().getName(), "onCreate: res i =" + i);//答案为4


        /*测试string去重*/
        String s = "abcabcdefac";
        LinkedHashSet set1 = new LinkedHashSet();
        int len1 = s.length();
        for (int j = 0; j < len1; j++) {
            set1.add(s.charAt(j));
        }
        Log.d(getClass().getName(), "onCreate:res= " + set1.toString());//[a, b, c, d, e, f]
        StringBuilder sbd = new StringBuilder();//单线程
        for (Object ss : set1) {
            sbd.append(ss);
        }
        Log.d(getClass().getName(), "onCreate:res= " + sbd.toString());

    }
}
