package com.example.ruru.sophiesblog.java.threaddemo.syn.SynchronizedDemo;

/**
 * 对象锁
 */
public class SynTest {

    private long count = 0;
    private Object obj = new Object();//作为一个锁

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    //count进行累加
    public void incCount() {
        synchronized (obj) {
            count++;
        }
    }

    public synchronized void incCount2() {
        count++;
    }

    //count进行累加
    public void incCount3() {
        synchronized (this) {
            count++;
        }
    }

    //线程
    private static class Count extends Thread {

        private SynTest simplOper;

        public Count(SynTest simplOper) {
            this.simplOper = simplOper;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                //因为是simplOper.incCount,所以是对象锁。
                simplOper.incCount();//count = count+10000
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SynTest simplOper = new SynTest();
        //启动两个线程
        Count count1 = new Count(simplOper);
        Count count2 = new Count(simplOper);
        count1.start();
        count2.start();
        Thread.sleep(50);
        System.out.println(simplOper.count);//20000
    }
}
