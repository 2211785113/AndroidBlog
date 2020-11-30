package com.example.ruru.sophiesblog.android;

import android.app.Application;
import android.os.Environment;
import android.os.StrictMode;

import com.example.ruru.sophiesblog.BuildConfig;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.SDKOptions;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.netease.nimlib.sdk.util.NIMUtil;
import com.tencent.mars.xlog.Log;
import com.tencent.mars.xlog.Xlog;

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

    android.util.Log.d(getClass().getName(), "onCreate: ");

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

    //Xlog
    initXlog();
  }

  private void initXlog() {
    android.util.Log.d(getClass().getName(), "onCreate: initXlog");

    System.loadLibrary("c++_shared");
    System.loadLibrary("marsxlog");

    //外部存储sdcard
    final String SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath();
    final String logPath = SDCARD + "/marssample/log";

    ///storage/emulated/0/marssample/log
    android.util.Log.d(getClass().getName(), "initXlog:logPath = "+logPath);

    //内部存储
//    final String filePath = this.getFilesDir() + "/xlog";
//    final String cachePath = this.getCacheDir() + "/xlog";

    final String logFileName = "";

    //init xlog
    Xlog xlog = new Xlog();
    Log.setLogImp(xlog);

    if (BuildConfig.DEBUG) {
      Log.setConsoleLogOpen(true);//是否把日志打印到控制台 debug下开启 release下关闭
      Log.appenderOpen(Xlog.LEVEL_DEBUG, Xlog.AppednerModeAsync, "", logPath, logFileName, 0);
    } else {
      Log.setConsoleLogOpen(false);
      Log.appenderOpen(Xlog.LEVEL_INFO, Xlog.AppednerModeAsync, "", logPath, logFileName, 0);
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
