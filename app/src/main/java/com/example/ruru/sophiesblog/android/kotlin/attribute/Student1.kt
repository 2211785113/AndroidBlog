package com.example.ruru.sophiesblog.android.kotlin.attribute

/**
 * Created by lyr on 2020/1/12 & content is
 */
class Student1 {
  private var name: String = ""
  private var age: Int = 0

  fun getName() = name
  fun setName(name: String) {
    this.name = name
  }

  fun getAge() = age
  fun setAge(age: Int) {
    this.age = age
  }
}