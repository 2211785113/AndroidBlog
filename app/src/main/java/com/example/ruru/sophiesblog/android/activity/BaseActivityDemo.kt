package com.example.ruru.sophiesblog.android.activity

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.ruru.sophiesblog.R

open class BaseActivityDemo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_demo)
        LogUtil.d("onCreate+++")
        LogUtil.d("onCreate:" + javaClass.simpleName + " TaskId:" + taskId + " hashcode:" + hashCode())
        dumpTaskAffinity()
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        LogUtil.d("onNewIntent+++")
        LogUtil.d("onNewIntent:" + javaClass.simpleName + " TaskId:" + taskId + " hashcode:" + hashCode())
        dumpTaskAffinity()
    }

    private fun dumpTaskAffinity() {
        try {
            val activityInfo = this.packageManager.getActivityInfo(componentName, PackageManager.GET_META_DATA)
            LogUtil.d("taskAffinity:" + activityInfo.taskAffinity)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
    }
}