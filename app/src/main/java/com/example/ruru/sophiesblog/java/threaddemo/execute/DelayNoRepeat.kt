package com.example.ruru.sophiesblog.java.threaddemo.execute

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ruru.sophiesblog.R

/**
 * 延时去重执行
 * 场景：多次重复执行网络请求 需要去重
 */
class DelayNoRepeat: AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_delay_no_repeat)

    DelayDistinctExecutor.submit(0xCBA, 300) {

    }
  }
}
