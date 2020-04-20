package com.example.ruru.sophiesblog.android.kotlin.collection

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.ruru.sophiesblog.R

class MapDemo: AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_map_demo)

    val map = mapOf("aa" to 1, "bb" to 2, "cc" to 3)
    println("map[aa]=${map["aa"]}") // 1
  }
}
