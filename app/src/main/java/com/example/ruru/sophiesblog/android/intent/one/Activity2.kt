package com.example.ruru.sophiesblog.android.intent.one

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ruru.sophiesblog.R

class Activity2: AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_2)

    val list = intent.getParcelableArrayListExtra<Stu>("list")
    for (item in list) {
      println("item=${item.id} ${item.name}")
    }
  }
}
