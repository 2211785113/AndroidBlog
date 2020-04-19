package com.example.ruru.sophiesblog.android.adaptive.multi_language

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.ruru.sophiesblog.R
import java.util.*

/**
 * 资源文件(自动建与手动建)：https://www.jb51.net/article/136192.htm
 */
class MultiLanguage: AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_multi_language)

    //获取首选语言
    var locale = Locale.getDefault()

    Log.d(localClassName, locale.toString())
  }
}
