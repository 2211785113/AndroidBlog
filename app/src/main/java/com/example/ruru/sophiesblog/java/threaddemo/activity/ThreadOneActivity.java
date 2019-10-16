package com.example.ruru.sophiesblog.java.threaddemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.ruru.sophiesblog.R;


public class ThreadOneActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threadone);

        /**
         * ANR：耗时操作
         * 新建线程
         * 终止线程：interrupt/stop
         * 启动线程：start/run
         * join：插入线程顺序执行
         * sleep：抛出异常
         */

        /**
         * 起点：耗时操作time consuming
         *
         * 先休眠5s（没有任何东西打断）--textView显示你好--主线程更新UI，10s后textView显示阳光
         */
        /*textView = findViewById(R.id.tv);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(getClass().getName(), "run: 准备更新text");
                textView.setText("阳光");
                Log.d(getClass().getName(), "run: 更新text完成");
            }
        }, 10000);

        try {
            Log.d(getClass().getName(), "onCreate:准备sleep5秒 ");
            Thread.sleep(5000);
            Log.d(getClass().getName(), "onCreate:sleep5秒完成 ");
        } catch (InterruptedException e) {
        }

        textView.setText("你好");*/


        // 新建线程：
        // 没有返回值：扩展Thread类；实现Runnable接口。
        // 有返回值：实现Callable接口。
       /* UseThread threadOne = new UseThread();
        threadOne.start();

        UseRun useRun = new UseRun();
        new Thread(useRun).start();

        FutureTask<String> futureTask = new FutureTask<>(new UseCall());
        new Thread(futureTask).start();
        try {
            System.out.println(futureTask.get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/


        //终止线程
        /*UserThread userThread = new UserThread();
        userThread.start();
        try {
            Thread.sleep(5000);//主线程休眠5s
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        userThread.interrupt();//终止子线程*/


        // 停止线程
        // 如果是interrupt两个都会输出；
        // stop：只输出了线程里第一个isInterrupted的值，没有输出第二个isInterrupted的值。
        /*UsersThread1 usersThread1 = new UsersThread1();
        usersThread1.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        usersThread1.stop();*/

        // 如果是interrupt，最后会输出i=1，j=1；
        // 但是如果是stop，最后啥都不会输出。
        /*UsersThread2 usersThread2 = new UsersThread2();
        usersThread2.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        usersThread2.stop();
        usersThread2.print();*/


        //startAndRun
        /*ThreadRun threadRun = new ThreadRun();
        threadRun.setName("run thread");
        threadRun.run();//打印结果为：main===调用了一个普通方法
        threadRun.start();//打印结果为：run thread===启动了一个线程*/


        //join
        /*Thread previous = Thread.currentThread();
        //0：现在的线程要插入新线程中执行

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new JoinRun(previous), String.valueOf(i));//Thread为新线程，其他线程插入
            thread.start();
            previous = thread;
        }

        try {
            Thread.sleep(2000);//主线程休眠2s
        } catch (InterruptedException e) {
        }
        System.out.println(Thread.currentThread().getName() + " terminated.");*/


        //sleep抛出异常但是不中断线程
        /*UseSleep useSleep = new UseSleep();
        useSleep.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }
        useSleep.interrupt();*/
    }
}
