package com.example.ruru.sophiesblog.android.network.xutils;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ruru.sophiesblog.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import androidx.appcompat.app.AppCompatActivity;

@ContentView(value = R.layout.activity_xutils_demo)
public class XutilsDemo extends AppCompatActivity {
    private static final String TAG = "info";
    @ViewInject(R.id.tv_name)
    private TextView tv_name;

    @ViewInject(R.id.btn_image)
    private Button btn_image;

    @ViewInject(R.id.img_name)
    private ImageView img_name;

    @ViewInject(R.id.btn_jiekou)
    private Button btn_jiekou;
    String s = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_xutils_demo);
//        x.view().inject(this);
    }

    @Event(value = {R.id.tv_name, R.id.btn_01, R.id.btn_image}, type = View.OnClickListener.class)
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_name: {
                tv_name.setText("你好");
            }
            break;
            //访问网络
            case R.id.btn_01:
                RequestParams params = new RequestParams("https://www.baidu.com");
                Callback.CommonCallback callback = new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        s = result;
                    }

                    @Override
                    public void onError(Throwable throwable, boolean b) {
                        Log.i(TAG, "onError: " + "onError");
                    }

                    @Override
                    public void onCancelled(CancelledException e) {
                        Log.i(TAG, "onCancelled: " + "onCancel");
                    }

                    @Override
                    public void onFinished() {
                        Log.i(TAG, "onFinished: " + "inFinish");
                    }
                };
//                x.http().post(params, callback);
                break;
            case R.id.btn_image:
//                ImageOptions io = null;
                ImageOptions io = new ImageOptions.Builder().build();
                Callback.CommonCallback callback1 = new Callback.CommonCallback<Drawable>() {
                    @Override
                    public void onSuccess(Drawable drawable) {
                    }

                    @Override
                    public void onError(Throwable throwable, boolean b) {
                    }

                    @Override
                    public void onCancelled(CancelledException e) {
                    }

                    @Override
                    public void onFinished() {
                    }
                };
//                x.image().bind(img_name, "http://e.hiphotos.baidu.com/zhidao/wh%3D450%2C600/sign=5483021aba315c6043c063ebb881e725/d52a2834349b033befc852d513ce36d3d539bd79.jpg", io, callback1);
                break;//如下image是下载完图片以后图片要显示的地方
            case R.id.btn_jiekou:
                //访问易源接口
                RequestParams params1 = new RequestParams("http://apis.baidu.com/showapi_open_bus/key_extra/key_ex");
//                params1.addBodyParameter("");--添加自己的appkey和参数
                Callback.CommonCallback callback2 = new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String o) {
                    }

                    @Override
                    public void onError(Throwable throwable, boolean b) {
                    }

                    @Override
                    public void onCancelled(CancelledException e) {
                    }

                    @Override
                    public void onFinished() {
                    }
                };
//                x.http().post(params1, callback2);
                break;
        }
    }
}
