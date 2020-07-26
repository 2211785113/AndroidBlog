package com.example.ruru.sophiesblog.android.activity.base.viewModelOne

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ruru.sophiesblog.R
import kotlinx.android.synthetic.main.activity_aa.*

class AActivity: AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_aa)

    var viewModel = ViewModelProvider(this).get(ViewModelA::class.java)

    viewModel.data1.observe(this, Observer {
      tv.text = it
    })

    btn_change.setOnClickListener {viewModel.getValue()}
  }
}