package com.example.ruru.sophiesblog.android.activity.standard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ruru.sophiesblog.R;
import com.example.ruru.sophiesblog.android.activity.BaseActivityDemo;

/**
 * 每次启动一个activity都会重新创建一个新的实例，不管这个实例存不存在
 * 启动了两次activity，存在两个activity实例
 * 所以hashcode是不一样的
 * <p>
 * 谁启动了该模式的activity，该activity就属于启动它的activity的任务栈
 * 第一次：MainActivity启动，存在于MainActivity的任务栈中
 * 第二次：StandardActivity启动，存在于StandardActivity的任务栈中
 * 所以taskId是一样的
 * <p>
 * 这个activity的onCreate(),onResume(),onStart()都会被调用
 * 按返回键的时候有两个activity
 */
public class StandardActivity extends BaseActivityDemo {

    private Button btn_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standard);

        btn_start = findViewById(R.id.btn_start);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StandardActivity.this, StandardActivity.class);
                startActivity(intent);
            }
        });
    }
}
