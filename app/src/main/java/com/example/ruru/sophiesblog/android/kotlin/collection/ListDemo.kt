package com.example.ruru.sophiesblog.android.kotlin.collection

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ruru.sophiesblog.R
import java.sql.DriverManager.println

class ListDemo: AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_list_demo)

    // 获取list集合最后一个元素的index索引，获取所有元素的索引
    val list = arrayListOf("aa", "bb", "cc")
    /*println("list.lastIndex=${list.lastIndex}") // 2
    println("list.indices=${list.indices}") // 0..2

    // 遍历list集合的元素带索引
    val indexList = list.withIndex()
    for (item in indexList) {
      println("list.withIndex=${item}") // IndexedValue(index=0, value=aa)  IndexedValue(index=1, value=bb) IndexedValue(index=2, value=cc)
    }

    // 遍历list集合的索引和元素
    for ((index, value) in list.withIndex()) {
      println("index=$index value=$value")
    }*/

    for (index in 0 .. 2) { //aa bb cc
      println("item=${list[index]}")
    }
  }
}
