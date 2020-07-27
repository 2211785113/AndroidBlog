package com.example.ruru.sophiesblog.java.threaddemo.execute

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.View
import com.example.ruru.sophiesblog.R
import kotlinx.android.synthetic.main.activity_time_repeat.*
import java.util.*

/**
 * 定时重复执行
 * 场景：界面定时进行网络请求 刷新数据
 * https://blog.csdn.net/Mr_Leixiansheng/article/details/68064834
 */
class TimeRepeat: AppCompatActivity(), View.OnClickListener {

  private val handler = object: Handler(Looper.getMainLooper()) {
    override fun handleMessage(msg: Message?) {
      super.handleMessage(msg)
      msg?.let {
        show_time.text = "点我停止计时：" + it.what
      }
    }
  }

  //handler 方式定时循环
  private val handlerTimer: Handler = object: Handler(Looper.getMainLooper()) {
    override fun handleMessage(msg: Message?) {
      super.handleMessage(msg)
      if (flog) {
        msg?.let {msg ->
          msg.target.sendEmptyMessageDelayed(num ++, 1000)
        }
      }
      show_time.text = "点击我停止计时： " + msg?.what
      if (! flog) {
        flog = true
      }
    }
  }

  private var timer: Timer? = null
  private var timerTask: TimerTask? = null
  private var num = 0  //计数值
  private var flog = true        //是否停止计时

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_time_repeat)

    setOnClick(timer_1, timer_2, timer_3, timer_4, clear, show_time)
  }

  private fun setOnClick(vararg view: View) {
    view.forEach {view ->
      view.setOnClickListener(this)
    }
  }

  override fun onClick(v: View?) {
    v?.let {view ->
      when(view.id) {
        R.id.timer_1 -> method1()
        R.id.timer_2 -> method2()
        R.id.timer_3 -> method3()
        R.id.timer_4 -> method4()
        R.id.clear -> {
          num = 0
          show_time.text = "请选择一种启动方式"
        }
        R.id.show_time -> {
          flog = false
        }
      }
    }
  }

  private fun method1() {
    Thread(Runnable {
      run {
        while (flog) {
          handler.sendEmptyMessage(num ++)
          try {
            Thread.sleep(1000)
          } catch (e: InterruptedException) {
            e.printStackTrace()
          }
        }
      }
    }).start()
    flog = true
  }

  private fun method2() {
    object: Thread() {
      override fun run() {
        super.run()
        while (flog) {
          handler.sendEmptyMessage(num ++)
          try {
            sleep(1000)
          } catch (e: InterruptedException) {
            e.printStackTrace()
          }
        }
      }
    }.start()
    flog = true
  }

  private fun method3() {
    //Timer一旦被cancel之后就废了，只有重新构造一个。
    if (flog) {
      timer = Timer()
      timerTask = object: TimerTask() {
        override fun run() {
          //执行的任务：可以往主线程传数据 也可以进行网络请求
          handler.sendEmptyMessage(num ++)
        }
      }
      /**
       * 或者 timerTask = MyTask()
       * inner class MyTask：TimerTask{
       *    override fun run(){
       *        handler.sendEmptyMessage(num ++)
       *    }
       * }
       */
      /**
       * 第一个参数：任务
       * 第二个参数：初始启动等待时间
       * 第三个参数：时间间隔
       */
      timer?.let {it.schedule(timerTask, 0, 1000)}
    } else {
      timer?.let {timer ->
        timer.cancel()
      }
      timer = null // 一定设置为null，否则定时器不会被回收
      timerTask?.let {timerTask ->
        timerTask.cancel()
      }
      flog = true
    }


    //Timer 暂停重启有问题（待改进）
    //Timer一旦被cancel之后就废了，只有重新构造一个。
    //        if (flog == true) {
    //            timerTask = new TimerTask() {
    //                @Override
    //                public void run() {
    //                    handler.sendEmptyMessage(num++);
    //                }
    //            };
    //            /**
    //             * 第一个参数：任务
    //             * 第二个参数：初始启动等待时间
    //             * 第三个参数：时间间隔
    //             */
    //            timer.schedule(timerTask, 0, 1000);
    //        } else {
    //            timer.cancel();
    //            flog = true;
    //        }
  }

  private fun method4() {
    object: Thread() {
      override fun run() {
        super.run()
        handlerTimer.sendEmptyMessage(num ++)
      }
    }.start()
  }
}

