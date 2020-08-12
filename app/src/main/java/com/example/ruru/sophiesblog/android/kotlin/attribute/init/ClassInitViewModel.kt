package com.example.ruru.sophiesblog.android.kotlin.attribute.init

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.ruru.sophiesblog.android.activity.viewmodel.viewModelTwo.base.BaseViewModel

/**
 * Created by lyr on 2020/7/28 & content is
 */
class ClassInitViewModel(application: Application): BaseViewModel(application) {

  val data: MutableLiveData<String> by lazy {MutableLiveData<String>()}

  //  val dataInt by lazy {MutableLiveData<Int>()}
}