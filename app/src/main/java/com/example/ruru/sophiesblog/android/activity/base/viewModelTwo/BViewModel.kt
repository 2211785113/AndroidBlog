package com.example.ruru.sophiesblog.android.activity.base.viewModelTwo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BViewModel: ViewModel() {

  private var itemsMap = HashMap<String, List<String>>()
  var itemsData = MutableLiveData<List<String>>()

  var containerViewId: Int = - 1

  fun getData(name: String) {
    itemsData.postValue(itemsMap[name])
  }
}