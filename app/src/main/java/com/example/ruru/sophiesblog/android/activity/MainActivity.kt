package com.example.ruru.sophiesblog.android.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.ruru.sophiesblog.R

/**
 * 隐藏状态栏 修改状态栏的颜色
 */
class MainActivity: AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    /**隐藏状态栏 设置全屏*/
    /*val decorView = window.decorView //获取屏幕的decorView
    decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN //设置全屏*/

    /**设置状态栏颜色 */
    /*window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.statusBarColor = resources.getColor(R.color.white, null)*/

    /**设置全屏和状态栏透明 设置状态栏为红色*/
    /*val decorView = window.decorView
    decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
    window.statusBarColor = resources.getColor(R.color.colorAccent, null)*/

    setContentView(R.layout.activity_main2)
  }
}
