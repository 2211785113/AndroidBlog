package com.example.ruru.sophiesblog.android.network.http;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.example.ruru.sophiesblog.R;
import com.example.ruru.sophiesblog.java.collection.list.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.ruru.sophiesblog.android.network.http.HttpManager.convertStreamToString;

public class HttpUrlConnDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_url_connection_demo);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    useHttpUrlConnectionPost("http://ip.taobao.com/instructions.php");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void useHttpUrlConnectionPost(String url) throws IOException {
        HttpURLConnection httpUrlConn = getHttpUrlConnection(url);
        List<NameValuePair> postParams = new ArrayList<>();
        postParams.add(new BasicNameValuePair("ip", "59.108.54.37"));
        postParams(httpUrlConn.getOutputStream(), postParams);
        httpUrlConn.connect();
        InputStream inputStream = httpUrlConn.getInputStream();
        int responseCode = httpUrlConn.getResponseCode();
        String response = convertStreamToString(inputStream);
        Log.d(getClass().getName(), "请求状态码=" + responseCode + " 请求结果=" + response);
        inputStream.close();
    }

    /**
     * 配置默认参数
     */
    private HttpURLConnection getHttpUrlConnection(String url) {
        HttpURLConnection mUrlConn = null;
        try {
            URL mUrl = new URL(url);
            mUrlConn = (HttpURLConnection) mUrl.openConnection();
            mUrlConn.setConnectTimeout(15000);//设置连接超时时间
            mUrlConn.setReadTimeout(15000);//设置读取超时时间
            mUrlConn.setRequestMethod("POST");//设置请求参数
            mUrlConn.setRequestProperty("Connection", "Keep-Alive");//添加Header
            mUrlConn.setDoInput(true);//接收输入流
            mUrlConn.setDoOutput(true);//传递参数时需要开启
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mUrlConn;
    }

    /**
     * 请求参数写入输出流
     */
    private void postParams(OutputStream output, List<NameValuePair> paramsList) throws IOException {
        StringBuilder sbd = new StringBuilder();
        for (NameValuePair pair : paramsList) {
            if (!TextUtils.isEmpty(sbd)) {
                sbd.append("&");
            }
            sbd.append(URLEncoder.encode(pair.getName(), "UTF-8"));
            sbd.append("=");
            sbd.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
        }
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output, "UTF-8"));
        writer.write(sbd.toString());
        writer.flush();
        writer.close();
    }


}
