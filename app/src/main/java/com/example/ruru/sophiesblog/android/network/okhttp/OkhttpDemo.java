package com.example.ruru.sophiesblog.android.network.okhttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;

import okhttp3.Response;

public class OkhttpDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp_demo2);

        String url = "http://www.baidu.com";

        OkhttpRequest.init(this).getAsynHttp(url, new ResultCallback() {
            @Override
            public void onResponse(Response response) {
                Log.d(getClass().getName(), "onResponse:res= " + response.body().toString());
            }

            @Override
            public void onError(String msg) {
                Log.d(getClass().getName(), "onError:res= " + msg);
            }
        });
    }
}
