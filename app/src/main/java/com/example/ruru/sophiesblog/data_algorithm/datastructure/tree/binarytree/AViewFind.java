package com.example.ruru.sophiesblog.data_algorithm.datastructure.tree.binarytree;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.example.ruru.sophiesblog.R;

import androidx.appcompat.app.AppCompatActivity;

public class AViewFind extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_find);

    }

    private View find(ViewGroup viewGroup, int id) {
        if (viewGroup == null)
            return null;
        int size = viewGroup.getChildCount();
        //循环遍历所有孩子
        for (int i = 0; i < size; i++) {
            View v = viewGroup.getChildAt(i);
            //如果当前孩子的id相同，那么返回
            if (v.getId() == id) return v;
            //如果当前孩子id不同，但是是一个ViewGroup，那么我们递归往下找
            if (v instanceof ViewGroup) {
                //递归
                View temp = find((ViewGroup) v, id);
                //如果找到了，就返回temp，如果没有找到，继续当前的for循环
                if (temp != null) {
                    return temp;
                }
            }
        }
        //到最后还没用找到，代表该ViewGroup vg 并不包含一个有该id的孩子，返回空
        return null;
    }
}
