package com.example.ruru.sophiesblog.design_model.behavior.observer.example1;

import android.os.Bundle;

import com.example.ruru.sophiesblog.R;
import com.example.ruru.sophiesblog.design_model.behavior.observer.example1.be.WxUser;
import com.example.ruru.sophiesblog.design_model.behavior.observer.example1.isbe.WxPublic;

import androidx.appcompat.app.AppCompatActivity;

public class Client extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client3);

        //创建微信用户
        WxUser user1 = new WxUser("小红");
        WxUser user2 = new WxUser("小明");
        WxUser user3 = new WxUser("小丽");
        //订阅公众号
        WxPublic wxPublic = new WxPublic();
        wxPublic.attach(user1);
        wxPublic.attach(user2);
        wxPublic.attach(user3);
        //公众号更新发出消息给订阅的微信用户
        wxPublic.notify("公众号内容更新啦。");
    }
}
