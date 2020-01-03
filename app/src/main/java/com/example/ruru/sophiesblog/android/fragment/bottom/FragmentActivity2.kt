package com.example.ruru.sophiesblog.android.fragment.bottom

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.ruru.sophiesblog.R
import kotlinx.android.synthetic.main.activity_fragment2.*

class FragmentActivity2: AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_fragment2)

    bottomView.setOnNavigationItemSelectedListener {menuItem ->
      when(menuItem.itemId){
        R.id.bottom_home->{

        }
      }
      return@setOnNavigationItemSelectedListener false
    }
  }
}
