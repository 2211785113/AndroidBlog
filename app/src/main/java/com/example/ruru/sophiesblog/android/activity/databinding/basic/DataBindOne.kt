package com.example.ruru.sophiesblog.android.activity.databinding.basic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.ruru.sophiesblog.R
import com.example.ruru.sophiesblog.databinding.LayoutDataBindOneBinding

/**
 * DataBinding 类的位置：app-build-generated-databinding
 */
class DataBindOne: AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    //    var binding = DataBindingUtil.setContentView<LayoutDataBindOneBinding>(this, R.layout.layout_data_bind_one) // 这个可以

    //第二行必须有 否则 View 无法显示数据
    var binding = LayoutDataBindOneBinding.inflate(layoutInflater) // 这个不可以
    setContentView(binding.root)

    var user = User()
    user.setName("小红")
    user.setGender("女")
    binding.user = user
  }
}