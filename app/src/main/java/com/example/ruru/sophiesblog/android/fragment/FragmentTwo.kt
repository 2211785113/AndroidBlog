package com.example.ruru.sophiesblog.android.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.ruru.sophiesblog.R

/**
 * A simple [Fragment] subclass.
 */
class FragmentTwo: Fragment() {
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_fragment_two, container, false)
  }
}
