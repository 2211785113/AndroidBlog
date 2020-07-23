package com.example.ruru.sophiesblog.android.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MultiCheckViewModel: ViewModel() {

  val list = MutableLiveData<String>()
  var selectedList = arrayListOf<MultiCheck>()

  init {
    list.postValue(arrayListOf<String>("aa", "bb").toString())
  }
}