package com.example.ruru.sophiesblog.java.threaddemo.volatiles;

import android.util.Log;

/**
 * Created by SophieRu on 2019/7/26
 */
public class NotSafeThread extends Thread {

    private DataModel dataModel;

    public NotSafeThread(DataModel dataModel) {
        this.dataModel = dataModel;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            dataModel.initCount();
        }
    }
}
