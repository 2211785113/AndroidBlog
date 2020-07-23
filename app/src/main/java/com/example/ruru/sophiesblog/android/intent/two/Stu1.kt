package com.example.ruru.sophiesblog.android.intent.two

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


class Stu1: Parcelable {

  var people: People? = null
  var type = 0

  constructor(parcel: Parcel) {
    people = parcel.readParcelable(People::class.java.classLoader)
    type = parcel.readInt()
  }

  constructor(people: People) {
    this.people = people
    type = 1
  }

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeParcelable(people, flags)
    parcel.writeInt(type)
  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR: Parcelable.Creator<Stu1> {
    override fun createFromParcel(parcel: Parcel): Stu1 {
      return Stu1(parcel)
    }

    override fun newArray(size: Int): Array<Stu1?> {
      return arrayOfNulls(size)
    }
  }
}