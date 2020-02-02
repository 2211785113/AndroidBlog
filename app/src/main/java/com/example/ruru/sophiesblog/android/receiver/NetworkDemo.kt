package com.example.ruru.sophiesblog.android.receiver

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ruru.sophiesblog.R

class NetworkDemo: AppCompatActivity() {

  private var netWorkStateReceiver: NetWorkStateReceiver? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_network_demo)
  }

  override fun onResume() {

    if (netWorkStateReceiver == null) {
      netWorkStateReceiver = NetWorkStateReceiver()
    }
    val filter = IntentFilter()
    filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
    registerReceiver(netWorkStateReceiver, filter)

    super.onResume()
  }

  override fun onPause() {
    unregisterReceiver(netWorkStateReceiver)

    super.onPause()
  }

}
