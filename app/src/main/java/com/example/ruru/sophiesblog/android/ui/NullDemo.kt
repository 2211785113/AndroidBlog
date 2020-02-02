package com.example.ruru.sophiesblog.android.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.ruru.sophiesblog.R
import kotlinx.android.synthetic.main.activity_null_demo.*

class NullDemo: AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_null_demo)

    // 不管view是否可见，都不为空null，都返回false
    Log.d(localClassName, "etName1=${etName1 == null}")
    Log.d(localClassName, "etName2=${etName2 == null}")
    Log.d(localClassName, "etName3=${etName3 == null}")
  }
}
