package com.example.ruru.sophiesblog.design_model.structure.proxy.example1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.ruru.sophiesblog.R;
import com.example.ruru.sophiesblog.design_model.structure.proxy.example1.dynamic.MarkCompany;
import com.example.ruru.sophiesblog.design_model.structure.proxy.example1.public_.AFactory;
import com.example.ruru.sophiesblog.design_model.structure.proxy.example1.public_.BFactory;
import com.example.ruru.sophiesblog.design_model.structure.proxy.example1.static_.Leo;
import com.example.ruru.sophiesblog.design_model.structure.proxy.example1.public_.MakeFactory;
import com.example.ruru.sophiesblog.design_model.structure.proxy.example1.static_.Mark;

public class Client extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //静态代理
        Mark mark = new Mark(new AFactory());
        mark.saleThings();//结果：A工厂在卖东西呢

        Leo leo = new Leo(new BFactory());
        leo.saleThings();//结果：B工厂在卖东西呢

        //动态代理
        MarkCompany markCompany = new MarkCompany();

        markCompany.setFactory(new AFactory());
        MakeFactory proxyer1 = (MakeFactory) markCompany.getProxyInstance();
        proxyer1.saleThings();//结果：A工厂在卖东西呢

        markCompany.setFactory(new BFactory());
        MakeFactory proxyer2 = (MakeFactory) markCompany.getProxyInstance();
        proxyer2.saleThings();//结果：B工厂在卖东西呢
    }
}
