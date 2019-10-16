package com.example.ruru.sophiesblog.java.threaddemo.StopThread;

import android.util.Log;

/**
 * Created by SophieRu on 2019/7/21
 */
public class UsersThread2 extends Thread {

    int i = 0;
    int j = 0;

    @Override
    public void run() {
        synchronized (this) {
            ++i;

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            ++j;
        }
    }

    public void print() {
        Log.d(getClass().getName(), "print: i=" + i + " j=" + j);
    }
}
