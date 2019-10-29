package com.example.ruru.sophiesblog.android.network.retrofit;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.ruru.sophiesblog.R;
import com.example.ruru.sophiesblog.android.network.retrofit.bean.IpModel;
import com.example.ruru.sophiesblog.android.network.retrofit.interfaces.IpServiceForGet;
import com.example.ruru.sophiesblog.android.network.retrofit.interfaces.UploadFileForPart;


import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_demo);

        /**
         get请求：
         创建Retrofit，并创建接口文件。
         请求URL拼接而成：
         baseUrl + 请求网络接口的@GET(”getIpInfo.php?ip=59.108.54.37”)中URL。
         用Retrofit动态代理获取到之前定义的接口，并调用该接口定义的getIpMsg方法得到Call对象。
         用Call请求网络并处理回调，异步请求网络-回调的Callback运行在UI线程。
         */
        String urlGet = "http://ip.taobao.com/service/";//get请求URL
//        String urlGetWithVal = "http://ip.taobao.com/";//get请求URL传递值
//        String urlPost = "http://ip.taobao.com/service/";//post请求URL

        String url = urlGet;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                //增加返回值为gson的支持
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        File file = new File(Environment.getExternalStorageDirectory(), "a.png");
        RequestBody photoRequestBody = RequestBody.create(MediaType.parse("image/png"), file);
        MultipartBody.Part photo = MultipartBody.Part.createFormData("photos", "a.png", photoRequestBody);
        UploadFileForPart uploadFile = retrofit.create(UploadFileForPart.class);
        uploadFile.updateUser(photo, (org.xutils.http.body.RequestBody) RequestBody.create(null, "a"));

        IpServiceForGet ipServiceForGet = retrofit.create(IpServiceForGet.class);
        Call<IpModel> callGet = ipServiceForGet.getIpMsg();

//        IpServiceForGetPath ipServiceGetVal = retrofit.create(IpServiceForGetPath.class);
//        Call<IpModel> callGetVal = ipServiceGetVal.getIpMsg("service");//传入"service"来替换@GET注解中{path}的值。
//
//        IpServiceForPost ipServicePost = retrofit.create(IpServiceForPost.class);
//        Call<IpModel> callPost = ipServicePost.getIpMsg("59.108.54.37");

//        IpServiceForPostJson ipServicePostVal = retrofit.create(IpServiceForPostJson.class);
//        Call<IpModel> callPostVal = ipServicePostVal.getIpMsg(new IP("59.108.54.37"));

        Call<IpModel> call = callGet;

        call.enqueue(new Callback<IpModel>() {
            @Override
            public void onResponse(Call<IpModel> call, Response<IpModel> response) {
                String country = response.body().getData().getCountry();
                Toast.makeText(getApplicationContext(), country, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<IpModel> call, Throwable t) {

            }
        });

        //中断网络请求
        call.cancel();
    }
}
