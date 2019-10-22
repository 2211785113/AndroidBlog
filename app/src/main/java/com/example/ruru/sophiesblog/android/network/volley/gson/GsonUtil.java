package com.example.ruru.sophiesblog.android.network.volley.gson;

import com.example.ruru.sophiesblog.android.network.volley.Result;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * GSON的封装
 */
public class GsonUtil {

    private static Gson gson;

    static {
        if (gson == null) {
            gson = new Gson();
        }
    }

    //单例
    private static GsonUtil instance = new GsonUtil();

    public static GsonUtil init() {
        return instance;
    }

    /**
     * 最后都解析成Result的对象
     */
    public <T> Result<T> result(String response, Type type) {
        return gson.fromJson(response, type);
    }
}
