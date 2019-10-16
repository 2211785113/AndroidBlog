package com.example.ruru.sophiesblog.java.threaddemo.volatiles;

/**
 * Created by SophieRu on 2019/7/26
 */
public class VolatileCase {
    public static volatile boolean ready;//注意看有没有关键字的区别（适用场景：写一次读多次。本例：写一次。）
    public static int number;

    public static class PrintThread extends Thread {
        @Override
        public void run() {
            System.out.println("PrintThread is running...");
            System.out.println("number outer =" + number);
            System.out.println("ready = " + ready);
            while (!ready) {
                System.out.println("number inner =" + number);
            }
        }
    }
}