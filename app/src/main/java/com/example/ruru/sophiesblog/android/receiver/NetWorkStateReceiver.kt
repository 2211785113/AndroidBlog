package com.example.ruru.sophiesblog.android.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Build
import android.widget.Toast
import java.lang.StringBuilder

/**
 * Created by lyr on 2020/1/3 & content is 监听网络状态
 * 另一种方式：https://www.jianshu.com/p/f61849073bad
 */
class NetWorkStateReceiver: BroadcastReceiver() {

  override fun onReceive(context: Context?, intent: Intent?) {
    println("网络状态发生了变化")

    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {

      context?.let {context ->
        val connMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val wifiState = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        val mobileState = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        if (wifiState.isConnected && mobileState.isConnected) {
          Toast.makeText(context, "WIFI已连接，移动数据已连接", Toast.LENGTH_SHORT).show()
        } else if (wifiState.isConnected && ! mobileState.isConnected) {
          Toast.makeText(context, "WIFI已连接，移动数据已断开", Toast.LENGTH_SHORT).show()
        } else if (! wifiState.isConnected && mobileState.isConnected) {
          Toast.makeText(context, "WIFI已断开，移动数据已连接", Toast.LENGTH_SHORT).show()
        } else {
          Toast.makeText(context, "WIFI已断开，移动数据已断开", Toast.LENGTH_SHORT).show()
        }

        // 其它判断逻辑
        /*val activeNetworkInfo = connMgr.activeNetworkInfo
        if (activeNetworkInfo.isAvailable) {
          Toast.makeText(context, "当前网络可用", Toast.LENGTH_SHORT).show()
        } else {
          Toast.makeText(context, "当前网络不可用", Toast.LENGTH_SHORT).show()
        }*/
      }

    } else {

      context?.let {context ->
        val connMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val allNetworks = connMgr.allNetworks
        val sbd = StringBuilder()
        for (network in allNetworks) {
          val networkInfo = connMgr.getNetworkInfo(network)
          sbd.append(networkInfo.typeName + " ${networkInfo.isConnected}")
        }
        Toast.makeText(context, sbd, Toast.LENGTH_SHORT).show()
      }

    }
  }
}