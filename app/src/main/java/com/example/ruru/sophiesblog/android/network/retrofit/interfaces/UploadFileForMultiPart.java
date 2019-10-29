package com.example.ruru.sophiesblog.android.network.retrofit.interfaces;

import com.example.ruru.sophiesblog.android.network.retrofit.bean.User;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

/**
 * 多个文件上传：@PartMap
 * 使用Map封装了上传的文件
 */
public interface UploadFileForMultiPart {

    @Multipart
    @POST("user/photo")
    Call<User> updateUser(@PartMap Map<String, RequestBody> photos, @Part("description") RequestBody descroption);
}
