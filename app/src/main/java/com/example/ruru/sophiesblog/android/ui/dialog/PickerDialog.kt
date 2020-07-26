package com.example.ruru.sophiesblog.android.ui.dialog

import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import com.example.ruru.sophiesblog.R

/**
 * Created by lyr on 2020/1/17 & content is 日期时间选择对话框
 */
class PickerDialog: Dialog {

  constructor(context: Context): super(context) {
    initView(context)
  }

  private fun initView(context: Context) {
    val view = LayoutInflater.from(context).inflate(R.layout.dialog_bottom, null)
    setContentView(view)

    window.setGravity(Gravity.BOTTOM)

  }
}