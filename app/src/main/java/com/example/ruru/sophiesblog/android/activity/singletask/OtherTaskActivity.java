package com.example.ruru.sophiesblog.android.activity.singletask;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ruru.sophiesblog.R;

import androidx.appcompat.app.AppCompatActivity;

/**
 * standard模式
 */
public class OtherTaskActivity extends AppCompatActivity {
    private Button btn_singletask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_task);

        btn_singletask = findViewById(R.id.btn_singletask);
        btn_singletask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OtherTaskActivity.this, SingleTaskActivity.class);
                startActivity(intent);
            }
        });
    }
}
