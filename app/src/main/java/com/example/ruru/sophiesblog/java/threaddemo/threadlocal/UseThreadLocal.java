package com.example.ruru.sophiesblog.java.threaddemo.threadlocal;

public class UseThreadLocal {

    //静态变量
    static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 1;
        }
    };

    public void StartThreadArray() {
        //定义3个线程
        Thread[] runs = new Thread[3];
        //运行3个线程
        for (int i = 0; i < runs.length; i++) {
            runs[i] = new Thread(new TestThread(i));
        }
        //启动3个线程
        for (int i = 0; i < runs.length; i++) {
            runs[i].start();
        }
    }

    /**
     * 运行线程：将ThreadLocal变量的值变化，并写回。
     */
    public static class TestThread implements Runnable {
        int id;

        public TestThread(int id) {
            this.id = id;
        }

        public void run() {
            Integer s = threadLocal.get();//值为initialValue=1
            System.out.println(Thread.currentThread().getName() + " before s = " + threadLocal.get());//1，1，1
            s = s + id;//id分别为0/1/2，所以s结果分别为1/2/3
            threadLocal.set(s);
            System.out.println(Thread.currentThread().getName() + " after s = " + threadLocal.get());//1，2，3

            //threadLocal.remove();
        }
    }
}
