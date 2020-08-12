package com.example.ruru.sophiesblog.android.kotlin.attribute.init

import android.os.Bundle
import android.os.PersistableBundle
import androidx.lifecycle.Observer
import com.example.ruru.sophiesblog.android.activity.viewmodel.viewModelTwo.base.BaseActivity

/**
 * Created by lyr on 2020/7/28 & content is 延迟初始化 1 --- lateinit
 */
class ClassInit: BaseActivity() {

  private lateinit var viewModel: ClassInitViewModel

  override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
    super.onCreate(savedInstanceState, persistentState)

    viewModel = bindViewModel(ClassInitViewModel::class.java)

    viewModel.data.observe(this, Observer {
      //更新数据
    })
  }
}