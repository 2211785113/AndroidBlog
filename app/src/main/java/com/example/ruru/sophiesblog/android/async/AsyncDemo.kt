package com.example.ruru.sophiesblog.android.async

import com.google.android.exoplayer.C

/**
 * 异步Demo
 *
 * 解决办法：
 * 1.for循环外层：记一个map<Int,ClazzB>，循环结束判断map的size等于AList的size即可进行下面操作
 * 2.服务端一次性把memberNum返回来 循环结束判断集合size等于AList的size即可进行下面操作
 */


fun main() {
  val clazzAList = arrayListOf<ClazzA>()
  clazzAList.add(ClazzA(1, "班级1"))
  clazzAList.add(ClazzA(2, "班级2"))

  val clazzBList = arrayListOf<ClazzB>()

  for (item in clazzAList) {
    var memberNum = 0
    //网络请求根据 item 的 id 获取 ClazzB的memberN--->得到memberNum值
    clazzBList.add(ClazzB(item.id, item.name, memberNum))
  }

  println("clazzBList=${clazzBList}")
  // 问题在这里 最后打印出clazzBList里的memberNum数值都为0 说明 还没从网络请求得到 memberNum 数据便添加到了clazzBList里边
  // 解决办法：
  /*val map = HashMap<String, Int>()
  val requestNum = 10
  for (item in clazzAList) {
    request {result ->
      requestNum --
      map[id] = resultNum
      if (requestNum == 0) {
        // 通过list A 和 map，生成 list B，然后通知出去-怎么通知出去
      }
    }
  }*/
}

data class ClazzA(val id: Int, val name: String)

data class ClazzB(val id: Int, val name: String, val memberNum: Int)