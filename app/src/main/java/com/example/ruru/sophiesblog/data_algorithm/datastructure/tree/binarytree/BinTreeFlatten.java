package com.example.ruru.sophiesblog.data_algorithm.datastructure.tree.binarytree;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ruru.sophiesblog.R;

/**
 * 二叉树平铺
 */
public class BinTreeFlatten extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flatten_binary_tree);
    }

    public TreeNode flatten(TreeNode root) {
        //base case
        if (root == null) {
            return null;
        } else {
            //用递归的思想，把左右先铺平
            TreeNode left = flatten(root.left);
            TreeNode right = flatten(root.right);
            //把左指针和右指针先指向空。
            root.left = null;
            root.right = null;
            //假如左子树生成的链表为空，那么忽略它，把右子树生成的链表指向根节点的右指针
            if (left == null) {
                root.right = right;
                return root;
            }
            //如果左子树生成链表不为空，那么用while循环获取最后一个节点，并且它的右指针要指向右子树生成的链表的头节点
            root.right = left;
            TreeNode lastLeft = left;
            while (lastLeft != null && lastLeft.right != null) {
                lastLeft = lastLeft.right;
            }
            lastLeft.right = right;

            return root;
        }

    }
}
