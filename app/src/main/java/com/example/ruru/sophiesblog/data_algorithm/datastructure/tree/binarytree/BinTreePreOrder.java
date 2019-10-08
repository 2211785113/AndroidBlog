package com.example.ruru.sophiesblog.data_algorithm.datastructure.tree.binarytree;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;

import java.util.Stack;

import static com.example.ruru.sophiesblog.data_algorithm.datastructure.tree.binarytree.TreeNode.createTree;

/**
 * 先序遍历:ABDECFG
 */
public class BinTreePreOrder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binary_tree_preorder_traversal);

        TreeNode node = createTree();
//        preOrder(node);
//        preOrder1(node);
        preOrder2(node);
    }

    /**
     * 递归版本：耗内存（递归的本质：堆栈）
     *
     * @param node
     */
    private void preOrder(TreeNode node) {
        //递归的base case
        if (node == null) {
            return;
        }
        Log.d(getClass().getName(), (String) node.value);
        preOrder(node.left);
        preOrder(node.right);
    }

    /*
     * 非递归版本：用堆栈
     *
     * @param node
     */
    private void preOrder1(TreeNode node) {
        if (node != null) {
            Stack<TreeNode> stack = new Stack<>();
            stack.add(node);
            while (!stack.isEmpty()) {
                node = stack.pop();
                Log.d(getClass().getName(), (String) node.value);
                if (node.right != null) {
                    stack.push(node.right);
                }
                if (node.left != null) {
                    stack.push(node.left);
                }
            }
        }
    }

    /**
     * 非递归
     *
     * @param node
     */
    private void preOrder2(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                Log.d(getClass().getName(), (String) node.value);
                stack.push(node);
                node = node.left;
            }
            if (!stack.isEmpty()) {
                node = stack.pop();
                node = node.right;
            }
        }
    }

}
