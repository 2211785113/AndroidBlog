package com.example.ruru.sophiesblog.android.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.example.ruru.sophiesblog.R
import com.example.ruru.sophiesblog.android.activity.singletask.SingleTaskActivity
import com.example.ruru.sophiesblog.android.activity.singletop.SingleTopActivity
import com.example.ruru.sophiesblog.android.activity.standard.StandardActivity

class ActivityDemo : AppCompatActivity() {
    private var btn_enter_standard: Button? = null
    private var btn_enter_singletop: Button? = null
    private var btn_enter_singletask: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo)
        initView()
        initClick()
    }

    private fun initView() {
        btn_enter_standard = findViewById(R.id.btn_enter_standard)
        btn_enter_singletop = findViewById(R.id.btn_enter_singletop)
        btn_enter_singletask = findViewById(R.id.btn_enter_singletask)
    }

    private fun initClick() {
        btn_enter_standard!!.setOnClickListener {
            val intent = Intent(this@ActivityDemo, StandardActivity::class.java)
            startActivity(intent)
        }
        btn_enter_singletop!!.setOnClickListener {
            val intent = Intent(this@ActivityDemo, SingleTopActivity::class.java)
            startActivity(intent)
        }
        btn_enter_singletask!!.setOnClickListener {
            val intent = Intent(this@ActivityDemo, SingleTaskActivity::class.java)
            startActivity(intent)
        }
    }
}