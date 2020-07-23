package com.example.ruru.sophiesblog.data_algorithm.datastructure.linked.traverse;

import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;
import com.example.ruru.sophiesblog.data_algorithm.datastructure.linked.bean.Node;
import com.example.ruru.sophiesblog.java.collection.list.ArrayList;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 循环单链表(闭合)遍历
 */
public class CircleTraversal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_traversal);

        String[] str = {"A", "B", "C", "D", "E", "F", "G", "C"};
        Node node = strToCircleLink(str);

        circleTraversal1(node);//答案：A B C D E F G A

        int length = getLength(node);
        Log.d(getClass().getName(), "onCreate:res= " + length);//8
    }

    /**
     * for循环
     */
    private void circleTraversal(Node first) {
        for (Node x = first.next; x != first; x = x.next) {
            Log.d(getClass().getName(), "circleTraversal:res= " + x.e);
        }
    }

    private int getLength(Node first) {
        int index = 1;
        for (Node x = first.next; x != first; x = x.next) {
            index++;
        }
        return index;
    }

    /**
     * while循环
     */
    private void circleTraversal1(Node first) {
        Node p = first.next;
        while (p != first) {
            Log.d(getClass().getName(), "circleTraversal:res= " + p.e);
            p = p.next;
        }
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

        list.get(len - 1).next = list.get(0);

        for (int i = len - 2; i >= 0; i--) {
            list.get(i).next = list.get(i + 1);
        }

        return list.get(0);
    }
}
