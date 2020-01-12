package com.example.ruru.sophiesblog.android.kotlin.constructor.kotlin.example1

/**
 * Created by lyr on 2019/12/16
 */
open class SuperClass1(name: String) {

  private var mName: String? = null

  init {
    this.mName = name

    println("SuperClass1 name=$mName")
  }
}