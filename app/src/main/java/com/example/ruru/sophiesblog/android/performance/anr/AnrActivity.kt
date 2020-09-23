package com.example.ruru.sophiesblog.android.performance.anr

import android.Manifest
import android.os.Bundle
import android.os.Environment
import android.os.StrictMode
import androidx.appcompat.app.AppCompatActivity
import com.example.ruru.sophiesblog.R
import com.example.ruru.sophiesblog.android.MyApplication
import com.tbruyelle.rxpermissions3.RxPermissions
import kotlinx.android.synthetic.main.activity_anr.*
import java.io.File
import java.io.FileOutputStream

/**
 * 主线程中进行写入操作会造成ANR
 * threadPolicy vmPolicy 线程政策 虚拟机政策
 * 手机-开发者选项-严格模式
 */
class AnrActivity: AppCompatActivity() {

  private lateinit var rxPermissions: RxPermissions

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_anr)
    rxPermissions = RxPermissions(this)

    StrictMode.setThreadPolicy(MyApplication.getInstance().threadPolicy)
    click.setOnClickListener {
      writeToFile()
    }
  }

  private fun writeToFile() {
    rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe {granted ->
      if (granted) {
        runOnUiThread {
          //向文件中写入操作 == 获取内置sd卡的路径 absolutePath 和 path 均为storage/emulated/0
          var absolutePath = Environment.getExternalStorageDirectory().absolutePath
          var path = Environment.getExternalStorageDirectory().path

          var file = File(path, "aaa.txt")
          if (! file.exists()) {
            file.mkdir()
          }
          var str = "111111111111111111111111111111111"
          var outputStream = FileOutputStream(file)
          outputStream.write(str.toByteArray())
          outputStream.flush()
          outputStream.close()
        }
      } else {
      }
    }
  }
}