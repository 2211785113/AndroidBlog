package com.example.ruru.sophiesblog.android.adapter;

import android.os.Bundle;

import com.example.ruru.sophiesblog.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewA extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        RecyclerView recv = findViewById(R.id.recv);

    }
}
