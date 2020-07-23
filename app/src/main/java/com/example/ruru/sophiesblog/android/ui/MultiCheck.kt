package com.example.ruru.sophiesblog.android.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ruru.sophiesblog.R
import com.example.ruru.sophiesblog.android.ui.adapter.MultiCheckAdapter
import kotlinx.android.synthetic.main.layout_multi_check.*

class MultiCheck: AppCompatActivity() {

  private lateinit var adapter: MultiCheckAdapter
  private lateinit var viewModel: MultiCheckViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.layout_multi_check)

    var viewModel = ViewModelProvider(this).get(MultiCheckViewModel::class.java)

    /*adapter = MultiCheckAdapter(R.layout.layout_multi_check_item, )
    recv.layoutManager = LinearLayoutManager(this)
    recv.adapter = adapter*/
  }
}