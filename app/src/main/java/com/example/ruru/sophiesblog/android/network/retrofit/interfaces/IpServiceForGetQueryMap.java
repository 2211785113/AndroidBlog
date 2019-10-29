package com.example.ruru.sophiesblog.android.network.retrofit.interfaces;

import com.example.ruru.sophiesblog.android.network.retrofit.bean.IpModel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * 动态指定查询条件组： @QueryMap
 * 传入很多查询参数：所有参数集成在一个Map中统一传递
 */
public interface IpServiceForGetQueryMap {
    @GET("getIpInfo.php")
    Call<IpModel> getIpMsg(@QueryMap Map<String, String> options);
}
