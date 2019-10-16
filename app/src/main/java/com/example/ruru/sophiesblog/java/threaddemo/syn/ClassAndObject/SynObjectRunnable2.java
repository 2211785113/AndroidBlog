package com.example.ruru.sophiesblog.java.threaddemo.syn.ClassAndObject;

import android.util.Log;

/**
 * Created by SophieRu on 2019/7/23
 */
@Deprecated
public class SynObjectRunnable2 implements Runnable {

    private Instance instance;

    public SynObjectRunnable2(Instance instance) {
        this.instance = instance;
    }

    @Override
    public void run() {
        Log.d(getClass().getName(), "run: " + instance);
        instance.instance1();
    }
}
