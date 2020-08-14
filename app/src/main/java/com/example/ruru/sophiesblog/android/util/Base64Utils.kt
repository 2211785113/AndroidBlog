package com.example.ruru.sophiesblog.android.util

import android.text.TextUtils
import android.util.Base64
import java.io.UnsupportedEncodingException

/**
 * Created by lyr on 2020/8/14 & content is Base64 编解码
 */
class Base64Utils {
  companion object {
    /*
    * 编码
    * str-->>>传值得时候 str 不为空 外部调用时进行判断
    */
    open fun encodeToString(str: String): String? {
      //      if (TextUtils.isEmpty(str)) return str 不需要
      try {
        return Base64.encodeToString(str.toByteArray(charset("UTF-8")), Base64.DEFAULT)
      } catch (e: UnsupportedEncodingException) {
        e.printStackTrace()
      }
      return str
    }

    /**
     * 解码
     */
    fun decodeToString(str: String): String? {
      if (TextUtils.isEmpty(str)) return str
      try {
        return String(Base64.decode(str.toByteArray(charset("UTF-8")), Base64.DEFAULT))
      } catch (e: UnsupportedEncodingException) {
        e.printStackTrace()
      }
      return str
    }
  }
}