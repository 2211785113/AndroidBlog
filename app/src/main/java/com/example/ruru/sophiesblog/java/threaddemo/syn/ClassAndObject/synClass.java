package com.example.ruru.sophiesblog.java.threaddemo.syn.ClassAndObject;

import android.util.Log;

import com.example.ruru.sophiesblog.java.threaddemo.tools.SleepTools;

/**
 * Created by SophieRu on 2019/7/22
 */
@Deprecated
public class synClass extends Thread {

    @Override
    public void run() {
        Log.d(getClass().getName(), "run: test class is running");
        synClass();
    }

    private synchronized void synClass() {
        initSleep();
    }


    private Object obj = new Object();

    private void synStaticObject() {
        synchronized (obj) {
            initSleep();
        }
    }


    private void initSleep() {
        SleepTools.second(1);
        Log.d(getClass().getName(), "synClass: going...");
        SleepTools.second(1);
        Log.d(getClass().getName(), "synClass: end...");
    }
}
