package com.example.ruru.sophiesblog.android.fragment

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.ruru.sophiesblog.R
import com.example.ruru.sophiesblog.android.adapter.VpAdapter
import kotlinx.android.synthetic.main.activity_fragment.*

class FragmentActivity: AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_fragment)

    val list = ArrayList<Fragment>()
    list.add(FragmentOne())
    list.add(FragmentTwo())

    val titles = listOf<String>("nihao1", "nihao2")

    viewPager.adapter = VpAdapter(supportFragmentManager, list, titles)
    tabLayout.setupWithViewPager(viewPager)
  }
}
