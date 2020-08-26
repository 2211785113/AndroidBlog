package com.example.ruru.sophiesblog.android.activity.viewmodel.viewModelTwo.base

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*

open class BaseActivity: AppCompatActivity(), LifecycleOwner {

  fun <T: BaseViewModel> bindViewModel(cls: Class<T>): T {
    println("data bind viewModel $this $cls")
    println("data bind viewModel cla.canonicalName ${cls.canonicalName}")

    var viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(cls)
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
