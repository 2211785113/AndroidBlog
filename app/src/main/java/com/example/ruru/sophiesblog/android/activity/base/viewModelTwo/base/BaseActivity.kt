package com.example.ruru.sophiesblog.android.activity.base.viewModelTwo.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.*
import com.example.ruru.sophiesblog.android.activity.base.viewModelTwo.BViewModel

open class BaseActivity: AppCompatActivity(), LifecycleOwner {

  fun <T: BaseViewModel> bindViewModel(cls: Class<T>): T {
    var viewModel = ViewModelProvider(this).get(cls)
    bindProgress(viewModel.progress)
    return viewModel
  }

  fun bindProgress(progressData: MutableLiveData<Boolean>) {
    progressData.observe(this, Observer {
      if (it) {
        showProgressDialog()
      } else {
        dismissProgressDialog()
      }
    })
  }

  private fun showProgressDialog() {

  }

  private fun dismissProgressDialog() {

  }
}
