package com.example.ruru.sophiesblog.java.threaddemo.threadlocal;

/**
 * 多个线程共同持有一个静态变量
 */
public class NoThreadLocal {

    //静态变量
    static Integer count = new Integer(1);

    /**
     * 运行3个线程
     */
    public void StartThreadArray() {
        Thread[] runs = new Thread[3];
        for (int i = 0; i < runs.length; i++) {
            runs[i] = new Thread(new TestTask(i));
        }
        for (int i = 0; i < runs.length; i++) {
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
