package com.example.ruru.sophiesblog.android.network.volley;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.ruru.sophiesblog.R;
import com.example.ruru.sophiesblog.android.network.volley.bean.Stu;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import static android.graphics.Bitmap.Config.RGB_565;

public class VolleyDemo extends AppCompatActivity {

    private TextView textView;
    private ImageView image;
    private ImageView image1;
    private NetworkImageView image2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley_demo);

        //string请求
        String strUrl = "http://www.baidu.com";
        VolleyRequest.init().newStringRequest(strUrl, new TypeToken<Result<String>>() {
        }.getType(), new Callback<String>() {
            @Override
            public void onSuccess(String response) {
                Log.d(getClass().getName(), "onSuccess:res= " + response.substring(0, 100));
                textView.setText(response.substring(0, 100));
            }

            @Override
            public void onFailure(String msg) {
                Log.d(getClass().getName(), "onFailure:failure= " + msg);
                textView.setText(msg);
            }

            @Override
            public void onError(String msg) {
                Log.d(getClass().getName(), "onError: err=" + msg);
                textView.setText(msg);
            }

            @Override
            public void onComplete() {

            }
        });

        //json请求
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", "1");
            jsonObject.put("name", "小红");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        VolleyRequest.init().newJsonRequest(strUrl, jsonObject, new TypeToken<Result<List<Stu>>>() {
        }.getType(), new Callback<List<Stu>>() {
            @Override
            public void onSuccess(List<Stu> list) {
                Log.d(getClass().getName(), "onSuccess:res= " + list.toString());
            }

            @Override
            public void onFailure(String msg) {
                Log.d(getClass().getName(), "onFailure: failure= " + msg);
            }

            @Override
            public void onError(String msg) {
                Log.d(getClass().getName(), "onError: err=" + msg);
            }

            @Override
            public void onComplete() {

            }
        });

        //img请求
        String imgUrl = "https://image.b·aidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E5%B0%8F%E7%8C%AB&hs=2&pn=1&spn=0&di=146850&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&ie=utf-8&oe=utf-8&cl=2&lm=-1&cs=3488207672%2C3352446836&os=3368208275%2C2848243393&simid=1935247%2C833331033&adpicid=0&lpn=0&ln=30&fr=ala&fm=&sme=&cg=&bdtype=0&oriquery=%E5%B0%8F%E7%8C%AB&objurl=http%3A%2F%2Fimage.biaobaiju.com%2Fuploads%2F20180803%2F20%2F1533300593-nYwpoTMUEs.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bktw5kwt37_z%26e3Bv54AzdH3Fi5g2geiwtztAzdH3Fdlcdb_z%26e3Bip4s&gsm=&islist=&querylist=";
        VolleyRequest.init().newImageRequest(imgUrl, new TypeToken<Result<Bitmap>>() {
        }.getType(), new Callback<Bitmap>() {
            @Override
            public void onSuccess(Bitmap bitmap) {
                image.setImageBitmap(bitmap);
            }

            @Override
            public void onFailure(String msg) {
                Log.d(getClass().getName(), "onFailure:failure= " + msg);
            }

            @Override
            public void onError(String msg) {
                Log.d(getClass().getName(), "onError:err= " + msg);
            }

            @Override
            public void onComplete() {

            }
        }, 100, 100, ImageView.ScaleType.CENTER_CROP, RGB_565);
    }

    private void initView() {
        textView = findViewById(R.id.text);
        image = findViewById(R.id.img_name);
        image1 = findViewById(R.id.img_name1);
        image2 = findViewById(R.id.img_name2);
    }
}
