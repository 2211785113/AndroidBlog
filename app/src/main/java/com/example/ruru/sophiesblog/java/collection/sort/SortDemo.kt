package com.example.ruru.sophiesblog.java.collection.sort

import java.util.*
import kotlin.collections.ArrayList

fun main() {

  //  var list = ArrayList<Student>()
  //  list.add(Student(1, "小红", 444))
  //  list.add(Student(2, "小丽", 555))
  //  list.add(Student(3, "小明", 333))

  //Comparable排序
  //  list.sort()

  var list1 = ArrayList<Student1>()
  list1.add(Student1(1, "小红", 444))
  list1.add(Student1(2, "小丽", 555))
  list1.add(Student1(3, "小明", 333))

  //Comparator排序
  //  Collections.sort(list1, StuComparator(1))

  //kotlin排序
  //  list1.sortedBy {it.type} //不起作用
  //  list1.sortedByDescending {it.type} //不起作用

  for (item in list1) {
    println("item=$item")
  }
}