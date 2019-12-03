package com.example.ruru.sophiesblog.android.activity.singleinstance

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.ruru.sophiesblog.R

class SingleInstanceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_instance)
    }
}