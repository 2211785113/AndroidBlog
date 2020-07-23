package com.example.ruru.sophiesblog.android.intent.one

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ruru.sophiesblog.R

class Activity1: AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_1)

    var intent = Intent(this, Activity2::class.java)

    var stuList = ArrayList<Stu>()
    stuList.add(Stu(1, "小红"))
    stuList.add(Stu(2, "小里"))

    intent.putParcelableArrayListExtra("list", stuList)

    startActivity(intent)
  }
}
