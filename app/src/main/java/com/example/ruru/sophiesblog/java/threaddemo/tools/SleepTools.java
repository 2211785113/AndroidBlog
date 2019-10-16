package com.example.ruru.sophiesblog.java.threaddemo.tools;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * Created by SophieRu on 2019/7/22
 */
public class SleepTools {

    public static void second(int second) {
        try {
            TimeUnit.SECONDS.sleep(second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void millisecond(int millisecond) {
        try {
            TimeUnit.MILLISECONDS.sleep(millisecond);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
