package com.example.ruru.sophiesblog.android.util.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ruru.sophiesblog.R
import com.example.ruru.sophiesblog.android.util.Base64Utils

class Base64Dmo: AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_base64_dmo)

    var str = "5Y+8"
    Base64Utils.decodeToString(str)
  }
}
