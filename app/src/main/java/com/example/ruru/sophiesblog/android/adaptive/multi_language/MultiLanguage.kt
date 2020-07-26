package com.example.ruru.sophiesblog.android.adaptive.multi_language

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.ruru.sophiesblog.R
import com.example.ruru.sophiesblog.android.adaptive.multi_language.LocaleUtils.Companion.needUpdate
import com.example.ruru.sophiesblog.android.adaptive.multi_language.LocaleUtils.Companion.updateLang
import kotlinx.android.synthetic.main.activity_multi_language.*
import java.util.*

/**
 * Android 应用外系统设置多语言切换（应用随系统语言改变）
 * Android 应用内实现多语言切换（应用不随系统语言改变）https://blog.csdn.net/cekiasoo/article/details/54933135
 * 资源文件(自动建与手动建)：https://www.jb51.net/article/136192.htm
 */
class MultiLanguage: AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_multi_language)

    toolBar.inflateMenu(R.menu.menu)
    toolBar.setOnMenuItemClickListener {item ->
      when(item.itemId) {
        R.id.first -> {
          println("current click zh")
          if (needUpdate(this, LocaleUtils.LOCALE_CHINESE)) {
            updateLang(this, LocaleUtils.LOCALE_CHINESE)
            reStartAct()
          }
        }
        R.id.second -> {
          println("current click en")
          if (needUpdate(this, LocaleUtils.LOCALE_ENGLISH)) {
            updateLang(this, LocaleUtils.LOCALE_ENGLISH)
            reStartAct()
          }
        }
        else -> {

        }
      }
      return@setOnMenuItemClickListener false
    }
  }

  /** 重新启动act界面 */
  private fun reStartAct() {
    var intent = Intent(this, MultiLanguage::class.java)
    startActivity(intent)
    overridePendingTransition(0, 0)
    //获取首选语言
    var locale = Locale.getDefault()

    Log.d(localClassName, locale.toString())
  }
}

