package com.example.ruru.sophiesblog.android.activity.base.viewModelOne

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelA: ViewModel() {

  var data1 = MutableLiveData<String>()

  init {
    data1.postValue("nihao")
  }

  fun getValue(){
    data1.postValue("yangguang")
  }
}