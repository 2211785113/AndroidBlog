package com.example.ruru.sophiesblog.android.adapter

import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

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