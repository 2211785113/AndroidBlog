package com.example.ruru.sophiesblog.android.camera

import android.graphics.Point
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.ruru.sophiesblog.R
import kotlinx.android.synthetic.main.activity_camera_split.*

/**
 * 任务：
 * 要求：实现摄像头的切割，从1个摄像头 一直到 17个摄像头
 * 实现：摄像头View 用FrameLayout实现
 * 结果：把图片装到切割好的框里,将EEOLiveView放到相对应的Rect里即setLayout
 */
class CameraSplit: AppCompatActivity() {

  private var rectList: MutableList<Rect> = mutableListOf()
  private var windowParams: IntArray = IntArray(2)
  private var size = 2 //双击最大化的摄像头个数

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_camera_split)

    initWindowParams()

    rectList.clear()
    rectList.add(Rect(0, 0, windowParams[0], windowParams[1])) //left和top不一定为0
    arrangeCamera(size)
    for (i in 0 until size) {
      var rect = rectList[i]
      Log.d("CameraSplit", "onCreate:left=" + rect.left + " top = " + rect.top + " right = " + rect.right + " bottom =" + rect.bottom)
      liveView.changeLayout(rect.left, rect.top, rect.right, rect.bottom)
    }
  }

  /**
   * 切割摄像头
   */
  private fun arrangeCamera(size: Int) {
    when(size) {
      1 -> {
      }
      2 -> {
        splitRectOnly(0, Direction.Vertical, 0.5)
        return
      }
      3 -> {
        splitRectOnly(0, Direction.Vertical, 0.3333)
        splitRectOnly(1, Direction.Vertical, 0.5)
        return
      }
      4 -> {
        splitRectOnly(0, Direction.Horizontal, 0.5)
        splitRectOnly(1, Direction.Vertical, 0.5)
        return
      }
      5 -> {
        return
      }
      6 -> {

      }
      7 -> {

      }
      8 -> {

      }
      9 -> {

      }
      10 -> {

      }
      11 -> {

      }
      12 -> {

      }
      13 -> {

      }
      14 -> {

      }
      15 -> {

      }
      16 -> {

      }
      17 -> {

      }
    }
  }

  /**
   * 一刀切割
   * split_index：切割块的下标
   * direction：一刀切割的方向
   * ratio：切割快的比例
   */
  private fun splitRectOnly(split_index: Int, direction: Direction, ratio: Double) {
    var inRect = rectList[split_index]
    if (inRect != null) {
      var rect1 = Rect()
      var rect2 = Rect()
      if (direction.ordinal == 0) { //水平切割
        rect1 = Rect(inRect.left, inRect.top, inRect.width(), (inRect.height() * ratio).toInt())
        rect2 = Rect(inRect.left, rect1.bottom, inRect.width(), inRect.height())
      } else if (direction.ordinal == 1) { //竖直切割
        rect1 = Rect(inRect.left, inRect.top, (inRect.width() * ratio).toInt(), inRect.height())
        rect2 = Rect(rect1.right, inRect.top, inRect.width(), inRect.height())
      }
      rectList.remove(inRect)
      rectList.add(rect1)
      rectList.add(rect2)
    }
  }

  private fun initWindowParams() {
    var display = windowManager.defaultDisplay
    var point = Point()
    display.getSize(point)
    var width = point.x
    var height = point.y
    windowParams[0] = width
    windowParams[1] = height
    Log.d("cameraSplit", "initWindowParams: width = $width height = $height") //2305 1080
  }

  /**
   * 数值少的时候 直接用 private int 水平/竖直 均可
   */
  private enum class Direction {
    Horizontal, //水平切割
    Vertical //竖直切割
  }
}