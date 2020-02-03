package com.example.ruru.sophiesblog.android.kotlin.override.kotlin

/**
 * Created by lyr on 2020/1/12 & content is
 * 子类必须实现超类中的方法，不是必须实现接口中的方法
 */
class SubClass: SuperClass(), SuperInterface {
  override fun execute() {
    super<SuperClass>.execute()
    super<SuperInterface>.execute()
  }
}