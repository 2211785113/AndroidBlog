package com.example.ruru.sophiesblog.android.network.retrofit.interfaces;

import com.example.ruru.sophiesblog.android.network.retrofit.bean.IpModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 动态指定查询条件：
 * @Query：动态指定ip的值
 */
public interface IpServiceForGetQuery {
    @GET("getIpInfo.php")
    Call<IpModel> getIpMsg(@Query("ip") String ip);
}
