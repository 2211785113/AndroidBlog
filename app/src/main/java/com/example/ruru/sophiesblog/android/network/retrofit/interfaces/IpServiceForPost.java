package com.example.ruru.sophiesblog.android.network.retrofit.interfaces;

import com.example.ruru.sophiesblog.android.network.retrofit.bean.IpModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 网络请求接口：
 * 传输数据类型为键值对@Field
 *
 * @FormUrlEncoded 这个注解说明：这是一个表单请求
 * getIpMsg中：使用@Field注解来标示所对应的String类型数据的键，从而组成一组键值对进行传递
 */
public interface IpServiceForPost {
    @FormUrlEncoded
    @POST("getIpInfo.php")
    Call<IpModel> getIpMsg(@Field("ip") String first);
}
