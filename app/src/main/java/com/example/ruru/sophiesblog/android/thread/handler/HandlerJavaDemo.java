package com.example.ruru.sophiesblog.android.thread.handler;

import android.os.Handler;
import android.os.Message;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ruru.sophiesblog.R;

public class HandlerJavaDemo extends AppCompatActivity {

  private Handler handler = new Handler(new Handler.Callback() {
    @Override
    public boolean handleMessage(Message msg) {
      switch (msg.what) {
        case 1:
          Message message = Message.obtain(msg);
          handler.sendMessageDelayed(message, 2000);
        default:
          break;
      }
      return false;
    }
  });

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_handler_java_demo);

    Message msg = new Message();
    msg.what = 1;
    handler.sendMessage(msg);
  }
}
