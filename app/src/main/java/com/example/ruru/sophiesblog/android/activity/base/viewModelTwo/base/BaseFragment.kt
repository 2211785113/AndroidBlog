package com.example.ruru.sophiesblog.android.activity.base.viewModelTwo.base

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

open class BaseFragment: Fragment() {

  private var baseActivity: Activity? = null

  override fun onAttach(context: Context) {
    super.onAttach(context)
    baseActivity = context as Activity
  }

  open fun getBaseActivity(): Activity? = baseActivity

  fun <T: ViewModel> bindViewModel(cls: Class<T>): T {
    return ViewModelProvider(activity !!).get(cls)
  }
}
