package com.example.ruru.sophiesblog.android.kotlin.code

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.ruru.sophiesblog.R

class ListDemo: AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_list_demo)

    val list = listOf("aa", "bb", "cc")
    println("list.lastIndex=${list.lastIndex}") // 2
    println("list.indices=${list.indices}") // 0..2

    val mapOf = mapOf("aa" to 1, "bb" to 2, "cc" to 3)
    println("map[key]=${mapOf["aa"]}") // 1

    val indexList = list.withIndex()
    for (item in indexList) {
      println("list.withIndex=${item}") // IndexedValue(index=0, value=aa)  IndexedValue(index=1, value=bb) IndexedValue(index=2, value=cc)
    }

    for ((index,value) in list.withIndex()){
      println("index=$index value=$value")
    }
  }
}
