package com.example.ruru.sophiesblog.android.intent.two

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


class People(val id: Int, val name: String): Parcelable {
  constructor(parcel: Parcel): this(
    parcel.readInt(),
    parcel.readString()) {
  }

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeInt(id)
    parcel.writeString(name)
  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR: Parcelable.Creator<People> {
    override fun createFromParcel(parcel: Parcel): People {
      return People(parcel)
    }

    override fun newArray(size: Int): Array<People?> {
      return arrayOfNulls(size)
    }
  }
}