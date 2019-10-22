package com.example.ruru.sophiesblog.android.network.volley;

public interface Callback<T> {

    /**
     * 数据请求成功
     */
    void onSuccess(T t);

    /**
     * 数据请求失败
     */
    void onFailure(String msg);

    /**
     * 数据请求错误
     */
    void onError(String msg);

    /**
     * 数据请求完成
     */
    void onComplete();
}
