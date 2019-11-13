package com.example.ruru.sophiesblog.data_algorithm.datastructure.linked.traverse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;
import com.example.ruru.sophiesblog.data_algorithm.datastructure.linked.bean.Node;
import com.example.ruru.sophiesblog.java.collection.list.ArrayList;

import java.util.List;

/**
 * 循环单链表(非闭合)遍历
 */
public class CircleTraversal2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_traversal2);

        String[] str = {"A", "B", "C", "D", "E", "F", "G", "C"};
        Node node = strToCircleLink(str);

        Node res = traversal(node);
        Log.d(getClass().getName(), "onCreate:res= " + res.e);//C

        int len = getLength(node);
        Log.d(getClass().getName(), "onCreate:len= " + len);//8
    }

    private Node traversal(Node node) {

        Node x = node;
        List<String> list = new ArrayList<>();

        while (!list.contains(x.e)) {
            Log.d(getClass().getName(), "traversal:node== " + x.e);
            list.add((String) x.e);
            x = x.next;
        }

        Log.d(getClass().getName(), "traversal:node== " + x.e);

        return x;
    }

    private int getLength(Node node) {
        int index = 0;
        Node x = node;
        List<String> list = new ArrayList<>();

        while (!list.contains(x.e)) {
            Log.d(getClass().getName(), "traversal:node== " + x.e);
            list.add((String) x.e);
            index++;
            x = x.next;
        }

        Log.d(getClass().getName(), "traversal:node== " + x.e);
        index += 1;

        return index;
    }

    /**
     * 创建链表
     */
    private Node strToCircleLink(String[] str) {
        int len = str.length;
        List<Node> list = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            Node<String> node = new Node<>(str[i], null);
            list.add(node);
        }

        list.get(len - 1).next = list.get(2);

        for (int i = len - 2; i >= 0; i--) {
            list.get(i).next = list.get(i + 1);
        }

        return list.get(0);
    }
}
