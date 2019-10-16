package com.example.ruru.sophiesblog.java.threaddemo.startAndRun;

/**
 * Created by SophieRu on 2019/7/6
 */
public class ThreadRun extends Thread {

    @Override
    public void run() {
        //打印结果：main主线程
        System.out.println("thread name=" + Thread.currentThread().getName());
    }
}
