package com.example.ruru.sophiesblog.android.network.retrofit.interfaces;

import com.example.ruru.sophiesblog.android.network.retrofit.bean.User;

import org.xutils.http.body.RequestBody;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * @Part 单个文件上传
 * @Multipart 多个文件上传
 * updateUser方法：
 * 第一个参数：准备上传的图片文件，使用MultipartBody.Part类型；
 * 第二个参数：RequestBody类型，用来传递简单的键值对。
 */
public interface UploadFileForPart {
    @Multipart
    @POST("user/photo")
    Call<User> updateUser(@Part MultipartBody.Part photo, @Part("description") RequestBody description);
}
