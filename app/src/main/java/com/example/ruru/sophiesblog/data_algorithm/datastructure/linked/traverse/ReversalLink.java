package com.example.ruru.sophiesblog.data_algorithm.datastructure.linked.traverse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;
import com.example.ruru.sophiesblog.data_algorithm.datastructure.linked.bean.Node;
import com.example.ruru.sophiesblog.java.collection.list.ArrayList;

import java.util.List;
import java.util.Stack;

import static com.example.ruru.sophiesblog.data_algorithm.datastructure.linked.create.CreateLink.strToLink;

/**
 * 反向遍历/反转
 */
public class ReversalLink extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reversal_link);

        String[] str = {"A", "B", "C", "D", "E", "F", "G"};
        Node node = strToLink(str);

        //非递归-集合
//        reversal(node);

        //递归-栈，占用内存空间
//        Node node1 = reversal1(node);
//        Log.d(getClass().getName(), "onCreate:res= " + node1.e);//A

        //修改头尾指针-非递归
//        Node node2 = reversal2(node);
//        Log.d(getClass().getName(), "onCreate:res1= " + node2.e);//G

        //修改头尾指针-递归：栈，占用内存空间
//        Node node3 = reversal3(node);
//        Log.d(getClass().getName(), "onCreate: res2= " + node3.e);//G

        Node node4 = reverse(node);
        Log.d(getClass().getName(), "onCreate: res3= " + node4.e);//G
    }

    /*======================================非递归：List集合-浪费空间============================================================================*/

    private void reversal(Node node) {
        List<Node> list = new ArrayList<>();
        for (Node x = node; x != null; x = x.next) {
            list.add(x);
        }

        int len = list.size();

        for (int i = len - 1; i > 0; i--) {
            list.get(i).next = list.get(i - 1);
            Log.d(getClass().getName(), "reversal:res= " + list.get(i).e);
        }

        list.get(0).next = null;

        Log.d(getClass().getName(), "reversal:res= " + list.get(0).e);
    }

    /*========================================递归-栈============================================================================================*/

    private Node reversal1(Node node) {
        Stack<Node> stack = new Stack<>();
        for (Node x = node; x != null; x = x.next) {
            stack.push(x);
        }

        return doReversal(stack);
    }

    /**
     * 核心-递归函数：
     * 想要干什么：栈中取出，指向下一个结点
     * 结束条件：栈为空
     * 等价条件：每个结点都指向下一个
     */
    private Node doReversal(Stack<Node> stack) {
        Node pop = stack.pop();
        if (stack.isEmpty()) {
            pop.next = null;
            return pop;
        }
        pop.next = doReversal(stack);
        return pop;
    }

    /*========================================修改头尾指针-非递归============================================================================================*/

    /**
     * 修改头尾指针：最后输出反转链表的头结点
     */
    private Node reversal2(Node node) {
        Node nodPrev = null;
        Node nodNext = null;

        while (true) {

            nodNext = node.next;

            node.next = nodPrev;//指针反转重新指向

            nodPrev = node;
            node = nodNext;

            if (node == null) {
                return nodPrev;
            }
        }
    }

    /*========================================修改头尾指针-递归============================================================================================*/

    private Node reversal3(Node node) {

        Node nodPrev = null;

        return doReversal1(nodPrev, node);
    }

    /**
     * 核心-递归函数：
     * 这个函数是干什么的：修改一个结点的指针
     * 结束条件：
     * 等价关系：下一个结点
     */
    private Node doReversal1(Node nodPrev, Node node) {

        Node nodNext = node.next;

        node.next = nodPrev;//指针反转重新指向

        nodPrev = node;
        node = nodNext;

        if (node == null) {
            return nodPrev;
        }

        return doReversal1(nodPrev, node);
    }

    /*========================================修改头尾指针-递归(待看)============================================================================================*/

    public Node reverse(Node node) {
        if (node == null || node.next == null)
            return node;
        Node nodNext = node.next;
        Node newHead = reverse(node.next);
        nodNext.next = node;
        node.next = null;
        return newHead;
    }
}
