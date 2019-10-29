package com.example.ruru.sophiesblog.android.network.retrofit.interfaces;

import com.example.ruru.sophiesblog.android.network.retrofit.bean.IP;
import com.example.ruru.sophiesblog.android.network.retrofit.bean.IpModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * 网络请求接口：
 * 传输数据类型JSON字符串： @Body
 * 用POST方式将JSON字符串作为请求体发送到服务器
 * 用@Body这个注解标识参数对象即可，Retrofit会将Ip对象转换为字符串：
 */
public interface IpServiceForPostJson {
    @POST("getIpInfo.php")
    Call<IpModel> getIpMsg(@Body IP ip);
}
