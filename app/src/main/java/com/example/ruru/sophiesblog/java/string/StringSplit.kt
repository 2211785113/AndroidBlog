package com.example.ruru.sophiesblog.java.string

fun main() {
  var a = "你好#123"
  println("res=${a.split("#")}") // res=[你好, 123]

  var b = "你好#123#456"
  println("res=${b.split("#")}") // res=[你好, 123, 456]
}