package com.example.ruru.sophiesblog.android.kotlin.attribute

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.ruru.sophiesblog.R

class AttributeDemo: AppCompatActivity() {
  var name: String
    get() = "aa"
    set(value) {
      this.name = value
    }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_attribute_demo)
  }
}
