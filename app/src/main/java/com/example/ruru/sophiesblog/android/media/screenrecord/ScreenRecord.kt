package com.example.ruru.sophiesblog.android.media.screenrecord

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.projection.MediaProjectionManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.ruru.sophiesblog.R
import com.example.ruru.sophiesblog.android.common.Constants.ScreenRecord.REQUEST_CODE
import kotlinx.android.synthetic.main.activity_screen_record.*

/**
 * https://www.jianshu.com/p/7560ce9e4136
 * https://blog.csdn.net/sinat_28238111/article/details/78394677
 */
class ScreenRecord : AppCompatActivity() {

    private var isVideoSd = true  // 是否为标清
    private var isAudio = false  // 是否有声音
    private var isStart = false // 屏幕录制是否开始

    private var mScreenWidth = 0
    private var mScreenHeight = 0
    private var mScreenDensity = 0

    private var mPermissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen_record)

        initPermission()
        initData()
        initView()
    }

    private fun initPermission() {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            var permissionList = arrayListOf<String>()
            for (item in mPermissions) {
                if (ContextCompat.checkSelfPermission(this, item) != PackageManager.PERMISSION_GRANTED) {
                    permissionList.add(item)
                }
            }

            var requestCode = 100
            if (permissionList.isNotEmpty()) {
                ActivityCompat.requestPermissions(this, mPermissions, requestCode)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            if (grantResults.isNotEmpty()) {
                for (item in grantResults) {
                    if (item != PackageManager.PERMISSION_GRANTED) {
                        initPermission()
                        break
                    }
                }
            }
        }
    }

    private fun initData() {
        getScreenData()
    }

    private fun getScreenData() {
        val metrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(metrics)
        mScreenWidth = metrics.widthPixels
        mScreenHeight = metrics.heightPixels
        mScreenDensity = metrics.densityDpi
    }

    private fun initView() {
        radio.check(0)
        radio.setOnCheckedChangeListener { radio, checkedId ->
            when (checkedId) {
                0 -> {
                    isVideoSd = true
                }
                1 -> {
                    isVideoSd = false
                }
            }
        }

        checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
            isAudio = isChecked
        }

        btn_start.setOnClickListener { view ->
            if (isStart) {
                stopScreenRecord()
            } else {
                startScreenRecord()
            }
        }

        //播放
        /*btn_play.setOnClickListener { view ->
            var intent = Intent(this, IjkPlayerActivity::class.java)
            startActivity(intent)
        }*/
    }

    /**
     * 开始屏幕录制
     */
    private fun startScreenRecord() {
        //获取屏幕录制权限
        val mediaProjectionManager = getSystemService(Context.MEDIA_PROJECTION_SERVICE) as MediaProjectionManager
        val intent = mediaProjectionManager.createScreenCaptureIntent()

        if (packageManager.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY) != null) {
            //存在录屏授权的Activity
            startActivityForResult(intent, REQUEST_CODE)
        } else {
            Toast.makeText(this, resources.getString(R.string.unableScreenRecord), Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * 开始录制获取到权限
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {

            println("service:startService")

            val intent = Intent(this, ScreenRecordService::class.java)
            intent.putExtra("code", resultCode)
            intent.putExtra("data", data)
            intent.putExtra("width", mScreenWidth)
            intent.putExtra("height", mScreenHeight)
            intent.putExtra("density", mScreenDensity)
            intent.putExtra("isAudio", isAudio)
            intent.putExtra("isVideoSd", isVideoSd)
            startService(intent)

            isStart = !isStart
            btn_start.setText(getString(R.string.stopRecord))
            btn_start.setBackgroundColor(resources.getColor(R.color.colorAccent, null))
        } else {//没有开启录屏权限
            Toast.makeText(this, "拒绝录屏", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * 停止屏幕录制
     */
    private fun stopScreenRecord() {
        isStart = !isStart
        btn_start.text = getString(R.string.startRecord)
        btn_start.setBackgroundColor(resources.getColor(R.color.colorPrimary, null))
    }
}
