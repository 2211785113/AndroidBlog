package com.example.ruru.sophiesblog.android.network.volley;

import android.content.Context;
import android.graphics.Bitmap;
import android.telecom.Call;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ruru.sophiesblog.R;
import com.example.ruru.sophiesblog.android.network.volley.gson.GsonUtil;

import org.json.JSONObject;

import java.lang.reflect.Type;

/**
 * Volley请求
 */
public class VolleyRequest {

    //单例
    private static VolleyRequest instance = new VolleyRequest();

    public static VolleyRequest init() {
        return instance;
    }

    //创建volley请求队列
    private static RequestQueue queue;

    public static RequestQueue buildRequestQueue(Context context) {
        if (queue == null)
            Volley.newRequestQueue(context);
        return queue;
    }

    //string请求
    public void newStringRequest(String url, final Type type, final Callback callback) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Result result = GsonUtil.init().result(response, type);
                        if (result.getCode() == 0) {
                            callback.onSuccess(result.getData());
                        } else if (result.getCode() == 203) {
                            callback.onFailure("code=" + result.getCode() + " data=" + result.getMsg());
                        } else {
                            callback.onFailure("code=" + result.getCode() + " data=" + result.getMsg());
                        }
                        callback.onComplete();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onError(error.getMessage());
                        callback.onComplete();
                    }
                });
        queue.add(stringRequest);
    }

    //jsonObject请求
    public void newJsonRequest(String url, JSONObject jsonObject, final Type type, final Callback callback) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Result result = GsonUtil.init().result(response.toString(), type);
                if (result.getCode() == 0) {
                    callback.onSuccess(result.getData());
                } else if (result.getCode() == 203) {
                    callback.onFailure("code=" + result.getCode() + " data=" + result.getMsg());
                } else {
                    callback.onFailure("code=" + result.getCode() + " data=" + result.getMsg());
                }
                callback.onComplete();
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onError(error.getMessage());
                callback.onComplete();
            }
        });
        queue.add(jsonObjectRequest);
    }

    /**
     * image请求
     * scaleType：根据设置的 maxWidth 和 maxHeight 压缩图片
     * config：图片解码格式
     */
    public void newImageRequest(String url, final Type type, final Callback callback, int maxWidth, int maxHeight, ImageView.ScaleType scaleType, Bitmap.Config config) {
        ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                Result result = GsonUtil.init().result(response.toString(), type);
                if (result.getCode() == 0) {
                    callback.onSuccess(result.getData());
                } else if (result.getCode() == 203) {
                    callback.onFailure("code=" + result.getCode() + " data=" + result.getMsg());
                } else {
                    callback.onFailure("code=" + result.getCode() + " data=" + result.getMsg());
                }
                callback.onComplete();
                callback.onSuccess(response);
            }
        }, maxWidth, maxHeight, scaleType, config, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onError(error.getMessage());
                callback.onComplete();
            }
        });
        queue.add(imageRequest);
    }

    /**
     * imageLoader请求-加载图片：
     * ImageLoader内部使用ImageRequest实现，构造方法内部传入一个ImageCache缓存形参，实现图片缓存功能
     * 会先显示默认图片，等图片加载完才会显示在ImageView上
     * 还可以过滤重复连接， 避免重复发送请求
     * 可以设置最大宽度和最大高度
     */
    public void newImageLoaderRequest(String url, int defaultResId, int errorResId, ImageView imgView, int maxWidth, int maxHeight, Callback callback) {
        ImageLoader imageLoader = new ImageLoader(queue, new ImageLoader.ImageCache() {
            @Override
            public Bitmap getBitmap(String url) {
                return null;
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {

            }
        });
        ImageLoader.ImageListener imageListener = imageLoader.getImageListener(imgView, defaultResId, errorResId);
        imageLoader.get(url, imageListener, maxWidth, maxHeight);//两个max可设置也可不设置
    }

    /**
     * networkImage请求-加载图片：
     * NetworkImageView封装了请求网络加载图片的功能
     * 没有提供设置最大宽度和高度的方法，而是根据设置控件的宽和高，结合网络图片的宽和高内部会自动实现压缩。
     * 如果不想要压缩， 也可以设置NetworkImageView控件的宽和高都为wrap_content。
     */
    public void newNetworkImageRequest(String url, int defaultResId, int errorResId, NetworkImageView imgView) {
        ImageLoader imageLoader = new ImageLoader(queue, new ImageLoader.ImageCache() {
            @Override
            public Bitmap getBitmap(String url) {
                return null;
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {

            }
        });
        imgView.setDefaultImageResId(defaultResId);
        imgView.setErrorImageResId(errorResId);
        imgView.setImageUrl(url, imageLoader);
    }
}
