package com.example.ruru.sophiesblog.android.activity.base.viewModelTwo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ruru.sophiesblog.R
import com.example.ruru.sophiesblog.android.activity.base.viewModelTwo.base.BaseFragment

/**
 * fragment 获取 父布局的  activity：
 * 1.getActivity :调用父布局 context 获取实例 -->> FragmentActivity $activity
 * 2.onAttach 中 context ${getBaseActivity()}
 * --->>都是BActivity
 */
class B1Fragment: BaseFragment() {

  private lateinit var viewModel: BViewModel

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?): View? {
    val view = inflater.inflate(R.layout.fragment_b1, container, false)
    view.findViewById<TextView>(R.id.nextFragment).setOnClickListener {
      onNewFragment()
    }
    return view
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    viewModel = bindActivityViewModel(BViewModel::class.java)

    initData()
  }

  private fun initData() {
    viewModel.itemsData.observe(this, Observer {
      //设置数据
    })
  }

  override fun onStart() {
    super.onStart()
    viewModel.getData(this.javaClass.name)
  }

  private fun onNewFragment() {
    activity?.let {activity ->
      var transaction = activity.supportFragmentManager.beginTransaction()
      transaction.replace(viewModel.containerViewId, B2Fragment.newInstance())
      transaction.addToBackStack(null).commit()
    }
  }

  companion object {
    fun newInstance(): Fragment = B1Fragment()
  }
}