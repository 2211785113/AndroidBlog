package com.example.ruru.sophiesblog.java.extends_.one;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;
import com.example.ruru.sophiesblog.java.extends_.one.A.B;

/**
 * 画图：
 * A a = new B：创建了一个B对象，对应一个b引用；有一个A对象，有一个a引用；把b引用赋值给a引用。所以a.name答案还是A。此处有点疑问。
 * B b = new B：创建了一个B对象，所以b.name答案是B。
 */
public class ClientExtends extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aclass);

        /**
         * 典型的多态，向上转型
         *
         * 类A在栈里，类B在堆里；a是引用，只是把对象B的实例赋值给引用a
         * 创建一个类A的引用a
         * 创建一个类B的实例
         * 把B的引用赋值给a
         */
        A a = new B();
        Log.d(getClass().getName(), "onCreate:res1= " + a.name);//答案：A

        B b = new B();
        Log.d(getClass().getName(), "onCreate:res2= " + b.name);//答案：B
    }
}
