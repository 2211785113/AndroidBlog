package com.example.ruru.sophiesblog.android.thread.handler

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ruru.sophiesblog.R

class HandlerKotlinDemo: AppCompatActivity() {

//  private val handler= object :Handler()

  /*private val handler: Handler = Handler(Handler.Callback {msg ->
    var msg = msg
    when(msg.what) {
      1 -> {
        val msg = Message.obtain(msg)
        handler.sendMessageDelayed(msg, 2000)
      }
    }

    false
  })*/

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_handler_kotlin_demo)

   /* val msg = Message()
    msg.what = 1
    handler.sendMessage(msg)*/
  }
}
