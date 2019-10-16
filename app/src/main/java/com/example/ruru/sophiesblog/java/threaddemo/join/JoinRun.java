package com.example.ruru.sophiesblog.java.threaddemo.join;

/**
 * Created by SophieRu on 2019/7/6
 * 任务队列
 */
public class JoinRun implements Runnable {

    private Thread thread;//用来插队的线程

    public JoinRun(Thread thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        System.out.println(thread.getName() + " will be join before " + Thread.currentThread().getName());
        try {
            thread.join();//b线程要插入a线程中顺序执行
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " termited.");
    }
}
