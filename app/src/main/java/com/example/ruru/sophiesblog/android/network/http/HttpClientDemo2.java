package com.example.ruru.sophiesblog.android.network.http;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.ruru.sophiesblog.R;
import com.example.ruru.sophiesblog.java.collection.list.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

public class HttpClientDemo2 extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_content;
    private String s;
    private HttpClient httpClient;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_client_demo2);
        initView();
    }

    private void initView() {
        handler = new Handler();
        tv_content = (TextView) findViewById(R.id.tv_content);
        findViewById(R.id.btn_httpGet).setOnClickListener(this);
        findViewById(R.id.btn_httpPost).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_httpGet:
                Log.i(getClass().getName(), "onClick: " + "get请求被点击");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            requestYiyuan("http://route.showapi.com/341-3");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                break;
            case R.id.btn_httpPost:
                Log.i(getClass().getName(), "onClick: " + "post请求被点击");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            requestHttpPost("http://route.showapi.com/341-3");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                break;
        }
    }

    //httpGet请求
    private void requestYiyuan(String s) throws IOException {
        Log.i(getClass().getName(), "requestYiyuan: " + "开始get请求");
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(s + "?" + "maxResult=&page=&showapi_appid=18603&showapi_sign=65929cb8fe2a41b38586a30626e66bb6");//向浏览器中输入地址
        HttpResponse response = httpClient.execute(httpGet);
        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity entity = response.getEntity();
            String string = EntityUtils.toString(entity, "utf-8");
            Log.i(getClass().getName(), "requestYiyuan:" + string);
        } else {
            Log.i(getClass().getName(), "requestYiyuan: " + "请求失败");
        }
    }

    //httpPost请求
    private void requestHttpPost(String url) throws IOException {
        Log.i(getClass().getName(), "requestHttpPost: " + "开始post请求");
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
        list.add(new BasicNameValuePair("showapi_sign", "65929cb8fe2a41b38586a30626e66bb6"));
        list.add(new BasicNameValuePair("showapi_appid", "18603"));
        list.add(new BasicNameValuePair("maxResult", "5"));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list);
        httpPost.setEntity(entity);
        HttpResponse response = httpClient.execute(httpPost);
        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity entity1 = response.getEntity();
            s = EntityUtils.toString(entity1, "utf-8");
            Log.i(getClass().getName(), "requestHttpPost: " + s);
            handler.post(new Runnable() {
                @Override
                public void run() {
                    tv_content.setText(s);
                }
            });
        } else {
            Log.i(getClass().getName(), "requestHttpPost: " + "请求失败");
        }
    }

}
