package com.example.ruru.sophiesblog.android.fragment.top

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ruru.sophiesblog.R

class FragmentOne: Fragment() {

  private val TAG = "FragmentOne"

  override fun onAttach(context: Context?) {
    super.onAttach(context)
    Log.d(TAG, "onAttach")
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    Log.d(TAG, "onCreate")
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?): View? {
    Log.d(TAG, "onCreateView")
    return inflater.inflate(R.layout.fragment_fragment_one, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    Log.d(TAG, "onActivityCreated")
  }

  override fun onStart() {
    super.onStart()
    Log.d(TAG, "onStart")

  }

  override fun onResume() {
    super.onResume()
    Log.d(TAG, "onResume")

  }

  override fun onPause() {
    super.onPause()
    Log.d(TAG, "onPause")

  }

  override fun onStop() {
    super.onStop()
    Log.d(TAG, "onStop")

  }

  override fun onDestroyView() {
    super.onDestroyView()
    Log.d(TAG, "onDestroyView")

  }

  override fun onDestroy() {
    super.onDestroy()
    Log.d(TAG, "onDestroy")

  }

  override fun onDetach() {
    super.onDetach()
    Log.d(TAG, "onDetach")

  }


}
