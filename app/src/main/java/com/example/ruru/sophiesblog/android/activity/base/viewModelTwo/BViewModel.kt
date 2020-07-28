package com.example.ruru.sophiesblog.android.activity.base.viewModelTwo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ruru.sophiesblog.android.activity.base.viewModelTwo.base.BaseViewModel

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