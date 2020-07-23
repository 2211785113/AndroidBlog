/*
package com.example.ruru.sophiesblog.android.media.ijkplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dl7.player.media.IjkPlayerView
import kotlinx.android.synthetic.main.activity_ijk_player.*
import android.content.res.Configuration
import android.view.KeyEvent
import com.bumptech.glide.Glide
import com.example.ruru.sophiesblog.R

*/
/**
 * 使用别人编译好的库播放
 * 此库报错废弃
 * compile 'com.github.Rukey7:IjkPlayerView:{lastest-version}'
 *//*

class IjkPlayerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ijk_player)

        Glide.with(this).load("http://goo.gl/gEgYUd").fitCenter().into(mPlayerView.mPlayerThumb); // Show the thumb before play
        mPlayerView.init()              // Initialize, the first to use
                .setTitle("Title")    // set title
                .setSkipTip(1000 * 60 * 1)  // set the position you want to skip
                .enableOrientation()    // enable orientation
                //      .setVideoPath(VIDEO_URL)    // set video url
                .setVideoSource(null, VIDEO_URL, VIDEO_URL, VIDEO_URL, null) // set multiple video url
                .setMediaQuality(IjkPlayerView.MEDIA_QUALITY_HIGH)  // set the initial video url
                .enableDanmaku()        // enable Danmaku
//                .setDanmakuSource(getResources().openRawResource(R.raw.comments)) // add Danmaku source, you need to use enableDanmaku() first
                .start();   // Start playing
    }

    override fun onResume() {
        super.onResume()
        mPlayerView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mPlayerView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPlayerView.onDestroy()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        mPlayerView.configurationChanged(newConfig)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return if (mPlayerView.handleVolumeKey(keyCode)) {
            true
        } else super.onKeyDown(keyCode, event)
    }

    override fun onBackPressed() {
        if (mPlayerView.onBackPressed()) {
            return
        }
        super.onBackPressed()
    }

    companion object {
        private var VIDEO_URL: String = ""

        fun setVideoUrl(videoUrl: String) {
            VIDEO_URL = videoUrl
        }
    }
}
*/
