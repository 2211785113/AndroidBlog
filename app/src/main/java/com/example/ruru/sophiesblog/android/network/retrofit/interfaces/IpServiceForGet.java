package com.example.ruru.sophiesblog.android.network.retrofit.interfaces;

import com.example.ruru.sophiesblog.android.network.retrofit.bean.IpModel;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * 网络请求接口：get请求，url地址，返回Call<IpModel>类型的数据
 */
public interface IpServiceForGet {
    @GET("getIpInfo.php?ip=59.108.54.37")
    Call<IpModel> getIpMsg();
}
