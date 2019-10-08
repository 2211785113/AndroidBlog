package com.example.ruru.sophiesblog.data_algorithm.datastructure.tree.binarytree;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ruru.sophiesblog.R;

/**
 * 翻转二叉树:相当于左右子树交换
 * 思想：分而治之
 */
public class BinTreeReversal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reversal_bin_tree);


    }

    private TreeNode reversalBinaryTree(TreeNode node) {
        if (node == null) {
            return null;
        } else {
            TreeNode left = reversalBinaryTree(node.left);//翻转左子树
            TreeNode right = reversalBinaryTree(node.right);//翻转右子树

            //把左右子树分别赋值给node节点，但是是翻转过来的顺序
            node.left = right;
            node.right = left;

            //返回根节点
            return node;
        }
    }
}
