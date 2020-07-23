package com.example.ruru.sophiesblog.design_model.create.builder.example2;

import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;

import androidx.appcompat.app.AppCompatActivity;

public class Client2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client2);

        //客户：我需要一个可以摇一摇找妹子的软件
        //产品经理：分析得出那就做一个探探吧
        //技术主管：appName:探探  系统：ios,android 功能：摇一摇，找妹子
        Product android = ProductManager.create(Product.ANDROID);
        Product ios = ProductManager.create(Product.IOS);

        Log.d(getClass().getName(), "onCreate:res1= " + android);
        Log.d(getClass().getName(), "onCreate:res2= " + ios);

        //程序猿觉得太累了，工资又少，干的最多。最后决定自己出去单干。
        Progremer niubiProgremer = new Progremer();

        Product androidBest = niubiProgremer.setAppName("探探").setAppSystem(Product.ANDROID).setAppFuction("摇一摇，找妹子").build();
        Product iosBest = niubiProgremer.setAppName("探探").setAppSystem(Product.IOS).setAppFuction("摇一摇，找妹子").build();

        Log.d(getClass().getName(), "onCreate:res3= " + androidBest);
        Log.d(getClass().getName(), "onCreate:res4= " + iosBest);
    }
}
