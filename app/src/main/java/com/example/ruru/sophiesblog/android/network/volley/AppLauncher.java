package com.example.ruru.sophiesblog.android.network.volley;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import static com.example.ruru.sophiesblog.android.network.volley.VolleyRequest.buildRequestQueue;

public class AppLauncher extends android.app.Application {

    private static Context mContext;
    private static AppLauncher instance;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        instance = this;
        //创建volley请求队列
        buildRequestQueue(mContext);
    }

    /**
     * 获取appContext
     */
    public static Context getAppContext() {
        return mContext;
    }

    /**
     * 获取appLauncher实例
     */
    public static AppLauncher getInstance() {
        return instance;
    }


}
