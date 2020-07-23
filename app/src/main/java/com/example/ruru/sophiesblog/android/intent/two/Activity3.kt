package com.example.ruru.sophiesblog.android.intent.two

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ruru.sophiesblog.R
import com.example.ruru.sophiesblog.android.intent.one.Activity2
import com.example.ruru.sophiesblog.android.intent.one.Stu

class Activity3: AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_3)

    var intent = Intent(this, Activity4::class.java)

    var stuList = ArrayList<Stu1>()
    stuList.add(Stu1(People(2, "你好")))
    stuList.add(Stu1(People(3, "阳光")))
    intent.putParcelableArrayListExtra("list", stuList)
    startActivity(intent)
  }
}
