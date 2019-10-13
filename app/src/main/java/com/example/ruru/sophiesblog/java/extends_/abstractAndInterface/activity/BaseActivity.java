package com.example.ruru.sophiesblog.java.extends_.abstractAndInterface.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ruru.sophiesblog.R;

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
