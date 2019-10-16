package com.example.ruru.sophiesblog.java.threaddemo.threadlocal;

public class UseThreadLocal {

    static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 1;
        }
    };

    /**
     * 运行3个线程
     */
    public void StartThreadArray() {
        Thread[] runs = new Thread[3];
        for (int i = 0; i < runs.length; i++) {
            System.out.println("for循环" + i);
            runs[i] = new Thread(new TestThread(i));
        }
        for (int i = 0; i < runs.length; i++) {
            System.out.println("第" + i + "次");
            runs[i].start();
        }
    }

    /**
     * 类说明：测试线程，线程的工作是将ThreadLocal变量的值变化，并写回，
     * 看看线程之间是否会互相影响
     */
    public static class TestThread implements Runnable {
        int id;

        public TestThread(int id) {
            this.id = id;
        }

        public void run() {
            Integer s = threadLocal.get();
            System.out.println(Thread.currentThread().getName() + " before s = " + threadLocal.get());
            s = s + id;
            threadLocal.set(s);
            System.out.println(Thread.currentThread().getName() + " after s = " + threadLocal.get());

            //threadLocal.remove();
        }
    }
}
