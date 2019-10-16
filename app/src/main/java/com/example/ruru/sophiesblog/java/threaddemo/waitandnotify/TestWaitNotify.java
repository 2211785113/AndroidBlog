package com.example.ruru.sophiesblog.java.threaddemo.waitandnotify;

import android.util.Log;

/**
 * 测试wait/notify/notifyAll
 */
public class TestWaitNotify {

    private static ExpressBean express = new ExpressBean(0, ExpressBean.CITY);

    /*检查里程数变化的线程,不满足条件，线程一直等待*/
    public static class CheckKm extends Thread {
        @Override
        public void run() {
            Log.d(getClass().getName(), "CheckKm is running... ");
            express.waitKm();
        }
    }

    /*检查地点变化的线程,不满足条件，线程一直等待*/
    public static class CheckSite extends Thread {
        @Override
        public void run() {
            Log.d(getClass().getName(), "CheckSite is running... ");
            express.waitSite();
        }
    }
}
