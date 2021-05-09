package com.example.ruru.sophiesblog.android.camera

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import com.example.ruru.sophiesblog.R

/**
 * Created by lyr on 2021/5/9 & content is 摄像头View
 */
class LiveView @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defStyleAttr: Int = 0):
  FrameLayout(context, attributeSet, defStyleAttr) {

  private var imgView: ImageView

  init {
    var view = LayoutInflater.from(context).inflate(R.layout.layout_live_view, this, true)
    imgView = view.findViewById(R.id.imgView)
  }

  /**
   * 可自行添加动画效果
   */
  fun changeLayout(left: Int, top: Int, right: Int, bottom: Int) {
    synchronized(this) {
      imgView.layout(left, top, right, bottom)
    }
  }
}