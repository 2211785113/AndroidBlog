package com.example.ruru.sophiesblog.android.kotlin.constructor

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.ruru.sophiesblog.R
import com.example.ruru.sophiesblog.android.kotlin.constructor.java.SubClass
import com.example.ruru.sophiesblog.android.kotlin.constructor.kotlin.example1.SubClass1
import com.example.ruru.sophiesblog.android.kotlin.constructor.kotlin.example2.SubClass2
import com.example.ruru.sophiesblog.android.kotlin.constructor.kotlin.example3.SubClass3

/**
 * 构造函数传值测试
 */
class ConstructorActivity: AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_constructor)

    // 打印SuperClass name
    val subClass = SubClass("小红", "11")

    // 打印SuperClass1 name
    val subClass1 = SubClass1("小明", "12")

    // 打印SuperClass2 name&age
    val subClass2 = SubClass2("小李", "13")

    // 打印SuperClass3 name
    val subClass3 = SubClass3("小丽", "14")
  }
}
