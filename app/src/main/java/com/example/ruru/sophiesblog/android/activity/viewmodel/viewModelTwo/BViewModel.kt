package com.example.ruru.sophiesblog.android.activity.viewmodel.viewModelTwo

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.ruru.sophiesblog.android.activity.viewmodel.viewModelTwo.base.BaseViewModel

class BViewModel(application: Application): BaseViewModel(application) {

  private var itemsMap = HashMap<String, List<String>>()
  var itemsData = MutableLiveData<List<String>>()

  var containerViewId: Int = - 1

  fun getData(name: String) {
    progress.postValue(true)
    itemsData.postValue(itemsMap[name])
    progress.postValue(false)
  }
}