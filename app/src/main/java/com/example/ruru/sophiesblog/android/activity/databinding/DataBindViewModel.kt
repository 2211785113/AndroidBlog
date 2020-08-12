package com.example.ruru.sophiesblog.android.activity.databinding

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.ruru.sophiesblog.android.activity.viewmodel.viewModelTwo.base.BaseViewModel

/**
 * Created by lyr on 2020/7/29 & content is
 */
class DataBindViewModel(application: Application): BaseViewModel(application) {
  val data = MutableLiveData<String>()
  var name = "你好阳光"
}