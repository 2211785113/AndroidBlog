package com.example.ruru.sophiesblog.android.media.screenrecord

import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaRecorder
import android.media.projection.MediaProjection
import android.media.projection.MediaProjectionManager
import android.os.Environment
import android.os.IBinder
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import android.hardware.display.DisplayManager
import android.hardware.display.VirtualDisplay

class ScreenRecordService : Service() {

    private val TAG: String = "ScreenRecordService"

    private var resultCode = 0
    private var resultData: Intent? = null
    private var mScreenWidth = 0
    private var mScreenHeight = 0
    private var mScreenDensity = 0
    private var isAudio = true
    private var isVideoSd = true

    private var mMediaProjection: MediaProjection? = null
    private var mMediaRecorder: MediaRecorder? = null
    private var mVirtualDisplay: VirtualDisplay? = null

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        intent?.let { intent ->
            resultCode = intent.getIntExtra("code", -1)
            resultData = intent.getParcelableExtra<Intent>("data")
            mScreenWidth = intent.getIntExtra("width", 720)
            mScreenHeight = intent.getIntExtra("height", 1280)
            mScreenDensity = intent.getIntExtra("density", 1)
            isAudio = intent.getBooleanExtra("isAudio", true)
            isVideoSd = intent.getBooleanExtra("isVideoSd", true)
        }

        mMediaProjection = createMediaProjection()
        mMediaRecorder = createMediaRecorder()
        mVirtualDisplay = createVirtualDisplay(); // 必须在mediaRecorder.prepare() 之后调用，否则报错"fail to get surface"

        mMediaRecorder?.let {
            it.start()
        }

        return START_NOT_STICKY
    }

    /**
     * MediaProjection:屏幕录制视频类
     */
    private fun createMediaProjection(): MediaProjection? {
        var mediaProjectionManager = getSystemService(Context.MEDIA_PROJECTION_SERVICE) as MediaProjectionManager
        return mediaProjectionManager.getMediaProjection(resultCode, resultData)
    }

    /**
     * MediaRecorder:设置视频编解码和视频大小
     */
    private fun createMediaRecorder(): MediaRecorder? {

        val formatter = SimpleDateFormat("yyyy-MM-dd-HH-mm-ss")
        val curDate = Date(System.currentTimeMillis())
        val curTime = formatter.format(curDate).replace(" ", "")

        var videoQuality = "HD"
        if (isVideoSd) videoQuality = "SD"

        val mediaRecorder = MediaRecorder()
        if (isAudio) mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC)// 设置音频来源
        mediaRecorder.setVideoSource(MediaRecorder.VideoSource.SURFACE)// 设置视频来源
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)// 设置音频输出格式（必须在设置音频编码格式之前设置）
        var videoUrl = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES).toString() + "/" + videoQuality + curTime + ".mp4"
        mediaRecorder.setOutputFile(videoUrl)//设置保存音视频的地址

        /*标记：MP4的录屏地址在这里*/

        mediaRecorder.setVideoSize(mScreenWidth, mScreenHeight)  //after setVideoSource(), setOutFormat() 设置视频大小
        mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264)  //after setOutputFormat() 设置视频编码
        if (isAudio) mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)  //after setOutputFormat() 设置音频编码

        val bitRate: Int
        if (isVideoSd) {
            mediaRecorder.setVideoEncodingBitRate(mScreenWidth * mScreenHeight)
            //设置帧率和比特率
            mediaRecorder.setVideoFrameRate(30)
            bitRate = mScreenWidth * mScreenHeight / 1000
        } else {
            mediaRecorder.setVideoEncodingBitRate(5 * mScreenWidth * mScreenHeight)
            mediaRecorder.setVideoFrameRate(60) //after setVideoSource(), setOutFormat()
            bitRate = 5 * mScreenWidth * mScreenHeight / 1000
        }
        try {
            mediaRecorder.prepare()
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        println("Audio=$isAudio SD video=$isVideoSd BitRate=$bitRate kbps")

        return mediaRecorder
    }

    /**
     * VirtualDisplay 可以理解为虚拟的呈现器，它可以捕获屏幕上的内容，并将其捕获的内容渲染到 Surface 上(Surace 由 MediaRecorder 提供，通过 getSurface() 方法得到),MediaRecorder 再进一步将其封装处理为 Mp4 文件。
     */
    private fun createVirtualDisplay(): VirtualDisplay? {
        return mMediaProjection?.let { mediaProjection ->
            mediaProjection.createVirtualDisplay(TAG, mScreenWidth, mScreenHeight, mScreenDensity, DisplayManager.VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR, mMediaRecorder?.let { it.surface }
                    ?: null, null, null)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        if (mVirtualDisplay != null) {
            mVirtualDisplay!!.release()
            mVirtualDisplay = null
        }

        if (mMediaRecorder != null) {
            mMediaRecorder!!.setOnErrorListener(null)
            mMediaProjection!!.stop()
            mMediaRecorder!!.reset()
        }

        if (mMediaProjection != null) {
            mMediaProjection!!.stop()
            mMediaProjection = null
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}