package com.example.ruru.sophiesblog.java.collection.sort


data class Student(val id: Int, val name: String, val type: Int): Comparable<Student> {

  //降序排序
  override fun compareTo(other: Student): Int {
    return other.type - this.type
  }
}