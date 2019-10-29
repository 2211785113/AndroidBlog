package com.example.ruru.sophiesblog.android.network.retrofit.interfaces;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

/**
 * 添加消息报头：
 * HTTP请求中，为了防止攻击或过滤掉不安全的访问，或添加特殊加密的访问等，
 * 以便减轻服务器的压力和保证请求的安全，通常都会在消息报头中携带一些特殊的消息头处理。
 * 静态方式：@Headers，多个消息报头，用{}包含起来
 * 动态方式：
 */
public interface SomeService {
    @GET("some/endpoint")

        /*静态*/
//    @Headers("Accept-Encoding:application/json")
//    @Headers({"Accept-Encoding:application/json","User-Agent:MoonRetrofit"})
//    Call<ResponseBody> getCarType();

        /*动态*/
    Call<ResponseBody> getCarType(@Header("Location") String location);
}
