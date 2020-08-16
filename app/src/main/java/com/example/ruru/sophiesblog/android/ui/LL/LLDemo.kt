package com.example.ruru.sophiesblog.android.ui.LL

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ruru.sophiesblog.R

/**
 * 定义：什么是剩余空间。
 * 系统  先根据  width 属性 分配宽度
 * 然后  再根据 weight 属性 分配剩余空间
 * 最后的宽度 = 原来 width 宽度 + 剩余空间 weight 宽度
 *
 * 链接：https://blog.csdn.net/qq_2300688967/article/details/81003994
 */
class LLDemo: AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_l_l_demo)
  }
}
