package com.example.ruru.sophiesblog.android.activity.base.viewModelTwo.base

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

open class BaseFragment: Fragment() {

  private var baseActivity: BaseActivity? = null

  override fun onAttach(context: Context) {
    super.onAttach(context)
    baseActivity = context as BaseActivity
  }

  open fun getBaseActivity(): BaseActivity? = baseActivity

  fun <T: BaseViewModel> bindActivityViewModel(cls: Class<T>): T {
    return if (baseActivity != null) {
      ViewModelProvider(baseActivity !!).get(cls)
    } else {
      bindViewModel(cls)
    }
  }

  fun <T: BaseViewModel> bindViewModel(cls: Class<T>): T {
    var viewModel = ViewModelProvider(this).get(cls)
    bindProgress(viewModel.progress)
    return viewModel
  }

  fun bindProgress(progressData: MutableLiveData<Boolean>) {
    progressData.observe(if (getBaseActivity() == null) this else getBaseActivity() !!, Observer {
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
