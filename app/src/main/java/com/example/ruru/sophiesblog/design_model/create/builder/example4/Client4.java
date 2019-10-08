package com.example.ruru.sophiesblog.design_model.create.builder.example4;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.ruru.sophiesblog.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.EventBusBuilder;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;

public class Client4 extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client4);

        initView();

        /**
         * 通过建造者模式创建EventBus：new一个具体建造者Builder然后返回一个EventBus对象；
         * 通过单例模式创建EventBus：EventBus#getDefault
         */
//        EventBus eventbus = new EventBusBuilder().eventInheritance(false).sendNoSubscriberEvent(true).skipMethodVerificationFor(Client4.class).build();

        /*通过建造者模式创建Retrofit：new一个具体建造者Builder然后返回一个Retrofit对象*/
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .build();


        /*通过建造者模式创建okhttp*/
        Request.Builder builder = new Request.Builder().url("http://www.baidu.com/").method("GET", null);
        Request request = builder.build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                Log.d(getClass().getName(), "onResponse:str= " + str);
            }
        });


        /*通过建造者模式创建AnimatorSet的Builder*/
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(tv, "translationX", 0.0f, 200.0f, 0f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(tv, "scaleX", 1.0f, 2.0f);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(tv, "rotationX", 0.0f, 90.0f, 0.0f);

        AnimatorSet set = new AnimatorSet();
        set.setDuration(1000);
        set.play(animator1).with(animator2).after(animator3);
    }

    private void initView() {
        tv = findViewById(R.id.textView);
    }
}
