package com.example.ruru.sophiesblog.android.kotlin.kotlin

/**
 * Created by lyr on 2019/12/16
 */
class SubClass(name: String): SuperClass(name) {

  private var mName: String? = null


  init {
    this.mName = name
  }

  private var mAge: String? = null

  constructor(name: String, age: String): this(name) {
    this.mAge = age
  }
}