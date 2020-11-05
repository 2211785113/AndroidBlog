package com.example.ruru.sophiesblog.android;

import android.app.Application;
import android.os.StrictMode;
import android.util.Log;

import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.SDKOptions;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.netease.nimlib.sdk.util.NIMUtil;

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

    //网易云信
    NIMClient.init(this, new LoginInfo("ruru", "ruru123456"), SDKOptions.DEFAULT);
    if (NIMUtil.isMainProcess(this)) {
      //UI相关初始化操作
      //相关service调用
    }
  }

  public StrictMode.ThreadPolicy getThreadPolicy() {
    Log.d(getClass().getName(), "getThreadPolicy: " + threadPolicy);
    return threadPolicy;
  }

  public static MyApplication getInstance() {
    return new MyApplication();
  }
}
