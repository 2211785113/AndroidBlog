package com.example.ruru.sophiesblog.android.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.View
import android.view.ViewGroup

/**
 * Created by lyr on 2019/12/8
 */
class VpAdapter(supportFragmentManager: FragmentManager, private var list: List<Fragment>, private var titles: List<String>):
  FragmentPagerAdapter(supportFragmentManager) {

  override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
    super.destroyItem(container, position, `object`)
  }

  override fun getItem(p0: Int): Fragment {
    return list.get(p0)
  }

  override fun getCount(): Int {
    return list.size
  }

  override fun getPageTitle(position: Int): CharSequence? {
    return titles.get(position)
  }
}