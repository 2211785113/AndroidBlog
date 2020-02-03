package com.example.ruru.sophiesblog.android.kotlin.constructor.kotlin.example2

/**
 * Created by lyr on 2020/1/10 & content is
 */
open class SuperClass2 {

  private var mName: String? = null
  private var mAge: String? = null

  constructor(name: String, age: String) {
    this.mName = name
    this.mAge = age

    println("SuperClass2 name=$mName age=$mAge")
  }
}