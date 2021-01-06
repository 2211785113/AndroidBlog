package com.example.ruru.sophiesblog.java

/**
 * Created by lyr on 2020/12/15 & content is
 */
object CosUtils {

  var id = 0
  var str = ""
  var expire = 0L

  fun get() {
    if (System.currentTimeMillis() > 0) {
      id = 1
      str = "夏普将陪你过"
      expire = System.currentTimeMillis()
      put()
    } else {
      put()
    }
  }

  private fun put() {
    println("cosutils id=$id str=$str")
  }
}