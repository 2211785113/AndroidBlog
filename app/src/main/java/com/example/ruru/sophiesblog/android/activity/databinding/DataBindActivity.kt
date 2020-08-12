package com.example.ruru.sophiesblog.android.activity.databinding

import android.os.Bundle
import android.os.PersistableBundle
import com.example.ruru.sophiesblog.R
import com.example.ruru.sophiesblog.android.activity.viewmodel.viewModelTwo.base.BaseActivity
import kotlinx.android.synthetic.main.layout_data_bind.*

/**
 * Created by lyr on 2020/7/29 & content is 数据绑定库
 */
class DataBindActivity: BaseActivity() {

  private lateinit var viewModel: DataBindViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.layout_data_bind)

    viewModel = bindViewModel(DataBindViewModel::class.java)

    // 没用数据绑定库 之前：数据 加载到 UI控件上
    //    tv_name.text = viewModel.name
  }
}