package com.example.ruru.sophiesblog.java;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;

public class AString extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_string);

        String a = "123456";
        a.substring(0, 3);
        Log.d(getClass().getName(), "onCreate:res= " + a);
    }
}
