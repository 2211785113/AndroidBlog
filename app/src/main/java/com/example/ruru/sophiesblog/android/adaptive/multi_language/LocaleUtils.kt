package com.example.ruru.sophiesblog.android.adaptive.multi_language

import android.content.Context
import android.os.Build
import java.util.*

/**
 * Created by lyr on 2020/4/20 & content is 语言环境工具类
 *
 * Locale:语言环境类
 * locale.getLanguage:获取该国家的语言
 */
class LocaleUtils {

  companion object {
    open val LOCALE_CHINESE = Locale.CHINESE
    open val LOCALE_ENGLISH = Locale.ENGLISH

    /** 判断是否需要更新多语言 */
    fun needUpdate(context: Context, newLocale: Locale): Boolean {
      var res = when(newLocale) {
        Locale.CHINESE -> {
          getCurrentLang(context).language.contains("zh") || getCurrentLang(context).language.contains("zh_CN")
        }
        LOCALE_ENGLISH -> {
          getCurrentLang(context).language.contains("en")
        }
        else -> {
          false
        }
      }
      println("current need update=${! res}")
      return ! res
    }

    /** 获取现在的多语言 */
    fun getCurrentLang(context: Context): Locale {
      println("current lang=${if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { //7.0有多语言设置获取顶部的语言
        context.resources.configuration.locales.get(0)
      } else {
        context.resources.configuration.locale
      }}")

      return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { //7.0有多语言设置获取顶部的语言
        context.resources.configuration.locales.get(0)
      } else {
        context.resources.configuration.locale
      }
    }

    /** 更新多语言 */
    fun updateLang(context: Context, locale: Locale) {
      var config = context.resources.configuration
      if (needUpdate(context, locale)) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1) {
          config.setLocale(locale)
        } else {
          config.locale = locale
        }

        var displayMetrics = context.resources.displayMetrics
        context.resources.updateConfiguration(config, displayMetrics)
      }
  }
  }
}