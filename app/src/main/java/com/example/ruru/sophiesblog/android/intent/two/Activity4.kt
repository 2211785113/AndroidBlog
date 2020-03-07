package com.example.ruru.sophiesblog.android.intent.two

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ruru.sophiesblog.R
import com.example.ruru.sophiesblog.android.intent.one.Stu

class Activity4: AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_4)

    val list = intent.getParcelableArrayListExtra<Stu1>("list")
    for (item in list) {
      println("item=${item.people?.name}")
    }
  }
}
