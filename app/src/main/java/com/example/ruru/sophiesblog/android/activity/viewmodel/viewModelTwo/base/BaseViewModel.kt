package com.example.ruru.sophiesblog.android.activity.viewmodel.viewModelTwo.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

/**
 * Created by lyr on 2020/7/28 & content is
 */
open class BaseViewModel(application: Application): AndroidViewModel(application) {
  val progress = MutableLiveData<Boolean>()
}