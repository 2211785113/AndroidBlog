package com.example.ruru.sophiesblog.android.kotlin.genericity.java;

public class PanZi<T> {

  T t;

  private T getFruit(){
    return t;
  }

  private void setFruit(T t){
    this.t = t;
  }
}

