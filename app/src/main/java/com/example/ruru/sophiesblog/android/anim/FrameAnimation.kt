package com.example.ruru.sophiesblog.android.anim

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ruru.sophiesblog.R
import kotlinx.android.synthetic.main.activity_frame_animation.*

class FrameAnimation: AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_frame_animation)

    val drawable= img.drawable as AnimationDrawable
    drawable.start()
  }
}
