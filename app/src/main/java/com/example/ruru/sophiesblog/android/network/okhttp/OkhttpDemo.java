package com.example.ruru.sophiesblog.android.network.okhttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkhttpDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp_demo);

        OkHttpClient client = new OkHttpClient();

        String url = "http://www.baidu.com";

        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            Response response = client.newCall(request).execute();
            Log.d(getClass().getName(), "onCreate:res= " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
