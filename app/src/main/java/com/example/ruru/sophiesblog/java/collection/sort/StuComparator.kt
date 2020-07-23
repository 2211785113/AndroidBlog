package com.example.ruru.sophiesblog.java.collection.sort

class StuComparator(val sortType: Int): Comparator<Student1> {

  /**
   * 排序规则
   */
  override fun compare(o1: Student1?, o2: Student1?): Int {
    when(sortType) {
      1 -> { //大于0降序
        return o2?.type !! - o1?.type !!
      }
      2 -> { //小于0升序
        return o1?.type !! - o2?.type !!
      }
      else -> {

      }
    }
    return 0
  }
}