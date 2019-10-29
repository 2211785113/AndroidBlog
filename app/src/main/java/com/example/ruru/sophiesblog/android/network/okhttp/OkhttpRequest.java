package com.example.ruru.sophiesblog.android.network.okhttp;

import android.content.Context;
import android.os.Handler;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkhttpRequest {

    private OkHttpClient client;
    private Handler handler;

    //用这个单例：因为要传一个context
    private static OkhttpRequest instance;

    public static OkhttpRequest init(Context context) {
        if (instance == null) {
            synchronized (OkhttpRequest.class) {
                if (instance == null) {
                    instance = new OkhttpRequest(context);
                }
            }
        }
        return instance;
    }

    /**
     * 初始化client：不用OkHttpClient client = new OkHttpClient();
     * 设置超时(连接/写入/读取)时间和缓存
     */
    private OkhttpRequest(Context context) {
        File sdcache = context.getExternalCacheDir();
        int cacheSize = 10 * 1024 * 1024;
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .cache(new Cache(sdcache.getAbsoluteFile(), cacheSize));
        client = builder.build();
        //初始化Handler
        handler = new Handler();
    }

    //异步get请求：也可以起名：requestAPIGetAsyn（同步：sync；异步：asyn）
    public void getAsynHttp(String url, ResultCallback callback) {
        Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        dealresult(call, callback);
    }

    private void dealresult(Call call, ResultCallback callback) {
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (callback != null) {
                            callback.onError(e.getMessage());
                        }
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (callback != null) {
                            callback.onResponse(response);
                        }
                    }
                });
                callback.onResponse(response);
            }
        });

        //取消请求
        call.cancel();
    }
}
