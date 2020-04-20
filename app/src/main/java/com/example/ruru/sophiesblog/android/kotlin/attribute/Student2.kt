package com.example.ruru.sophiesblog.android.kotlin.attribute

import org.xutils.view.annotation.ViewInject

/**
 * Created by lyr on 2020/1/12 & content is
 */
class Student2 {
  var name: String
    get() = this.name
    set(value) {
      this.name = value
    }

  var age: Int
    get() = this.age // 22
    set(value) {
      this.age = value
    }

  var height: Int
    get() = this.height
    private set(value) {
      this.height = value
    }

  var width: Int
    get() = this.width
    /*可以加注解*/ set(value) {
      this.width = value
    }
}