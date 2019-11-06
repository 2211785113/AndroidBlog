package com.example.ruru.sophiesblog.data_algorithm.datastructure.linked.create;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;
import com.example.ruru.sophiesblog.data_algorithm.datastructure.linked.bean.Node;
import com.example.ruru.sophiesblog.java.collection.list.ArrayList;

import java.util.List;

/**
 * 单链表创建：返回单链表的头部
 */
public class CreateLink extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_link);

        /* 普通 */
        Node node1 = createLink();
        Log.d(getClass().getName(), "onCreate:res1= " + node1.e);


        /* 非递归 */
        String[] str = {"A", "B", "C", "D", "E", "F", "G"};
        Node node2 = strToLink(str);
        Log.d(getClass().getName(), "onCreate:res2= " + node2.e);


        /* 递归-未实现 */
        int len = str.length;

        for (int i = len - 1; i >= 0; i--) {

        }
    }

    /**
     * 普通
     */
    public static Node createLink() {
        Node<String> g = new Node<>("G", null);
        Node<String> f = new Node<>("F", g);
        Node<String> e = new Node<>("E", f);
        Node<String> d = new Node<>("D", e);
        Node<String> c = new Node<>("C", d);
        Node<String> b = new Node<>("B", c);
        Node<String> a = new Node<>("A", b);
        return a;
    }

    /**
     * 非递归
     */
    public static Node strToLink(String[] str) {

        int len = str.length;
        List<Node> list = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            Node<String> node = new Node<>(str[i], null);
            list.add(node);
        }

        for (int i = len - 2; i >= 0; i--) {
            list.get(i).next = list.get(i + 1);
        }

        return list.get(0);
    }

    /**
     * 非递归：没想通
     */
    /*private Node strToLink1(String[] str) {
        int len = str.length;
        for (int i = len - 1; i >= 0; i--) {
            Node node = new Node(str[i], null);
            if (i != (len - 1)) {
                node.next=
            }
        }
        return
    }*/

    /**
     * 递归：没想通
     * 这个函数想要干什么：这个结点链接后边的一个结点
     * 递归结束条件：i=0
     * 等价条件：新建的一个结点指向之前的一个结点
     */
    private Node oneNode(String val, Node node) {
        Node nod = new Node(val, null);
        nod.next = node;
        return nod;
    }
}
