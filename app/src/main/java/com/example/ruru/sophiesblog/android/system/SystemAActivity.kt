package com.example.ruru.sophiesblog.android.system

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ruru.sophiesblog.R
import kotlinx.android.synthetic.main.activity_system_a.*

/**Android 侧滑 返回*/
class SystemAActivity: AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_system_a)

    btn_skip.setOnClickListener {
      startActivity(Intent(this, SystemBActivity::class.java))
    }
  }
}