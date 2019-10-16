package com.example.ruru.sophiesblog.java.threaddemo.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by SophieRu on 2019/7/24
 */
public class LockDemo {
    private int count = 0;
    private Lock lock = new ReentrantLock(true);//可重入锁

    //显示锁
    public void incr() {
        lock.lock();//加锁
        try {
            count++;//执行语句
        } finally {
            lock.unlock();//释放锁。因为可能会抛出异常导致代码不执行，所以放在finally里边。
        }
    }

    //同步锁（测试可重入锁）加了锁又获取锁，这个锁没有实现可重入，导致自己把自己锁死。
    public synchronized void incr2() {
        count++;
        incr2();
    }

    public static void main(String[] args) {
        LockDemo lockDemo = new LockDemo();
    }
}
