package com.example.ruru.sophiesblog.android.activity.singletask;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ruru.sophiesblog.R;
import com.example.ruru.sophiesblog.android.activity.BaseActivityDemo;

/**
 * singletask模式
 * <p>
 * 如果栈中存在这个activity的实例就会复用这个activity,不管它是否位于栈顶，复用时，会将上面的activity全部出栈，并回调该实例的onNewIntent方法。
 * <p>
 * 任务栈的分配：会在自己需要的任务栈中寻找实例。任务栈通过taskAffinity指定。
 */
public class SingleTaskActivity extends BaseActivityDemo {

    private Button btn_singletask;
    private Button btn_other;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_task);

        initView();
        initClick();

    }

    private void initView() {
        btn_singletask = findViewById(R.id.btn_singletask);
        btn_other = findViewById(R.id.btn_other);
    }

    private void initClick() {
        btn_singletask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SingleTaskActivity.this, SingleTaskActivity.class);
                startActivity(intent);
            }
        });
        btn_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SingleTaskActivity.this, OtherTaskActivity.class);
                startActivity(intent);
            }
        });
    }
}
