package com.example.ruru.sophiesblog.android.kotlin.code

import com.example.ruru.sophiesblog.android.kotlin.clazz.User

/**
 * Created by lyr on 2020/1/8 & content is kotlin入口函数
 */
private lateinit var name: String
private lateinit var user: User

fun main() {
  println("hello world")

  // kotlin换行
  var c = """
    nihao s
     ygd
       klsh
  """.trimIndent()

  println("main c=$c")


  //lateinit是否初始化测试
  name = "你好"

  if (::name.isInitialized) {
    println("name=$name")
  }

  user = User(1, "啊啊啊")

  if (isNameInit()) {
    println("user.name=${user.name}")
  }
}

fun isNameInit() = ::name.isInitialized

data class User(var name: String)