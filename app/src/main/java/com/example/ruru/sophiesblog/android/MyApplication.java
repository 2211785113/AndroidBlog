package com.example.ruru.sophiesblog.android;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //xutils
//        x.Ext.init(this);

        //Crash
        CrashReport.initCrashReport(getApplicationContext(), "注册时申请的APPID", false);
    }
}
