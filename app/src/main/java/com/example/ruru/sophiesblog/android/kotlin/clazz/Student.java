package com.example.ruru.sophiesblog.android.kotlin.clazz;

import androidx.annotation.NonNull;

public class Student {

  private int id;
  private String name;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @NonNull
  @Override
  public String toString() {
    return super.toString();
  }
}
