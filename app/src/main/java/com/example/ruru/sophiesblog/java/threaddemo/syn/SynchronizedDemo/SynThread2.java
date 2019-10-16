package com.example.ruru.sophiesblog.java.threaddemo.syn.SynchronizedDemo;

import android.util.Log;


/**
 * Created by SophieRu on 2019/7/21
 * 对象锁
 */
public class SynThread2 extends Thread {

    private DataModel1 dataModel;

    public SynThread2(DataModel1 dataModel) {
        this.dataModel = dataModel;
    }

    @Override
    public void run() {
        Log.d(getClass().getName(), Thread.currentThread().getName() + "run: count 1 = " + dataModel.getCount());

        for (int i = 0; i < 10000; i++) {
            dataModel.initCount();
        }

        Log.d(getClass().getName(), Thread.currentThread().getName() + "run: count 2 = " + dataModel.getCount());
    }

}
