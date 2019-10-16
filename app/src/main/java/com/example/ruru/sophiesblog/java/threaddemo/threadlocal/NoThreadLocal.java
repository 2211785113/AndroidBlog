package com.example.ruru.sophiesblog.java.threaddemo.threadlocal;

import android.util.Log;

public class NoThreadLocal {

    static Integer count = new Integer(1);

    /**
     * 运行3个线程
     */
    public void StartThreadArray() {
        Thread[] runs = new Thread[3];
        for (int i = 0; i < runs.length; i++) {
            System.out.println("for循环" + i);
            runs[i] = new Thread(new TestTask(i));
        }
        for (int i = 0; i < runs.length; i++) {
            System.out.println("第" + i + "次");
            runs[i].start();
        }
    }

    public static class TestTask implements Runnable {
        int id;

        public TestTask(int id) {
            this.id = id;
        }

        public void run() {
            System.out.println(Thread.currentThread().getName() + "count before = " + count);
            count = count + id;
            System.out.println(Thread.currentThread().getName() + "count after = " + count);
        }
    }
}
