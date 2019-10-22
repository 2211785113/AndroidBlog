package com.example.ruru.sophiesblog.java.threaddemo.syn.ClassAndObject;

import android.util.Log;

import com.example.ruru.sophiesblog.java.threaddemo.tools.SleepTools;

/**
 * 类锁
 * Created by SophieRu on 2019/7/23
 */
public class SynClaAndIns {

    /**
     * 类锁
     */
    //使用类锁的线程
    public static class SynClass extends Thread {
        @Override
        public void run() {
            Log.d(getClass().getName(), "TestClass is running... ");
            synClass();
        }
    }

    //类锁：实际是锁类的class对象
    private static synchronized void synClass() {
        SleepTools.second(1);
        Log.d("SynClaAndIns", "synClass is going... ");
        SleepTools.second(1);
        Log.d("SynClaAndIns", "synClass end... ");
    }

    private static Object obj = new Object();

    private void synStaticObject() {
        synchronized (obj) {//类似于类锁，obj在全虚拟机中只有一份。
            SleepTools.second(1);
            Log.d("SynClaAndIns", "synStaticObject is going... ");
            SleepTools.second(1);
            Log.d("SynClaAndIns", "synStaticObject end... ");
        }
    }


    /**
     * 对象锁
     */
    //使用对象锁的线程
    public static class InstanceSyn implements Runnable {
        private SynClaAndIns synClaAndIns;

        public InstanceSyn(SynClaAndIns synClaAndIns) {
            this.synClaAndIns = synClaAndIns;
        }

        @Override
        public void run() {
            Log.d(getClass().getName(), "TestInstance is running... ");
            synClaAndIns.instance();
        }
    }

    //使用对象锁的线程
    public static class Instance2Syn implements Runnable {
        private SynClaAndIns synClaAndIns;

        public Instance2Syn(SynClaAndIns synClaAndIns) {
            this.synClaAndIns = synClaAndIns;
        }

        @Override
        public void run() {
            Log.d(getClass().getName(), "TestInstance2 is running... ");
            synClaAndIns.instance2();
        }
    }

    //锁对象
    private synchronized void instance() {
        SleepTools.second(3);
        Log.d(getClass().getName(), "synInstance is going... ");
        SleepTools.second(3);
        Log.d(getClass().getName(), "synInstance ended... ");
    }

    //锁对象
    private synchronized void instance2() {
        SleepTools.second(3);
        Log.d(getClass().getName(), "synInstance2 is going... ");
        SleepTools.second(3);
        Log.d(getClass().getName(), "synInstance2 ended ... ");
    }
}
