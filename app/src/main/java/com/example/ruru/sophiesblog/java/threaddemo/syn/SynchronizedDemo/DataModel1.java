package com.example.ruru.sophiesblog.java.threaddemo.syn.SynchronizedDemo;

/**
 * Created by SophieRu on 2019/7/21
 */
public class DataModel1 {

    //单例：整个应用只有一个实例
    private static DataModel1 dataModel = new DataModel1();

    private Object obj = new Object();//作为一个锁

    private DataModel1() {

    }

    public static DataModel1 getInstance() {
        return dataModel;
    }

    //域，构造方法，get/set方法
    private int count;

    public void setCount(int count) {
        this.count = count;
    }

    /**
     因为多个线程同时访问同一个变量count，导致count变小。
     对象锁：方法上锁，锁的是对象
     第一种方式：对象锁：锁的是这个类的当前实例，即this。
     ⚠️因为是dataModel.initCount，所以是对象锁。
     */
//    public synchronized void initCount() {
//        count++;
//    }

    /**
     第二种方式：对象锁，锁了一个obj对象
     */
//    public void initCount() {
//        synchronized (obj) {
//            count++;
//        }
//    }

    /**
     第三种方式：对象锁：锁的是this
     */
    public void initCount() {
        synchronized (this) {
            count++;
        }
    }

    public int getCount() {
        return count;
    }

}
