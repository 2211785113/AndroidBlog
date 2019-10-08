package com.example.ruru.sophiesblog.design_model.behavior.observer.example2.isbe;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.ruru.sophiesblog.R;
import com.example.ruru.sophiesblog.design_model.behavior.observer.example2.be.AFragment;
import com.example.ruru.sophiesblog.design_model.behavior.observer.example2.be.BFragment;
import com.example.ruru.sophiesblog.design_model.behavior.observer.example2.be.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

//具体被观察者
public class ClientO extends AppCompatActivity {

    private SwipeRefreshLayout srl;
    private List<OnRefreshListener> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client5);

        initView();
        initData();
    }

    private void initData() {

        list.add(new AFragment());
        list.add(new BFragment());

        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                for (OnRefreshListener listener : list) {
                    listener.onRefresh();
                }
            }
        });
    }

    private void initView() {
        srl = findViewById(R.id.srl);
    }
}
