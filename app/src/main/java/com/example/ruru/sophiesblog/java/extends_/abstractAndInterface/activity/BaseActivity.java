package com.example.ruru.sophiesblog.java.extends_.abstractAndInterface.activity;

import android.os.Bundle;

import com.example.ruru.sophiesblog.R;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());

        initMembersView(savedInstanceState);
    }

    public abstract int getContentViewId();

    public abstract void initMembersView(Bundle savedInstanceState);
}
