package com.example.ruru.sophiesblog.java.threaddemo.syn.SynchronizedDemo;

import android.util.Log;

/**
 * Created by SophieRu on 2019/7/11
 */
@Deprecated
public class SynThread1 extends Thread {

    private static int count = 0;

    @Override
    public synchronized void run() {
        Log.d(getClass().getName(), Thread.currentThread().getName() + "  run:count 1=  " + count);
        for (int i = 0; i < 10000; i++) {
            count++;
        }
        Log.d(getClass().getName(), Thread.currentThread().getName() + "  run:count 2=  " + count);
    }

    public static int getCount() {
        return count;
    }
}
