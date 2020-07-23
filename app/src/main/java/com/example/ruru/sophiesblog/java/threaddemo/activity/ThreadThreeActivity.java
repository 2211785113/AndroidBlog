package com.example.ruru.sophiesblog.java.threaddemo.activity;

import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;
import com.example.ruru.sophiesblog.java.threaddemo.volatiles.DataModel;
import com.example.ruru.sophiesblog.java.threaddemo.volatiles.NotSafeThread;

import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;

public class ThreadThreeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threadthree);

        /**
         * 手写线程池
         */
        /*MyThreadPool pool = new MyThreadPool(3, 0);//启动3个工作线程
        pool.execute(new MyTask("testA"));//线程池放入5个任务
        pool.execute(new MyTask("testB"));
        pool.execute(new MyTask("testC"));
        pool.execute(new MyTask("testD"));
        pool.execute(new MyTask("testE"));
        System.out.println("pool = " + pool);// 第一个打印
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pool.destroy();//所有线程都执行完才destroy   // 第三个打印
        System.out.println(pool);// 第四个打印*/


        /**
         * 使用Asynctask
         */
        /*URL url = null;
        try {
            url = new URL("https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E5%B0%8F%E7%8C%AB&hs=2&pn=6&spn=0&di=143000&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&ie=utf-8&oe=utf-8&cl=2&lm=-1&cs=2671739265%2C1202249534&os=3805227444%2C574498523&simid=0%2C0&adpicid=0&lpn=0&ln=30&fr=ala&fm=&sme=&cg=&bdtype=0&oriquery=%E5%B0%8F%E7%8C%AB&objurl=http%3A%2F%2Fpic.90sjimg.com%2Fdesign%2F00%2F21%2F84%2F57%2F58c1706029bbc.png&fromurl=ippr_z2C%24qAzdH3FAzdH3Flafij3t_z%26e3Bv54AzdH3Ff7vwtAzdH3F8namlnca_z%26e3Bip4s&gsm=0&islist=&querylist=");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        MyAsyncTask myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute(url);//将图片url传入doInBackground中。*/


        /**
         volatile关键字：
         优点：保证数据的可见性。

         ready不+volatile关键字：
         PrintThread is running...
         number=0
         number=0
         number=51
         main is ended

         ready+volatile关键字：
         PrintThread is running...
         number=0
         number=0
         main is ended
         */
        /*new VolatileCase.PrintThread().start();
        SleepTools.millisecond(1);
        VolatileCase.number = 51;
        VolatileCase.ready = true;
        SleepTools.millisecond(5);
        System.out.println("main is ended");*/


        /**
         * volatile关键字：
         * 缺点：不能保证数据的安全性。
         */
        DataModel dataModel = DataModel.getInstance();
        dataModel.setCount(0);
        NotSafeThread thread1 = new NotSafeThread(dataModel);
        NotSafeThread thread2 = new NotSafeThread(dataModel);
        thread1.start();
        thread2.start();
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d(getClass().getName(), "onCreate:rescount= " + dataModel.getCount());
        System.out.println("rescount=" + dataModel.getCount());
    }

    //任务类：有点不太理解？？？
    class MyTask implements Runnable {

        private String name;
        private Random r = new Random();

        public MyTask(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public void run() {// 执行任务
            try {
                Thread.sleep(r.nextInt(1000) + 2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println(Thread.currentThread().getId() + " sleep Interrupted " + Thread.currentThread().isInterrupted());
            }
        }
    }
}
