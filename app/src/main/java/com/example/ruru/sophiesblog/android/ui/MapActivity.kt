package com.example.ruru.sophiesblog.android.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.ruru.sophiesblog.R

class MapActivity: AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_map)

    var map = HashMap<Int, String>()
    map[1] = "aaa"
    map[1] = "bbb"
    Log.d("map act", "map act onCreate: map.size = " + map.size)
  }
}