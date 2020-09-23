package com.example.ruru.sophiesblog.android;

import android.app.Application;
import android.os.StrictMode;
import android.util.Log;

//import com.tencent.bugly.crashreport.CrashReport;

public class MyApplication extends Application {

  private StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder()
      .detectCustomSlowCalls()
      .detectDiskReads()
      .detectDiskWrites()
      .detectNetwork()
      .penaltyDialog()
      .penaltyLog()
      .penaltyFlashScreen()
      .build();

  @Override
  public void onCreate() {
    super.onCreate();

    Log.d(getClass().getName(), "onCreate: ");

    //xutils
//        x.Ext.init(this);

    //Crash
//        CrashReport.initCrashReport(getApplicationContext(), "注册时申请的APPID", false);
  }

  public StrictMode.ThreadPolicy getThreadPolicy() {
    Log.d(getClass().getName(), "getThreadPolicy: " + threadPolicy);
    return threadPolicy;
  }

  public static MyApplication getInstance() {
    return new MyApplication();
  }
}
