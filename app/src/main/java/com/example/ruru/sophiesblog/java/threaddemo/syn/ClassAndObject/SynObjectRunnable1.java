package com.example.ruru.sophiesblog.java.threaddemo.syn.ClassAndObject;

import android.util.Log;

/**
 * Created by SophieRu on 2019/7/22
 */
@Deprecated
public class SynObjectRunnable1 implements Runnable {

    private Instance instance;

    public SynObjectRunnable1(Instance instance) {
        this.instance = instance;
    }

    @Override
    public void run() {
        Log.d(getClass().getName(), "run: " + instance);
        instance.instance();
    }
}
