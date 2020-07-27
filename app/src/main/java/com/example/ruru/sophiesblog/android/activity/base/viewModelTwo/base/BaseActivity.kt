package com.example.ruru.sophiesblog.android.activity.base.viewModelTwo.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ruru.sophiesblog.android.activity.base.viewModelTwo.BViewModel

open class BaseActivity: AppCompatActivity() {

  fun <T: ViewModel> bindViewModel(cls: Class<T>): T {
    var viewModel = ViewModelProvider(this).get(cls)
    return viewModel
  }
}
