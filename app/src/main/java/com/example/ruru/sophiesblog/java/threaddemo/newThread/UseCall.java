package com.example.ruru.sophiesblog.java.threaddemo.newThread;

import java.util.concurrent.Callable;

/**
 * Created by SophieRu on 2019/7/3
 */
public class UseCall implements Callable<String> {
    @Override
    public String call() throws Exception {
        return "CallResult";
    }
}
