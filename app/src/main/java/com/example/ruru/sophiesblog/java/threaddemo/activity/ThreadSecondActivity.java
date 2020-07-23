package com.example.ruru.sophiesblog.java.threaddemo.activity;

import android.os.Bundle;

import com.example.ruru.sophiesblog.R;
import com.example.ruru.sophiesblog.java.threaddemo.syn.SynchronizedDemo.DataModel1;
import com.example.ruru.sophiesblog.java.threaddemo.syn.SynchronizedDemo.SynThread2;
import com.example.ruru.sophiesblog.java.threaddemo.threadlocal.UseThreadLocal;
import com.example.ruru.sophiesblog.java.threaddemo.waitandnotify.ExpressBean;
import com.example.ruru.sophiesblog.java.threaddemo.waitandnotify.TestWaitNotify;

import androidx.appcompat.app.AppCompatActivity;

public class ThreadSecondActivity extends AppCompatActivity {

    private static int count;
    private static final int minthreadCount = 3;//最少线程数
    private static final int readWriteRatio = 10;//读写线程的比例

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threadsecond);

        /**
         * synchronized：
         同步syn：错误❌
         打印结果：
         Thread-6  run:count 1=  0
         Thread-5  run:count 1=  3316
         Thread-6  run:count 2=  10581
         Thread-5  run:count 2=  13897
         结果：说明上锁不对。
         问题：count应该在外层，不应该在内层。
         */
        /*SynThread1 synTheada = new SynThread1();
        SynThread1 synTheadb = new SynThread1();
        synTheada.start();
        synTheadb.start();
        try {
            Thread.sleep(50);//运行一个线程一般5ms，100个线程500ms。
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("SynThread1 getCount=" + SynThread1.getCount());*/


        /**
         同步syn：对象锁。
         */
        /*DataModel1 dataModel = DataModel1.getInstance();
        dataModel.setCount(0);//可以不写这句话，因为count默认就为0
        SynThread2 synThreadaa = new SynThread2(dataModel);
        SynThread2 synThreadbb = new SynThread2(dataModel);
        synThreadaa.start();
        synThreadbb.start();
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("SynThread2 getCount=" + dataModel.getCount());*/


        /**
         对象锁与类锁：
         不同的对象锁：不受影响，各执行各的
         相同的对象锁：顺序执行。
         同一个类锁：顺序执行。
         */
       /* SynClaAndIns synClaAndIns = new SynClaAndIns();
        Thread t1 = new Thread(new SynClaAndIns.InstanceSyn(synClaAndIns));

        SynClaAndIns synClaAndIns2 = new SynClaAndIns();
        Thread t2 = new Thread(new SynClaAndIns.Instance2Syn(synClaAndIns2));

        t1.start();
        t2.start();

        SynClaAndIns.SynClass synClass = new SynClaAndIns.SynClass();
        synClass.start();
        SleepTools.second(1);*/


        /**
         * ThreadLocal：
         */
        /*NoThreadLocal noThreadLocal = new NoThreadLocal();
        noThreadLocal.StartThreadArray();*/

        /*UseThreadLocal useThreadLocal = new UseThreadLocal();
        useThreadLocal.StartThreadArray();*/

//==================

        /**
         * wait/notify：
         解决问题：A线程不满足条件进入阻塞状态，需要其他线程来唤醒。
         * 流程：开启子线程等到，主线程修改条件通知。
         * 刚开始：site 线程满足条件阻塞 wait，km 线程也阻塞 wait。
         * 但是：km 数变化，把 site 线程和 km 线程都唤醒。
         * ？？？执行结果不对。wait和notify后的代码没有执行。
         */
        for (int i = 0; i < 3; i++) {//三个线程,等待快递到达地点的变化
            new TestWaitNotify.CheckSite().start();
        }
        for (int i = 0; i < 3; i++) {//三个线程,等待里程数的变化
            new TestWaitNotify.CheckKm().start();
        }

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ExpressBean expressBean = new ExpressBean();
        expressBean.changeKm();//快递里程数变化

//==================

        /**
         *  读写锁：
         解决问题：读和写线程分离，锁也分离，可以同时进行，而且性能非常高。
         结果：lock加锁后的代码不执行
         */
        /*GoodsInfoBean goodsInfo = new GoodsInfoBean("Cup", 100000, 10000); //GoodInfoBean类
//        GoodsService goodsService = new UseReadWriteLock(goodsInfo); //三个类：用户读写 getNum 和 setNum 操作
        GoodsService goodsService = new UseSynchronized(goodsInfo);
        for (int i = 0; i < minthreadCount; i++) {
            Thread setT = new Thread(new GoodsOperate.SetThread(goodsService));//读/写线程
            for (int j = 0; j < readWriteRatio; j++) {
                Thread getT = new Thread(new GoodsOperate.GetThread(goodsService));
                getT.start();
            }
            SleepTools.millisecond(100);
            setT.start();
        }*/

        /*
         * Lock锁（对比同步锁synchronized）
         * 和
         * Condition#await/signal（对比Object#wait/notify）
         * 结果：不执行await语句？？？
         */
       /* for (int i = 0; i < 3; i++) {
            new TestCond.CheckSite().start();
        }
        for (int i = 0; i < 3; i++) {
            new TestCond.CheckKm().start();
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ExpressCond expressCond = new ExpressCond();
        expressCond.changeKm();//快递里程变化*/
    }
}
