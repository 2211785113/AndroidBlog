package com.example.ruru.sophiesblog.android.kotlin.override

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ruru.sophiesblog.R
import com.example.ruru.sophiesblog.android.kotlin.override.kotlin.SubClass

class OverrideActivity: AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_override_kotlin)

    var subClass = SubClass()
    subClass.execute()
  }
}
