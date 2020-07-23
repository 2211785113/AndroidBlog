package com.example.ruru.sophiesblog.java.threaddemo.threadpool;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 用3个线程实现5个任务
 * Created by SophieRu on 2019/7/25
 */
public class MyThreadPool {

    //线程池中默认线程的个数为5
    private static int WORK_NUM = 5;
    //队列默认任务个数为100
    private static int TASK_COUNT = 100;

    //工作线程组
    private WorkThread[] workThreads;

    //任务队列，作为一个缓冲
    private final BlockingQueue<Runnable> taskQueue;
    //用户在构造这个函数，希望启动的线程数
    private final int worker_num;

    public MyThreadPool() {
        this(WORK_NUM, TASK_COUNT);
    }

    public MyThreadPool(int worker_num, int taskCount) {
        if (worker_num <= 0)
            worker_num = WORK_NUM;
        if (taskCount <= 0)
            taskCount = TASK_COUNT;
        this.worker_num = worker_num;
        taskQueue = new ArrayBlockingQueue<>(taskCount);
        workThreads = new WorkThread[worker_num];
        for (int i = 0; i < worker_num; i++) {
            workThreads[i] = new WorkThread();
            workThreads[i].start();
        }
//        int count = Runtime.getRuntime().availableProcessors();//取CPU的逻辑核心数
    }

    // 执行任务，其实只是把任务加入任务队列，什么时候执行由线程池管理器决定
    public void execute(Runnable task) {
        try {
            taskQueue.put(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 销毁线程池，该方法保证在所有任务都完成的情况下才销毁所有线程，否则等待任务完成才销毁
    public void destroy() {
        // 工作线程停止工作，且置为null
        System.out.println("ready close pool...");
        for (int i = 0; i < worker_num; i++) {
            workThreads[i].stopWorker();
            workThreads[i] = null;// help gc
        }
        taskQueue.clear(); // 清空任务队列
    }

    @Override
    public String toString() {
        return "WorkThread number:" + worker_num + " wait task number:" + taskQueue.size();
    }

    /**
     * 内部类，工作线程即子线程。有点不太理解？？？
     */
    private class WorkThread extends Thread {
        @Override
        public void run() {
            Runnable r = null;
            try {
                while (!isInterrupted()) {
                    r = taskQueue.take();
                    if (r != null) {
                        System.out.println(getId() + " ready exec：" + r);//第二个打印
                        r.run();
                    }
                    r = null;//help gc
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void stopWorker() {
            interrupt();
        }
    }
}
