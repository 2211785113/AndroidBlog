package com.example.ruru.sophiesblog.android.activity.databinding.basic

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.ruru.sophiesblog.BR

/**
 * Created by lyr on 2020/8/24 & content is Model 数据类 实时变化
 */
class User: BaseObservable() {

  private var name: String = ""
  private var gender: String = ""

  @Bindable
  fun getName() = this.name

  fun setName(name: String) {
    this.name = name
    notifyPropertyChanged(BR.name)
  }

  @Bindable
  fun getGender() = this.gender

  fun setGender(gender: String) {
    this.gender = gender
    notifyPropertyChanged(BR.gender)
  }
}