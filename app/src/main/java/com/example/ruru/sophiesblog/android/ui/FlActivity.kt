package com.example.ruru.sophiesblog.android.ui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import com.example.ruru.sophiesblog.R
import kotlinx.android.synthetic.main.activity_fl.*

class FlActivity: AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_fl)

    var imgView = View(this)
    imgView.setBackgroundColor(Color.parseColor("#DCDCDC"))
    imgView.layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, 50)
    fl_layout.addView(imgView, 0)

    var imgView1 = View(this)
    imgView1.setBackgroundColor(Color.parseColor("#DC143C"))
    imgView1.layoutParams = ViewGroup.LayoutParams(30, 50)
    fl_layout.addView(imgView1, 1)

    var imgView2 = View(this)
    imgView2.setBackgroundColor(Color.parseColor("#AFEEEE"))
    imgView2.layoutParams = ViewGroup.LayoutParams(100, 50)
    fl_layout.addView(imgView2, 2)

    var endImgView = View(this)
    endImgView.setBackgroundColor(Color.parseColor("#000000"))
    endImgView.layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, 50)
    fl_layout.addView(endImgView, 3)
  }
}