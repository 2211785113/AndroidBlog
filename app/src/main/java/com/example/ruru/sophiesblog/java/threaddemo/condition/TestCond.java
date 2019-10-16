package com.example.ruru.sophiesblog.java.threaddemo.condition;

/**
 * Lock和Condition实现等待通知
 */
public class TestCond {
    private static ExpressCond express = new ExpressCond(0, ExpressCond.CITY);

    /*检查里程数变化的线程,不满足条件，线程一直等待*/
    public static class CheckKm extends Thread {
        @Override
        public void run() {
            express.waitKm();
        }
    }

    /*检查地点变化的线程,不满足条件，线程一直等待*/
    public static class CheckSite extends Thread {
        @Override
        public void run() {
            express.waitSite();
        }
    }
}
