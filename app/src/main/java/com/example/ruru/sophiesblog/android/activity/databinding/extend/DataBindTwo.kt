package com.example.ruru.sophiesblog.android.activity.databinding.extend

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.ruru.sophiesblog.R
import com.example.ruru.sophiesblog.android.activity.viewmodel.viewModelTwo.base.BaseActivity
import com.example.ruru.sophiesblog.databinding.LayoutDataBindTwoBinding

/**
 * Created by lyr on 2020/7/29 & content is 数据绑定库
 * DataBinding 关联 View
 * DataBinding 设置数据
 * 数据显示到View上
 */
class DataBindTwo: BaseActivity() {

  private lateinit var viewModel: DataBindViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    var binding = DataBindingUtil.setContentView<LayoutDataBindTwoBinding>(this, R.layout.layout_data_bind_two)
    var viewModel = bindViewModel(DataBindViewModel::class.java)

    // 此处不能 强转为 DataBindViewModel 因为 binding.viewModel 是一个复杂表达式
    /*binding.viewModel = bindViewModel(DataBindViewModel::class.java)
    binding.viewModel.name = "222222222222222"*/
  }
}