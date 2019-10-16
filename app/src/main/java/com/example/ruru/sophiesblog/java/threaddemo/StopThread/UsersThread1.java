package com.example.ruru.sophiesblog.java.threaddemo.StopThread;

import android.util.Log;

/**
 * Created by SophieRu on 2019/7/21
 */
public class UsersThread1 extends Thread {

    @Override
    public void run() {
        super.run();

        Log.d(getClass().getName(), "run: isInterrupted=" + isInterrupted());
        while (!isInterrupted()) {
            Log.d(getClass().getName(), "Thread is running");
        }
        Log.d(getClass().getName(), "run: isInterrupted=" + isInterrupted());
    }
}
