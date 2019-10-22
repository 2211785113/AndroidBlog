package com.example.ruru.sophiesblog.android.network.http;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;
import com.example.ruru.sophiesblog.java.collection.list.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import static com.example.ruru.sophiesblog.android.network.http.HttpManager.convertStreamToString;

public class HttpClientDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_client_demo);

        new Thread(new Runnable() {
            @Override
            public void run() {
                useHttpClientGet("http://www.baidu.com");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    useHttpClientPost("http://ip.taobao.com/instructions.php");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void useHttpClientGet(String url) {
        Log.d(getClass().getName(), "useHttpClientGet:url= " + url);
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("Connection", "Keep-Alive");//请求头
        HttpClient httpClient = createHttpClient();
        try {
            Log.d(getClass().getName(), "url= " + url);
            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            Log.d(getClass().getName(), "useHttpClientGet:请求状态码= " + statusCode);
            if (httpEntity != null) {
                InputStream inputStream = httpEntity.getContent();
                String response = convertStreamToString(inputStream);
                Log.d(getClass().getName(), "useHttpClientGet:请求状态码= " + statusCode + " 请求结果=" + response);
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void useHttpClientPost(String url) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Connection", "Keep-Alive");
        HttpClient httpClient = createHttpClient();
        List<NameValuePair> postParams = new ArrayList<>();
        //要传递的参数
        postParams.add(new BasicNameValuePair("ip", "59.108.54.37"));
        httpPost.setEntity(new UrlEncodedFormEntity(postParams));
        HttpResponse httpResponse = httpClient.execute(httpPost);
        HttpEntity httpEntity = httpResponse.getEntity();
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (httpEntity != null) {
            InputStream inputStream = httpEntity.getContent();
            String response = convertStreamToString(inputStream);
            Log.d(getClass().getName(), "useHttpClientPost: code=" + statusCode + " response=" + response);
            inputStream.close();
        }
    }

    /**
     * 实例化HttpClient，并配置默认请求参数
     */
    private HttpClient createHttpClient() {
        BasicHttpParams httpParams = new BasicHttpParams();
        //设置连接超时
        HttpConnectionParams.setConnectionTimeout(httpParams, 15000);
        //设置请求超时
        HttpConnectionParams.setSoTimeout(httpParams, 15000);
        HttpConnectionParams.setTcpNoDelay(httpParams, true);
        HttpProtocolParams.setVersion(httpParams, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(httpParams, HTTP.UTF_8);
        //持续握手
        HttpProtocolParams.setUseExpectContinue(httpParams, true);
        HttpClient httpClient = new DefaultHttpClient(httpParams);
        return httpClient;
    }

}
