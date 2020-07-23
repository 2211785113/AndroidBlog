package com.example.ruru.sophiesblog.android.ui.keyboard

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.example.ruru.sophiesblog.R
import kotlinx.android.synthetic.main.activity_test.*

/**
 * 飞书登录键盘效果
 *
 * 第一种方案：设置adjustResize，LL设置Space控件 控制比例为3比5 优点:连带顶部图标直接滑动上去 缺点:感觉缺少一点滑动效果
 * 第二种方案：滑动高度 开始设置3比5，键盘弹起时移动高度为space顶部的高度-5
 * 统一：et获取到焦点的时候页面整体上移 不用判断高度
 * 统一：整体上移的高度为
 *
 * adjustResize：该Activity总是调整屏幕的大小以便留出软键盘的空间
 * adjustPan：当前窗口的内容将自动移动以便当前焦点从不被键盘覆盖，并且用户总能看到输入内容的部分
 */
class TestActivity: AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_test)

    etName.hint.length
    println("EETTT ${etName.selectionStart} ${etName.selectionEnd} ${etName.hint.length}")
    etName.setSelection((etName.hint.length)/2)

    etName.addTextChangedListener(object: TextWatcher {
      override fun afterTextChanged(s: Editable?) {

      }

      override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

      }

      override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
      }
    })
  }
}
