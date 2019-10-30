package com.example.ruru.sophiesblog.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.ruru.sophiesblog.R;
import com.example.ruru.sophiesblog.android.activity.singletask.SingleTaskActivity;
import com.example.ruru.sophiesblog.android.activity.singletop.SingleTopActivity;
import com.example.ruru.sophiesblog.android.activity.standard.StandardActivity;

public class ActivityDemo extends AppCompatActivity {

    private Button btn_enter_standard;
    private Button btn_enter_singletop;
    private Button btn_enter_singletask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        initView();
        initClick();
    }

    private void initView() {
        btn_enter_standard = findViewById(R.id.btn_enter_standard);
        btn_enter_singletop = findViewById(R.id.btn_enter_singletop);
        btn_enter_singletask = findViewById(R.id.btn_enter_singletask);
    }

    private void initClick() {
        btn_enter_standard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityDemo.this, StandardActivity.class);
                startActivity(intent);
            }
        });
        btn_enter_singletop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityDemo.this, SingleTopActivity.class);
                startActivity(intent);
            }
        });
        btn_enter_singletask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityDemo.this, SingleTaskActivity.class);
                startActivity(intent);
            }
        });
    }
}
