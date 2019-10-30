package com.example.ruru.sophiesblog.android.activity.singletop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ruru.sophiesblog.R;
import com.example.ruru.sophiesblog.android.activity.BaseActivityDemo;

/**
 * singletop模式：
 * activity位于栈顶，activity不会被重写新建，同时它的onNewIntent方法会被调用
 */
public class SingleTopActivity extends BaseActivityDemo {

    private Button btn_enter_singletop;
    private Button btn_enter_othertop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_top);

        initView();
        initClick();
    }

    private void initView() {
        btn_enter_singletop = findViewById(R.id.btn_enter_singletop);
        btn_enter_othertop = findViewById(R.id.btn_enter_othertop);
    }

    private void initClick() {
        btn_enter_singletop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SingleTopActivity.this, SingleTopActivity.class);
                startActivity(intent);
            }
        });
        btn_enter_othertop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SingleTopActivity.this, OtherTopActivity.class);
                startActivity(intent);
            }
        });
    }
}
