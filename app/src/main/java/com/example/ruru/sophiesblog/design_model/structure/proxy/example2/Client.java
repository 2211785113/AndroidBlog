package com.example.ruru.sophiesblog.design_model.structure.proxy.example2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ruru.sophiesblog.R;
import com.example.ruru.sophiesblog.design_model.structure.proxy.example2.dynamic.DynamicOther;
import com.example.ruru.sophiesblog.design_model.structure.proxy.example2.public_.IShop;
import com.example.ruru.sophiesblog.design_model.structure.proxy.example2.static_.Other;
import com.example.ruru.sophiesblog.design_model.structure.proxy.example2.public_.Wo;

import java.lang.reflect.Proxy;

public class Client extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        //静态
        Other other = new Other(new Wo());
        other.buy();//结果：打印"购买"

        //动态
        IShop wo = new Wo();
        DynamicOther mDynamicOther = new DynamicOther(wo);//创建动态代理
        ClassLoader classLoader = wo.getClass().getClassLoader();//创建wo的ClassLoader
        IShop other1 = (IShop) Proxy.newProxyInstance(classLoader, new Class[]{IShop.class}, mDynamicOther);//动态创建代理类
        other1.buy();
    }
}
