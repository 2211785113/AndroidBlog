package com.example.ruru.sophiesblog.android.adaptive.screen;

import android.os.Bundle;

import com.example.ruru.sophiesblog.R;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 布局适配-限定符：双面板布局用large,sw600dp限定符
 */
public class Page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);
    }
}
