package com.example.ruru.sophiesblog.android.activity.base.viewModelTwo

import android.os.Bundle
import com.example.ruru.sophiesblog.R
import com.example.ruru.sophiesblog.android.activity.base.viewModelTwo.base.BaseActivity
import kotlinx.android.synthetic.main.activity_bb.*

class BActivity: BaseActivity() {

  private lateinit var viewModel: BViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_bb)

    initView()

    viewModel = bindViewModel(BViewModel::class.java)
    viewModel.containerViewId = R.id.frameLayout

    if (savedInstanceState == null) {
      val transaction = supportFragmentManager.beginTransaction()
      transaction.add(viewModel.containerViewId, B1Fragment.newInstance())
      transaction.commit()
    }
  }

  private fun initView() {
    toolBar.setNavigationOnClickListener {
      onBackPressed()
    }
  }
}