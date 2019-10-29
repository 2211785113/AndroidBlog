package com.example.ruru.sophiesblog.android.network.okhttp;

import okhttp3.Response;

/**
 * 用抽象类也可以，只定义方法
 * 因为抽象类和接口都能实现多态，都需要实现
 */
public interface ResultCallback {

    /**
     * 成功时回调
     */
    void onResponse(Response response);

    /**
     * 错误时回调
     */
    void onError(String msg);
}
