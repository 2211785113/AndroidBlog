package com.example.ruru.sophiesblog.android.network.okhttp.old;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 注意：这个Demo返回结果在子线程，直接运行会报错。
 */
public class OkhttpOldDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp_demo);

        OkHttpClient client = new OkHttpClient();

        String url = "http://www.baidu.com";


        //同步get请求：execute执行
        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            Response response = client.newCall(request).execute();
            Log.d(getClass().getName(), "onCreate:res= " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }


        //异步get请求：enqueue排队
        Request.Builder builder = new Request.Builder().url(url);
        builder.method("GET", null);
        Request request1 = builder.build();


        client.newCall(request1).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //onResponse回调不在主线程
                String res = response.body().toString();
                Log.d(getClass().getName(), "onResponse:res= " + res);
            }
        });

        //异步post请求：okhttp3和2的区别，用功能更强大的FormBody替代了FormEncodingBuilder
        RequestBody formBody = new FormBody.Builder().add("id", "1").build();
        Request request2 = new Request.Builder().url(url).post(formBody).build();

        client.newCall(request2).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().toString();
                Log.d(getClass().getName(), "onResponse:res= " + res);
            }
        });

        //异步上传文件：post请求
        MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown;charset=utf-8");//文件类型
        //在sd卡根目录创建一个a.txt文件，内容为"content"
        String filepath = "";
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            filepath = Environment.getExternalStorageDirectory().getAbsolutePath();
        } else {
            return;
        }
        File file = new File(filepath, "a.txt");
        Request request3 = new Request.Builder().url(url).post(RequestBody.create(MEDIA_TYPE_MARKDOWN, file)).build();
        client.newCall(request3).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().toString();
                Log.d(getClass().getName(), "onResponse:res= " + res);
            }
        });

        //异步下载文件:下载图片，得到response后将流写进我们指定的图片文件中。
        String imgUrl = "http://image.baidu.com/search/detail?ct=503316480&z=undefined&tn=baiduimagedetail&ipn=d&word=%E4%BD%A0%E5%A5%BD&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=undefined&hd=undefined&latest=undefined&copyright=undefined&cs=1312518729,3342606856&os=2673589776,30825524&simid=0,0&pn=4&rn=1&di=110&ln=1519&fr=&fmq=1571816994375_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&is=0,0&istype=0&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=0&objurl=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F0170185812d13ba84a0e282b060b8c.jpg%402o.jpg&rpstart=0&rpnum=0&adpicid=0&force=undefined&ctd=1571816998516^3_1440X690%1";
        Request request4 = new Request.Builder().url(imgUrl).build();
        client.newCall(request4).enqueue(new Callback() {


            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream inputStream = response.body().byteStream();
                FileOutputStream fileOus = null;
                String filepath = "";
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    filepath = Environment.getExternalStorageDirectory().getAbsolutePath();
                } else {
                    filepath = getFilesDir().getAbsolutePath();
                }
                File file = new File(filepath, "a.jpg");
                if (file != null) {
                    fileOus = new FileOutputStream(file);
                    byte[] buffer = new byte[2048];
                    int len = 0;
                    while ((len = inputStream.read(buffer)) != -1) {
                        fileOus.write(buffer, 0, len);
                    }
                    fileOus.flush();
                }
            }
        });

        //异步上传Multipart文件：okhttp的好处：上传文件同时可以上传其它类型字段。注意：服务器不接收Multipart文件，只是举例子而已
        MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");//上传文件类型
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("title", "a")//key-value形式上传
                .addFormDataPart("image", "a.jpg",
                        RequestBody.create(MEDIA_TYPE_PNG, new File("/sdcard/a.jpg")))//上传表单
                .build();
        Request request5 = new Request.Builder().header("Authorization", "Client-ID " + "...").url(url).post(requestBody).build();
        client.newCall(request5).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().toString();
                Log.d(getClass().getName(), "onResponse:res= " + res);
            }
        });

        //设置超时时间(连接/写入/读取)和缓存：okhttp2直接通过OkHttpClient设置；okhttp3通过OkHttpClient.Builder来设置，然后通过build得到OkHttpClient
        File sdcache = getExternalCacheDir();
        int cacheSize = 10 * 1024 * 1024;
        OkHttpClient.Builder builder1 = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .cache(new Cache(sdcache.getAbsoluteFile(), cacheSize));
        client = builder1.build();

        //取消请求：当用户离开应用或跳到其他界面时，使用call.cancel() 停止正在执行的call，释放网络资源。同步异步call都可以取消，也可以通过tag来同时取消多个请求。
        //当构建一个请求时，使用Request.Builder.tag(Object tag) 来分配一个标签，
        //之后用OkHttpClient.cancel(Object tag) 来取消所有带有这个tag的call。
        //结果：cancel没有起到作用，每隔100ms网络请求依然会成功。所以设置每隔1ms中断一次请求，测试成功。
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        Request request6 = new Request.Builder()
                .url("http://www.baidu.com")
                .cacheControl(CacheControl.FORCE_NETWORK)//设置每次请求都要请求网络，为了测试在请求过程中调用cancel的效果
                .build();
        Call call = client.newCall(request6);
        //创建线程池，100 ms后调用call.cancel() 来取消请求。
        executor.schedule(new Runnable() {
            @Override
            public void run() {
                call.cancel();
            }
        }, 100, TimeUnit.MICROSECONDS);//改为1，测试成功。
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.cacheResponse() != null) {
                    String res = response.cacheResponse().toString();
                    Log.d(getClass().getName(), "onResponse:cache res= " + res);
                } else {
                    String s = response.networkResponse().toString();
                    Log.d(getClass().getName(), "onResponse:network res= " + s);
                }
            }
        });

    }
}
