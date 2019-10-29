package com.example.ruru.sophiesblog.android.network.retrofit.interfaces;

import com.example.ruru.sophiesblog.android.network.retrofit.bean.IpModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 网络请求接口：
 *
 * @Path 动态配置URL地址
 * GET注解中包含了{path}，对应着@Path注解中的"path”，用来替换{path}的正是需要传入的"String path"的值。
 */
public interface IpServiceForGetPath {
    @GET("{path}/getIpInfo.php?ip=59.108.54.37")
    Call<IpModel> getIpMsg(@Path("path") String path);
}
