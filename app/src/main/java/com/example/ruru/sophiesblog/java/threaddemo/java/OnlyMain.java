package com.example.ruru.sophiesblog.java.threaddemo.java;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by SophieRu on 2019/7/2
 */
public class OnlyMain extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //一个获取JVM运行信息：线程，内存的简单方法中有6个线程
        //主线程：main
        //Finalizer线程：资源回收动作（Object#finalize方法）
        //GC线程：内存不足调用

    }
}
