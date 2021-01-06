package com.example.ruru.sophiesblog.android.objectbox

import android.content.Context
import io.objectbox.BoxStore

/**
 * Created by lyr on 2020/12/1 & content is
 */
object ObjectBox {
  lateinit var boxStore:BoxStore
  private set

  fun init(context:Context){
    boxStore = MyObjectBox.builder()
      .androidContext(context.applicationContext)
      .build()
  }
}