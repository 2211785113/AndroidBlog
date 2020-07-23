package com.example.ruru.sophiesblog.android.activity.singletop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ruru.sophiesblog.R;

import androidx.appcompat.app.AppCompatActivity;

/**
 * standard模式
 */
public class OtherTopActivity extends AppCompatActivity {

    private Button btn_enter_singletop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_top);

        btn_enter_singletop = findViewById(R.id.btn_enter_singletop);
        btn_enter_singletop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OtherTopActivity.this, SingleTopActivity.class);
                startActivity(intent);
            }
        });
    }
}
