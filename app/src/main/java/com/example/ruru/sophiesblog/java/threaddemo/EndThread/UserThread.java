package com.example.ruru.sophiesblog.java.threaddemo.EndThread;

/**
 * Created by SophieRu on 2019/7/6
 */
public class UserThread extends Thread {
    @Override
    public void run() {
        super.run();

        String threadName = Thread.currentThread().getName();

        //结果：线程从app启动开始一直在运行
//        while (true) {
//            System.out.println(threadName + "is running");
//        }


        //结果：开始isInterrupted=false，线程运行5s后停止，isInterrupted=true
//        System.out.println("isInterrupted=" + isInterrupted());
//        while (!isInterrupted()) {
//            System.out.println(threadName + "is running");
//        }
//        System.out.println("isInterrupted=" + isInterrupted());


        //结果：开始isInterrupted=false，线程运行5s后停止，isInterrupted=false
        System.out.println("isInterrupted=" + isInterrupted());
        while (!Thread.interrupted()) {
            System.out.println(threadName + "is running");
        }
        System.out.println("isInterrupted=" + isInterrupted());
    }
}
