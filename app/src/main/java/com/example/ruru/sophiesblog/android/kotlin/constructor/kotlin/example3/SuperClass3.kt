package com.example.ruru.sophiesblog.android.kotlin.constructor.kotlin.example3

/**
 * Created by lyr on 2020/1/12 & content is
 */
open class SuperClass3 {

  private var mName: String? = null

  constructor(name: String) {
    this.mName = name

    println("SuperClass3 name=$mName")
  }
}