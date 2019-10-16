package com.example.ruru.sophiesblog.java.threaddemo.syn.ClassAndObject;

import android.util.Log;

import com.example.ruru.sophiesblog.java.threaddemo.tools.SleepTools;

/**
 * Created by SophieRu on 2019/7/22
 */
@Deprecated
public class Instance {

    public synchronized void instance() {
        initSleep();
    }

    public synchronized void instance1() {
        initSleep();
    }

    private void initSleep() {
        SleepTools.second(3);
        Log.d(getClass().getName(), "synInstance is going ");
        SleepTools.second(3);
        Log.d(getClass().getName(), "synInstance is ended ");
    }
}
